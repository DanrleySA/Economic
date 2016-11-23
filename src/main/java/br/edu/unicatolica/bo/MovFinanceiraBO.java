/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.MovFinanceiraDAO;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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

    public List<MovimentacaoFinanceira> getMovimentacoes(TipoMovimentacao tipo, Date dataInicial, Date dataFinal, Usuario usuario) {
        return MovFinanceiraDAO.getInstance().getMovimentacoesFiltro(tipo, dataInicial, dataFinal, usuario);
    }

    public BigDecimal calculaSaldo(List<MovimentacaoFinanceira> movimentacoes) {
        BigDecimal saldo = new BigDecimal("0.00");
        for (MovimentacaoFinanceira mov : movimentacoes) {
            if (mov.getTipo() == TipoMovimentacao.RECEITA) {
                saldo = saldo.add(mov.getValor());
            } else {
                saldo = saldo.subtract(mov.getValor());
            }
        }
        return saldo;
    }

}
