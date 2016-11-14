/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.dao;

import br.edu.unicatolica.entity.Movimentacao;
import java.io.Serializable;

/**
 *
 * @author Danrley
 */
public class MovimentacaoDAO extends GenericoDAO<Movimentacao> implements Serializable {

    private static MovimentacaoDAO instance;

    private MovimentacaoDAO() {

    }

    public static MovimentacaoDAO getInstance() {
        if (instance == null) {
            instance = new MovimentacaoDAO();
        }
        return instance;
    }
}
