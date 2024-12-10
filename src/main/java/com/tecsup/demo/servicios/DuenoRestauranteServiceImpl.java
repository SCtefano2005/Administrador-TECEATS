package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.DuenoRestauranteRepository;
import com.tecsup.demo.modelo.entidades.DuenoRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DuenoRestauranteServiceImpl implements DuenoRestauranteService {

    @Autowired
    private DuenoRestauranteRepository duenoRestauranteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DuenoRestaurante> listar() {
        return duenoRestauranteRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(DuenoRestaurante duenoRestaurante) {
        duenoRestauranteRepository.save(duenoRestaurante);
    }

    @Override
    @Transactional(readOnly = true)
    public DuenoRestaurante buscar(Integer id) {
        return duenoRestauranteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        duenoRestauranteRepository.deleteById(id);
    }
}
