/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.MovFinanceiraBO;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.enumeration.TipoMovimentacao;
import br.edu.unicatolica.jsf.util.FacesUtil;
import br.edu.unicatolica.security.Seguranca;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

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
                tipoSelecionado, dataInicial, dataFinal, new Seguranca().getUsuarioLogado().getUsuario());
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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
//</editor-fold>
}
