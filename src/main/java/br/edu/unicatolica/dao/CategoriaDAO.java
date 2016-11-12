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
import br.edu.unicatolica.filter.CategoriaFilter;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class CategoriaDAO implements Serializable {

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

	public void salvarOuAtualizar(Categoria categoria) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(categoria);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void remover(Categoria categoria) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			categoria = em.find(Categoria.class, categoria.getId());
			em.getTransaction().begin();
			em.remove(categoria);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
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
}
