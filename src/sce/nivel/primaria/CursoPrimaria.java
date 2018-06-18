/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import sce.principal.Curso;
import sce.principal.ElementoEducativo;

/**
 *
 * @author juan_
 */
public class CursoPrimaria implements Curso,ElementoEducativo{

    public CursoPrimaria() {
        System.out.println("CursoPrimaria->()");
    }
    
    private String nombre;
    private int duracion;

    @Override
    public void consultarCatedratico() {
        System.out.println("CursoPrimaria->consultarCatedratico()");
    }

    @Override
    public void consultarParticipantes() {
        System.out.println("CursoPrimaria->consultarParticipantes()");
    }

    @Override
    public void consultarDistribucionNotas() {
        System.out.println("CursoPrimaria->consultarDistribucionNotas()");
    }
    
    public Boolean setAtributo(int tipo, Object valor){
        return true;
};
    
}
