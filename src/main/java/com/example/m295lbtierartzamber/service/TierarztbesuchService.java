package com.example.m295lbtierartzamber.service;


import com.example.m295lbtierartzamber.model.Tierarztbesuch;
import com.example.m295lbtierartzamber.repository.TierarztbesuchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TierarztbesuchService {
    private final TierarztbesuchRepository repository;

    public TierarztbesuchService(TierarztbesuchRepository repository) {
        this.repository = repository;
    }

    public Tierarztbesuch create(Tierarztbesuch besuch) {
        return repository.save(besuch);
    }

    public Optional<Tierarztbesuch> getById(Long id) {
        return repository.findById(id);
    }

    public Tierarztbesuch update(Long id, Tierarztbesuch updated) {
        return repository.findById(id)
                .map(b -> {
                    b.setDatum(updated.getDatum());
                    b.setBeschreibung(updated.getBeschreibung());
                    b.setKosten(updated.getKosten());
                    return repository.save(b);
                })
                .orElseThrow(() -> new RuntimeException("Besuch nicht gefunden"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}