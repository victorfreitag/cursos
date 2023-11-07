package com.cursos.controller;

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

import com.cursos.entities.Cursos;
import com.cursos.service.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cursos", description = "API REST DE GERENCIAMENTO DE USUÁRIOS")
@RestController
@RequestMapping("/cursos")
public class CursosController {
	private final CursosService funcionarioService;

	@Autowired
	public CursosController(CursosService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuario por ID")
	public ResponseEntity<Cursos> buscarId(@PathVariable Long id) {
		Cursos funcionario = funcionarioService.buscarId(id);
		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os usuarios")
	public ResponseEntity<List<Cursos>> buscartodos() {
		List<Cursos> funcionario = funcionarioService.buscarTodos();
		return ResponseEntity.ok(funcionario);
	}

	@PostMapping("/")
	@Operation(summary = "Inserindo Dados")
	public ResponseEntity<Cursos> salvar(@RequestBody @Valid Cursos funcionario) {
		Cursos salvar = funcionarioService.salvar(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
	}

	@PutMapping("/")
	@Operation(summary = "Aterando Dados")
	public ResponseEntity<Cursos> alterar(@PathVariable Long id, @RequestBody @Valid Cursos funcionario) {
		Cursos alterar = funcionarioService.alterar(id, funcionario);
		if (alterar != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletando Dados")
	public ResponseEntity<Cursos> apagar(@PathVariable Long id) {
		boolean apagar = funcionarioService.apagar(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}