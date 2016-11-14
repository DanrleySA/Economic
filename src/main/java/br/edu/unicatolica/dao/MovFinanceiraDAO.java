package br.edu.unicatolica.dao;


import br.edu.unicatolica.dao.GenericoDAO;
import br.edu.unicatolica.dao.MovimentacaoDAO;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import java.io.Serializable;

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
}
