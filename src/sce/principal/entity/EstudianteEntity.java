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
import javax.persistence.Table;
import sce.principal.Persona;

/**
 *
 * @author Usuario
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Estudiante.buscarPorCui", query="SELECT COUNT(e.cui) FROM Estudiante AS e WHERE e.cui = :estudianteCui"),
    @NamedQuery(name="Estudiante.buscarPorAsignacionId", query="SELECT e FROM Estudiante AS e WHERE e.asignacion_id = :asignacionId")
})
@Table(name = EstudianteEntity.tableName)
public class EstudianteEntity implements Serializable, Persona {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "estudiante";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cui, nombres, apellidos, direccion;
    private Long asignacion_id=0l;

    public Long getId() {
        return id;
    }

    public String getCui() {
        return cui;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public Long getAsignacion_id() {
        return asignacion_id;
    }

    @Override
    public boolean yaExiste() {
        return id != null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAsignacion_id(Long asignacion_id) {
        this.asignacion_id = asignacion_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteEntity)) {
            return false;
        }
        EstudianteEntity other = (EstudianteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "orm.Estudiante[ id=" + id + ", nombres = "+nombres+" ]";
    }
}
