/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico;

import javax.persistence.EntityManagerFactory;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.persona.catedratico.orm.CatedraticoJpaController;

/**
 *
 * @author juan_
 */
public class ConsultorCatedratico {
    public static Boolean existeCatedratico(Long idCatedratico, EntityManagerFactory emf){
        CatedraticoEntity catedratico = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
        return catedratico != null;
    }
    public static Boolean isCatedraticoAnulado(Long idCatedratico, EntityManagerFactory emf){
        if (existeCatedratico(idCatedratico,emf)){
            return true;
        }
        CatedraticoEntity catedratico = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
        return catedratico.getAnulado();
    } 
}
