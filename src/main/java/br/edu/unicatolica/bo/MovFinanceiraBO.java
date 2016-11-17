/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.MovFinanceiraDAO;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author danrl
 */
public class MovFinanceiraBO implements Serializable {

    private static MovFinanceiraBO instance;

    private MovFinanceiraBO() {
    }

    public static MovFinanceiraBO getInstance() {
        if (instance == null) {
            instance = new MovFinanceiraBO();
        }
        return instance;
    }

    public void salvarOuAtualizar(MovimentacaoFinanceira mov) {
        MovFinanceiraDAO.getInstance().salvarOuAtualizar(mov);
    }

    public void remover(MovimentacaoFinanceira mov) {
        MovFinanceiraDAO.getInstance().remover(MovimentacaoFinanceira.class, mov);
    }

    public List<MovimentacaoFinanceira> getMovimentacoes(Usuario usuario) {
        return MovFinanceiraDAO.getInstance().getMovimentacoes(usuario);
    }
}