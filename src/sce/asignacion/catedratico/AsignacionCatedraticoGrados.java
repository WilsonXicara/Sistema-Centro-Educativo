/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import sce.principal.command.AsignacionCommand;
import sce.principal.entity.AsignacionCatedraticoEntity;
import sce.principal.entity.AsignacionCatedraticoGradosEntity;
import sce.principal.entity.AsignacionGradoEntity;
import sce.principal.ormjpa.AsignacionCatedraticoGradosJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionCatedraticoGrados implements AsignacionCommand{
    
    private final EntityManagerFactory emf;
    private AsignacionCatedraticoEntity ace;
    private AsignacionGradoEntity age;

    public AsignacionCatedraticoGrados(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setAce(AsignacionCatedraticoEntity ace) {
        this.ace = ace;
    }

    public void setAge(AsignacionGradoEntity age) {
        this.age = age;
    }
    
    
    
    @Override
    public void crearAsignacion() {
        AsignacionCatedraticoGradosJpaController asignadorCatGrados = new AsignacionCatedraticoGradosJpaController(emf);
        AsignacionCatedraticoGradosEntity asignacionCatGrados = new AsignacionCatedraticoGradosEntity();
        asignacionCatGrados.setAsignacion_catedratico_id(ace.getId());
        asignacionCatGrados.setAsignacion_grado_id(age.getId());
        asignadorCatGrados.create(asignacionCatGrados); 
    }
    
}
