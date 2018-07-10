/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.ciclo;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.ElementoAsignaturaEntity;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarEntity;
import sce.principal.elemento_asignatura.ciclo.orm.CicloEscolarJpaController;

/**
 *
 * @author juan_
 */
public class RegistroCiclo extends AbstractElementoAsignatura {
    private final RegistroInformacionCiclo registroCiclo;

    public RegistroCiclo(RegistroInformacionCiclo registroCiclo) {
        this.registroCiclo = new RegistroInformacionCiclo();
        this.registroCiclo.setIdCiclo(idElementoAsignatura = null);
    }
    
    public RegistroCiclo(Long idCiclo, EntityManagerFactory emf){
        CicloEscolarEntity auxCiclo = new CicloEscolarJpaController(emf)
                .findCicloEscolarEntity(idCiclo);
        this.registroCiclo = new RegistroInformacionCiclo();
        if (auxCiclo != null) {
            this.registroCiclo.setIdCiclo(this.idElementoAsignatura = idCiclo);
            this.registroCiclo.setCiclo_escolar(auxCiclo.getCiclo_escolar());
            this.registroCiclo.setListo(auxCiclo.isListo());
            this.registroCiclo.setCerrado(auxCiclo.isCerrado());
        } else {
            this.registroCiclo.setIdCiclo(this.idElementoAsignatura = null);
        }
    }
    
    public String getNombreCiclo(){
        return this.registroCiclo.getCiclo_escolar();
    }
    public Boolean isListo(){
        return this.registroCiclo.isListo();
    }
    public Boolean isCerrado(){
        return this.registroCiclo.isCerrado();
    }

    @Override
    public ElementoAsignaturaEntity getElementoAsignaturaEntity() {
        CicloEscolarEntity cicloEscolar = new CicloEscolarEntity(registroCiclo.getCiclo_escolar(), registroCiclo.isListo(), registroCiclo.isCerrado());
        cicloEscolar.setId(idElementoAsignatura);
        return cicloEscolar;
    }
    
    
}
