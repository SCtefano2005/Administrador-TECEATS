package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.PedidoRepository;
import com.tecsup.demo.modelo.entidades.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido buscar(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
