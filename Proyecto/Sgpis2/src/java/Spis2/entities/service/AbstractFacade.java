/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spis2.entities.service;

import Spis2.entities.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

/**
 *
 * @author carlos
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    /*Se agrego una nueva funcion para el login*/
    public boolean login(Usuario user){//se recibe un objeto user
        String consulta = "Select nombre from Usuario where email ilike "+"'"+
                 user.getEmail()+"'"+" and password like "+"'"+user.getPassword()+"'";//se construye la consulta
        javax.persistence.Query q = getEntityManager().createNativeQuery(consulta);//se consulta por medio de la persistencia
        if(q.getResultList().isEmpty())//se consulta se hubo resultados, si no el usuario o pass estan incorrectos
            return false;
        else
            return true;
    }
    
    /*Se agrego una nueva funcion para el login que retorna un JSON*/
    public String login_object(Usuario user){//se recibe un objeto user
        
        JSONObject obj = new JSONObject();
        String retorno_consulta;
        String[] campos_consulta;
        
        String consulta = "Select nombre ||'-'|| apellido ||'-'|| status ||'-'|| id_group ||'-'|| id_rol"
                + " from Usuario where email = '" + user.getEmail() + "'"
                + " and password = " + "'" + user.getPassword() + "'"; //se construye la consulta
        javax.persistence.Query q = getEntityManager().createNativeQuery(consulta);//se consulta por medio de la persistencia
        
        if(q.getResultList().isEmpty())//se consulta se hubo resultados, si no el usuario o pass estan incorrectos
            return "";
        else
        retorno_consulta = q.getSingleResult().toString();
            campos_consulta = retorno_consulta.split("-");
            
        try {
            obj.put("nombre", campos_consulta[0]);
            obj.put("apellido", campos_consulta[1]);
            obj.put("status", campos_consulta[2]);
            obj.put("id_group", campos_consulta[3]);
            obj.put("id_rol", campos_consulta[4]);

        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
        return obj.toString();
    }
    
    
    /*Se agrego una nueva funcion para el login que retorna un JSON*/
    public String tareas_usuario(Usuario user){//se recibe un objeto user
        
        //JSONObject obj = new JSONObject();
        JSONArray objJsonArray = new JSONArray();
        List retorno_consulta;
        String[] campos_consulta;
        
        String consulta = "Select sprint.id_sprint ||'-'|| sprint.nombre"
                + " from usuario join groups on usuario.id_group = groups.id_group"
                + " join projects on projects.id_project = groups.id_project "
                + " join sprint on sprint.id_project = projects.id_project"
                + " where usuario.id_usuario = '" + user.getIdUsuario().toString() + "'"; 
        //se construye la consulta
        
        javax.persistence.Query q = getEntityManager().createNativeQuery(consulta);//se consulta por medio de la persistencia
        
        if(q.getResultList().isEmpty())//se consulta se hubo resultados, si no el usuario o pass estan incorrectos
            return "";
        else
        retorno_consulta = q.getResultList();
            try {

                for (int posicion = 0; posicion < retorno_consulta.size(); posicion++) {
                    JSONObject obj = new JSONObject();
                    campos_consulta = retorno_consulta.get(posicion).toString().split("-");
                    obj.put("Numero de tarea", campos_consulta[0]);
                    obj.put("Nombre de la tarea", campos_consulta[1]);
                    objJsonArray.add(obj); 
                }

            } catch (Exception e) {
                String message = e.getMessage();
                System.out.println(message);
            }
        return objJsonArray.toString();
    }    
    
}
