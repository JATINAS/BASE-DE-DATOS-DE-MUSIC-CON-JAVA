package com.ebac.modulo39.controller;

import com.ebac.modulo39.dto.Cliente;
import com.ebac.modulo39.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/clientes/login")
    public Boolean login(@RequestBody Cliente cliente) {
        log.info("Intentando login para cliente: {}", cliente.getNombre());

        return clienteService.validarCliente(cliente.getNombre(), cliente.getPassword());
    }

    @GetMapping("/clientes")
    public ResponseWrapper<List<Cliente>> obtenerCliente() {
        log.info("Obteniendo Clientes existentes");
        List<Cliente> clienteList = clienteService.obtenerClientes();

        return new ResponseWrapper<>(true, "Listado de clientes existentes", clienteList);
    }

    @PostMapping("/clientes")
    public ResponseWrapper<Cliente> crearCliente(
            @RequestBody Cliente cliente) {

        try {
            Cliente clienteCreado = clienteService.crearCliente(cliente);

            return new ResponseWrapper<>(true, "Cliente creado exitosamente", clienteCreado);
        } catch (Exception e) {
            return new ResponseWrapper<>(false, e.getMessage(), null);
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseWrapper<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {

        Optional<Cliente> clienteOptional = clienteService.obtenrClientePorId(id);

        if (clienteOptional.isPresent()) {
            clienteActualizado.setIdCliente(clienteOptional.get().getIdCliente());
            clienteService.actualizarCliente(clienteActualizado);

            return new ResponseWrapper<>(true, "Cliente actualizado correctamente", clienteActualizado);
        } else {
            return new ResponseWrapper<>(false, "El cliente indicado no existe", null);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseWrapper<Void> eliminarCliente(
            @PathVariable Long id) {

        Optional<Cliente> clienteOptional = clienteService.obtenrClientePorId(id);

        if (clienteOptional.isEmpty()) {
            return new ResponseWrapper<>(false, "El cliente indicado no existe", null);
        }
        clienteService.eliminarCliente(id);

        return new ResponseWrapper<>(true, "Cliente eliminado correctamente", null);
    }
}

