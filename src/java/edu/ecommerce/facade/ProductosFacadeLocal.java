/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.facade;

import edu.ecommerce.entity.Productos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ProductosFacadeLocal {

    void create(Productos productos);

    void edit(Productos productos);

    void remove(Productos productos);

    Productos find(Object id);

    List<Productos> findAll();

    List<Productos> findRange(int[] range);

    int count();

    public boolean ingresarProducto(String nombre, String descripcion, String cantidad, int valor, int id_subCategoria);

    public List<Productos> leerProductosCategoria(int id_categoria);

    public boolean imagenProducto(int id_imagen, int id_producto);

    public Productos productoActualizado(int id_producto);

    public int consultarProducto(String nombre, String descripcion);

    public boolean updateProducto(int valor, int cantidad, int idProducto);

    public List<Productos> todosProductos();
    
}
