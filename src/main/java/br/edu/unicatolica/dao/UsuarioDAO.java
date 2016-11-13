package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.jpa.util.JPAUtil;
import java.io.Serializable;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Danrley
 */
public class UsuarioDAO extends GenericoDAO<Usuario> implements Serializable {

    private static UsuarioDAO instance;

    private UsuarioDAO() {

    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    public Usuario getUserPorEmail(String email) {
        EntityManager em = JPAUtil.createEntityManager();
        Usuario usuario = null;

        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(Usuario.class);

            criteria.add(Restrictions.eq("email", email));
            usuario = (Usuario) criteria.uniqueResult();
            return usuario;
        } finally {
            em.close();
        }
    }
}
