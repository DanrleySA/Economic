/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.enumeration;

/**
 *
 * @author danrley
 */
public enum TipoUsuario {
    USUARIO("Usuário");

    private String descricao;

    private TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
