package com.example.demo.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;


@Repository
@Transactional
public class ImplementsUserDetailsService  implements UserDetailsService{

	@Autowired
	private UsuarioRepository ur;
	
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = ur.findByEmail(login);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		
		return new User(usuario.getEmail(),usuario.getSenha(),true, true, true, true, usuario.getAuthorities());
	}

}
