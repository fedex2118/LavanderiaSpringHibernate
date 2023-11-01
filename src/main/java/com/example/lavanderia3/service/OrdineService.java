package com.example.lavanderia3.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lavanderia3.model.Cliente;
import com.example.lavanderia3.model.Ordine;
import com.example.lavanderia3.model.Servizio;
import com.example.lavanderia3.repository.ClienteRepository;
import com.example.lavanderia3.repository.OrdineRepository;
import com.example.lavanderia3.repository.ServizioRepository;

@Service
public class OrdineService {
	
	@Autowired
    private OrdineRepository ordineRepository;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
    private ServizioRepository servizioRepository;

    // Salvare un ordine
    public Ordine save(Ordine ordine, Long clienteId) {
    	Set<Servizio> servizi = ordine.getServizi();
    	Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
    	
    	if(cliente == null || servizi.isEmpty()) {
    		System.err.println("Cliente non esiste oppure non hai aggiunto servizi!");
    		return null;
    	}
    	
    	double costoTotale = 0.0;
    	
    	for(Servizio s: servizi) {
    		// L'id va sempre messo nel body per funzionare altrimenti abbiamo errore!
    		Servizio serv = servizioRepository.findById(s.getId()).orElse(null);
    		
    		if(serv == null) {
    			System.err.println("Errore: uno dei servizi nel body ha un id che non esiste...");
    			System.err.println("...oppure non c'era un id nel body della richiesta!");
    			return null;
    		}
    		
    		s.setCosto(serv.getCosto());
    		s.setTipoServizio(serv.getTipoServizio());
    		s.setTempoEsecuzione(serv.getTempoEsecuzione());
    	}
    	
    	for(Servizio s: servizi) {
    		costoTotale += s.getCosto();
    	}
    	
    	// si potrebbero gestire dataConsegna e dataRitiro...
    	
    	Ordine o = new Ordine();
    	o.setId(ordine.getId());
    	o.setDataConsegna(ordine.getDataConsegna());
    	o.setDataRitiro(ordine.getDataRitiro());
    	o.setCliente(cliente);
    	o.setServizi(servizi);
    	o.setCostoTotale(costoTotale);
    	
    	System.out.println(servizi);
    	System.out.println(costoTotale);
    	
        return ordineRepository.save(o);
    }
    
    public Ordine save(Ordine ordine) {
    	return ordineRepository.save(ordine);
    }

    // Trovare tutti gli ordini
    public List<Ordine> findAll() {
        return ordineRepository.findAll();
    }

    // Trovare un ordine per ID
    public Optional<Ordine> findById(Long id) {
        return ordineRepository.findById(id);
    }

    // Eliminare un ordine
    public void delete(Long id) {
        ordineRepository.deleteById(id);
    }

    // Recuperare il cliente associato a un ordine
    public Optional<Cliente> findClienteByOrdineId(Long id) {
        Optional<Ordine> ordine = ordineRepository.findById(id);
        return ordine.map(Ordine::getCliente);
    }
}
