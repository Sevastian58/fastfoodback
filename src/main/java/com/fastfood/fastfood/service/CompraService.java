package com.fastfood.fastfood.service;

import com.fastfood.fastfood.entity.Compra;

import java.util.List;

public interface CompraService {
    public List<Compra> getAll();

    public Compra getById(Long idCompra);

    public Compra save(Compra compra);
}
