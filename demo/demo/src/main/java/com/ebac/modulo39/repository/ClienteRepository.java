package com.ebac.modulo39.repository;

import com.ebac.modulo39.dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNombreAndPassword(String nombre, String password);
}
