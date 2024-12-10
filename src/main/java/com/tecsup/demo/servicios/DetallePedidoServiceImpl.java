package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.DetallePedidoRepository;
import com.tecsup.demo.modelo.entidades.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DetallePedido> listar() {
        return detallePedidoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(DetallePedido detallePedido) {
        detallePedidoRepository.save(detallePedido);
    }

    @Override
    @Transactional(readOnly = true)
    public DetallePedido buscar(Integer id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        detallePedidoRepository.deleteById(id);
    }
}
