/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.elemento_asignatura.grado.orm;

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
@Entity(name = GradoEntity.tableName)
@NamedQueries({
    @NamedQuery(name="Grado.buscarPorGradoSeccion", query="SELECT g FROM "+GradoEntity.tableName+" AS g WHERE g.grado = :nombreGrado AND g.seccion = :nombreSeccion")
})
@Table(name = GradoEntity.tableName)
public class GradoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String tableName = "grado";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grado, seccion;
    private Integer capacidad;
    private Long prerequisito_id, asignacion_id=0l;

    public Long getId() { return id; }
    public String getGrado() { return grado; }
    public String getSeccion() { return seccion; }
    public Integer getCapacidad() { return capacidad; }
    public Long getPrerequisito_id() { return prerequisito_id; }
    public Long getAsignacion_id() { return asignacion_id; }
    public void setId(Long id) { this.id = id; }
    public void setGrado(String grado) { this.grado = grado; }
    public void setSeccion(String seccion) { this.seccion = seccion; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public void setPrerequisito_id(Long prerequisito_id) { this.prerequisito_id = prerequisito_id; }
    public void setAsignacion_id(Long asignacion_id) { this.asignacion_id = asignacion_id; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradoEntity)) {
            return false;
        }
        GradoEntity other = (GradoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "sce.principal.entity.GradoEntity{" + "id=" + id + ", grado=" + grado + ", seccion=" + seccion + ", capacidad=" + capacidad + ", prerequisito_id=" + prerequisito_id + ", asignacion_id=" + asignacion_id + '}';
    }
    
    public void copy(Object object) {
        if (object instanceof GradoEntity) {
            GradoEntity aux = (GradoEntity)object;
            this.id = aux.id;
            this.grado = aux.grado;
            this.seccion = aux.seccion;
            this.capacidad = aux.capacidad;
            this.prerequisito_id = aux.prerequisito_id;
            this.asignacion_id = aux.asignacion_id;
        }
    }
}