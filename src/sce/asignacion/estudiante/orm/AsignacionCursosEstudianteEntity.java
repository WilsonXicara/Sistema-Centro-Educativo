/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante.orm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity(name = AsignacionCursosEstudianteEntity.tableName)
@NamedQueries({
    @NamedQuery(name="AsignacionCursosEstudiante.buscarCursoAsignado", query="SELECT aec FROM "+AsignacionCursosEstudianteEntity.tableName+" AS aec WHERE aec.asignacion_estudiante_id = :idAsignacionEst AND aec.asignacion_curso_id = :idCurso")
})
@Table(name = AsignacionCursosEstudianteEntity.tableName)
public class AsignacionCursosEstudianteEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_estudiante_cursos";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_estudiante_id, asignacion_curso_id;
    private Float acumulado = 0f;
    private Boolean anulado = false;
    private String razon_anulacion;

    public Long getId() { return id; }
    public Long getAsignacion_estudiante_id() { return asignacion_estudiante_id; }
    public Long getAsignacion_curso_id() { return asignacion_curso_id; }
    public Float getAcumulado() { return acumulado; }
    public Boolean getAnulado() { return anulado; }
    public String getRazon_anulacion() { return razon_anulacion; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_estudiante_id(Long asignacion_estudiante_id) { this.asignacion_estudiante_id = asignacion_estudiante_id; }
    public void setAsignacion_curso_id(Long asignacion_curso_id) { this.asignacion_curso_id = asignacion_curso_id; }
    public void setAcumulado(Float acumulado) { this.acumulado = acumulado; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }
    public void setRazon_anulacion(String razon_anulacion) { this.razon_anulacion = razon_anulacion; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionCursosEstudianteEntity)) {
            return false;
        }
        AsignacionCursosEstudianteEntity other = (AsignacionCursosEstudianteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.AsignacionCursosEstudianteEntity{" + "id=" + id + ", asignacion_estudiante_id=" + asignacion_estudiante_id + ", curso_id=" + asignacion_curso_id + ", acumulado=" + acumulado + ", anulado=" + anulado + ", razon_anulacion=" + razon_anulacion + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionCursosEstudianteEntity) {
            AsignacionCursosEstudianteEntity aux = (AsignacionCursosEstudianteEntity)object;
            this.id = aux.id;
            this.asignacion_estudiante_id = aux.asignacion_estudiante_id;
            this.asignacion_curso_id = aux.asignacion_curso_id;
            this.acumulado = aux.acumulado;
            this.anulado = aux.anulado;
            this.razon_anulacion = aux.razon_anulacion;
        }
    }
}