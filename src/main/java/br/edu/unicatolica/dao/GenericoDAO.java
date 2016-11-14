package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Categoria;
import br.edu.unicatolica.jpa.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Danrley
 */
public class GenericoDAO<T extends EntidadeBase> implements Serializable {

    public void salvarOuAtualizar(T t) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void remover(Class<T> classe, T t) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            t = em.find(classe, t.getId());
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public T getEntidadePorId(Class<T> classe, Long id) {
        EntityManager em = JPAUtil.createEntityManager();
        T t = null;
        try {
            return t = (T) em.find(classe, id);
        } finally {
            em.close();
        }
    }

    public List<T> getListaEntidade(Class<T> classe) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(classe);

            return criteria.list();
        } finally {
            em.close();
        }
    }

}
