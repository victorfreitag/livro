package com.gerenciadorlivro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorlivro.entities.Livro;
import com.gerenciadorlivro.repository.LivroRepository;

@Service
public class LivroService {
	private LivroRepository livroRepository;
	
	@Autowired
	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	public List<Livro> buscarTodos(){
	return livroRepository.findAll();
	}
	public Livro buscarId(Long id) {
		Optional <Livro> Usuario = livroRepository.findById(id);
		return Usuario.orElse(null);
	}
	public Livro salvar(Livro usuario) {
		return livroRepository.save(usuario);
	}
	public Livro alterar(Long id, Livro alterarprod) {
		Optional <Livro> existe  = livroRepository.findById(id);
		if(existe.isPresent()) {
			alterarprod.setId(id);
			return livroRepository.save(alterarprod);
		}
		return null;
	}
	public boolean apagar(Long id) {
		Optional <Livro> existe = livroRepository.findById(id);
		if(existe.isPresent()) {
			livroRepository.deleteById(id);
			return true;
		}
				
		return false;
	}

}

