package com.fastfood.fastfood.service.impl;

import com.fastfood.fastfood.dao.ClienteRepository;
import com.fastfood.fastfood.entity.Cliente;
import com.fastfood.fastfood.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional()
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteDao;

    @Override
    public List<Cliente> getAll() {
        return clienteDao.findAll();
    }

    @Override
    public Cliente getById(Long idCliente) {
        return clienteDao.findById(idCliente).orElse(null);
    }

    @Override
    @Transactional()
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    @Transactional()
    public Cliente update(Cliente cliente) {
        return clienteDao.saveAndFlush(cliente);
    }

    @Override
    public Cliente checkCredentials(String email, String password) {

        List<Cliente> clientes = this.getAll();
        Cliente cliente = null;

        if (clientes != null) {
            for (Cliente c : clientes) {
                if (isCredentialsEquals(c, email, password)) return c;
            }
        }

        return cliente;
    }

    private boolean isCredentialsEquals(Cliente cliente, String correo, String password) {
        return cliente.getCorreo().equalsIgnoreCase(correo) && cliente.getPassword().equalsIgnoreCase(password);
    }

}
