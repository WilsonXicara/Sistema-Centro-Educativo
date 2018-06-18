/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;
import sce.principal.Catedratico;
import sce.principal.Persona;

/**
 *
 * @author juan_
 */
public class CatedraticoPrimaria implements Catedratico, Persona {
    
    @Override
    public void consultarCicloEscolar() {
        System.out.println("CatedraticoPrimaria->consultarCicloEscolar()");
    }

    @Override
    public void consultarGrados() {
        System.out.println("CatedraticoPrimaria->consultarGrados()");
    }

    @Override
    public void consultarCursos() {
        System.out.println("CatedraticoPrimaria->consultarCursos()");
    }
    
}
