/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import sce.principal.Curso;
import sce.principal.ElementoEducativo;

/**
 *
 * @author Usuario
 */
public class CursoBasico implements ElementoEducativo, Curso {
    
    public CursoBasico() {
        System.out.println("CursoBasico->()");
    }

    @Override
    public void consultarCatedratico() {
        System.out.println("CursoBasico->consultarCatedratico()");
    }

    @Override
    public void consultarParticipantes() {
        System.out.println("CursoBasico->consultarParticipantes()");
    }

    @Override
    public void consultarDistribucionNotas() {
        System.out.println("CursoBasico->consultarDistribucionNotas()");
    }
    
}
