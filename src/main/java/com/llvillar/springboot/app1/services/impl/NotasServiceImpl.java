package com.llvillar.springboot.app1.services.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.llvillar.springboot.app1.model.Nota;
import com.llvillar.springboot.app1.services.NotasService;

@Service
public class NotasServiceImpl implements NotasService{

    @Value("${url.notas.rest.service}")
    String urlNotas;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Nota> findAll() {
        Nota[] ca = restTemplate.getForObject(urlNotas+"notas", Nota[].class);
        List<Nota> notas = Arrays.asList(ca);

        return notas;
    }

    @Override
    public Nota find(int id) {
        Nota nota = restTemplate.getForObject(urlNotas+"notas/{codigo}", Nota.class, id);
        return nota;
    }

    @Override
    public List<Nota> findPorTituloFecha(String titulo,String fecha) {
        Nota[] ca = restTemplate.getForObject(urlNotas+"notas/busquedaTituloFecha/{titulo}/{fecha}", Nota[].class, titulo, fecha);
        List<Nota> notas = Arrays.asList(ca);
        return notas;
    }

    @Override
    public Nota save(Nota nota) {
        Nota c  = restTemplate.postForObject(urlNotas+"notas", nota, Nota.class);
        nota.setCodigo(c.getCodigo());
        return nota;
    }

    @Override
    public void update(Nota nota) {
        restTemplate.put(urlNotas+"notas/{codigo}", nota,nota.getCodigo());
    }

    @Override
    public void delete(int id) {
        restTemplate.delete(urlNotas+"notas/{codigo}",id);
    }

    @Override
    public List<Nota> findPorTituloFecha(String titulo, Date fecha) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(fecha);

        Map<String, String> params = new HashMap<String, String>();
        params.put("titulo", titulo);
        params.put("fecha", date);

        Nota[] ca = restTemplate.getForObject(urlNotas+"notas/busquedaTituloFecha?titulo={titulo}&fecha={fecha}", Nota[].class, params);
        List<Nota> notas = Arrays.asList(ca);
        return notas;
    }

    
}
