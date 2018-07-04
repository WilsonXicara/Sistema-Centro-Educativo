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
import sce.persona.orm.AtributosAdicionalesEntity;
import sce.persona.orm.TablaExtendidaEntity;
import sce.persona.orm.AtributosAdicionalesJpaController;
import sce.persona.orm.TablaExtendidaJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AtributoAdicionalEditor {
    private final EntityManagerFactory emf;
    private final TablaExtendidaEntity tablaExtendida;
    private final ArrayList<AtributosAdicionalesEntity> listaAtributos, listaAtributosEliminados;
    
    public AtributoAdicionalEditor(EntityManagerFactory emf, String nombreTabla) {
        this.emf = emf;
        this.tablaExtendida = new TablaExtendidaEntity();
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
    public boolean agregarAtributo(String atributo) {
        // Verifico si el nuevo atributo es uno ya eliminado
        int cantidad = listaAtributosEliminados.size(), index;
        for(index=0; index<cantidad; index++) {
            if (listaAtributosEliminados.get(index).getNombre_atributo().equals(atributo)) {
                listaAtributos.add(listaAtributosEliminados.remove(index));
                System.out.println("+"+atributo+": true; Elim; size=["+listaAtributos.size()+","+listaAtributosEliminados.size()+"]");
                return true;
            }
        }
        // Verifico si el nuevo atributo ya existe
        cantidad = listaAtributos.size();
        for(index=0; index<cantidad; index++) {
            if (listaAtributos.get(index).getNombre_atributo().equals(atributo)) {
                System.out.println("+"+atributo+": false; Exist; size=["+listaAtributos.size()+","+listaAtributosEliminados.size()+"]");
                return false;
            }
        }
        // No existe. Se agrega como nuevo
        AtributosAdicionalesEntity nuevo = new AtributosAdicionalesEntity();
        nuevo.setNombre_atributo(atributo);
        nuevo.setTabla_extendida_id(tablaExtendida.getId());
        listaAtributos.add(nuevo);
        System.out.println("+"+atributo+": true; size=["+listaAtributos.size()+","+listaAtributosEliminados.size()+"]");
        return true;
    }
    public boolean editarAtributo(String nombreAnterior, String nombreNuevo) {
        // Verifico que el atributo a eliminar exista
        int cantidad = listaAtributos.size(), index;
        for(index=0; index<cantidad; index++) {
            if (listaAtributos.get(index).getNombre_atributo().equals(nombreAnterior)) {
                listaAtributos.get(index).setNombre_atributo(nombreNuevo);
                System.out.println("*"+nombreAnterior+": true; size=["+listaAtributos.size()+","+listaAtributosEliminados.size()+"]");
                return true;
            }
        }
        System.out.println("*"+nombreAnterior+": true; size=["+listaAtributos.size()+","+listaAtributosEliminados.size()+"]");
        return false;
    }
    public boolean eliminarAtributo(String atributo) {
        // Verifico que el atributo a eliminar exista
        int cantidad = listaAtributos.size(), index;
        for(index=0; index<cantidad; index++) {
            if (listaAtributos.get(index).getNombre_atributo().equals(atributo)) {
                listaAtributosEliminados.add(listaAtributos.remove(index));
                System.out.println("-"+atributo+": true; size=["+listaAtributos.size()+","+listaAtributosEliminados.size()+"]");
                return true;
            }
        }
        System.out.println("-"+atributo+": false; NoExist; size=["+listaAtributos.size()+","+listaAtributosEliminados.size()+"]");
        return false;
    }
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
    private void eliminarListaAtributosAdicionales() throws NonexistentEntityException {
        // Eliminación de los atributos eliminados
        AtributosAdicionalesJpaController controller = new AtributosAdicionalesJpaController(emf);
        int cantidad = listaAtributosEliminados.size(), indexError = 0;
        Long idError = 0l;
        System.out.println("SE ELIMINARÀN "+cantidad+" REGISTROS");
        try {
            for(indexError=0; indexError<cantidad; indexError++) {
                AtributosAdicionalesEntity eliminado = listaAtributosEliminados.get(indexError);
                System.out.println("eliminando "+eliminado);
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