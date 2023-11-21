package com.ProjetoSomativa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoSomativa.entities.Curso;
import com.ProjetoSomativa.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Curso", description = "API REST DE GERECIAMENTO DE CLIENTES")
@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "*")
public class CursoController {
	private final CursoService cursoService;

	@Autowired
	public CursoController(CursoService cursosService) {
		this.cursoService = cursosService;
	}


	@GetMapping("/{id}")
	@Operation(summary = "Localiza um cursos por ID")
	public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
		Curso Curso = cursoService.getCursoById(id);
		if (Curso != null) {
			return ResponseEntity.ok(Curso);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "Apresenta todos os cursos")
	public ResponseEntity<List<Curso>> getAllCurso() {
		List<Curso> Curso = cursoService.getAllCurso();
		return ResponseEntity.ok(Curso);
	}

	@PostMapping
	@Operation(summary = "Cadastra um cursos")
	public ResponseEntity<Curso> criarCurso(@RequestBody @Valid Curso curso) {
		Curso criarCurso = cursoService.salvarCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCurso);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar um cursos")
	public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody @Valid Curso cursos) {
		Curso updatedCurso = cursoService.updateCurso(id, cursos);
		if (updatedCurso != null) {
			return ResponseEntity.ok(updatedCurso);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um cursos")
	public ResponseEntity<String> deleteCurso(@PathVariable Long id) {
		boolean deleted = cursoService.deleteCurso(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
