package com.fastfood.fastfood.service.impl;

import com.fastfood.fastfood.dao.PlatoRepository;
import com.fastfood.fastfood.entity.Plato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository repoPlato;

    public List<Plato> listAll(){
        return repoPlato.findAll();
    }

    public Plato registrar(Plato p){
        return  repoPlato.save(p);
    }

    public Plato modificar(Plato p){

       /* p.getListaDetalleCompra().forEach(detalleCompra -> detalleCompra.setPlato(p));

        // Guardar la entidad modificada
        repoPlato.save(p);*/

        return repoPlato.save(p);
    }

    public void eliminar(Integer id){
        repoPlato.deleteById(id);
    }

    public Plato searchById(Integer id){
       return repoPlato.findById(id).orElse(null);
    }

    public void updateStock(int idPlato, int cantidad) {
        Plato plato = this.searchById(idPlato);
        plato.setCantidad(plato.getCantidad() - cantidad);
        this.modificar(plato);
    }



}
