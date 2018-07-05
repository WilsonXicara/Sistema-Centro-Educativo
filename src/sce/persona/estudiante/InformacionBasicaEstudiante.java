/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.estudiante;

import sce.persona.estudiante.orm.EstudianteEntity;

/**
 * Registro no editable con la información básica de un Estudiante.
 * @author Usuario
 */
public class InformacionBasicaEstudiante {
    private final Long idEstudiante;
    private final String cui, nombres, apellidos, razonAnulacion;
    private final boolean anulado;
    public InformacionBasicaEstudiante(EstudianteEntity informacion) {
        this.idEstudiante = informacion.getId();
        this.cui = informacion.getCui();
        this.nombres = informacion.getNombres();
        this.apellidos = informacion.getApellidos();
        this.anulado = informacion.getAnulado();
        this.razonAnulacion = informacion.getRazon_anulacion();
    }
    public Long getIdEstudiante() { return idEstudiante; }
    public String getCui() { return cui; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public boolean isAnulado() { return anulado; }
    public String getRazonAnulacion() { return razonAnulacion; }
}
