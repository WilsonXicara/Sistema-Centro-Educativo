/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.grado;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.principal.entity.AsignacionGradoEntity;
import sce.principal.ormjpa.AsignacionGradoJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionGradoBuscador {
    
    private final  EntityManagerFactory emf;

    public AsignacionGradoBuscador(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
   public List<AsignacionGradoEntity> obtenerGradosAsignados(Long idCarrera){
          return (List<AsignacionGradoEntity>)new AsignacionGradoJpaController(emf).buscarPorCarrera(idCarrera); 
   }
    
}
