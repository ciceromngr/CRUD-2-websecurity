package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioDto;
import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public List<Usuario> pegarTodosUsuario() throws Exception{
		return usuarioRepository.findAll();
	}
	
	public Usuario cadastrarUsuario(Usuario user) throws Exception{
		if(user.getNome() != null && user.getEmail() != null && user.getSenha() != null) {
			user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
			return usuarioRepository.save(user);
		} else {
			throw new Exception("Algum Campo está Vazios!");
		}
	}
	
	public Boolean login(UsuarioDto dto) throws Exception{
		Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		
		if(encoder.matches(dto.getSenha(), usuario.getPassword()) == true) {
			return true;
		}else {
			throw new Exception("Email ou senha Invalidos!");
		}
	}
	
	public void deletarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
}
