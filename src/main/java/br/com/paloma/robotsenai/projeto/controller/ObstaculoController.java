package br.com.paloma.robotsenai.projeto.controller;

import br.com.paloma.robotsenai.projeto.model.Obstaculo;
import br.com.paloma.robotsenai.obstaculo.service.java.ObstaculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/obstaculos")
public class ObstaculoController {

    private final ObstaculoService service;

    public ObstaculoController(ObstaculoService service) {
        this.service = service;
    }

    // Criar
    @PostMapping
    public ResponseEntity<Obstaculo> criar(@RequestBody Obstaculo obstaculo) {
        Obstaculo novo = service.criar(obstaculo);
        return ResponseEntity.created(URI.create("/obstaculos/" + novo.getId())).body(novo);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Obstaculo> atualizar(@PathVariable Long id, @RequestBody Obstaculo obstaculo) {
        return service.atualizar(id, obstaculo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Obstaculo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Obstaculo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
