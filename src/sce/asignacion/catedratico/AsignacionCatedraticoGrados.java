/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosEntity;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosJpaController;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author juan_
 */
public class AsignacionCatedraticoGrados implements AsignacionCommand{
    
    private final EntityManagerFactory emf;
    private Long idAsignacionCat;
    private final ArrayList<Long> idAsignacionGrado;
    

    public AsignacionCatedraticoGrados(EntityManagerFactory emf) {
        this.emf = emf;
        this.idAsignacionGrado = new ArrayList<>();
    }

    public void setIdAsignacionCat(Long idAsignacionCat) {
        this.idAsignacionCat = idAsignacionCat;
    }
    
    public void addIdAsignacionGrado(Long idAsignacionGrado) {
        this.idAsignacionGrado.add(idAsignacionGrado);
    }
    
    
    @Override
    public void crearAsignacion() throws NonexistentEntityException{
        EntityManager em = null;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        AsignacionCatedraticoEntity catedraticoExistente = new AsignacionCatedraticoJpaController(emf).findAsignacionCatedraticoEntity(idAsignacionCat);
        if (catedraticoExistente != null){
            AsignacionCatedraticoGradosEntity asignacionCatGrados = new AsignacionCatedraticoGradosEntity(); 
            asignacionCatGrados.setAsignacion_catedratico_id(idAsignacionCat);
            for (Long elementos : idAsignacionGrado){
                AsignacionGradoEntity asigGradoExistente = new AsignacionGradoJpaController(emf).findAsignacion_Grado(elementos);
                if(asigGradoExistente != null){
                    asignacionCatGrados.setAsignacion_grado_id(elementos);
                    new AsignacionCatedraticoGradosJpaController(emf).create(asignacionCatGrados,em);    
                } else {
                    em.getTransaction().rollback();
                    throw new NonexistentEntityException("No existen una asignación grado con el id siguiente: " + elementos);
                }  
            }
        } else {
            em.getTransaction().rollback();
            throw new NonexistentEntityException("No existen una asignación catedratico con el id siguiente: " + idAsignacionCat );
        }
        
        em.getTransaction().commit();
    
    }
    
}
