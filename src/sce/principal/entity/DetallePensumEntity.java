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
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity(name = DetallePensumEntity.tableName)
@Table(name = DetallePensumEntity.tableName)
public class DetallePensumEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "detalle_pensum";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pensum_id, curso_id, grado_id, prerrequisito_curso_id;
    private Integer prerrequisito_creditos;

    public Long getId() { return id; }
    public Long getPensum_id() { return pensum_id; }
    public Long getCurso_id() { return curso_id; }
    public Long getGrado_id() { return grado_id; }
    public Long getPrerrequisito_curso_id() { return prerrequisito_curso_id; }
    public Integer getPrerrequisito_creditos() { return prerrequisito_creditos; }
    public void setId(Long id) { this.id = id; }
    public void setPensum_id(Long pensum_id) { this.pensum_id = pensum_id; }
    public void setCurso_id(Long curso_id) { this.curso_id = curso_id; }
    public void setGrado_id(Long grado_id) { this.grado_id = grado_id; }
    public void setPrerrequisito_curso_id(Long prerrequisito_curso_id) { this.prerrequisito_curso_id = prerrequisito_curso_id; }
    public void setPrerrequisito_creditos(Integer prerrequisito_creditos) { this.prerrequisito_creditos = prerrequisito_creditos; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePensumEntity)) {
            return false;
        }
        DetallePensumEntity other = (DetallePensumEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.DetallePensumEntity{" + "id=" + id + ", pensum_id=" + pensum_id + ", curso_id=" + curso_id + ", grado_id=" + grado_id + ", prerrequisito_curso_id=" + prerrequisito_curso_id + ", prerrequisito_creditos=" + prerrequisito_creditos + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof DetallePensumEntity) {
            DetallePensumEntity aux = (DetallePensumEntity)object;
            this.id = aux.id;
            this.pensum_id = aux.pensum_id;
            this.curso_id = aux.curso_id;
            this.grado_id = aux.grado_id;
            this.prerrequisito_curso_id = aux.prerrequisito_curso_id;
            this.prerrequisito_creditos = aux.prerrequisito_creditos;
        }
    }
}