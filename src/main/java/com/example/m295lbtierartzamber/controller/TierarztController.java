package com.example.m295lbtierartzamber.controller;

import com.example.m295lbtierartzamber.model.Tier;
import com.example.m295lbtierartzamber.service.TierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiere")
@RequiredArgsConstructor
public class TierarztController {

    private final TierService service;

    // CREATE
    @PostMapping
    public ResponseEntity<Tier> create(@Valid @RequestBody Tier tier) {
        Tier saved = service.save(tier);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // READ single
    @GetMapping("/{id}")
    public ResponseEntity<Tier> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ all
    @GetMapping
    public List<Tier> getAll() {
        return service.findAll();
    }

    // UPDATE
    /*@PutMapping("/{id}")
    public ResponseEntity<Tier> update(
            @PathVariable Long id,
            @Valid @RequestBody Tier tier
    ) {
        return ResponseEntity.ok(service.update(id, tier));
    }*/

    // DELETE single
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE all (optional)
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
