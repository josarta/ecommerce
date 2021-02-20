/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.controler;

import edu.ecommerce.entity.Categoria;
import edu.ecommerce.entity.SubCategoria;
import edu.ecommerce.facade.CategoriaFacadeLocal;
import edu.ecommerce.facade.SubCategoriaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Usuario
 */
@Named(value = "categoriaView")
@ViewScoped
public class CategoriaView implements Serializable {

    @EJB
    CategoriaFacadeLocal categoriaFacadeLocal;
    @EJB
    SubCategoriaFacadeLocal subCategoriaFacadeLocal;

    private Categoria categoriaNuevo = new Categoria();
    private Categoria categoriaGestion = new Categoria();
    private SubCategoria subCategoriaNuevo = new SubCategoria();
    private String nombreSubCategoria;
    private String nombreCategoria;

    /**
     * Creates a new instance of CategoriaView
     */
    public CategoriaView() {
    }

    public void crearCategoria() {
        if (!nombreCategoria.isEmpty()) {
            categoriaNuevo = new Categoria();
            categoriaNuevo.setNombre(nombreCategoria);
            categoriaFacadeLocal.create(categoriaNuevo);
            nombreCategoria = "";
        }
    }

    public void crearSubCategoria() {
        if (!nombreSubCategoria.isEmpty()) {
            subCategoriaFacadeLocal.ingresarSubCategoria(nombreSubCategoria, categoriaGestion.getIdcategoria());
            categoriaGestion = categoriaFacadeLocal.buscarPorId(categoriaGestion.getIdcategoria());
        }

    }

    public void removerSubCategoria( SubCategoria removerIn) {
       subCategoriaFacadeLocal.remove(removerIn);
    }

    
    
    public void actualCategoria(Categoria categoriaIn) {
        categoriaGestion = new Categoria();
        categoriaGestion = categoriaIn;
    }

    public List<Categoria> leerCategorias() {
        return categoriaFacadeLocal.findAll();
    }

    public Categoria getCategoriaNuevo() {
        return categoriaNuevo;
    }

    public void setCategoriaNuevo(Categoria categoriaNuevo) {
        this.categoriaNuevo = categoriaNuevo;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public SubCategoria getSubCategoriaNuevo() {
        return subCategoriaNuevo;
    }

    public void setSubCategoriaNuevo(SubCategoria subCategoriaNuevo) {
        this.subCategoriaNuevo = subCategoriaNuevo;
    }

    public String getNombreSubCategoria() {
        return nombreSubCategoria;
    }

    public void setNombreSubCategoria(String nombreSubCategoria) {
        this.nombreSubCategoria = nombreSubCategoria;
    }

    public Categoria getCategoriaGestion() {
        return categoriaGestion;
    }

    public void setCategoriaGestion(Categoria categoriaGestion) {
        this.categoriaGestion = categoriaGestion;
    }

}
