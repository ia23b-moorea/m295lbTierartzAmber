package com.example.m295lbtierartzamber.service;

import com.example.m295lbtierartzamber.model.Tier;
import com.example.m295lbtierartzamber.repository.TierRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TierService {

    private final TierRepository repo;

    // CREATE
    public Tier save(Tier tier) {
        log.info("Erstelle neues Tier: {}", tier.getName());
        Tier saved = repo.save(tier);
        log.info("Tier gespeichert mit ID={}", saved.getId());
        return saved;
    }

    // READ single
    public Optional<Tier> findById(Long id) {
        log.debug("Hole Tier mit ID={}", id);
        return repo.findById(id);
    }

    // READ all
    public List<Tier> findAll() {
        log.debug("Hole alle Tiere");
        return repo.findAll();
    }

    // DELETE single
    public void deleteById(Long id) {
        log.info("Lösche Tier ID={}", id);
        repo.deleteById(id);
        log.debug("Tier ID={} gelöscht", id);
    }

    // DELETE all
    public void deleteAll() {
        log.info("Lösche alle Tiere");
        repo.deleteAll();
        log.debug("Alle Tiere gelöscht");
    }

    public Tier update(Long id, @Valid Tier tier) {
        log.info("Update ein Tier: {}", tier.getName());
        Tier saved = repo.save(tier);
        log.info("Tier gespeichert mit ID={}", saved.getId());
        return saved;
    }
}
