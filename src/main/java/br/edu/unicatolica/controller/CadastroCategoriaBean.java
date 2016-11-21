package br.edu.unicatolica.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.bo.CategoriaBO;
import br.edu.unicatolica.entity.Categoria;
import br.edu.unicatolica.jsf.util.FacesUtil;
import br.edu.unicatolica.security.Seguranca;

@ManagedBean
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Categoria categoria;

    public CadastroCategoriaBean() {
        limpar();
    }

    public void salvar() {
        if (categoria.getId() == null) {
            categoria.setUsuario(new Seguranca().getUsuarioLogado().getUsuario());
            CategoriaBO.getInstance().salvarOuAtualizar(categoria);

            FacesUtil.addInfoMessage("Categoria cadastrada com sucesso!");
        } else {
            CategoriaBO.getInstance().salvarOuAtualizar(categoria);

            FacesUtil.addInfoMessage("Categoria atualizada com sucesso!");
        }

        limpar();
    }

    private void limpar() {
        categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
