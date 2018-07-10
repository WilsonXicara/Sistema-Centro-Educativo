/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.grado;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.excepciones.PreexistingEntityException;
import sce.principal.elemento_asignatura.grado.orm.GradoEntity;
import sce.principal.elemento_asignatura.grado.orm.GradoJpaController;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.AbstractRegistroElementoAsignaturaSave;

/**
 *
 * @author juan_
 */
public class RegistroGradoSave extends AbstractRegistroElementoAsignaturaSave {

    @Override
    public void guardarElementosAsignatura(EntityManagerFactory emf) throws PreexistingEntityException, ExcepcionTipoNoSoportado {
        for (AbstractElementoAsignatura elemento : elementosAsignatura) {
            if (elemento instanceof RegistroGrado) {
                RegistroGrado registroCarrera = (RegistroGrado)elemento;
                GradoEntity grado = (GradoEntity)registroCarrera.getElementoAsignaturaEntity();
                GradoJpaController controller = new GradoJpaController(emf);
                if (grado.getId() == null) {
                    GradoEntity existente = controller.buscarPorGradoSeccion(grado.getGrado(), grado.getSeccion());
                    if (existente != null) {
                        throw new PreexistingEntityException("El grado '"+grado.getGrado()+"' ya existe");
                    }
                    controller.create(grado);  
                } else {
                    try {
                        controller.edit(grado);     
                    } catch (Exception ex) {
                        Logger.getLogger(RegistroGradoSave.class.getName()).log(Level.SEVERE, null, ex);
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
 
