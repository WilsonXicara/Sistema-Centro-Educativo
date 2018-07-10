/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.persona.orm.AtributosAdicionalesEntity;
import sce.persona.orm.TablaExtendidaEntity;
import sce.persona.orm.AtributosAdicionalesJpaController;
import sce.persona.orm.TablaExtendidaJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 * LISTO!!!
 * Inicializa una instancia para editar los Atributos Adicionales de un registro en la Base de datos que lo soporte.
     * Los Atributos adicionales son la alternativa a la creación de columnas para registrar nuevos atributos en
     * una tabla, por lo que generalmente se utiliza para las tablas con información de personas.
     * Por ejemplo, puede considerarse una tabla_estudiante con campos (nombre, apellidos); si se desea registrar,
     * además de lo anterior, dirección y edad se crean los 'Atributos adicionales' para 'tabla_estudiante'
     * (direccion, edad) pero sin crear estas dos colunas en la tabla_estudiante. Por lo tanto, cuando se crea o
     * edite un registro estudiante se guardará en una columna de la trabla (como tabla_estudiante.atributos_adicionales)
     * los nuevos atributos en un formato JSON. Dicho atributo tendrá: {"direccion":"Dirección del estudiante",
     * "edad":"Edad del estudiante"}.
     * Los cambios en los atributos adicionales de un registro se reflejarán hasta que se edite dicho registro, ya
     * que es demasiado costoso revisar todos los registros, convertir de formato JSON a uno manejable, agregar o
     * eliminar atributos, y volver a guardar en formato JSON.
     * Se implementa un estilo propio para manejar las Transacciones.
 * @author Usuario
 */
public class AtributoAdicionalEditor {
    private final EntityManagerFactory emf;
    private final TablaExtendidaEntity tablaExtendida;
    private final ArrayList<AtributosAdicionalesEntity> listaAtributos, listaAtributosEliminados;
    
    /**
     * Inicializa una nueva instancia para manejar los Atributos adicionales de la tabla en la Base de datos con
     * nombre 'nombreTabla'. Si aún no existe un registro asociado con nombreTabla, se creará; si existe, se extrae
     * y se cargan los Atributos adicionales que tiene asignado.
     * @param emf
     * @param nombreTabla el nombre de la tabla en la Base de datos al que se le relacionan los Atributos adicionales.
     * @throws ExcepcionParametrosIncompletos se lanza si la conexión es nula o está cerrada, o si no se especifica un
     * valor para 'nombreTabla'
     */
    public AtributoAdicionalEditor(EntityManagerFactory emf, String nombreTabla)
            throws ExcepcionParametrosIncompletos {
        this.emf = emf;
        this.tablaExtendida = new TablaExtendidaEntity();
        if (nombreTabla == null) {
            throw new ExcepcionParametrosIncompletos("El nombre de la tabla no puede ser nulo");
        } if (nombreTabla.length() == 0) {
            throw new ExcepcionParametrosIncompletos("No puede existir una tabla sin nombre");
        } if (emf == null) {
            throw new ExcepcionParametrosIncompletos("No se ha proporcionado una conexión válida");
        } if (!emf.isOpen()) {
            throw new ExcepcionParametrosIncompletos("La conexión ya ha sido cerrada");
        }
        this.listaAtributos = new ArrayList<>();
        this.listaAtributosEliminados = new ArrayList<>();
        // Se obtiene el registro de la tabla
        TablaExtendidaEntity auxTabla = new TablaExtendidaJpaController(emf).buscarTablaExtendida(nombreTabla);
        if (auxTabla == null) {
            this.tablaExtendida.setNombre_tabla(nombreTabla);
            return;
        } else {
            this.tablaExtendida.copy(auxTabla);
        }
        // Se obtienen los atributos ya existentes para la tabla actual
        List<AtributosAdicionalesEntity> atributos = new AtributosAdicionalesJpaController(emf)
                .buscarAtributosParaTabla(tablaExtendida.getId());
        for (AtributosAdicionalesEntity atributo : atributos) {
            listaAtributos.add(atributo);
        }
    }
    /**
     * Agrega un nuevo Atributo adicional a la lista. Valida que éste no se repita con uno ya existene.
     * @param atributo nombre del nuevo Atributo adicional a agregar.
     * @return true si el atributo no existe y se agrega; false si ya existe
     */
    public boolean agregarAtributo(String atributo) {
        // Verifico si el nuevo atributo es uno ya eliminado
        int cantidad = listaAtributosEliminados.size(), index;
        for(index=0; index<cantidad; index++) {
            if (listaAtributosEliminados.get(index).getNombre_atributo().equals(atributo)) {
                listaAtributos.add(listaAtributosEliminados.remove(index));
                return true;
            }
        }
        // Verifico si el nuevo atributo ya existe
        cantidad = listaAtributos.size();
        for(index=0; index<cantidad; index++) {
            if (listaAtributos.get(index).getNombre_atributo().equals(atributo)) {
                return false;
            }
        }
        // No existe. Se agrega como nuevo
        AtributosAdicionalesEntity nuevo = new AtributosAdicionalesEntity();
        nuevo.setNombre_atributo(atributo);
        nuevo.setTabla_extendida_id(tablaExtendida.getId());
        listaAtributos.add(nuevo);
        return true;
    }
    /**
     * Elimina un Atributo adicional de la lista, siempre y cuando exista en la misma.
     * @param atributo nombre del Atributo adicional que se eliminará.
     * @return true si el atributo existe y es eliminado; false si no existe.
     */
    public boolean eliminarAtributo(String atributo) {
        // Verifico que el atributo a eliminar exista
        int cantidad = listaAtributos.size(), index;
        for(index=0; index<cantidad; index++) {
            if (listaAtributos.get(index).getNombre_atributo().equals(atributo)) {
                listaAtributosEliminados.add(listaAtributos.remove(index));
                return true;
            }
        }
        return false;
    }
    /**
     * Guarda los cambios de los Atributos adicionales relacionados a la tabla especificada en el constructor.
     * Si aún no existe el nombre de la tabla registrada, se crea, al igual con los nuevos atributos relacionados.
     * Se borran los Atributos adicionales de la Base de datos que hayan sido eliminados en esta instancia.
     * Tomar en cuenta que esta función no evalúa si existe una tabla llamada 'nombreTabla' en la Base de datos, ya
     * que sólo evalúa que se haya especificado un nombre.
     * @throws NonexistentEntityException si ocurre un error al intentar agregar o eliminar algún Atributo adicional
     * a la Base de datos.
     */
    public void guardarCambios() throws NonexistentEntityException {
        TablaExtendidaJpaController controller = new TablaExtendidaJpaController(emf);
        try {
            // Se crear el registro en tabla_extendida (si aún no existe)
            if (tablaExtendida.getId() == null) {
                controller.create(tablaExtendida);
            }
            // Eliminación de la BD de los registros eliminados
            eliminarListaAtributosAdicionales();
            // Creación de los registros aún no existentes
            guardarListaAtributosAdicionales();
        } catch (NonexistentEntityException ex) {
            if (tablaExtendida.getId() != null) {
                controller.destroy(tablaExtendida.getId());
            }
            throw new NonexistentEntityException(ex.getMessage());
        }
            
    }
    /**
     * Auxiliar para guardar los cambios. Intenta borrar de la BD los Atributos adicionales eliminados en esta instancia.
     * @throws NonexistentEntityException se lanza ocurre un error al intentar borrar un registro de la BD.
     */
    private void eliminarListaAtributosAdicionales() throws NonexistentEntityException {
        // Eliminación de los atributos eliminados
        AtributosAdicionalesJpaController controller = new AtributosAdicionalesJpaController(emf);
        int cantidad = listaAtributosEliminados.size(), indexError = 0;
        Long idError = 0l;
        try {
            for(indexError=0; indexError<cantidad; indexError++) {
                AtributosAdicionalesEntity eliminado = listaAtributosEliminados.get(indexError);
                idError = eliminado.getId();
                if (idError != null) {
                    controller.destroy(idError);
                }
            }
        } catch (NonexistentEntityException ex) {
            // Haciendo un "ROLLBACK" propio
            for(int index=0; index<indexError; index++) {
                if (listaAtributosEliminados.get(index).getId() == null) {
                    controller.create(listaAtributosEliminados.get(index));
                }
            }
            throw new NonexistentEntityException("No existe un Atributo Adicional con id="+idError);
        }
    }
    /**
     * Auxiliar para guardar los cambios. Intenta escribir en la BD los nuevos Atributos adicionales.
     * @throws NonexistentEntityException  se lanza si ocurre un error al intentar escribir un registro en la BD.
     */
    private void guardarListaAtributosAdicionales() throws NonexistentEntityException {
        // Creación de los registros aún no existentes
        AtributosAdicionalesJpaController controller = new AtributosAdicionalesJpaController(emf);
        int cantidad = listaAtributos.size(), indexError=0;
        Long idError = 0l;
        try {
            for(indexError=0; indexError<cantidad; indexError++) {
                AtributosAdicionalesEntity nuevo = listaAtributos.get(indexError);
                nuevo.setTabla_extendida_id(tablaExtendida.getId());
                if (nuevo.getNombre_atributo().equals("sexo")) {
                    nuevo.setTabla_extendida_id(null);
                }
                System.out.println("guardando "+nuevo);
                idError = nuevo.getId();
                if (idError == null) {
                    controller.create(nuevo);
                }
            }
        } catch (Exception ex) {
            // Haciendo un "ROLLBACK" propio
            for(int index=0; index<indexError; index++) {
                if (listaAtributos.get(index).getId() != null) {
                    controller.destroy(listaAtributos.get(index).getId());
                }
            }
            throw new NonexistentEntityException("Ocurrió un error con Atributo Adicional con id="+idError);
        }
    }
    
    public static ArrayList<String> obtenerListaAtributos(EntityManagerFactory emf, String nombreTabla) {
        ArrayList<String> lista = new ArrayList<>();
        TablaExtendidaEntity tablaExtendida = new TablaExtendidaJpaController(emf).buscarTablaExtendida(nombreTabla);
        if (tablaExtendida == null) {
            return lista;
        }
        // Se obtienen los atributos ya existentes para la tabla actual
        List<AtributosAdicionalesEntity> atributos = new AtributosAdicionalesJpaController(emf).buscarAtributosParaTabla(tablaExtendida.getId());
        for (AtributosAdicionalesEntity atributo : atributos) {
            lista.add(atributo.getNombre_atributo());
        }
        return lista;
    }
    public static String convertirArrayListAJSON(ArrayList<String> listaAtributos, ArrayList<String> listaValores) {
        JsonObject toJson = new JsonObject();
        int cantidad = listaAtributos.size(), index;
        while (listaValores.size() < cantidad) {
            listaValores.add("");
        }
        for(index=0; index<cantidad; index++) {
            toJson.addProperty(listaAtributos.get(index), listaValores.get(index));
        }
        return new Gson().toJson(toJson);
    }
    public static ArrayList<String> convertirJSONAArrayList(ArrayList<String> listaAtributos, String cadenaJSON) {
        ArrayList<String> listaValores = new ArrayList<>();
        JsonObject jsonObject = new JsonParser().parse(cadenaJSON).getAsJsonObject();
        for (String atributo : listaAtributos) {
            JsonElement valor = jsonObject.get(atributo);
            listaValores.add(valor == null ? "" : valor.toString().replace("\"", ""));
        }
        return listaValores;
    }
}