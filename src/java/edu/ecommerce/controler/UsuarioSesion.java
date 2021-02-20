/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.controler;

import edu.ecommerce.entity.Rol;
import edu.ecommerce.entity.Usuario;
import edu.ecommerce.facade.RolFacadeLocal;
import edu.ecommerce.facade.UsuarioFacadeLocal;
import edu.ecommerce.utilidades.Email;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
@Named(value = "usuarioSesion")
@SessionScoped
public class UsuarioSesion implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    RolFacadeLocal rolFacadeLocal;

    private Usuario usuReg = new Usuario();
    private Usuario usuLog = new Usuario();
    private Usuario usuGestion = new Usuario();

    private List<Rol> noRoles = new ArrayList<>();

    private String regEx = "0";
    private String correoIn = "";
    private String claveIn = "";

    /**
     * Creates a new instance of UsuarioSesion
     */
    public UsuarioSesion() {

    }

    public void crearUsuario() {
        try {
            usuReg.setEstado(Short.valueOf("1"));
            usuarioFacadeLocal.create(usuReg);
            rolFacadeLocal.ingresarRol(usuReg.getIdUsuario(), 2);
            usuReg = new Usuario();
            regEx = "1";
        } catch (Exception e) {
            regEx = "2";
        }

    }

    public void tomarDatos(Usuario usuarioIn) {
        usuGestion = new Usuario();
        usuGestion = usuarioIn;
        noRoles.clear();
        noRoles.addAll(rolFacadeLocal.noRoles(usuGestion.getIdUsuario()));
    }

    public void validarUsuario() {
        try {
            usuLog = usuarioFacadeLocal.validarUsuario(correoIn, claveIn);
            if (usuLog == null) {
                regEx = "3";
            } else {
                if (usuLog.getEstado() == 1) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("Cliente/index.xhtml");
                } else {
                    regEx = "4";
                }
            }

        } catch (Exception e) {
            regEx = "2";
        }
    }
    
    
     public void recuperarClave() {
        try {
            usuLog = usuarioFacadeLocal.buscarPorCorreo(correoIn);
            if (usuLog == null) {
                regEx = "3";
            } else {
                 String  nuevaClave = "ADSI-" + 100000 * Math.random();
                 usuLog.setClave(nuevaClave);
                 usuarioFacadeLocal.edit(usuLog);
                 Email.sendClaves(usuLog.getCorreo(), usuLog.getNombre()+" "+ usuLog.getApellido() , nuevaClave);
                regEx = "4";
            }

        } catch (Exception e) {
            regEx = "2";
        }
    }
     

    public void eliminarUsuario(int id_usuario) {
        try {
            usuReg = usuarioFacadeLocal.find(id_usuario);
            usuarioFacadeLocal.remove(usuReg);

        } catch (Exception e) {
            regEx = "2";
        }
    }

    public void activaUsuario(int id_usuario) {
        usuarioFacadeLocal.cambioEstado(id_usuario, 1);
    }

    public void desactivaUsuario(int id_usuario) {
        usuarioFacadeLocal.cambioEstado(id_usuario, 0);
    }

    public void adicionaRol(Rol rolIn) {
        rolFacadeLocal.ingresarRol(usuGestion.getIdUsuario(), rolIn.getIdrol());
        usuGestion = usuarioFacadeLocal.buscarPorId(usuGestion.getIdUsuario());
         noRoles.clear();
        noRoles.addAll(rolFacadeLocal.noRoles(usuGestion.getIdUsuario()));
    }

    public void removerRol(Rol rolIn) {
        rolFacadeLocal.removerRol(usuGestion.getIdUsuario(), rolIn.getIdrol());
        usuGestion = usuarioFacadeLocal.buscarPorId(usuGestion.getIdUsuario());
         noRoles.clear();
        noRoles.addAll(rolFacadeLocal.noRoles(usuGestion.getIdUsuario()));
    }

    public List<Usuario> listaUsuarios() {
        return usuarioFacadeLocal.listarTodos();
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public String getRegEx() {
        return regEx;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public String getClaveIn() {
        return claveIn;
    }

    public void setClaveIn(String claveIn) {
        this.claveIn = claveIn;
    }

    public Usuario getUsuLog() {
        return usuLog;
    }

    public void setUsuLog(Usuario usuLog) {
        this.usuLog = usuLog;
    }

    public Usuario getUsuGestion() {
        return usuGestion;
    }

    public void setUsuGestion(Usuario usuGestion) {
        this.usuGestion = usuGestion;
    }

    public List<Rol> getNoRoles() {
        return noRoles;
    }

    public void setNoRoles(List<Rol> noRoles) {
        this.noRoles = noRoles;
    }

}
