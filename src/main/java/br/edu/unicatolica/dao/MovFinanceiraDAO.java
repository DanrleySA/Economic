package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.DataValor;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import br.edu.unicatolica.jpa.util.JPAUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Danrley
 */
public class MovFinanceiraDAO extends GenericoDAO<MovimentacaoFinanceira> implements Serializable {

    private static MovFinanceiraDAO instance;

    private MovFinanceiraDAO() {

    }

    public static MovFinanceiraDAO getInstance() {
        if (instance == null) {
            instance = new MovFinanceiraDAO();
        }
        return instance;
    }

    public List<MovimentacaoFinanceira> getMovimentacoes(TipoMovimentacao tipo, Date dataInicial, Date dataFinal, Usuario usuario) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(MovimentacaoFinanceira.class);
            criteria.add(Restrictions.eq("usuario", usuario));

            if (tipo != null) {
                criteria.add(Restrictions.eq("tipo", tipo));
            }
            if (dataInicial != null && dataFinal != null) {
                criteria.add(Restrictions.between("dataVencimento", dataInicial, dataFinal));
            }

            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Map<Date, BigDecimal> consultaGrafico(Map<Date, BigDecimal> resultado, Usuario usuario, Calendar dataInicial, TipoMovimentacao tipo) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(MovimentacaoFinanceira.class);
            criteria.add(Restrictions.eq("usuario", usuario));
            criteria.add(Restrictions.eq("tipo", tipo));

            criteria.setProjection(Projections.projectionList()
                    .add(Projections.sqlGroupProjection("date(dataVencimento) as data", "date(dataVencimento)",
                            new String[]{"data"},
                            new Type[]{StandardBasicTypes.DATE}))
                    .add(Projections.sum("valor").as("valor"))
            )
                    .add(Restrictions.ge("dataVencimento", dataInicial.getTime()))
                    .add(Restrictions.eq("usuario", usuario));

            List<DataValor> valoresPorData = criteria
                    .setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();

            for (DataValor dataValor : valoresPorData) {
                resultado.put(dataValor.getData(), dataValor.getValor());
            }

            return resultado;
        } finally {
            em.close();
        }
    }
}
