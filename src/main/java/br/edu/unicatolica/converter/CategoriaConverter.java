/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.converter;

import br.edu.unicatolica.dao.CategoriaDAO;
import br.edu.unicatolica.entity.Categoria;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author danrl
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Categoria categoria = null;
        if (value != null) {
            Long id = new Long(value);
            return CategoriaDAO.getInstance().getEntidadePorId(Categoria.class, id);
        }
        return categoria;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            Categoria categoria = (Categoria) value;
            return categoria.getId() == null ? null : categoria.getId().toString();
        }
        return "";
    }

}
