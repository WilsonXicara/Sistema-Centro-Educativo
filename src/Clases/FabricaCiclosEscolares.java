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
public class FabricaCiclosEscolares implements CiclosEscolares {
    private static final int TIPO_CICLO_ESCOLAR_ANUAL = 0;
    private static final int TIPO_CICLO_ESCOLAR_SEMESTRAL = 1;
    private static final int TIPO_CICLO_ESCOLAR_TRIMESTRAL = 2;

    @Override
    public CicloEscolar crearCicloEscolar(int tipoCicloEscolar) {
        switch(tipoCicloEscolar) {
            case TIPO_CICLO_ESCOLAR_ANUAL:
                return new CicloEscolarAnual();
            case TIPO_CICLO_ESCOLAR_SEMESTRAL:
                return new CicloEscolarSemestral();
            case TIPO_CICLO_ESCOLAR_TRIMESTRAL:
                return new CicloEscolarTrimestral();
        }
        return null;
    }
    
}
