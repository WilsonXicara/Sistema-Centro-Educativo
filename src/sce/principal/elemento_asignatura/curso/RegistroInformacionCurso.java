/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.curso;

/**
 *
 * @author Usuario
 */
public class RegistroInformacionCurso {
    private Long idCurso;
    private String nombreCurso, descripcionCurso;
    
    public RegistroInformacionCurso() {}

    public Long getIdCurso() { return idCurso; }
    public String getNombreCurso() { return nombreCurso; }
    public String getDescripcionCurso() { return descripcionCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }
    public void setDescripcionCurso(String descripcionCurso) { this.descripcionCurso = descripcionCurso; }
}
