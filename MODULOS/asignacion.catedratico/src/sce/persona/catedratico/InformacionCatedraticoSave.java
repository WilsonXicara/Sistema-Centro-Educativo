/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.persona.AtributoAdicionalEditor;
import sce.persona.builder.AbstractInformacionPersona;
import sce.excepciones.ExcepcionTipoNoSoportado;
import sce.persona.save.InformacionPersonaCommand;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.persona.catedratico.orm.CatedraticoJpaController;
import sce.excepciones.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class InformacionCatedraticoSave implements InformacionPersonaCommand {
    private final EntityManagerFactory emf;
    
    public InformacionCatedraticoSave(EntityManagerFactory emf) { this.emf = emf; }

    @Override
    public void guardarInformacionPersona(AbstractInformacionPersona informacionPersona) throws PreexistingEntityException, ExcepcionTipoNoSoportado {
        if (informacionPersona instanceof InformacionCatedratico) {
            // Se guardará el registro en la tabla de Catedráticos (dentro de la BD)
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            CatedraticoJpaController controller = new CatedraticoJpaController(emf);
            CatedraticoEntity catedratico = (CatedraticoEntity)informacionPersona.getPersonaEntity();
            // Verifico que no existe un registro con el mismo DPI
            CatedraticoEntity existente = controller.buscarPorDpi(catedratico.getAtributoUnico());
            if (existente != null) {
                em.getTransaction().rollback();
                throw new PreexistingEntityException("Ya existe un registro Catedrático con DPII="+catedratico.getAtributoUnico());
            }
            // Obtención y conversión de los Valores adicionales a formato JSON
            ArrayList<String> atributosAdicionales = informacionPersona.getAtributosAdicionales();
            ArrayList<String> valoresAdicionales = informacionPersona.getValoresAtributosAdicionales();
            String valorAtributosAdicionales = AtributoAdicionalEditor.convertirArrayListAJSON(atributosAdicionales, valoresAdicionales);
            catedratico.setAtributos_adicionales(valorAtributosAdicionales);
            // Creación o editación del registro
            if (catedratico.getId() == null) {
                controller.create(catedratico, em);
            } else {
                controller.edit(catedratico, em);
            }
            em.getTransaction().commit();
        } else {
            throw new ExcepcionTipoNoSoportado("La clase '"+this.getClass().getName()+"' no puede guardar un registro de la clase '"+informacionPersona.getClass().getName()+"'");
        }
    }
    
}
