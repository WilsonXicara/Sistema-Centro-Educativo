/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.estudiante;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.persona.AtributoAdicionalEditor;
import sce.persona.builder.AbstractInformacionPersona;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.persona.save.InformacionPersonaCommand;
import sce.persona.estudiante.orm.EstudianteEntity;
import sce.persona.estudiante.orm.EstudianteJpaController;
import sce.excepciones.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class InformacionEstudianteSave implements InformacionPersonaCommand {
    private final EntityManagerFactory emf;
    
    public InformacionEstudianteSave(EntityManagerFactory emf) { this.emf = emf; }

    @Override
    public void guardarInformacionPersona(AbstractInformacionPersona informacionPersona) throws PreexistingEntityException, ExcepcionTipoNoSoportado {
        if (informacionPersona instanceof InformacionEstudiante) {
            // Se guardará el registro en la tabla de Estudiantes (dentro de la BD)
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            EstudianteJpaController controller = new EstudianteJpaController(emf);
            EstudianteEntity estudiante = (EstudianteEntity)informacionPersona.getPersonaEntity();
            // Verifico que no existe un registro con el mismo CUI
            EstudianteEntity existente = controller.buscarPorCui(estudiante.getAtributoUnico());
            if (existente != null) {
                em.getTransaction().rollback();
                throw new PreexistingEntityException("Ya existe un registro Estudiante con CUI="+estudiante.getAtributoUnico());
            }
            // Obtención y conversión de los Valores adicionales a formato JSON
            ArrayList<String> atributosAdicionales = informacionPersona.getAtributosAdicionales();
            ArrayList<String> valoresAdicionales = informacionPersona.getValoresAtributosAdicionales();
            String valorAtributosAdicionales = AtributoAdicionalEditor.convertirArrayListAJSON(atributosAdicionales, valoresAdicionales);
            estudiante.setAtributos_adicionales(valorAtributosAdicionales);
            // Creación o editación del registro
            if (estudiante.getId() == null) {
                controller.create(estudiante, em);
            } else {
                controller.edit(estudiante, em);
            }
            em.getTransaction().commit();
        } else {
            throw new ExcepcionTipoNoSoportado("La clase '"+this.getClass().getName()+"' no puede guardar un registro de la clase '"+informacionPersona.getClass().getName()+"'");
        }
    }
    
}
