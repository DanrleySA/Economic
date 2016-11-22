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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
    private DateFormat fr = new SimpleDateFormat("dd/MM");
    private Usuario usuario = new Seguranca().getUsuarioLogado().getUsuario();

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

    public static DateFormat getDATE_FORMAT() {
        return DATE_FORMAT;
    }

    public static void setDATE_FORMAT(DateFormat DATE_FORMAT) {
        GraficoMovBean.DATE_FORMAT = DATE_FORMAT;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
