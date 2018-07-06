/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.estudiante;

import java.io.Serializable;
import javax.persistence.Column;
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
@Entity(name = NotaActividadEntity.tableName)
@NamedQueries({
    @NamedQuery(name="NotaActividad.buscarPorAsignacionEstudianteCurso", query="SELECT na FROM "+NotaActividadEntity.tableName+" AS na WHERE na.asignacion_estudiante_cursos_id = :idAsignacionEstudianteCurso"),
    @NamedQuery(name="NotaActividad.buscarPorActividad", query="SELECT na FROM "+NotaActividadEntity.tableName+" AS na WHERE na.actividad_id = :idActividad")
})
@Table(name = NotaActividadEntity.tableName)
public class NotaActividadEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "nota_actividad";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "asignacion_estudiante_cursos_id")
    private Long asignacion_estudiante_cursos_id;
    private Long actividad_id;
    private Float obtenido;

    public Long getId() { return id; }
    public Long getAsignacion_estudiante_cursos_id() { return asignacion_estudiante_cursos_id; }
    public Long getActividad_id() { return actividad_id; }
    public Float getObtenido() { return obtenido; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_estudiante_cursos_id(Long asignacion_estudiante_cursos_id) { this.asignacion_estudiante_cursos_id = asignacion_estudiante_cursos_id; }
    public void setActividad_id(Long actividad_id) { this.actividad_id = actividad_id; }
    public void setObtenido(Float obtenido) { this.obtenido = obtenido; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaActividadEntity)) {
            return false;
        }
        NotaActividadEntity other = (NotaActividadEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.NotaActividadEntity{" + "id=" + id + ", asignacion_estudiante_curso_id=" + asignacion_estudiante_cursos_id + ", actividad_id=" + actividad_id + ", obtenido=" + obtenido + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof NotaActividadEntity) {
            NotaActividadEntity aux = (NotaActividadEntity)object;
            this.id = aux.id;
            this.asignacion_estudiante_cursos_id = aux.asignacion_estudiante_cursos_id;
            this.actividad_id = aux.actividad_id;
            this.obtenido = aux.obtenido;
        }
    }
}