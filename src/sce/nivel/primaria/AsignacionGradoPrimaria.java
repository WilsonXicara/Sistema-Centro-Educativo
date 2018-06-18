/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.nivel.primaria;

import java.util.ArrayList;
import sce.principal.Asignacion;
import sce.principal.CicloEscolar;
import sce.principal.Grado;

/**
 *
 * @author juan_
 */
public class AsignacionGradoPrimaria implements Asignacion {
    
    private CicloEscolar cicloEscolar;
    private ArrayList<Grado> grados;

    public AsignacionGradoPrimaria() {
        this.grados = new ArrayList();
        System.out.println("AsignacionGradoPrimaria->()");
    }

    public void setCicloEscolar(CicloEscolar ciclo){
        this.cicloEscolar = ciclo;
        System.out.println("AsignacionGradoPrimaria->setCicloEscolar(ciclo)");
    };
    
    public void addGrado(Grado grado){
        this.grados.add(grado);
        System.out.println("AsignacionGradoPrimaria->addGrado(grado)");
    };
    
    @Override
    public void crearAsignacion() {
    System.out.println("AsignacionGradoPrimaria->crearAsignacion()");
    }
    
    
}
