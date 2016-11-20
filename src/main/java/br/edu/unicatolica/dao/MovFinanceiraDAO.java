package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import br.edu.unicatolica.jpa.util.JPAUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
}
