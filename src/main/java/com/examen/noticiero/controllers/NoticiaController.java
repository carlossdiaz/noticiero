package com.examen.noticiero.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.examen.noticiero.model.Noticia;

@Controller
@RequestMapping("/noticias")
@Scope("session")
public class NoticiaController {
@Autowired


    @RequestMapping(value = "/list")
public ModelAndView list(){
    ModelAndView modelAndView= new ModelAndView();
    modelAndView.addObject("noticias", getNoticias());
    modelAndView.setViewName("noticias/list");

    return modelAndView;
}

@RequestMapping(path = {"/edit/{codigo}"})
public ModelAndView edit(
    @PathVariable(name = "codigo", required = true) int codigo
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticia", getNoticia(codigo));
        modelAndView.setViewName("noticias/edit");
        return modelAndView;
    }
@GetMapping(path = {"/create"})
public ModelAndView create(Noticia noticia) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("noticias/new");
    return modelAndView;
}
@PostMapping(path = {"/save"})
public ModelAndView save(Noticia noticia){

    List<Noticia> noticias = getNoticias();
    noticias.add(noticia);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("noticias", noticias);
    modelAndView.setViewName("noticias/list");
    return modelAndView;
}
@PostMapping(path = {"/update"})
public ModelAndView update(Noticia noticia){
    List<Noticia> noticias = getNoticias();

    int indexOf = noticias.indexOf(noticia);

    noticias.set(indexOf, noticia);

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("noticias", noticias);
    modelAndView.setViewName("noticias/list");
    return modelAndView;

}
@RequestMapping(path = {"/delete/{codigo}"})
public ModelAndView delete(
    @PathVariable(name = "codigo", required = true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        List<Noticia> noticias = getNoticias();
        noticias.remove(getNoticia(codigo));
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");
        return modelAndView;
    }

private Noticia getNoticia(int codigo){
    List<Noticia> noticias = getNoticias();
    int indexOf = noticias.indexOf(new Noticia(codigo));
    return noticias.get(indexOf);
}


private List<Noticia> getNoticias(){
    ArrayList<Noticia> noticias = new ArrayList<Noticia>();

    noticias.add(new Noticia(1, "Titular 1", "Descripción 1"));
    noticias.add(new Noticia(2, "Titular 2", "Descripción 2"));
    noticias.add(new Noticia(3, "Titular 3", "Descripción 3"));
    noticias.add(new Noticia(4, "Titular 4", "Descripción 4"));
    return noticias;

}
}
