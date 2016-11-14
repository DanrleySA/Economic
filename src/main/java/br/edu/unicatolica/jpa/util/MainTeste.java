package br.edu.unicatolica.jpa.util;

import br.edu.unicatolica.dao.CategoriaDAO;
import br.edu.unicatolica.dao.GrupoDAO;
import br.edu.unicatolica.dao.MovFinanceiraDAO;
import br.edu.unicatolica.dao.MovimentacaoDAO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Grupo;
import br.edu.unicatolica.entity.Movimentacao;
import br.edu.unicatolica.entity.MovimentacaoFinanceira;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.Status;
import java.util.Date;

/**
 *
 * @author Danrley
 */
public class MainTeste {

    public static void main(String[] args) {
       
        Usuario u = new Usuario();
        u.setEmail("ana@gmail.com");
        u.setNome("Ana");
        u.setSenha("123");
        u.getGrupos().add(GrupoDAO.getInstance().getEntidadePorId(Grupo.class, 1L));

        UsuarioDAO.getInstance().salvarOuAtualizar(u);
        
    }
}
