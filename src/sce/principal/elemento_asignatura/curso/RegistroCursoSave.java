/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.principal.elemento_asignatura.AbstractElementoAsignatura;
import sce.principal.elemento_asignatura.AbstractRegistroElementoAsignaturaSave;
import sce.excepciones.PreexistingEntityException;
import sce.principal.elemento_asignatura.curso.orm.CursoEntity;
import sce.principal.elemento_asignatura.curso.orm.CursoJpaController;

/**
 *
 * @author Usuario
 */
public class RegistroCursoSave extends AbstractRegistroElementoAsignaturaSave {

    @Override
    public void guardarElementosAsignatura(EntityManagerFactory emf) throws PreexistingEntityException, ExcepcionTipoNoSoportado {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (AbstractElementoAsignatura elemento : elementosAsignatura) {
            if (elemento instanceof RegistroCurso) {
                RegistroCurso registroCurso = (RegistroCurso)elemento;
                // Se guarda el Registro del nuevo Curso (o curso editado)
                CursoEntity curso = (CursoEntity)registroCurso.getElementoAsignaturaEntity();
                // Se crear el registro en tabla_extendida (si aún no existe)
                CursoJpaController controller = new CursoJpaController(emf);
                if (curso.getId() == null) {
                    // Se verifica que no exista otro curso con el mismo Nombre
                    CursoEntity existente = controller.buscarPorNombre(curso.getCurso());
                    if (existente != null) {
                        throw new PreexistingEntityException("El Curso '"+curso.getCurso()+"' ya existe");
                    }
                    controller.create(curso, em);   // Creación del Curso dentro de la BD
                } else {
                    controller.edit(curso, em);     // Editación del Curso dentro de la BD
                }
            } else {
                // Si este guardador en específico no soporta otro tipo de RegistroElementoAsignaturaCommand
                throw new ExcepcionTipoNoSoportado("La clase '"+this.getClass().getName()+"' no puede guardar un registro de la clase '"+elemento.getClass().getName()+"'");
            }
        }
        em.getTransaction().commit();
        // Limpiar el ArrayList para evitar que se intenten guardar los registros nuevamente
        this.elementosAsignatura.clear();
    }
}