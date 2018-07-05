/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.grado;

import javax.persistence.EntityManagerFactory;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.ElementoAsignaturaEntity;
import sce.principal.elemento_asignatura.grado.orm.GradoEntity;
import sce.principal.elemento_asignatura.grado.orm.GradoJpaController;

/**
 *
 * @author juan_
 */
public class RegistroGrado extends AbstractElementoAsignatura {
    private final RegistroInformacionGrado registroGrado;

    public RegistroGrado(RegistroInformacionGrado registroGrado) {
        this.registroGrado = new RegistroInformacionGrado();
        this.registroGrado.setIdGrado(idElementoAsignatura = null);
    }
    
    public RegistroGrado(Long idGrado, EntityManagerFactory emf){
        GradoEntity auxGrado = new GradoJpaController(emf).findGradoEntity(idGrado);
        this.registroGrado = new RegistroInformacionGrado();
        if (auxGrado != null) {
            this.registroGrado.setIdGrado(this.idElementoAsignatura = idGrado);
            this.registroGrado.setGrado(auxGrado.getGrado());
            this.registroGrado.setSeccion(auxGrado.getSeccion());
            this.registroGrado.setCapacidad(auxGrado.getCapacidad());
            this.registroGrado.setPrerrequisito_id(auxGrado.getPrerrequisito_id());
            this.registroGrado.setAsignacion_id(auxGrado.getAsignacion_id());
        } else {
            this.registroGrado.setIdGrado(this.idElementoAsignatura = null);
        }
    }
    
    public String getGrado(){
         return this.registroGrado.getGrado();
    } 
    public String getSeccion(){
        return this.registroGrado.getSeccion();
    }
    public int getCapacidad(){
        return this.registroGrado.getCapacidad();
    }
    public Long getPrerrequisitoId(){
        return this.registroGrado.getPrerrequisito_id();
    }
    public Long getAsignacionID(){
        return this.registroGrado.getAsignacion_id();
    }

    @Override
    public ElementoAsignaturaEntity getElementoAsignaturaEntity() {
        GradoEntity grado = new GradoEntity(registroGrado.getGrado(), registroGrado.getSeccion(), registroGrado.getCapacidad(),registroGrado.getPrerrequisito_id(), registroGrado.getPrerrequisito_id());
        grado.setId(idElementoAsignatura);
        return grado;
    }
    
}
