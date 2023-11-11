package com.fastfood.fastfood.service.impl;

import com.fastfood.fastfood.dao.CompraRepository;
import com.fastfood.fastfood.dao.DetalleCompraRepository;
import com.fastfood.fastfood.entity.Compra;
import com.fastfood.fastfood.entity.DetalleCompra;
import com.fastfood.fastfood.entity.Plato;
import com.fastfood.fastfood.service.CompraService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompraServiceImpl implements CompraService {

    @Autowired
    CompraRepository compraDao;

    @Autowired
    DetalleCompraRepository detalleCompraDao;

    @Autowired
    PlatoService platoService;

    @Override
    public List<Compra> getAll() {
        return compraDao.findAll();
    }

    @Override
    public Compra getById(Long idCompra) {
        return compraDao.findById(idCompra).orElse(null);
    }

    @Override
    @Transactional
    public Compra save(Compra compra) {
        compra.setFecha(new Date());

        compra.getListaDetalleCompra().forEach(detalle -> {
            Plato plato = platoService.searchById(detalle.getPlato().getCodigo());
            detalle.setPrecio(plato.getPrecio());
            detalle.setTotal(plato.getPrecio() * detalle.getCantidad());
            detalle.setCompra(compra);  // Establecer la relación bidireccional
            platoService.updateStock(detalle.getPlato().getCodigo(), detalle.getCantidad());
        });

        /*compra.setTotal(
                compra.getListaDetalleCompra()
                        .stream()
                        .mapToDouble(DetalleCompra::getTotal)
                        .sum()
        );*/

        System.out.println(compra.getTotal());
        compraDao.save(compra);

        return compra;
    }
}
