/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import sce.principal.Asignacion;
import sce.principal.AsignacionBuilder;
import sce.principal.CentroEducativo;
import sce.principal.CicloEscolar;
import sce.principal.ElementoEducativo;
import sce.principal.Grado;
import sce.principal.Persona;

/**
 *
 * @author Usuario
 */
public class AsignacionBasicoBuilder implements AsignacionBuilder {
    private Asignacion asignacion;
    private int tipoAsignacion;
    
    public AsignacionBasicoBuilder() {
        System.out.println("AsignacionBasicoBuilder->()");
    }

    @Override
    public void setElementoPrincipal(ElementoEducativo ee) {
        switch(tipoAsignacion) {
            case CentroEducativo.TIPO_ASIGNACION_GRADO:
                ((AsignacionGradoBasico)asignacion).setCicloEscolar((CicloEscolar)ee);
                break;
            case CentroEducativo.TIPO_ASIGNACION_CURSO:
                ((AsignacionCursoBasico)asignacion).setGrado((Grado)ee);
                break;
            case CentroEducativo.TIPO_ASIGNACION_ESTUDIANTE:
                ((AsignacionEstudianteBasico)asignacion).setCicloEscolar((CicloEscolar)ee);
                break;
            case CentroEducativo.TIPO_ASIGNACION_CATEDRATICO:
                ((AsignacionCatedraticoBasico)asignacion).setCicloEscolar((CicloEscolar)ee);
                break;
        }
        System.out.println("AsignacionBasicoBuilder->setElementoPrincipal()");
    }

    @Override
    public void setElementoSecundario(ElementoEducativo ee) {
        System.out.println("AsignacionBasicoBuilder->setElementoSecundario()");
    }

    @Override
    public void setElementoTerciario(Persona prsn) {
        System.out.println("AsignacionBasicoBuilder->setElementoTerciario()");
    }

    @Override
    public void setAsignacion(Asignacion asgncn, int i) {
        this.tipoAsignacion = i;
        this.asignacion = asgncn;
        System.out.println("AsignacionBasicoBuilder->setAsignacion()");
    }

    @Override
    public Asignacion getAsignacion() {
        System.out.println("AsignacionBasicoBuilder->getAsignacion()");
        return this.asignacion;
    }
    
}
