package com.example.lavanderia3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lavanderia3.model.Ordine;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
}
