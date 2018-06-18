/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import sce.principal.Asignacion;
import sce.principal.AsignacionBuilder;
import sce.principal.CentroEducativo;
import sce.principal.CicloEscolar;
import sce.principal.ElementoEducativo;
import sce.principal.Grado;
import sce.principal.Persona;

/**
 *
 * @author juan_
 */
public class AsignacionPrimariaBuilder implements AsignacionBuilder{
    private Asignacion asignacion;
    private int tipoAsignacion;

    public AsignacionPrimariaBuilder() {
    System.out.println("AsignacionPrimariaBuilder->()");
    }
    
   @Override
   public void setElementoPrincipal(ElementoEducativo principal){
    switch(this.tipoAsignacion){
        case CentroEducativo.TIPO_ASIGNACION_GRADO:
            ((AsignacionGradoPrimaria)this.asignacion).setCicloEscolar((CicloEscolar)principal);
            break;
        case CentroEducativo.TIPO_ASIGNACION_CURSO:
            ((AsignacionCursoPrimaria)this.asignacion).setGrado((Grado)principal);
            break;
        case CentroEducativo.TIPO_ASIGNACION_ESTUDIANTE:
            ((AsignacionEstudiantePrimaria)this.asignacion).setCicloEscolar((CicloEscolar)principal);
            break;
        case CentroEducativo.TIPO_ASIGNACION_CATEDRATICO:
            ((AsignacionCatedraticoPrimaria)this.asignacion).setCicloEscolar((CicloEscolar)principal);
            break;
    }
    System.out.println("AsignacionPrimariaBuilder->setElementoPrincipal()");
   }
   
   @Override
   public void setElementoSecundario(ElementoEducativo secundario){}
   
   @Override
   public void setElementoTerciario(Persona persona){}
  
   @Override
    public void setAsignacion(Asignacion asgncn, int i) {
        this.tipoAsignacion = i;
        this.asignacion = asgncn;
        System.out.println("AsignacionPrimariaBuilder->setAsignacion()");
    }

    @Override
    public Asignacion getAsignacion() {
        System.out.println("AsignacionPrimariaBuilder->getAsignacion()");
        return this.asignacion;
    }
   
}
