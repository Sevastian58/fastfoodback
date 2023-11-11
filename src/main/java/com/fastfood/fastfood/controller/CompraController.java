package com.fastfood.fastfood.controller;

import com.fastfood.fastfood.entity.Compra;
import com.fastfood.fastfood.service.impl.CompraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraServiceImpl compraService;

    @GetMapping("/list")
    public ResponseEntity<List<Compra>> getAll() {
        return new ResponseEntity<List<Compra>>(this.compraService.getAll(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Compra> getById(@PathVariable("id") Long idCompra) {
        return new ResponseEntity<Compra>(this.compraService.getById(idCompra), HttpStatus.ACCEPTED);
    }
    @PostMapping("/grabar")
    public ResponseEntity<Compra> save(@RequestBody Compra compra) {
        return new ResponseEntity<Compra>(this.compraService.save(compra), HttpStatus.CREATED);
    }
}
