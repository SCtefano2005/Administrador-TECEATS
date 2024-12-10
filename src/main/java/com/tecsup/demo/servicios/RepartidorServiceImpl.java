package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.daos.RepartidorRepository;
import com.tecsup.demo.modelo.entidades.Repartidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RepartidorServiceImpl implements RepartidorService {

    @Autowired
    private RepartidorRepository repartidorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Repartidor> listar() {
        return repartidorRepository.findAll();
    }

    @Override
    @Transactional
    public void grabar(Repartidor repartidor) {
        repartidorRepository.save(repartidor);
    }

    @Override
    @Transactional(readOnly = true)
    public Repartidor buscar(Integer id) {
        return repartidorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        repartidorRepository.deleteById(id);
    }
}
