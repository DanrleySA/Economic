package br.edu.unicatolica.jpa.util;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.enumeration.TipoUsuario;

/**
 *
 * @author Danrley
 */
public class MainTeste {

    public static void main(String[] args) {

        Usuario u = new Usuario();
        u.setEmail("dan@gmail.com");
        u.setNome("Danrley");
        u.setSenha("123");
        u.setTipo(TipoUsuario.USUARIO);

        UsuarioDAO.getInstance().salvarOuAtualizar(u);
    }
}
