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
@Entity(name = AsignacionEstudianteEntity.tableName)
@NamedQueries({
    @NamedQuery(name="AsignacionEstudiante.buscarPorCarrera", query="SELECT ae FROM "+AsignacionEstudianteEntity.tableName+" AS ae WHERE ae.asignacion_carrera_id = :idCarrera"),
    @NamedQuery(name="AsignacionEstudiante.buscarPorGrado", query="SELECT ae FROM "+AsignacionEstudianteEntity.tableName+" AS ae WHERE ae.asignacion_grado_id = :idGrado")
})
@Table(name = AsignacionEstudianteEntity.tableName)
public class AsignacionEstudianteEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_estudiante";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_carrera_id, estudiante_id, asignacion_grado_id;
    private Boolean anulado = false;
    private String razon_anulacion;

    public Long getId() { return id; }
    public Long getAsignacion_carrera_id() { return asignacion_carrera_id; }
    public Long getEstudiante_id() { return estudiante_id; }
    public Long getAsignacion_grado_id() { return asignacion_grado_id; }
    public Boolean getAnulado() { return anulado; }
    public String getRazon_anulacion() { return razon_anulacion; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_carrera_id(Long asignacion_carrera_id) { this.asignacion_carrera_id = asignacion_carrera_id; }
    public void setEstudiante_id(Long estudiante_id) { this.estudiante_id = estudiante_id; }
    public void setAsignacion_grado_id(Long asignacion_grado_id) { this.asignacion_grado_id = asignacion_grado_id; }
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
        if (!(object instanceof AsignacionEstudianteEntity)) {
            return false;
        }
        AsignacionEstudianteEntity other = (AsignacionEstudianteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.AsignacionEstudianteEntity{" + "id=" + id + ", asignacion_carrera_id=" + asignacion_carrera_id + ", estudiante_id=" + estudiante_id + ", grado_id=" + asignacion_grado_id + ", anulado=" + anulado + ", razon_anulacion=" + razon_anulacion + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionEstudianteEntity) {
            AsignacionEstudianteEntity aux = (AsignacionEstudianteEntity)object;
            this.id = aux.id;
            this.asignacion_carrera_id = aux.asignacion_carrera_id;
            this.estudiante_id = aux.estudiante_id;
            this.asignacion_grado_id = aux.asignacion_grado_id;
            this.anulado = aux.anulado;
            this.razon_anulacion = aux.razon_anulacion;
        }
    }
}
