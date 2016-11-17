/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.entity;

import br.edu.unicatolica.dao.EntidadeBase;
import br.edu.unicatolica.enumeration.Status;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Danrley
 */
@Entity
public class MovimentacaoFinanceira implements Serializable, EntidadeBase {

    private Long id;
    private String descricao;
    private Categoria categoria;
    private Date dataVencimento;
    private Status status;
    private BigDecimal valor;
    private Usuario usuario;
    private TipoMovimentacao tipo;

    public MovimentacaoFinanceira() {
        setValor(new BigDecimal("0.00"));
    }

    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 150)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @ManyToOne
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

//<editor-fold defaultstate="collapsed" desc="equals and hashCode">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovimentacaoFinanceira other = (MovimentacaoFinanceira) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
//</editor-fold>

}
