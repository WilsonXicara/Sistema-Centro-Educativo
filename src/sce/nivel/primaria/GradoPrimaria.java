/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import sce.principal.ElementoEducativo;
import sce.principal.Grado;

/**
 *
 * @author juan_
 */
public class GradoPrimaria implements Grado, ElementoEducativo {

    public GradoPrimaria() {
        System.out.println("GradoPrimaria->()");
    }
    
    @Override
    public void consultarCursos() {
        System.out.println("GradoPrimaria->consultarCursos()");
    }

    @Override
    public void consultarCatedratico() {
        System.out.println("GradoPrimaria->consularCatedratico()");
    }

    @Override
    public void consultarEstudiantes() {
        System.out.println("GradoPrimaria->consultarEstudiantes()");
    }
    
    public Boolean setAtributo(int tipo, Object valor){
        return true;
};
    
}
