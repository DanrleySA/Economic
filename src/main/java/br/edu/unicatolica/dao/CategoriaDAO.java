package br.edu.unicatolica.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.Categoria;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.filter.CategoriaFilter;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class CategoriaDAO extends GenericoDAO<Categoria> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static CategoriaDAO instance;

    private CategoriaDAO() {

    }

    public static CategoriaDAO getInstance() {
        if (instance == null) {
            instance = new CategoriaDAO();
        }
        return instance;
    }

    public Categoria getCategoriaPorId(Long id) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Categoria> getCategorias(CategoriaFilter categoriaFilter) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(Categoria.class);

            if (StringUtils.isNotBlank(categoriaFilter.getDescricao())) {
                criteria.add(Restrictions.ilike("descricao", categoriaFilter.getDescricao(), MatchMode.ANYWHERE));
            }
            return criteria.addOrder(Order.asc("descricao")).list();
        } finally {
            em.close();
        }
    }

    public List<Categoria> getCategoriasUsuario(CategoriaFilter categoriaFilter, Usuario usuario) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(Categoria.class);
            criteria.add(Restrictions.eq("usuario", usuario));

            if (StringUtils.isNotBlank(categoriaFilter.getDescricao())) {
                criteria.add(Restrictions.ilike("descricao", categoriaFilter.getDescricao(), MatchMode.ANYWHERE));

            }
            return criteria.addOrder(Order.asc("descricao")).list();
        } finally {
            em.close();
        }
    }

    public List<Categoria> getCategoriasSemFiltro(Usuario usuario) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(Categoria.class);
            criteria.add(Restrictions.eq("usuario", usuario));

            return criteria.list();
        } finally {
            em.close();
        }
    }
}
