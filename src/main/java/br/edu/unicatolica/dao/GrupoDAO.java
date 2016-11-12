package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Grupo;
import java.io.Serializable;

/**
 *
 * @author Danrley
 */
public class GrupoDAO extends GenericoDAO<Grupo> implements Serializable{

    private static GrupoDAO instance;

    private GrupoDAO() {

    }

    public static GrupoDAO getInstance() {
        if (instance == null) {
            instance = new GrupoDAO();
        }
        return instance;
    }
}
