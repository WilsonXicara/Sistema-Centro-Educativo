/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel;

import sce.principal.Asignacion;
import sce.principal.AsignacionBuilder;
import sce.principal.ElementoEducativo;
import sce.principal.Persona;

/**
 *
 * @author Usuario
 */
public class AsignacionBasicoBuilder implements AsignacionBuilder {
    private Asignacion asignacion;
    private int tipoAsignacion;
    
    public AsignacionBasicoBuilder() { }

    @Override
    public void setAsignacion(Asignacion asignacion, int tipoAsignacion) {
        this.asignacion = asignacion;
        this.tipoAsignacion = tipoAsignacion;
    }

    @Override
    public void setElementoPrincipal(ElementoEducativo elemento) {
        // AÚN PENDIENTE DE IMPLEMENTAR
    }

    @Override
    public void setElementoSecundario(ElementoEducativo ee) {
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
