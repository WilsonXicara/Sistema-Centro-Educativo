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
@Entity
@Table(name = DistribucionNotasEntity.tableName)
public class DistribucionNotasEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "distribucion_notas";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float esperado;

    public Long getId() {
        return id;
    }

    public Float getEsperado() {
        return esperado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEsperado(Float esperado) {
        this.esperado = esperado;
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
        if (!(object instanceof DistribucionNotasEntity)) {
            return false;
        }
        DistribucionNotasEntity other = (DistribucionNotasEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.DistribucionNotasEntity[ id=" + id + " ]";
    }
    
}
