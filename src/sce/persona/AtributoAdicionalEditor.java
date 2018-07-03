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
import javax.persistence.EntityManager;
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
        List<AtributosAdicionalesEntity> atributos = new AtributosAdicionalesJpaController(emf).buscarAtributosParaTabla(tablaExtendida.getId());
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
        listaAtributos.add(nuevo);
        return true;
    }
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
    public void guardarCambios() throws NonexistentEntityException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Se crear el registro en tabla_extendida (si aún no existe)
        if (tablaExtendida.getId() == null) {
            new TablaExtendidaJpaController(emf).create(tablaExtendida, em);
        }
        AtributosAdicionalesJpaController controller = new AtributosAdicionalesJpaController(emf);
        // Eliminación de los atributos eliminados
        for (AtributosAdicionalesEntity eliminado : listaAtributosEliminados) {
            if (eliminado.getId() != null) {
                controller.destroy(eliminado.getId(), em);
            }
        }
        // Creación de los registros aún no existentes
        for (AtributosAdicionalesEntity nuevo : listaAtributos) {
            if (nuevo.getId() == null) {
                nuevo.setTabla_extendida_id(tablaExtendida.getId());
                controller.create(nuevo, em);
            }
        }
        em.getTransaction().commit();
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