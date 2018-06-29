/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.principal.entity.AsignacionCatedraticoEntity;
import sce.principal.ormjpa.AsignacionCatedraticoJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionCatedraticoBuscador {
    
    private final EntityManagerFactory emf;
    
    public AsignacionCatedraticoBuscador(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public List<AsignacionCatedraticoEntity> obtenerCarrerasAsignadas(Long idCarrera) {
        return (List<AsignacionCatedraticoEntity>)new AsignacionCatedraticoJpaController(emf).buscarPorCarrera(idCarrera);     
    }
    
}
