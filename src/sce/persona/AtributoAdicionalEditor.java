/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.principal.entity.AtributosAdicionalesEntity;
import sce.principal.entity.TablaExtendidaEntity;
import sce.principal.ormjpa.AtributosAdicionalesJpaController;
import sce.principal.ormjpa.TablaExtendidaJpaController;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AtributoAdicionalEditor {
    private final EntityManagerFactory emf;
    private final String nombreTabla;
    private final TablaExtendidaEntity tablaExtendida;
    private final ArrayList<AtributosAdicionalesEntity> listaAtributos, listaAtributosEliminados;
    public AtributoAdicionalEditor(EntityManagerFactory emf, String nombreTabla) {
        this.emf = emf;
        this.nombreTabla = nombreTabla;
        // Se obtiene el registro de la tabla
        TablaExtendidaEntity auxTabla = new TablaExtendidaJpaController(emf).buscarTabla(nombreTabla);
        this.tablaExtendida = new TablaExtendidaEntity();
        if (auxTabla == null) {
            this.tablaExtendida.setNombre_tabla(nombreTabla);
            new TablaExtendidaJpaController(emf).create(tablaExtendida);
        } else {
            this.tablaExtendida.copy(auxTabla);
        }
        // Se obtienen los atributos ya existentes para la tabla actual
        List<AtributosAdicionalesEntity> atributos = new AtributosAdicionalesJpaController(emf).buscarAtributosParaTabla(tablaExtendida.getId());
        this.listaAtributos = new ArrayList<>();
        this.listaAtributosEliminados = new ArrayList<>();
        for (AtributosAdicionalesEntity atributo : atributos) {
            listaAtributos.add(atributo);
        }
    }
    public boolean agregarAtributo(String atributo) {
        AtributosAdicionalesEntity nuevo = new AtributosAdicionalesEntity();
        nuevo.setNombre_atributo(atributo);
        int index = listaAtributosEliminados.indexOf(nuevo);
        if (index != -1) {
            listaAtributos.add(listaAtributosEliminados.remove(index));
            return true;
        }
        if (listaAtributos.contains(nuevo)) {
            return false;
        }
        listaAtributos.add(nuevo);
        return true;
    }
    public boolean eliminarAtributo(String atributo) {
        AtributosAdicionalesEntity nuevo = new AtributosAdicionalesEntity();
        nuevo.setNombre_atributo(atributo);
        int index = listaAtributos.indexOf(nuevo);
        if (index == -1) {
            return false;
        }
        listaAtributosEliminados.add(listaAtributos.remove(index));
        return true;
    }
    public void guardarAtributos(EntityManagerFactory emf) {
        // Se crear el registro en tabla_extendida si aún no existe
        if (tablaExtendida.getId() == null) {
            new TablaExtendidaJpaController(emf).create(tablaExtendida);
        }
        AtributosAdicionalesJpaController controller = new AtributosAdicionalesJpaController(emf);
        // Eliminación de atributos eliminados
        for (AtributosAdicionalesEntity eliminado : listaAtributosEliminados) {
            try {
                controller.destroy(eliminado.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(AtributoAdicionalEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Creación de los registros aún no existentes
        for (AtributosAdicionalesEntity nuevo : listaAtributos) {
            if (nuevo.getId() == null) {
                nuevo.setTabla_extendida_id(tablaExtendida.getId());
                controller.create(nuevo);
            }
        }
    }
    
    public static ArrayList<String> obtenerListaAtributos(EntityManagerFactory emf, String nombreTabla) {
        ArrayList<String> lista = new ArrayList<>();
        TablaExtendidaEntity tablaExtendida = new TablaExtendidaJpaController(emf).buscarTabla(nombreTabla);
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
        JsonObject jsonElement = new JsonParser().parse(cadenaJSON).getAsJsonObject();
        for (String atributo : listaAtributos) {
            String valor = jsonElement.get(atributo).toString();
            listaValores.add(valor == null ? "" : valor.replace("\\\"", ""));
        }
        return listaValores;
    }
}