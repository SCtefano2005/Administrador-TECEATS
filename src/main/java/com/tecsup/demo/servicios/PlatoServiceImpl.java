package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.PlatoRepository;
import com.tecsup.demo.modelo.entidades.Plato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PlatoServiceImpl implements PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Plato> listar() {
        return platoRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Plato plato) {
        platoRepository.save(plato);
    }

    @Override
    @Transactional(readOnly = true)
    public Plato buscar(Integer id) {
        return platoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        platoRepository.deleteById(id);
    }
}
