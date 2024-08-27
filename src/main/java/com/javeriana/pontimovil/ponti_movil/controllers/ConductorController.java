package com.javeriana.pontimovil.ponti_movil.controllers;

import com.javeriana.pontimovil.ponti_movil.entities.Conductor;
import com.javeriana.pontimovil.ponti_movil.services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conductores")
public class ConductorController {

    // Service:
    private final ConductorService conductorService;

    // Constructor:
    @Autowired
    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    // Métodos:
    @GetMapping
    public ModelAndView obtenerConductores() {
        List<Conductor> conductores = conductorService.obtenerConductores();
        ModelAndView conductoresModelo = new ModelAndView("coordinator/c-gestionar-conductores");
        conductoresModelo.addObject("conductores", conductores);
        return conductoresModelo;
    }

    @GetMapping("/{id}")
    public Conductor obtenerConductorPorId(@PathVariable UUID id) {
        return conductorService.obtenerConductorPorId(id);
    }

    // @PostMapping("/crear")
    // public void crearConductor(@RequestBody Conductor conductor) {
    //     List<Conductor> conductores = conductorService.obtenerConductores();
    //     ModelAndView conductoresModelo = new ModelAndView("coordinator/c-gestionar-conductores");
    //     conductoresModelo.addObject("conductores", conductores);
    //     return conductoresModelo;
    // }
    
    @GetMapping("/{id}/editar")
    public ModelAndView actualizarConductor(@PathVariable UUID id) {
        Conductor c = conductorService.obtenerConductorPorId(id);
        ModelAndView conductorActualizar = new ModelAndView("coordinator/c-conductor-actualizar");
        conductorActualizar.addObject("conductor", c);
        return conductorActualizar;
    }

    @PostMapping(value = "/actualizar")
    public Object actualizarConductor(@Valid Conductor conductor, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("coordinator/c-conductor-actualizar");
        }
        conductorService.actualizarConductor(conductor.getId(), conductor);
        return new RedirectView("/conductores");
    }

    @DeleteMapping("/{id}/eliminar")
    public void eliminarConductor(@PathVariable UUID id) {
        conductorService.eliminarConductor(id);
    }
}
