/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.calendario;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.excepciones.PreexistingEntityException;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.AbstractRegistroElementoAsignaturaSave;
import sce.principal.elemento_asignatura.calendario.orm.CalendarioCicloEscolarEntity;
import sce.principal.elemento_asignatura.calendario.orm.CalendarioCicloEscolarJpaController;

/**
 *
 * @author juan_
 */
public class RegistroCalendarioSave extends AbstractRegistroElementoAsignaturaSave {
   

    @Override
    public void guardarElementosAsignatura(EntityManagerFactory emf) throws PreexistingEntityException, ExcepcionTipoNoSoportado {
        for (AbstractElementoAsignatura elemento : elementosAsignatura) {
                   if (elemento instanceof RegistroCalendario) {
                       RegistroCalendario registroCalendario = (RegistroCalendario)elemento;
                       CalendarioCicloEscolarEntity calendarioCicloEscolar = (CalendarioCicloEscolarEntity)registroCalendario.getElementoAsignaturaEntity();
                       CalendarioCicloEscolarJpaController controller = new CalendarioCicloEscolarJpaController(emf);
                       if (calendarioCicloEscolar.getId() == null) {
                           CalendarioCicloEscolarEntity existente = controller.buscarPorParametros(calendarioCicloEscolar.getAsignacion_carrera_id(),calendarioCicloEscolar.getMes(), calendarioCicloEscolar.getDia_inicio(), calendarioCicloEscolar.getDia_fin(),calendarioCicloEscolar.getActividad());
                           if (existente != null) {
                               throw new PreexistingEntityException("El calendario de ciclo escolar con id '"+calendarioCicloEscolar.getId()+"' ya existe");
                           }
                           controller.create(calendarioCicloEscolar);  
                       } else {
                           try {
                               controller.edit(calendarioCicloEscolar);    
                           } catch (Exception ex) {
                               Logger.getLogger(RegistroCalendarioSave.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
                   } else {
                       // Si este guardador en específico no soporta otro tipo de RegistroElementoAsignaturaCommand
                       throw new ExcepcionTipoNoSoportado("La clase '"+this.getClass().getName()+"' no puede guardar un registro de la clase '"+elemento.getClass().getName()+"'");
                   }
               }
               // Limpiar el ArrayList para evitar que se intenten guardar los registros nuevamente
               this.elementosAsignatura.clear();
           }
    
    
}
