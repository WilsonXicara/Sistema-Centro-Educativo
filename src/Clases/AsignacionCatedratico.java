/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class AsignacionCatedratico implements Asignacion {
    // Atributos propios del objeto
    private CicloEscolar cicloEscolar;
    private Catedratico catedratico;
    private ArrayList<Grado> grados;
    private ArrayList<Curso> cursos;
    
    public AsignacionCatedratico() {}
    public AsignacionCatedratico(CicloEscolar cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    @Override
    public boolean crearAsignacion() {
        // CÓDIGO PARA CREAR LA ASIGNACIÓN DE UN CATEDRÁTICO
        return true;
    }

    @Override
    public boolean setAtributo(int tipoAtributo, Object valor) {
        // CÓDIGO PARA SETEAR UN ATRIBUTO DE UN CATEDRÁTICO
        return true;
    }
    
}
