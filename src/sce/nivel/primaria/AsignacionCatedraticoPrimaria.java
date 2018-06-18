/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import sce.principal.Asignacion;
import sce.principal.Catedratico;
import sce.principal.CicloEscolar;
import sce.principal.Grado;

/**
 *
 * @author juan_
 */
public class AsignacionCatedraticoPrimaria implements Asignacion{
    
    private CicloEscolar cicloEscolar;
    private Grado grado;
    private Catedratico catedratico;
    
    public AsignacionCatedraticoPrimaria() {
        System.out.println("AsignacionCatedraticoPrimaria->()");
    }
    
    public void setCicloEscolar(CicloEscolar ciclo){
       this.cicloEscolar = ciclo;
       System.out.println("AsignacionCatedraticoPrimaria->setCicloEscolar(ciclo)");
    };
    
    public void setGrado(Grado grado){
        this.grado = grado;
         System.out.println("AsignacionCatedraticoPrimaria->setGrado(grado)");
    };
    
    public void setCatedratico(Catedratico catedratico){
        this.catedratico = catedratico;
         System.out.println("AsignacionCatedraticoPrimaria->setCatdratico(catedratico)");
    };
    
    @Override
    public void crearAsignacion(){
         System.out.println("AsignacionCatedraticoPrimaria->crearAsignacion()");
    };
}
