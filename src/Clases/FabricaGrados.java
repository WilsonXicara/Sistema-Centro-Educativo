/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class FabricaGrados implements Grados {
    public static final int TIPO_GRADO_PRIMARIA = 0;
    public static final int TIPO_GRADO_BASICO = 1;
    public static final int TIPO_GRADO_DIVERSIFICADO = 2;

    @Override
    public Grado crearGrado(int tipoGrado) {
        switch(tipoGrado) {
            case TIPO_GRADO_PRIMARIA:
                return new GradoPrimaria();
            case TIPO_GRADO_BASICO:
                return new GradoBasico();
            case TIPO_GRADO_DIVERSIFICADO:
                return new GradoDiversificado();
        }
        return null;
    }
    
}
