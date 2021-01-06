/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import java.util.Objects;
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

    @ManyToOne
    @JoinColumn( name = "TIPOIVA_ID")
    private TipoIVA tipoIVA;
    
    @ManyToOne
    @JoinColumn( name= "FACTURA_ID")
    private Factura factura;
    
    
    private String concepto;
    private Double cantidad;
    private Double precioUnitario;
    private Double porcentajeDescuento;
    private Double importeTotal;

    public LineaFactura() {
    }

    public LineaFactura(TipoIVA tipoIVA,Factura factura, String concepto, Double cantidad, Double precioUnitario, Double porcentajeDescuento, Double importeTotal) {
        this.tipoIVA = tipoIVA;
        this.factura = factura;
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

    public Factura getFactura() {
        return factura;
    }

    public String getConcepto() {
        return concepto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoIVA(TipoIVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    @Override
    public int hashCode() {
        if (id != null) {
            return Objects.hashCode(this.id);
        } else {
            return hashCodePorContenido();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (id != null) {
            return equalsPorId(obj);
        } else {
            return equalsPorContenido(obj);
        }
    }

    public boolean equalsPorId(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineaFactura other = (LineaFactura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public int hashCodePorContenido() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.cantidad);
        hash = 97 * hash + Objects.hashCode(this.importeTotal);
        hash = 97 * hash + Objects.hashCode(this.porcentajeDescuento);
        return hash;
    }

    public boolean equalsPorContenido(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineaFactura other = (LineaFactura) obj;
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.importeTotal, other.importeTotal)) {
            return false;
        }
        if (!Objects.equals(this.porcentajeDescuento, other.porcentajeDescuento)) {
            return false;
        }
        return true;
    }


}
