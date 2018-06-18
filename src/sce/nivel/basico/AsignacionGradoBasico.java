/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.basico;

import java.util.ArrayList;
import sce.principal.Asignacion;
import sce.principal.CicloEscolar;
import sce.principal.Grado;

/**
 *
 * @author Usuario
 */
public class AsignacionGradoBasico implements Asignacion {
    private CicloEscolar ciclo;
    private ArrayList<Grado> grados;
    
    public AsignacionGradoBasico() {
        grados = new ArrayList<>();
        System.out.println("AsignacionGradoBasico->()");
    }
    
    public void setCicloEscolar(CicloEscolar ciclo) {
        this.ciclo = ciclo;
        System.out.println("AsignacionGradoBasico->setCicloEscolar(ciclo)");
    }
    public void addGrado(Grado grado) {
        grados.add(grado);
        System.out.println("AsignacionGradoBasico->addGrado(grado)");
    }

    @Override
    public void crearAsignacion() {
        System.out.println("AsignacionGradoBasico->crearAsignacion()");
    }
    
}
