package com.gerenciadorlivro.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciadorlivro.entities.Livro;
import com.gerenciadorlivro.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name= "Usuarios",description = "API REST DE GERENCIAMENTO DE USUÁRIOS")
@RestController
@RequestMapping("/livro")
public class LivroController {
	private final LivroService livroService;
	
	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuario por ID")
	public ResponseEntity<Livro> buscarId(@PathVariable Long id){
		Livro usuario = livroService.buscarId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}}
	
		@GetMapping("/")
		@Operation(summary = "Apresenta todos os usuarios")
		public ResponseEntity <List<Livro>> buscartodos(){
		List<Livro> usuarios = livroService.buscarTodos();
		return ResponseEntity.ok(usuarios);
	}
		@PostMapping("/")
		@Operation(summary = "Inserindo Dados")
		public ResponseEntity<Livro>salvar(@RequestBody @Valid Livro usuario){
			Livro salvar = livroService.salvar(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
		}
		@PutMapping("/")
		@Operation(summary = "Aterando Dados")
		public ResponseEntity<Livro> alterar(@PathVariable Long id, @RequestBody @Valid Livro livro){
			Livro alterar = livroService.alterar(id, livro);
			if(alterar !=null) {
				return ResponseEntity.ok(livro);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
			@DeleteMapping("/{id}")
			@Operation(summary = "Deletando Dados")
			public ResponseEntity<Livro> apagar(@PathVariable Long id){
				boolean apagar = livroService.apagar(id);
				if(apagar) {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}
				else {
					return ResponseEntity.notFound().build();
				}
			}
		}