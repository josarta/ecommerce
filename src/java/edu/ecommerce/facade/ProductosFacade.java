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
    public boolean ingresarProducto(String nombre, String descripcion, String cantidad, int valor, int id_subCategoria) {
        try {
            Query q = em.createNativeQuery("INSERT INTO productos (nombre, descripcion, estado, cantidad, valor, fk_sub_categoriaId) VALUES (?, ?, ?, ?, ?, ?)");
            q.setParameter(1, nombre);
            q.setParameter(2, descripcion);
            q.setParameter(3, "Activo");
            q.setParameter(4, cantidad);
            q.setParameter(5, valor);
            q.setParameter(6, id_subCategoria);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Productos> leerProductosCategoria(int id_categoria) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT p FROM Productos p WHERE p.fksubcategoriaId.fkcategoriaId.idcategoria = :id_categoria");
            q.setParameter("id_categoria", id_categoria);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean imagenProducto(int id_imagen, int id_producto) {
        try {
            Query q = em.createNativeQuery("INSERT INTO productos_has_imagenes (productos_idProductos, imagenes_idimagenes) VALUES (?, ?)");
            q.setParameter(1, id_producto);
            q.setParameter(2, id_imagen);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Productos productoActualizado(int id_producto) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT p FROM Productos p WHERE p.idProductos = :id_producto");
            q.setParameter("id_producto", id_producto);
            return (Productos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Productos> todosProductos() {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT p FROM Productos p");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    

    @Override
    public int consultarProducto(String nombre, String descripcion) {
        try {

            Query q = em.createQuery("SELECT p FROM Productos p WHERE p.nombre LIKE '%" + nombre + "%' AND p.descripcion LIKE '%" + descripcion + "%'");
            List<Productos> listaProductos = q.getResultList();
            return (int) listaProductos.get(0).getIdProductos();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean updateProducto(int valor, int cantidad, int idProducto) {
        try {
            String consultaNativa = "UPDATE productos SET cantidad = '" + cantidad + "', valor = '" + valor + "' WHERE (idProductos = '" + idProducto + "')";
            Query q = em.createNativeQuery(consultaNativa);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
