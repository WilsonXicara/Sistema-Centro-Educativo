/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.excepciones.NonexistentEntityException;
import sce.principal.elemento_asignatura.carrera.ConsultorRegistroCarrera;
import sce.principal.elemento_asignatura.ciclo.ConsultorRegistroCiclo;

/**
 *
 * @author juan_
 */
public class AsignacionCarrera implements AsignacionCommand {
    
    private final EntityManagerFactory emf;
    private Long idCicloEscolar;
    private Long idCarrera;

    public AsignacionCarrera(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setIdCicloEscolar(Long idCicloEscolar) {
        this.idCicloEscolar = idCicloEscolar;
    }

    public void setIdCarrera(Long idCarrera) {
        this.idCarrera = idCarrera;
    }

    
    
    @Override
    public void crearAsignacion() throws NonexistentEntityException {
        if (!ConsultorRegistroCarrera.existeCarrera(idCarrera, emf)){
            throw new NonexistentEntityException("No existe una carrera con el id siguiente: " + idCarrera);  
        }
        if (ConsultorRegistroCiclo.existeCicloEscolar(idCicloEscolar, emf) && ConsultorRegistroCiclo.esVigenteCicloEscolar(idCicloEscolar, emf)){
            AsignacionCarreraEntity asignacionCarrera = new AsignacionCarreraEntity();
            asignacionCarrera.setCarrera_id(idCarrera);
            asignacionCarrera.setCiclo_escolar_id(idCicloEscolar);
            new AsignacionCarreraJpaController(emf).create(asignacionCarrera);
        } else {
            throw new NonexistentEntityException("El ciclo con id " +idCicloEscolar+ "no existe o no está vigente.");
        }
        
    }
    
}
