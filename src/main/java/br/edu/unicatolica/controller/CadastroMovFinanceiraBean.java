/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.MovFinanceiraBO;
import br.edu.unicatolica.dao.CategoriaDAO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Categoria;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author danrl
 */
@ManagedBean
@ViewScoped
public class CadastroMovFinanceiraBean implements Serializable {

    private MovimentacaoFinanceira movimentacaoFinanceira;
    private List<Categoria> categorias;

    public CadastroMovFinanceiraBean() {
        limpar();
        movimentacaoFinanceira = new MovimentacaoFinanceira();
        movimentacaoFinanceira.setUsuario(buscarUsuario());
        pesquisarCategorias();
    }

    public void salvar() {
        if (movimentacaoFinanceira.getId() == null) {
            MovFinanceiraBO.getInstance().salvarOuAtualizar(movimentacaoFinanceira);
            FacesUtil.addInfoMessage("Movimentação salva com sucesso");
        } else {
            MovFinanceiraBO.getInstance().salvarOuAtualizar(movimentacaoFinanceira);
            FacesUtil.addInfoMessage("Movimentação atualizada com sucesso.");
        }
        limpar();
    }

    public Usuario buscarUsuario() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return UsuarioDAO.getInstance().getUserPorNome(((User) authentication.getPrincipal()).getUsername());

    }

    public void pesquisarCategorias() {
        categorias = CategoriaDAO.getInstance().getCategoriasSemFiltro(buscarUsuario());
    }

    public void limpar() {
        movimentacaoFinanceira = new MovimentacaoFinanceira();
    }

    public MovimentacaoFinanceira getMovimentacaoFinanceira() {
        return movimentacaoFinanceira;
    }

    public void setMovimentacaoFinanceira(MovimentacaoFinanceira movimentacaoFinanceira) {
        this.movimentacaoFinanceira = movimentacaoFinanceira;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

}
