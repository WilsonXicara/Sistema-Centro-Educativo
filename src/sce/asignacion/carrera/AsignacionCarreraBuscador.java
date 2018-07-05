/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionCarreraBuscador {
     private final EntityManagerFactory emf;
    
    public AsignacionCarreraBuscador(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public List<AsignacionCarreraEntity> obtenerCarrerasAsignadas(Long idCicloEscolar) {
        return (List<AsignacionCarreraEntity>)new AsignacionCarreraJpaController(emf).buscarPorCiclo(idCicloEscolar);     
    }
    
}
