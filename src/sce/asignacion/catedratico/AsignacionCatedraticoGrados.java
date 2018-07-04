/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import sce.asignacion.consultor.ConsultorGeneral;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosJpaController;
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
        
        if (!ConsultorGeneral.asignacionCatedraticoAnulada(idAsignacionCat, emf)){
            AsignacionCatedraticoGradosEntity asignacionCatGrados = new AsignacionCatedraticoGradosEntity(); 
            asignacionCatGrados.setAsignacion_catedratico_id(idAsignacionCat);
            for (Long elementos : idAsignacionGrado){
                if (ConsultorGeneral.asignaciongradoExistente(elementos, emf)){
                    asignacionCatGrados.setAsignacion_grado_id(elementos);
                    new AsignacionCatedraticoGradosJpaController(emf).create(asignacionCatGrados,em);    
                }
                else {
                    em.getTransaction().rollback();
                    throw new NonexistentEntityException("No existen una asignaciÃ³n grado con el id siguiente: " + elementos);
                }  
            }
        } else {
            em.getTransaction().rollback();
            throw new NonexistentEntityException("No existen una asignaciÃ³n catedratico con el id siguiente: " + idAsignacionCat );
        }
        
        em.getTransaction().commit();
    
    }
    
}
