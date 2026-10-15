package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Asignatura;
import com.example.demo.repository.IAsignaturaRepository;
import com.example.demo.repository.ICarreraRepository;

@Controller
// SE ELIMINÓ EL @RequestMapping DE AQUÍ PARA PODER ESCUCHAR LA RAÍZ GLOBAL
public class AsignaturaController {

    @Autowired
    private IAsignaturaRepository repoAsi;

    @Autowired
    private ICarreraRepository repoCar;

    // 0. REDIRECCIÓN AUTOMÁTICA AL INICIAR EL SERVIDOR
    @GetMapping("/")
    public String irAlInicio() {
        return "redirect:/asignaturas/listar"; // Redirecciona de golpe al entrar a localhost:8080
    }

    // 1. LISTADO (Se le agregó /asignaturas al inicio)
    @GetMapping("/asignaturas/listar")
    public String listar(Model model) {
        model.addAttribute("listaAsi", repoAsi.findAll());
        return "listado"; 
    }

    // 2. MOSTRAR FORMULARIO DE NUEVO REGISTRO (Se le agregó /asignaturas al inicio)
 // 2. MOSTRAR FORMULARIO DE NUEVO REGISTRO
    @GetMapping("/asignaturas/nuevo")
    public String nuevo(Model model) {
        Asignatura nuevaAsignatura = new Asignatura();
        
        // AGREGA ESTA LÍNEA CRÍTICA PARA EVITAR EL ERROR 500:
        nuevaAsignatura.setCarrera(new com.example.demo.model.Carrera()); 
        
        model.addAttribute("asignatura", nuevaAsignatura);
        model.addAttribute("listaCarreras", repoCar.findAll()); 
        return "formulario"; 
    }


    // 3. GRABAR EN BASE DE DATOS (Se le agregó /asignaturas al inicio)
    @PostMapping("/asignaturas/grabar")
    public String grabar(@ModelAttribute Asignatura asignatura) {
        repoAsi.save(asignatura);
        return "redirect:/asignaturas/listar"; 
    }
}
