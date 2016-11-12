package br.edu.unicatolica.security;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Grupo;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = UsuarioDAO.getInstance().getUserPorEmail(email);
        UsuarioSistema user = null;
        if (usuario != null) {
            user = new UsuarioSistema(usuario, getGrupos(usuario));
        }
        return user;
    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Grupo grupo : usuario.getGrupos()) {
            authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
        }
        return authorities;
    }

}
