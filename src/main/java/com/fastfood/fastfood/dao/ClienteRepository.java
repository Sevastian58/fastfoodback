package com.fastfood.fastfood.dao;

import com.fastfood.fastfood.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
