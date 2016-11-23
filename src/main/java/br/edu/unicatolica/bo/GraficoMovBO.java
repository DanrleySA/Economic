/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.MovFinanceiraDAO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import br.edu.unicatolica.security.Seguranca;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Danrley
 */
public class GraficoMovBO implements Serializable {

    private static GraficoMovBO instance;

    private GraficoMovBO() {

    }

    public static GraficoMovBO getInstance() {
        if (instance == null) {
            instance = new GraficoMovBO();
        }
        return instance;
    }

    public Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias, Usuario usuario, TipoMovimentacao tipo) {
        Calendar dataInicial = Calendar.getInstance();
        dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
        dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);

        Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias, dataInicial);

        resultado = MovFinanceiraDAO.getInstance().consultaGrafico(
                resultado,
                UsuarioDAO.getInstance().getEntidadePorId(Usuario.class, 1L),
                dataInicial,
                tipo);
        return resultado;
    }

    private Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias, Calendar dataInicial) {
        numeroDeDias -= 1;
        dataInicial = (Calendar) dataInicial.clone();
        Map<Date, BigDecimal> mapaInicial = new TreeMap<>();

        for (int i = 0; i < numeroDeDias; i++) {
            mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
            dataInicial.add(Calendar.DAY_OF_MONTH, 1);
        }

        return mapaInicial;
    }

    public BigDecimal saldoDoMes(Integer mes) {
        BigDecimal saldo = new BigDecimal("0.00");
        List<MovimentacaoFinanceira> movimentacoes
                = MovFinanceiraDAO.getInstance().getMovimentacoes(mes, new Seguranca().getUsuarioLogado().getUsuario());
        for (MovimentacaoFinanceira mov : movimentacoes) {
            if (mov.getTipo() == TipoMovimentacao.RECEITA) {
                System.out.println(mov.getDescricao());
                saldo = saldo.add(mov.getValor());
            } else {
                System.out.println(mov.getDescricao());
                saldo = saldo.subtract(mov.getValor());
            }
        }

        return saldo;
    }
}
