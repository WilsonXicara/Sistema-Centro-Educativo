/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.curso.orm;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import sce.principal.elemento_asignatura.ElementoAsignaturaEntity;

/**
 *
 * @author Usuario
 */
@Entity(name=CursoEntity.tableName)
@NamedQueries({
    @NamedQuery(name="Curso.buscarCursoPorNombre", query="SELECT c FROM "+CursoEntity.tableName+" AS c WHERE c.curso = :nombreCurso")
})
@Table(name = CursoEntity.tableName)
public class CursoEntity implements Serializable, ElementoAsignaturaEntity {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "curso";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String curso, descripcion;

    public CursoEntity() {}
    public CursoEntity(String curso, String descripcion) {
        this.curso = curso;
        this.descripcion = descripcion;
    }
    public Long getId() { return id; }
    public String getCurso() { return curso; }
    public String getDescripcion() { return descripcion; }
    public void setId(Long id) { this.id = id; }
    public void setCurso(String curso) { this.curso = curso; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoEntity)) {
            return false;
        }
        CursoEntity other = (CursoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.CursoEntity{" + "id=" + id + ", curso=" + curso + ", descripcion=" + descripcion + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof CursoEntity) {
            CursoEntity aux = (CursoEntity)object;
            this.id = aux.id;
            this.curso = aux.curso;
            this.descripcion = aux.descripcion;
        }
    }
}