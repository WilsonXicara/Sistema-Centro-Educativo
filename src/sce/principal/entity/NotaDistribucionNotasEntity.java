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
import sce.principal.RegistroEntity;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = NotaDistribucionNotasEntity.tableName)
public class NotaDistribucionNotasEntity implements Serializable, RegistroEntity {

    private static final long serialVersionUID = 1L;
    public static final String tableName = "nota_distribucion_notas";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long distribucion_notas_id;
    private Float acumulado;

    public Long getId() {
        return id;
    }

    public Long getDistribucion_notas_id() {
        return distribucion_notas_id;
    }

    public Float getAcumulado() {
        return acumulado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDistribucion_notas_id(Long distribucion_notas_id) {
        this.distribucion_notas_id = distribucion_notas_id;
    }

    public void setAcumulado(Float acumulado) {
        this.acumulado = acumulado;
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
        if (!(object instanceof NotaDistribucionNotasEntity)) {
            return false;
        }
        NotaDistribucionNotasEntity other = (NotaDistribucionNotasEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sce.principal.entity.NotaDistribucionNotasEntity[ id=" + id + " ]";
    }

    @Override
    public boolean yaExiste() {
        return id != null;
    }
    
}
