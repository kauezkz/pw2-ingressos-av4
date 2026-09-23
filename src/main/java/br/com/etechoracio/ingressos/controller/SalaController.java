package br.com.etechoracio.ingressos.controller;

import br.com.etechoracio.ingressos.entity.Sala;
import br.com.etechoracio.ingressos.Repository.SalaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
@CrossOrigin("*")
public class SalaController {

    private final SalaRepository repository;

    public SalaController(SalaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Sala> listar() {
        return repository.findByDataExclusaoIsNull();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        return repository.findByIdAndDataExclusaoIsNull(id)
                .map(sala -> ResponseEntity.ok(sala))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sala> cadastrar(@RequestBody Sala sala) {
        Sala novaSala = repository.save(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSala);
    }
}