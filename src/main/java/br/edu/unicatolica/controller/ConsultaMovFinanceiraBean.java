/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.MovFinanceiraBO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class ConsultaMovFinanceiraBean implements Serializable {

    private List<MovimentacaoFinanceira> movimentacoes;
    private TipoMovimentacao tipoSelecionado;
    private Date dataInicial;
    private Date dataFinal;
    private MovimentacaoFinanceira movSelecionada;

    public void remover() {
        MovFinanceiraBO.getInstance().remover(movSelecionada);
        movimentacoes.remove(movSelecionada);
        FacesUtil.addInfoMessage("Movimentação excluída com sucesso!");
    }

    public void pesquisarMovimentacoes() {
        movimentacoes = MovFinanceiraBO.getInstance().getMovimentacoes(
                tipoSelecionado, dataInicial, dataFinal, buscarUsuario());
    }

    public Usuario buscarUsuario() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return UsuarioDAO.getInstance().getUserPorNome(((User) authentication.getPrincipal()).getUsername());

    }

    public List<MovimentacaoFinanceira> getMovimentacoes() {
        if (movimentacoes == null) {
            movimentacoes = new ArrayList<>();
        }
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoFinanceira> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public TipoMovimentacao getTipo() {
        return tipoSelecionado;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipoSelecionado = tipo;
    }

    public TipoMovimentacao[] getTipos() {
        return TipoMovimentacao.values();
    }

    public TipoMovimentacao getTipoSelecionado() {
        return tipoSelecionado;
    }

    public void setTipoSelecionado(TipoMovimentacao tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public MovimentacaoFinanceira getMovSelecionada() {
        return movSelecionada;
    }

    public void setMovSelecionada(MovimentacaoFinanceira movSelecionada) {
        this.movSelecionada = movSelecionada;
    }

}
