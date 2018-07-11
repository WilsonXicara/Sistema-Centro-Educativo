/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.principal.elemento_asignatura.carrera.orm.CarreraEntity;
import sce.principal.elemento_asignatura.carrera.orm.CarreraJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionCarreraBuscador {
     private final EntityManagerFactory emf;
     private List<AsignacionCarreraEntity> listaAsignaciones;
     private final List<CarreraEntity> listaCarreras;
    
    public AsignacionCarreraBuscador(EntityManagerFactory emf) {
        this.emf = emf;
        this.listaAsignaciones = new ArrayList<>();
        this.listaCarreras = new ArrayList<>();
    }
    
    public List<CarreraEntity> obtenerAsignaciones() {
        listaAsignaciones = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntityEntities();  
        for (int x = 0; x < listaAsignaciones.size(); x++ ){ 
            listaCarreras.add(new CarreraJpaController(emf).findCarreraEntity(listaAsignaciones.get(x).getCarrera_id()));
        }
        return listaCarreras;
    }
    
    public List<AsignacionCarreraEntity> obtenerCarrerasAsignadas(Long idCicloEscolar) {
        return (List<AsignacionCarreraEntity>)new AsignacionCarreraJpaController(emf).buscarPorCiclo(idCicloEscolar);     
    }
    
    public AsignacionCarreraEntity obtenerAsignacionCarrera(Long idCarrera) {
        return (AsignacionCarreraEntity)new AsignacionCarreraJpaController(emf).buscarPorCarreraId(idCarrera);
    }
    
}
