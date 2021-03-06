/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.GraficoMovBO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import br.edu.unicatolica.security.Seguranca;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Danrley
 */
@ManagedBean
@RequestScoped
public class GraficoMovBean implements Serializable {

    private LineChartModel model;
    private Integer numeroDeDias = 15;
    private Usuario usuario = new Seguranca().getUsuarioLogado().getUsuario();
    private BigDecimal saldoDoMes = new BigDecimal("0.00");
    private Integer mes;

    public void preRender() {
        this.model = new LineChartModel();
        this.model.setAnimate(true);

        adicionarSerie("Receitas", usuario, TipoMovimentacao.RECEITA);
        adicionarSerie("Despesas", usuario, TipoMovimentacao.DESPESA);

        this.model.setTitle("Receita x Despesa");
        this.model.setLegendPosition("ne");
        Axis yAxis = this.model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Valor");

        DateAxis xAxis = new DateAxis("Datas");
        xAxis.setTickAngle(-50);
        xAxis.setTickFormat("%b %#d");
        this.model.getAxes().put(AxisType.X, xAxis);
    }

    public void adicionarSerie(String rotulo, Usuario usuario, TipoMovimentacao tipo) {
        Map<Date, BigDecimal> valoresPorData = GraficoMovBO.getInstance().valoresTotaisPorData(
                numeroDeDias, usuario, tipo);

        LineChartSeries series = new LineChartSeries();
        series.setLabel(rotulo);
        for (Date data : valoresPorData.keySet()) {
            series.set(data.getTime(), valoresPorData.get(data));
        }

        this.model.addSeries(series);
    }

    public void calculaSaldo() {
        saldoDoMes = GraficoMovBO.getInstance().saldoDoMes(mes);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public LineChartModel getModel() {
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }

    public Integer getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(Integer numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getSaldoDoMes() {
        return saldoDoMes;
    }

    public void setSaldoDoMes(BigDecimal saldoDoMes) {
        this.saldoDoMes = saldoDoMes;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }
//</editor-fold>

}
