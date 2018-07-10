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
import sce.asignacion.grado.AnuladorAsignacionGrado;
import sce.asignacion.grado.ConsultorAsignacionGrado;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author juan_
 */
public class AnuladorAsignacionCarrera {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCarrera;
    private String razon_anulacion; 

    public AnuladorAsignacionCarrera(EntityManagerFactory emf, Long idAsignacionCarrera) {
      this.emf = emf;
      this.idAsignacionCarrera = idAsignacionCarrera;
    }

    public void setRazon_anulacion(String razon_anulacion) {
        this.razon_anulacion = razon_anulacion;
    }

    public void anularAsignacion() throws NonexistentEntityException{
        /*List<AsignacionGradoEntity> listaGrado;
        List<AsignacionCatedraticoEntity> listaCatedratico;
        List<AsignacionEstudianteEntity> listaEstudiante;
        String razon = "La asignación de carrera con id " + idAsignacionCarrera + "ha sido anulada.";
        
        if(!ConsultorAsignacionCarrera.existeAsignacionCarrera(idAsignacionCarrera, emf)){
            throw new NonexistentEntityException("No existe una carrera con el id siguiente: " + idAsignacionCarrera);    
        }
        //Se hace la anulación de la asignación carrera
        AsignacionCarreraEntity asignacionAnulada = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        asignacionAnulada.setAnulado(true);
        asignacionAnulada.setRazon_anulacion(razon_anulacion);    
        try {
            new AsignacionCarreraJpaController(emf).edit(asignacionAnulada);
        } catch (Exception ex) {
            throw new NonexistentEntityException(ex.getMessage());
        }
        
        //Se busca con que otras asignaciones está relacionada esta asignacion y se anulan también
        
        //En este caso se busca la asignacion grado que contiene esta asigncion carrera y se anula
        listaGrado = new AsignacionGradoJpaController(emf).buscarPorCarrera(idAsignacionCarrera);
        if (!ConsultorAsignacionGrado.existeAsignacionGrado(listaGrado.get(0).getId(), emf)){
            throw new NonexistentEntityException("No existe una asignacion grado por anular.");
        }
        AnuladorAsignacionGrado anuladorAsigGrado = new AnuladorAsignacionGrado(emf,listaGrado.get(0).getId());
        anuladorAsigGrado.setRazon_anulacion(razon);
        anuladorAsigGrado.anularAsignacion();
      
        //Se anula la asignación del catedrático 
        listaCatedratico = new AsignacionCatedraticoJpaController(emf).buscarPorCarrera(idAsignacionCarrera);
        if (!ConsultorAsignacionCatedratico.existeAsignacionCatedratico(listaCatedratico.get(0).getId(), emf)){
            throw new NonexistentEntityException("No existe una asignacion catedratico por anular.");
        }
        AnuladorAsignacionCatedratico anuladorAsigCat = new AnuladorAsignacionCatedratico(emf, listaCatedratico.get(0).getId());
        anuladorAsigCat.setRazon_anulacion(razon);
        anuladorAsigCat.anularAsignacion();
       
        
        //Se anula una asignación de estudiante 
        listaEstudiante = new AsignacionEstudianteJpaController(emf).buscarPorCarrera(idAsignacionCarrera);
        try {
            AsignacionEstudianteAnulador.anularAsignacion(emf, listaEstudiante.get(0).getId(),razon);
        } catch (ExcepcionParametrosIncompletos ex) {
            throw new NonexistentEntityException(ex.getMessage());
        }
        
        //Pendiente la anulación de asignacion curso*/
        
    }
    
    
}
