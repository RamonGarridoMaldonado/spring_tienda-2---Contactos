package com.llvillar.springboot.app1.services;

import java.util.List;
import com.llvillar.springboot.app1.model.Contacto;


public interface ContactosService {
    public List<Contacto> findAll();
    public Contacto find(int id);
    public Contacto save(Contacto Departamento);
    public void update(Contacto Departamento);
    public void delete(int id);
}
