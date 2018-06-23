/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

//import sce.nivel.basico.NivelBasico;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sce.nivel.generico.ElementolEducativoFactory;
import sce.principal.Persona;
import sce.principal.command.EstudianteCommand;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.entity.CicloEscolarEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.ormjpa.CursoJpaController;
import sce.principal.ormjpa.EstudianteJpaController;
import sce.principal.ElementoAsignatura;
import sce.principal.ElementoEducativoSave;

/**
 *
 * @author Usuario
 */
public class Principal {
    //private static Connection conexion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // CREAR ESTUDIANTES
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        ElementolEducativoFactory director = new ElementolEducativoFactory(emf);
        ElementoEducativoSave guardador = new ElementoEducativoSave(emf);
        //Otro director = new Otro(emf);
        Persona encargado = director.obtenerPersona(Otro.TIPO_PERSONA_ENCARGADO);
        System.out.println("encargado = "+encargado);
        ElementoAsignatura elementoE = director.obtenerElementoAsignatura(Otro.TIPO_ELEMENTO_EDUCATIVO_GRADO);
        System.out.println("elemento = "+elementoE);
        EstudianteEntity estudiante = (EstudianteEntity)director.obtenerPersona(ElementolEducativoFactory.TIPO_PERSONA_ESTUDIANTE);
        // Llenado de estudiante
        estudiante.setCui("9234567890123");
        estudiante.setNombres("Óscar");
        estudiante.setApellidos("Juárez");
        estudiante.setDireccion("Xela");
        guardador.guardarPersona(estudiante);
        System.out.println("EXITO");
        /*AsignacionCatedraticoBasico asignadorCat = (AsignacionCatedraticoBasico) director.obtenerAsignacion(0);
        asignadorCat.guardarAsignacion(asignadorCat);
        director.guardarAsignacion(asignadorCat);*/
        // Ciclo escolar
        CicloEscolarEntity ciclo = (CicloEscolarEntity) director.obtenerElementoAsignatura(ElementolEducativoFactory.TIPO_ELEMENTO_EDUCATIVO_CICLO_ESCOLAR, 2l);
        System.out.println("ciclo="+ciclo);
        // CursoEntity
        CursoEntity curso = (CursoEntity)director.obtenerElementoAsignatura(ElementolEducativoFactory.TIPO_ELEMENTO_EDUCATIVO_CURSO);
        curso.setCurso("Matemáticas");
        curso.setDescripcion("Algo");
        guardador.guardarElementoAsignatura(curso);
        // Asignación curos
        AsignacionCursoEntity asignacionC = (AsignacionCursoEntity)director.obtenerAsignacion(ElementolEducativoFactory.TIPO_ASIGNACION_CURSO);
        asignacionC.setCiclo_escolar_id(ciclo.getId());
        asignacionC.setCurso_id(curso.getId());
        guardador.guardarAsignacion(asignacionC);
        EstudianteJpaController est = new EstudianteJpaController(emf);
        
        List<EstudianteEntity> lista = est.buscarPorAsignacionId(0l);
        for (EstudianteEntity estudiante1 : lista) {
            System.out.println("algo");
            System.out.println(estudiante1);
        }
        
        Otro2 otro2 = new Otro2(emf);
        otro2.create(estudiante);
    }
    public static class EstudiantePrimaria extends Estudiante {
        
        public EstudiantePrimaria(EntityManagerFactory emf) {
            super(emf);
        }
        @Override
        public void consultarCicloEscolar() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        public void otroMetodo() {
            
        }
    }
    public static class Estudiante implements EstudianteCommand {
        EntityManagerFactory emf;
        EstudianteEntity estudiante;
        String numeroTelefono = "";
        
        public Estudiante(EntityManagerFactory emf) {
            this.emf = emf;
        }
        public void setId(int id) {
            this.estudiante.setId((long)id);
        }
        public void consultarCursos() {
            CursoJpaController cursos = new CursoJpaController(emf);
            cursos.findCursoEntity(estudiante.getId());
        }
        public void guardar() {
            String aux = "";
            aux = "{Telefono:"+numeroTelefono+"}";
        }

        @Override
        public void consultarCicloEscolar() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void consultarGrado() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    public static class Encargado implements Persona {
    }
    public static class Otro extends ElementolEducativoFactory {
        public static final int TIPO_PERSONA_ENCARGADO = 100;
        public Otro(EntityManagerFactory emf) {
            super(emf);
        }
        @Override
        public Persona obtenerPersona(int tipoPersona, Long ID) {
            switch(tipoPersona) {
                case ElementolEducativoFactory.TIPO_PERSONA_ESTUDIANTE:
                case ElementolEducativoFactory.TIPO_PERSONA_CATEDRATICO:
                    return super.obtenerPersona(tipoPersona, ID);
                case TIPO_PERSONA_ENCARGADO:
                    return new Encargado();
            }
            return null;
        }
    }
    public static class Otro2 extends EstudianteJpaController {
        
        public Otro2(EntityManagerFactory emf) {
            super(emf);
        }
        
    }
}
