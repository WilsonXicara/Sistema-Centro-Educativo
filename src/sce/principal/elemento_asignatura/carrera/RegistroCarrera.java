/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.carrera;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.carrera.orm.CarreraEntity;
import sce.principal.elemento_asignatura.carrera.orm.CarreraJpaController;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.ElementoAsignaturaEntity;

/**
 *
 * @author juan_
 */
public class RegistroCarrera extends AbstractElementoAsignatura {
    private final RegistroInformacionCarrera registroCarrera;

    public RegistroCarrera(RegistroInformacionCarrera registroCarrera) {
        this.registroCarrera = new RegistroInformacionCarrera();
        this.registroCarrera.setIdCarrera(idElementoAsignatura = null);
    }
    
    public RegistroCarrera(Long idCarrera, EntityManagerFactory emf){
        CarreraEntity auxCarrera = new CarreraJpaController(emf).findCarreraEntity(idCarrera);
        this.registroCarrera = new RegistroInformacionCarrera();
        if (auxCarrera != null) {
            this.registroCarrera.setIdCarrera(this.idElementoAsignatura = idCarrera);
            this.registroCarrera.setNombre(auxCarrera.getNombre());
            this.registroCarrera.setDescripcion(auxCarrera.getDescripcion());
            this.registroCarrera.setPensum_id(auxCarrera.getPensum_id());
        } else {
            this.registroCarrera.setIdCarrera(this.idElementoAsignatura = null);
        }
    }
    
    public String getNombreCarrera() { 
        return this.registroCarrera.getNombre(); 
    }
    
    public String getDescripcionCarrera() {
        return this.registroCarrera.getDescripcion(); 
    }
    
    public Long getPensumID(){
        return this.registroCarrera.getPensum_id();
    }
    
    public void setPensumID(Long idPensum){
        this.registroCarrera.setPensum_id(idPensum);
    }
    
    public boolean setNombreCurso(String nombreCarrera) {
        if (this.idElementoAsignatura == null) {
            this.registroCarrera.setNombre(nombreCarrera);
            return true;
        }
        return false;
    }
    
    public void setDescripcionCurso(String descripcionCarrera) { 
        this.registroCarrera.setDescripcion(descripcionCarrera); 
    }

    @Override
    public ElementoAsignaturaEntity getElementoAsignaturaEntity() {
        CarreraEntity carrera = new CarreraEntity(registroCarrera.getNombre(), registroCarrera.getDescripcion(), registroCarrera.getPensum_id());
        carrera.setId(idElementoAsignatura);
        return carrera;
    }
    
}
