/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecommerce.controler;

import edu.ecommerce.entity.Categoria;
import edu.ecommerce.entity.Imagenes;
import edu.ecommerce.entity.Productos;
import edu.ecommerce.entity.SubCategoria;
import edu.ecommerce.facade.CategoriaFacadeLocal;
import edu.ecommerce.facade.ImagenesFacadeLocal;
import edu.ecommerce.facade.ProductosFacadeLocal;
import edu.ecommerce.facade.SubCategoriaFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.shaded.commons.io.FilenameUtils;

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
    @EJB
    ImagenesFacadeLocal imagenesFacadeLocal;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private String nombreProducto;
    private String descripcion;
    private String cantidad = "0";
    private int valor = 0;
    private int fk_subCategoria = 0;
    private int categoriaId = 0;
    private int categoriaIdtabla = 0;
    private Part archivoImagen;

    private List<Categoria> todasCategorias = new ArrayList<>();
    private List<SubCategoria> todasSubCategorias = new ArrayList<>();
    private List<Productos> todosProductos = new ArrayList<>();

    private Productos productoGestion = new Productos();

    /**
     * Creates a new instance of Productos
     */
    public ProductosView() {
    }

    @PostConstruct
    public void cargaInicial() {
        todasCategorias.addAll(categoriaFacadeLocal.findAll());
        if (todasCategorias.size() > 0) {
            todasSubCategorias.addAll(subCategoriaFacadeLocal.leerSubCategoria(todasCategorias.get(0).getIdcategoria()));
        }
        todosProductos.addAll(productosFacadeLocal.todosProductos());
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

    public void cargaImagen() {
        String mensajes = "";
        if (archivoImagen != null) {
            if (archivoImagen.getSize() > 4000000) {
                mensajes = "Swal.fire('Error!', 'El archivo es muy grande!', 'error')";
            } else if (!"image/jpeg".equals(archivoImagen.getContentType())) {
                mensajes = "Swal.fire('Error!', 'El archivo no es una imagen!', 'error')";
            }
            if (mensajes.isEmpty()) {
                File carpeta = new File("D:/ecommerce/imagenes/productos");
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }
                try (InputStream is = archivoImagen.getInputStream()) {
                    Calendar hoy = Calendar.getInstance();
                    String nombreArchivo = sdf.format(hoy.getTime()) + ".";
                    nombreArchivo += FilenameUtils.getExtension(archivoImagen.getSubmittedFileName());
                    Files.copy(is, (new File(carpeta, nombreArchivo)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    Imagenes imgNew = new Imagenes();
                    if (archivoImagen.getSubmittedFileName().length() > 30) {
                        imgNew.setNombre(archivoImagen.getSubmittedFileName().substring(0, 30));
                    } else {
                        imgNew.setNombre(archivoImagen.getSubmittedFileName());

                    }
                    imgNew.setRuta(nombreArchivo);
                    imgNew.setTipo("Producto");
                    imagenesFacadeLocal.create(imgNew);
                    productosFacadeLocal.imagenProducto(imgNew.getIdimagenes(), productoGestion.getIdProductos());
                    productoGestion = productosFacadeLocal.productoActualizado(productoGestion.getIdProductos());
                } catch (Exception e) {
                    mensajes = "Swal.fire('Error!', 'No se pudo guardar la imagen!', 'error')";

                }

            }

        } else {
            mensajes = "Swal.fire('Error!', 'No se encontro una imagen!', 'error')";
        }
        PrimeFaces.current().executeScript(mensajes);
        PrimeFaces.current().executeScript("$('#reset').click()");
    }

    public void cargaListadoProductos(FileUploadEvent event) throws IOException {
        InputStream input = event.getFile().getInputStream();
        List cellData = new ArrayList();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet hssfSheet = workbook.getSheetAt(0);
            Iterator rowIterador = hssfSheet.rowIterator();
            rowIterador.next();

            while (rowIterador.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterador.next();
                Iterator iterador = hssfRow.cellIterator();
                List cellTemp = new ArrayList();
                while (iterador.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterador.next();
                    cellTemp.add(hssfCell);
                }
                cellData.add(cellTemp);
            }

            for (int i = 0; i < cellData.size(); i++) {
                List cellTemp = (List) cellData.get(i);
                Productos ptNew = new Productos();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell celda = (XSSFCell) cellTemp.get(j);
                    switch (j) {
                        case 0:
                            ptNew.setNombre(celda.toString());
                            break;
                        case 1:
                            ptNew.setDescripcion(celda.toString());
                            break;
                        case 2:
                            ptNew.setEstado(celda.toString());
                            break;
                        case 3:
                            ptNew.setCantidad((int) celda.getNumericCellValue());
                            break;
                        case 4:
                            ptNew.setValor((float) celda.getNumericCellValue());
                            break;

                        case 5:
                            int idProducto = productosFacadeLocal.consultarProducto(ptNew.getNombre(), ptNew.getDescripcion());
                            if (idProducto != 0) {
                                productosFacadeLocal.updateProducto(ptNew.getValor().intValue(), ptNew.getCantidad(), idProducto);
                            } else {
                                productosFacadeLocal.ingresarProducto(
                                        ptNew.getNombre(),
                                        ptNew.getDescripcion(),
                                        ptNew.getCantidad().toString(),
                                        ptNew.getValor().intValue(),
                                        (int) celda.getNumericCellValue());
                            }
                            break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("edu.ecommerce.controler.ProductosView.cargaListadoProductos()" + e.getMessage());
        }
    }

    public void guardadProductoGestion(Productos productoSelect) {
        productoGestion = new Productos();
        productoGestion = productoSelect;
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

    public Productos getProductoGestion() {
        return productoGestion;
    }

    public void setProductoGestion(Productos productoGestion) {
        this.productoGestion = productoGestion;
    }

    public Part getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(Part archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

}
