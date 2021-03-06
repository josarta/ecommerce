/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.controler;

import edu.ecommerce.entity.Categoria;
import edu.ecommerce.entity.SubCategoria;
import edu.ecommerce.facade.CategoriaFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Usuario
 */
@Named(value = "clienteSession")
@SessionScoped
public class ClienteSession implements Serializable {

    @EJB
    CategoriaFacadeLocal categoriaFacadeLocal;

    private List<Categoria> todas = new ArrayList<>();
    private Categoria categoriaGestion = new Categoria();
    private SubCategoria subCategoriaGestion = new SubCategoria();
   
    /**
     * Creates a new instance of ClienteSession
     */
    public ClienteSession() {
    }

    @PostConstruct
    public void cargaInicial() {
        todas.addAll(categoriaFacadeLocal.findAll());
        if(todas.size() > 0){
              categoriaGestion = todas.get(0);
        }
      
    }

    public void categoriaSelecionada(Categoria catgoriaIn) {
        categoriaGestion = new Categoria();
        categoriaGestion = catgoriaIn;
    }

      public void subCategoriaSelecionada(SubCategoria subcatgoriaIn) {
        subCategoriaGestion = new SubCategoria();
        subCategoriaGestion = subcatgoriaIn;
    }
    public List<Categoria> getTodas() {
        return todas;
    }

    public void setTodas(List<Categoria> todas) {
        this.todas = todas;
    }

    public Categoria getCategoriaGestion() {
        return categoriaGestion;
    }

    public void setCategoriaGestion(Categoria categoriaGestion) {
        this.categoriaGestion = categoriaGestion;
    }

    public SubCategoria getSubCategoriaGestion() {
        return subCategoriaGestion;
    }

    public void setSubCategoriaGestion(SubCategoria subCategoriaGestion) {
        this.subCategoriaGestion = subCategoriaGestion;
    }

}
