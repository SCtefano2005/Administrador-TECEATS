package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.EntregaRepository;
import com.tecsup.demo.modelo.entidades.Entrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Entrega entrega) {
        entregaRepository.save(entrega);
    }

    @Override
    @Transactional(readOnly = true)
    public Entrega buscar(Integer id) {
        return entregaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        entregaRepository.deleteById(id);
    }
}
