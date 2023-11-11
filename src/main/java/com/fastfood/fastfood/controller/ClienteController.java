package com.fastfood.fastfood.controller;

import com.fastfood.fastfood.entity.Cliente;
import com.fastfood.fastfood.service.impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/list")
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<List<Cliente>>(this.clienteService.getAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") Long idCliente) {
        return new ResponseEntity<Cliente>(this.clienteService.getById(idCliente), HttpStatus.ACCEPTED);
    }

    @PostMapping("/grabar")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        return new ResponseEntity<Cliente>(this.clienteService.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
        return new ResponseEntity<Cliente>(this.clienteService.update(cliente), HttpStatus.ACCEPTED);
    }

    @PostMapping("/check")
    public ResponseEntity<Cliente> checkCredentials(@RequestBody Cliente cliente) {
        return new ResponseEntity<Cliente>(this.clienteService.checkCredentials(cliente.getCorreo(), cliente.getPassword()),
                HttpStatus.ACCEPTED);
    }

}
