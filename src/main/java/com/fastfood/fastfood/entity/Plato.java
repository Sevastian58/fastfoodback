package com.fastfood.fastfood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "plato")
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private Integer codigo;
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="imagen")
    private String imagen;
    @Column(name = "precio")
    private double precio;
    private int cantidad;


    //llaves foraneas
    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DetalleCompra> listaDetalleCompra;


}
