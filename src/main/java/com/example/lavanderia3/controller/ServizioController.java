package com.example.lavanderia3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lavanderia3.model.Servizio;
import com.example.lavanderia3.service.ServizioService;

@RestController
@RequestMapping("/servizio")
public class ServizioController {

    @Autowired
    private ServizioService servizioService;

    // GET: Ottenere tutti i servizi
    @GetMapping
    public ResponseEntity<List<Servizio>> getAllServizi() {
        return ResponseEntity.ok(servizioService.findAll());
    }

    // GET: Ottenere un servizio specifico
    @GetMapping("/{id}")
    public ResponseEntity<Servizio> getServizioById(@PathVariable Long id) {
        return servizioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Creare un nuovo servizio
    @PostMapping
    public ResponseEntity<Servizio> createServizio(@RequestBody Servizio servizio) {
    	return ResponseEntity.ok(servizioService.save(servizio));
    }

    // PUT: Modificare un servizio esistente
    @PutMapping("/{id}")
    public ResponseEntity<Servizio> updateServizio(@PathVariable Long id, @RequestBody Servizio servizio) {
        if (!servizioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        servizio.setId(id); // Assicurati che l'ID nel corpo corrisponda all'ID nel path
        return ResponseEntity.ok(servizioService.save(servizio));
    }

    // DELETE: Eliminare un servizio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServizio(@PathVariable Long id) {
        servizioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
