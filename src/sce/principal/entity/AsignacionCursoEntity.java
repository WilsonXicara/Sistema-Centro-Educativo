/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;;

/**
 *
 * @author Usuario
 */
@Entity(name = AsignacionCursoEntity.tableName)
@NamedQueries({
    @NamedQuery(name="AsignacionCurso.obtenerCursosAsignadosAGrado", query="SELECT ac FROM "+AsignacionCursoEntity.tableName+" AS ac WHERE ac.ciclo_escolar_id = :idCicloEscolar AND ac.grado_id = :idGrado")
})
@Table(name = AsignacionCursoEntity.tableName)
public class AsignacionCursoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "asignacion_curso";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asignacion_carrera_id, grado_id, curso_id, distribucion_notas_id;

    public Long getId() { return id; }
    public Long getAsignacion_carrera_id() { return asignacion_carrera_id; }
    public Long getGrado_id() { return grado_id; }
    public Long getCurso_id() { return curso_id; }
    public Long getDistribucion_notas_id() { return distribucion_notas_id; }
    public void setId(Long id) { this.id = id; }
    public void setAsignacion_carrera_id(Long asignacion_carrera_id) { this.asignacion_carrera_id = asignacion_carrera_id; }
    public void setGrado_id(Long grado_id) { this.grado_id = grado_id; }
    public void setCurso_id(Long curso_id) { this.curso_id = curso_id; }
    public void setDistribucion_notas_id(Long distribucion_notas_id) { this.distribucion_notas_id = distribucion_notas_id; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionCursoEntity)) {
            return false;
        }
        AsignacionCursoEntity other = (AsignacionCursoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.AsignacionCursoEntity{" + "id=" + id + ", asignacion_carrera_id=" + asignacion_carrera_id + ", grado_id=" + grado_id + ", curso_id=" + curso_id + ", distribucion_notas_id=" + distribucion_notas_id + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof AsignacionCursoEntity) {
            AsignacionCursoEntity aux = (AsignacionCursoEntity)object;
            this.id = aux.id;
            this.asignacion_carrera_id = aux.asignacion_carrera_id;
            this.grado_id = aux.grado_id;
            this.curso_id = aux.curso_id;
            this.distribucion_notas_id = aux.distribucion_notas_id;
        }
    }
}