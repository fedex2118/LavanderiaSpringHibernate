package com.example.lavanderia3.service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lavanderia3.model.Servizio;
import com.example.lavanderia3.model.TipoServizio;
import com.example.lavanderia3.repository.ServizioRepository;
import com.example.lavanderia3.repository.TipoServizioRepository;

@Service
public class ServizioService {
    
    @Autowired
    private TipoServizioRepository tipoServizioRepository;
	
    @Autowired
    private ServizioRepository servizioRepository;

    // Salvare un servizio
    public Servizio save(Servizio servizio) {
    	Servizio s = new Servizio();
    	TipoServizio tipoServizio = servizio.getTipoServizio();
    	Time time = servizio.getTempoEsecuzione();
    	
    	if(tipoServizio == null)
    		return null;
    	
    	System.out.println(servizio.getTempoEsecuzione());
    	
    	s.setId(servizio.getId());
    	s.setTempoEsecuzione(time);
    	s.setCosto(servizio.getCosto());
    	s.setTipoServizio(tipoServizio);
    	
    	
    	tipoServizioRepository.save(tipoServizio);
    	
        return servizioRepository.save(s);
    }

    // Trovare tutti i servizi
    public List<Servizio> findAll() {
        return servizioRepository.findAll();
    }

    // Trovare un servizio per ID
    public Optional<Servizio> findById(Long id) {
        return servizioRepository.findById(id);
    }

    // Eliminare un servizio
    public void delete(Long id) {
        servizioRepository.deleteById(id);
    }
	
}
