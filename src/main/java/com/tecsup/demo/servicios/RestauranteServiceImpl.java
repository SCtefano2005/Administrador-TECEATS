package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.RestauranteRepository;
import com.tecsup.demo.modelo.entidades.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Restaurante restaurante) {
        restauranteRepository.save(restaurante);
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurante buscar(Integer id) {
        return restauranteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        restauranteRepository.deleteById(id);
    }
}
