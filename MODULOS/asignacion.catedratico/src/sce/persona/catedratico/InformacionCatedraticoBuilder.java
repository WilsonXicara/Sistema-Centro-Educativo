/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.persona.AtributoAdicionalEditor;
import sce.persona.builder.AbstractInformacionPersona;
import sce.persona.builder.InformacionPersonaBuilder;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.persona.catedratico.orm.CatedraticoJpaController;

/**
 *
 * @author Usuario
 */
public class InformacionCatedraticoBuilder implements InformacionPersonaBuilder {
    private final EntityManagerFactory emf;
    private InformacionCatedratico informacionCatedratico;
    private CatedraticoEntity entityCatedratico;
    
    public InformacionCatedraticoBuilder(EntityManagerFactory emf) { this.emf = emf; }

    private void iniciarNuevoRegristroPersona() {
        informacionCatedratico = new InformacionCatedratico();
        informacionCatedratico.setRegistroPersonaEntity(entityCatedratico);
    }
    @Override
    public void nuevoRegistroPersona() {
        // Se inicializa un EstudianteEntity vacío
        entityCatedratico = new CatedraticoEntity();
        iniciarNuevoRegristroPersona();
    }
    @Override
    public void nuevoRegistroPersona(Long idPersona) {
        // Se inicializa un EstudianteEntity con un registro en la Base de Datos
        entityCatedratico = new CatedraticoJpaController(emf).findCatedraticoEntity(idPersona);
        if (entityCatedratico == null) {
            entityCatedratico = new CatedraticoEntity();
        }
        iniciarNuevoRegristroPersona();
    }
    @Override
    public void obtenerAtributosAdicionales() {
        // Obtención del listado de Atributos adicionales relacionados a la Tabla de Estudiantes
        ArrayList<String> atributosAdicionales = AtributoAdicionalEditor
                .obtenerListaAtributos(emf, CatedraticoEntity.tableName);
        informacionCatedratico.setAtributosAdicionales(atributosAdicionales);
    }
    @Override
    public void obtenerValoresAdicionales() {
        // Obtención del listado de Valores de los Atributos adicionales relacionados al EstudianteEntity existente
        if (entityCatedratico.getId() != null) {
            // Estos valores están almacenados en formato JSON
            ArrayList<String> copiaAtributos = informacionCatedratico.getAtributosAdicionales();
            String copiaValores = ((CatedraticoEntity)informacionCatedratico.getPersonaEntity()).getAtributos_adicionales();
            ArrayList<String> valoresAdicionales = AtributoAdicionalEditor
                    .convertirJSONAArrayList(copiaAtributos, copiaValores);
            // 'valoresAdicionales' tiene los valores, correlativos a los atributos adicionales

            int cantidad = copiaAtributos.size(), index;
            for(index=0; index<cantidad; index++) {
                informacionCatedratico.setValorAtributo(copiaAtributos.get(index), valoresAdicionales.get(index));
            }
        }
    }

    @Override
    public AbstractInformacionPersona getInformacionPersona() { return informacionCatedratico; }
}