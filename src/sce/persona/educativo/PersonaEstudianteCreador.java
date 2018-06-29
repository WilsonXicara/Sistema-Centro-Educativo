/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.educativo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.persona.AtributoAdicionalEditor;
import sce.principal.command.PersonaCommand;
import sce.principal.entity.AtributosAdicionalesEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.entity.TablaExtendidaEntity;
import sce.principal.ormjpa.AtributosAdicionalesJpaController;
import sce.principal.ormjpa.EstudianteJpaController;
import sce.principal.ormjpa.TablaExtendidaJpaController;

/**
 *
 * @author Usuario
 */
public class PersonaEstudianteCreador implements PersonaCommand {
    private final EntityManagerFactory emf;
    private EstudianteEntity estudiante;
    private final ArrayList<String> atributosAdicionales;
    private ArrayList<String> valoresAdicionales;
    
    public PersonaEstudianteCreador(EntityManagerFactory emf) {
        this.emf = emf;
        this.estudiante = new EstudianteEntity();
        // Se obtiene el listado de atributos adicionales
        TablaExtendidaEntity tabla = new TablaExtendidaJpaController(emf).buscarTabla(EstudianteEntity.tableName);
        this.atributosAdicionales = new ArrayList<>();
        this.valoresAdicionales = new ArrayList<>();
        if (tabla != null) {
            List<AtributosAdicionalesEntity> atributos = new AtributosAdicionalesJpaController(emf)
                    .buscarAtributosParaTabla(tabla.getId());
            for (AtributosAdicionalesEntity atributo : atributos) {
                this.atributosAdicionales.add(atributo.getNombre_atributo());
                this.valoresAdicionales.add("");
            }
        }
    }
    public PersonaEstudianteCreador(EntityManagerFactory emf, Long idEstudiante) {
        this.emf = emf;
        this.estudiante = new EstudianteEntity();
        EstudianteJpaController controller = new EstudianteJpaController(emf);
        EstudianteEntity encontrado = controller.findEstudianteEntity(idEstudiante);
        if (encontrado != null) {
            this.estudiante.copy(encontrado);
            System.out.println(estudiante);
        }
        // Se obtiene el listado de atributos adicionales y su respectivo valor
        TablaExtendidaJpaController tablaExtendida = new TablaExtendidaJpaController(emf);
        TablaExtendidaEntity tabla = tablaExtendida.buscarTabla(EstudianteEntity.tableName);
        this.atributosAdicionales = new ArrayList<>();
        this.valoresAdicionales = new ArrayList<>();
        if (estudiante.getId()!= null && tabla!=null) {
            List<AtributosAdicionalesEntity> atributos = new AtributosAdicionalesJpaController(emf)
                    .buscarAtributosParaTabla(tabla.getId());
            for (AtributosAdicionalesEntity atributo : atributos) {
                this.atributosAdicionales.add(atributo.getNombre_atributo());
            }
            this.valoresAdicionales = AtributoAdicionalEditor.convertirJSONAArrayList(this.atributosAdicionales, estudiante.getAtributos_adicionales());
        }
    }
    public ArrayList<String> getAtributosAdicionales() { return new ArrayList<>(atributosAdicionales); }
    public ArrayList<String> getValoresAdicionales() { return new ArrayList<>(valoresAdicionales); }
    public EstudianteEntity getInformacionBasica() { return estudiante; }
    
    /*public Long getId() { return estudiante.getId(); }
    public String getCui() { return estudiante.getCui(); }
    public String getNombres() { return estudiante.getNombres(); }
    public String getApellidos() { return estudiante.getApellidos(); }
    public String getDireccion() { return estudiante.getDireccion(); }
    public Long getAsignacion_id() { return estudiante.getAsignacion_id(); }
    public Boolean getAnulado() { return estudiante.getAnulado(); }
    public String getRazon_anulacion() { return estudiante.getRazon_anulacion(); }
    public void setCui(String cui) { estudiante.setCui(cui); }
    public void setNombres(String nombres) { estudiante.setNombres(nombres); }
    public void setApellidos(String apellidos) { estudiante.setApellidos(apellidos); }
    public void setDireccion(String direccion) { estudiante.setDireccion(direccion); }*/
    public boolean setValorAdicional(String atributo, String valor) {
        int index = atributosAdicionales.indexOf(atributo);
        if (index == -1) {
            return false;
        }
        valoresAdicionales.set(index, valor.replace("\\\"", ""));
        return true;
    }

    @Override
    public void crearPersona() {
        String valores = AtributoAdicionalEditor.convertirArrayListAJSON(atributosAdicionales, valoresAdicionales);
        estudiante.setAtributos_adicionales(valores);
        EstudianteJpaController controller = new EstudianteJpaController(emf);
        if (estudiante.getId() == null) {
            controller.create(estudiante);
        } else {
            try {
                controller.edit(estudiante);
            } catch (Exception ex) {
                Logger.getLogger(PersonaEstudianteCreador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}