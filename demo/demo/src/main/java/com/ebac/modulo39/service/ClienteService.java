package com.ebac.modulo39.service;

import com.ebac.modulo39.dto.Cliente;
import com.ebac.modulo39.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenrClientePorId(Long idCliente) {
        return clienteRepository.findById(idCliente);
    }

    public void actualizarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Boolean validarCliente(String nombre, String password) {
        Optional<Cliente> cliente = clienteRepository.findByNombreAndPassword(nombre, password);

        return cliente.isPresent();
    }
}
