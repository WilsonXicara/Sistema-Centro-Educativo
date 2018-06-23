/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.generico;

import sce.principal.Asignacion;
import sce.principal.AsignacionBuilder;
import sce.principal.Persona;
import sce.principal.ElementoAsignatura;

/**
 *
 * @author Usuario
 */
public class AsignacionConcretBuilder implements AsignacionBuilder {
    private Asignacion asignacion;
    private int tipoAsignacion;
    
    public AsignacionConcretBuilder() { }

    @Override
    public void setAsignacion(Asignacion asignacion, int tipoAsignacion) {
        this.asignacion = asignacion;
        this.tipoAsignacion = tipoAsignacion;
    }

    @Override
    public void setElementoPrincipal(ElementoAsignatura elemento) {
        // AÚN PENDIENTE DE IMPLEMENTAR
    }

    @Override
    public void setElementoSecundario(ElementoAsignatura ee) {
        // AÚN PENDIENTE DE IMPLEMENTAR
    }

    @Override
    public void setElementoTerciario(Persona prsn) {
        // AÚN PENDIENTE DE IMPLEMENTAR
    }

    @Override
    public Asignacion getAsignacion() {
        // AÚN PENDIENTE DE IMPLEMENTAR
        return this.asignacion;
    }
    
}
