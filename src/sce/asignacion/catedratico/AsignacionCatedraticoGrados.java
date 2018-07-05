/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoGradosJpaController;
import sce.asignacion.grado.ConsultorAsignacionGrado;
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
        if (!ConsultorAsignacionCatedratico.existeAsignacionCatedratico(idAsignacionCat, emf)){
            throw new NonexistentEntityException("No existen una asignacion catedratico con el id siguiente: " + idAsignacionCat);
        }
        if (ConsultorAsignacionCatedratico.isAsignacionCatedraticoAnulada(idAsignacionCat, emf)){
            throw new NonexistentEntityException("La asignacion catedratico con id " + idAsignacionCat + "está anulada");
        }
        
        AsignacionCatedraticoGradosEntity asignacionCatGrados = new AsignacionCatedraticoGradosEntity(); 
        asignacionCatGrados.setAsignacion_catedratico_id(idAsignacionCat);
        for (Long elementos : idAsignacionGrado){
            if (ConsultorAsignacionGrado.existeAsignacionGrado(elementos, emf)){
                if (!ConsultorAsignacionGrado.isAsignacionGradoAnulada(elementos, emf))
                {
                    asignacionCatGrados.setAsignacion_grado_id(elementos);
                    new AsignacionCatedraticoGradosJpaController(emf).create(asignacionCatGrados);
                } else {
                    throw new NonexistentEntityException("La asignacion grado con id " +idAsignacionGrado+ "está anulada.");
                }        
            }
            else {
                throw new NonexistentEntityException("No existen una asignacion grado con el id siguiente: " + elementos);
            }  
        }
    } 
    
}
