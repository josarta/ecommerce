/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.controler;

import edu.ecommerce.entity.Categoria;
import edu.ecommerce.entity.Productos;
import edu.ecommerce.entity.SubCategoria;
import edu.ecommerce.facade.CategoriaFacadeLocal;
import edu.ecommerce.facade.ProductosFacadeLocal;
import edu.ecommerce.facade.SubCategoriaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Usuario
 */
@Named(value = "productosView")
@ViewScoped
public class ProductosView implements Serializable {

    @EJB
    CategoriaFacadeLocal categoriaFacadeLocal;
    @EJB
    SubCategoriaFacadeLocal subCategoriaFacadeLocal;
    @EJB
    ProductosFacadeLocal productosFacadeLocal;

    private String nombreProducto;
    private String descripcion;
    private String cantidad = "0";
    private int valor = 0;
    private int fk_subCategoria = 0;
    private int categoriaId = 0;
    private int categoriaIdtabla = 0;

    private List<Categoria> todasCategorias = new ArrayList<>();
    private List<SubCategoria> todasSubCategorias = new ArrayList<>();
    private List<Productos> todosProductos = new ArrayList<>();

    /**
     * Creates a new instance of Productos
     */
    public ProductosView() {
    }

    @PostConstruct
    public void cargaInicial() {
        todasCategorias.addAll(categoriaFacadeLocal.findAll());
        todasSubCategorias.addAll(subCategoriaFacadeLocal.leerSubCategoria(todasCategorias.get(0).getIdcategoria()));
        todosProductos.addAll(productosFacadeLocal.findAll());
    }

    public void leerSubCategorias() {
        todasSubCategorias.clear();
        todasSubCategorias.addAll(subCategoriaFacadeLocal.leerSubCategoria(categoriaId));

    }

    public void crearProductos() {
        productosFacadeLocal.ingresarProducto(nombreProducto, descripcion, cantidad, valor, fk_subCategoria);
        todosProductos.clear();
        todosProductos.addAll(productosFacadeLocal.findAll());
        nombreProducto = "";
        descripcion = "";
        cantidad = "0";
        valor = 0;
    }

    public void leerProductosCategoria() {
        todosProductos.clear();
        todosProductos.addAll(productosFacadeLocal.leerProductosCategoria(categoriaIdtabla));

    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getFk_subCategoria() {
        return fk_subCategoria;
    }

    public void setFk_subCategoria(int fk_subCategoria) {
        this.fk_subCategoria = fk_subCategoria;
    }

    public List<Categoria> getTodasCategorias() {
        return todasCategorias;
    }

    public void setTodasCategorias(List<Categoria> todasCategorias) {
        this.todasCategorias = todasCategorias;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<SubCategoria> getTodasSubCategorias() {
        return todasSubCategorias;
    }

    public void setTodasSubCategorias(List<SubCategoria> todasSubCategorias) {
        this.todasSubCategorias = todasSubCategorias;
    }

    public int getCategoriaIdtabla() {
        return categoriaIdtabla;
    }

    public void setCategoriaIdtabla(int categoriaIdtabla) {
        this.categoriaIdtabla = categoriaIdtabla;
    }

    public List<Productos> getTodosProductos() {
        return todosProductos;
    }

    public void setTodosProductos(List<Productos> todosProductos) {
        this.todosProductos = todosProductos;
    }

}
