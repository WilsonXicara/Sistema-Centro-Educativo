/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.estudiante;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.persona.AtributoAdicionalEditor;
import sce.persona.builder.AbstractInformacionPersona;
import sce.persona.builder.InformacionPersonaBuilder;
import sce.persona.estudiante.orm.EstudianteEntity;
import sce.persona.estudiante.orm.EstudianteJpaController;

/**
 *
 * @author Usuario
 */
public class InformacionEstudianteBuilder implements InformacionPersonaBuilder {
    private final EntityManagerFactory emf;
    private InformacionEstudiante informacionEstudiante;
    private EstudianteEntity entityEstudiante;

    public InformacionEstudianteBuilder(EntityManagerFactory emf) { this.emf = emf; }

    private void iniciarNuevoRegristroEstudiante() {
        informacionEstudiante = new InformacionEstudiante();
        informacionEstudiante.setRegistroPersonaEntity(entityEstudiante);
    }
    @Override
    public void nuevoRegistroPersona() {
        // Se inicializa un EstudianteEntity vacío
        entityEstudiante = new EstudianteEntity();
        iniciarNuevoRegristroEstudiante();
    }
    @Override
    public void nuevoRegistroPersona(Long idPersona) {
        // Se inicializa un EstudianteEntity con un registro en la Base de Datos
        entityEstudiante = new EstudianteJpaController(emf).findEstudianteEntity(idPersona);
        if (entityEstudiante == null) {
            entityEstudiante = new EstudianteEntity();
        }
        iniciarNuevoRegristroEstudiante();
    }
    @Override
    public void obtenerAtributosAdicionales() {
        // Obtención del listado de Atributos adicionales relacionados a la Tabla de Estudiantes
        ArrayList<String> atributosAdicionales = AtributoAdicionalEditor
                .obtenerListaAtributos(emf, EstudianteEntity.tableName);
        informacionEstudiante.setAtributosAdicionales(atributosAdicionales);
    }
    @Override
    public void obtenerValoresAdicionales() {
        // Obtención del listado de Valores de los Atributos adicionales relacionados al EstudianteEntity existente
        if (entityEstudiante.getId() != null) {
            // Estos valores están almacenados en formato JSON
            ArrayList<String> copiaAtributos = informacionEstudiante.getAtributosAdicionales();
            String copiaValores = ((EstudianteEntity)informacionEstudiante.getPersonaEntity()).getAtributos_adicionales();
            ArrayList<String> valoresAdicionales = AtributoAdicionalEditor
                    .convertirJSONAArrayList(copiaAtributos, copiaValores);
            // 'valoresAdicionales' tiene los valores, correlativos a los atributos adicionales

            int cantidad = copiaAtributos.size(), index;
            for(index=0; index<cantidad; index++) {
                informacionEstudiante.setValorAtributo(copiaAtributos.get(index), valoresAdicionales.get(index));
            }
        }
    }
    @Override
    public AbstractInformacionPersona getInformacionPersona() { return informacionEstudiante; }
}