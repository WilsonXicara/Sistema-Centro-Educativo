/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.persona.catedratico.orm.CatedraticoJpaController;

/**
 *
 * @author juan_
 */
public class BuscadorCatedratico {
    private final EntityManagerFactory emf;

    public BuscadorCatedratico(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public List<CatedraticoEntity> obtenerCatedraticos() {
        return (List<CatedraticoEntity>)new CatedraticoJpaController(emf).findCatedraticoEntityEntities();
    }
    
    public CatedraticoEntity obtenerCatedratico(String dpi){
        return (CatedraticoEntity)new CatedraticoJpaController(emf).buscarPorDpi(dpi);
    }
    
}
