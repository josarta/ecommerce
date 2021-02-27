/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.Productos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos> implements ProductosFacadeLocal {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }
    
    
    
    @Override
    public boolean ingresarProducto(String nombre, String descripcion, String cantidad ,int  valor ,int id_subCategoria) {
        try {
            Query q = em.createNativeQuery("INSERT INTO productos (nombre, descripcion, estado, cantidad, valor, fk_sub_categoriaId) VALUES (?, ?, ?, ?, ?, ?)");
            q.setParameter(1, nombre);
            q.setParameter(2, descripcion);
            q.setParameter(3, "Activo");
            q.setParameter(4, cantidad);
            q.setParameter(5, valor);
            q.setParameter(6,id_subCategoria);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public List<Productos> leerProductosCategoria(int id_categoria){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT p FROM Productos p WHERE p.fksubcategoriaId.fkcategoriaId.idcategoria = :id_categoria");
            q.setParameter("id_categoria", id_categoria);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
