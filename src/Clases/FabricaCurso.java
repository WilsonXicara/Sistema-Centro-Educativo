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
public class FabricaCurso implements Cursos {
    public static final int TIPO_CURSO_ANUAL = 0;
    public static final int TIPO_CURSO_SEMESTRAL = 1;
    public static final int TIPO_CURSO_TRIMESTRAL = 2;

    @Override
    public Curso crearCurso(int tipoCurso) {
        switch(tipoCurso) {
            case TIPO_CURSO_ANUAL:
                return new CursoAnual();
            case TIPO_CURSO_SEMESTRAL:
                return new CursoSemestral();
            case TIPO_CURSO_TRIMESTRAL:
                return new CursoTrimestral();
        }
        return null;
    }
    
}
