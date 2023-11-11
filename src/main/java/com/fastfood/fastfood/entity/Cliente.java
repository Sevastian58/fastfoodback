package com.fastfood.fastfood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String apellido;
    private String correo;
    private String password;
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Compra> listaCompras;
}
