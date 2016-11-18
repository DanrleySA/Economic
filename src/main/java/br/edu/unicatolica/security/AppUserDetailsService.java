package br.edu.unicatolica.security;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Danrley
 */
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = UsuarioDAO.getInstance().getUserPorNome(nome);
        UsuarioSistema user = null;
        if (usuario != null) {
            user = new UsuarioSistema(usuario, getPermissoes(usuario));
        }
        return user;
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        List<SimpleGrantedAuthority> permissoes = new ArrayList<>();
        permissoes.add(new SimpleGrantedAuthority(usuario.getTipo().toString().toUpperCase()));

        return permissoes;
    }

}
