package com.llvillar.springboot.app1.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.llvillar.springboot.app1.model.Nota;
import com.llvillar.springboot.app1.services.NotasService;

@Controller
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    NotasService notasService;

    @GetMapping(value = "/list")
    public ModelAndView listPage(@RequestParam(value = "titulo",required=false) String titulo,@RequestParam(value = "fecha",required=false) Date fecha,Model model) {
        if (titulo==null || titulo.equals("") || fecha==null) {
            List<Nota> notas = notasService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("notas/list");
            modelAndView.addObject("notas", notas);
            return modelAndView;
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
            String strDate = dateFormat.format(fecha);  
            List<Nota> notas = notasService.findPorTituloFecha(titulo,fecha);
    
            ModelAndView modelAndView = new ModelAndView("notas/list");
            modelAndView.addObject("notas", notas);
            return modelAndView;
        }
    }
    
    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(@PathVariable(name = "codigo", required = true) int id) {

        Nota nota = notasService.find(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nota", nota);
        modelAndView.setViewName("notas/edit");
        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Nota nota) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nota", new Nota());
        modelAndView.setViewName("notas/new");
        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Nota nota) {

        notasService.save(nota);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + nota.getCodigo());
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Nota nota) {

        notasService.update(nota);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + nota.getCodigo());
        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) int id) {

        notasService.delete(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:../list");
        return modelAndView;
    }
}