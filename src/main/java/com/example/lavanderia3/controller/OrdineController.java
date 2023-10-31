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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lavanderia3.model.Cliente;
import com.example.lavanderia3.model.Ordine;
import com.example.lavanderia3.service.OrdineService;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    // GET: Ottenere tutti gli ordini
    @GetMapping
    public ResponseEntity<List<Ordine>> getAllOrdini() {
        return ResponseEntity.ok(ordineService.findAll());
    }

    // GET: Ottenere un ordine specifico
    @GetMapping("/{id}")
    public ResponseEntity<Ordine> getOrdineById(@PathVariable Long id) {
        return ordineService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Creare un nuovo ordine
    @PostMapping
    public ResponseEntity<Ordine> createOrdine(@RequestBody Ordine ordine, @RequestParam Long clienteId) {
        Cliente cliente = new Cliente();
    	if(ordine.getCliente() == null) {
        	ordine.setCliente(cliente);
        }
    	return ResponseEntity.ok(ordineService.save(ordine, clienteId));
    }

    // PUT: Modificare un ordine esistente
    @PutMapping("/{id}")
    public ResponseEntity<Ordine> updateOrdine(@PathVariable Long id, @RequestBody Ordine ordine) {
        if (!ordineService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ordine.setId(id); // Assicurati che l'ID nel corpo corrisponda all'ID nel path
        return ResponseEntity.ok(ordineService.save(ordine));
    }

    // DELETE: Eliminare un ordine
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdine(@PathVariable Long id) {
        ordineService.delete(id);
        return ResponseEntity.ok().build();
    }
}
