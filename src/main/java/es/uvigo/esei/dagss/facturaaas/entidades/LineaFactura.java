/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name ="LINEAFACTURA")
public class LineaFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn( name = "TIPOIVA_ID")
    private TipoIVA tipoIVA;
    
    @ManyToOne
    @JoinColumn( name= "FACTURA_ID")
    private Factura factura;
    
    
    private String concepto;
    private Long cantidad;
    private Long precioUnitario;
    private Long porcentajeDescuento;
    private Long importeTotal;

    public LineaFactura() {
    }

    public LineaFactura(Long id, TipoIVA tipoIVA, String concepto, Long cantidad, Long precioUnitario, Long porcentajeDescuento, Long importeTotal) {
        this.id = id;
        this.tipoIVA = tipoIVA;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.porcentajeDescuento = porcentajeDescuento;
        this.importeTotal = importeTotal;
    }

    public Long getId() {
        return id;
    }

    public TipoIVA getTipoIVA() {
        return tipoIVA;
    }

    public String getConcepto() {
        return concepto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public Long getPrecioUnitario() {
        return precioUnitario;
    }

    public Long getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public Long getImporteTotal() {
        return importeTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoIVA(TipoIVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(Long precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setPorcentajeDescuento(Long porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void setImporteTotal(Long importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    
    



    
}
