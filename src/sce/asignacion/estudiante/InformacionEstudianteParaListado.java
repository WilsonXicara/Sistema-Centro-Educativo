/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

/**
 *
 * @author Usuario
 */
public class InformacionEstudianteParaListado {
    private final Long idEstudiante;
    private final String nombres, apellidos;

    public InformacionEstudianteParaListado(Long idEstudiante, String nombres, String apellidos) {
        this.idEstudiante = idEstudiante;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Long getIdEstudiante() { return idEstudiante; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
}