package br.edu.unicatolica.bo;

import java.io.Serializable;
import java.util.List;

import br.edu.unicatolica.dao.CategoriaDAO;
import br.edu.unicatolica.entity.Categoria;
import br.edu.unicatolica.filter.CategoriaFilter;

public class CategoriaBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static CategoriaBO instance;

	private CategoriaBO() {

	}

	public static CategoriaBO getInstance() {
		if (instance == null) {
			instance = new CategoriaBO();
		}
		return instance;
	}

	public void salvarOuAtualizar(Categoria categoria) {
		CategoriaDAO.getInstance().salvarOuAtualizar(categoria);
	}

	public void remover(Categoria categoria) {
		CategoriaDAO.getInstance().remover(categoria);
	}

	public List<Categoria> getCategorias(CategoriaFilter categoriaFilter) {
		return CategoriaDAO.getInstance().getCategorias(categoriaFilter);
	}
}
