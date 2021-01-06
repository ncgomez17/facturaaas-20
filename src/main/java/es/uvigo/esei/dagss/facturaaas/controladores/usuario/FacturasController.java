/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.controladores.usuario;

import es.uvigo.esei.dagss.facturaaas.controladores.AutenticacionController;
import es.uvigo.esei.dagss.facturaaas.daos.ClienteDAO;
import es.uvigo.esei.dagss.facturaaas.daos.DatosFacturacionDAO;
import es.uvigo.esei.dagss.facturaaas.daos.FacturaDAO;
import es.uvigo.esei.dagss.facturaaas.daos.FormaPagoDAO;
import es.uvigo.esei.dagss.facturaaas.daos.PagoDAO;
import es.uvigo.esei.dagss.facturaaas.daos.TipoIVADAO;
import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.DatosFacturacion;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.EstadoPago;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import es.uvigo.esei.dagss.facturaaas.entidades.FormaPago;
import es.uvigo.esei.dagss.facturaaas.entidades.LineaFactura;
import es.uvigo.esei.dagss.facturaaas.entidades.Pago;
import es.uvigo.esei.dagss.facturaaas.entidades.TipoIVA;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author usuario
 */
@Named(value = "facturasController")
@ViewScoped
public class FacturasController implements Serializable {
    
    private List<Factura> facturas;
    private List<LineaFactura> lineasFacturaActual;
    private List<Pago> pagos;

    private Factura facturaActual;
    private LineaFactura lineaActual;
    private Pago pagoActual;
    private TipoIVA tipoIVA;


    private boolean esNuevo;

    private boolean esNuevaLinea;   //es lineaDeFactura nueva
    private Cliente clienteElegido; //Para insertar el cliente (elegido en lista desplegable) vinculado a la factura
    private FormaPago formaPago;    //Para insertar la formaPago (elegido en lista desplegable) vinculada a la factura
    private DatosFacturacion datosFacturacion;
  
    private Cliente clienteBusqueda; //Para buscar facturas de un cliente en concreto

    private EstadoFactura[] estadosFactura = EstadoFactura.values();
    private EstadoPago[] estadosPago = EstadoPago.values();
   

    
    @Inject
    private TipoIVADAO tipoIVADAO;
    
    @Inject
    private FacturaDAO facturaDAO;

    @Inject
    private ClienteDAO clienteDAO;
    
    @Inject
    private PagoDAO pagoDAO;
    @Inject
    private FormaPagoDAO formaPagoDAO;
    
    @Inject
    private AutenticacionController autenticacionController;

    @Inject
    private DatosFacturacionDAO datosFacturacionDAO;  //Para acceder a la formaPago por defecto del usuario
        
    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<LineaFactura> getLineasFacturaActual() {
        return lineasFacturaActual;
    }

    public void setLineasFacturaActual(List<LineaFactura> lineasFacturaActual) {
        this.lineasFacturaActual = lineasFacturaActual;
    }
    
    public boolean isEsNuevaLinea() {
        return esNuevaLinea;
    }

    public void setEsNuevaLinea(boolean esNuevaLinea) {
        this.esNuevaLinea = esNuevaLinea;
    }
    
    public LineaFactura getLineaActual() {
        return lineaActual;
    }

    public void setLineaActual(LineaFactura lineaActual) {
        this.lineaActual = lineaActual;
    }
    
    public TipoIVA getTipoIVA() {
        return tipoIVA;
    }

    public void setTipoIVA(TipoIVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }
    
    public Factura getFacturaActual() {
        return facturaActual;
    }

    public void setFacturaActual(Factura facturaActual) {
        this.facturaActual = facturaActual;
    }

    
    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Cliente getClienteElegido() {
        return clienteElegido;
    }

    public void setClienteElegido(Cliente clienteElegido) {
        this.clienteElegido = clienteElegido;
    }
    
    public EstadoFactura[] getEstadosFactura() {
        return estadosFactura;
    }
    
    public Cliente getClienteBusqueda() {
        return clienteBusqueda;
    }

    public void setClienteBusqueda(Cliente clienteBusqueda) {
        this.clienteBusqueda = clienteBusqueda;
    }
    
    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public DatosFacturacion getDatosFacturacion() {
        return datosFacturacion;
    }

    public void setDatosFacturacion(DatosFacturacion datosFacturacion) {
        this.datosFacturacion = datosFacturacion;
    }

    public Pago getPagoActual() {
        return pagoActual;
    }

    public void setPagoActual(Pago pagoActual) {
        this.pagoActual = pagoActual;
    }

    public EstadoPago[] getEstadosPago() {
        return estadosPago;
    }

    public void setEstadosPago(EstadoPago[] estadosPago) {
        this.estadosPago = estadosPago;
    }
    
    

    @PostConstruct
    public void cargaInicial() {

        this.facturas = refrescarLista();
        this.pagos = listadoPagos();
        this.facturaActual = null;
        this.lineaActual = null;
        this.esNuevo = false;
        this.esNuevaLinea = false;

    }

    
    public void doBuscarConPropietario() {
        System.out.println("Buscar con propietario");
        this.facturas = refrescarLista();
    }
    
    public void doBuscarConPropietarioPorCliente() {
        System.out.println("Buscar por Cliente");
        this.facturas = facturaDAO.buscarPorClienteConPropietario(autenticacionController.getUsuarioLogueado(), clienteBusqueda);
    }
    public void doBuscarConPropietarioPorClientepago(){
        System.out.println("Buscar por Cliente");
        this.pagos = pagoDAO.buscarPorClienteConPropietario(autenticacionController.getUsuarioLogueado(), clienteBusqueda);
    }
    public void doBuscarTodos() {
        System.out.println("Buscar TODOS");
        this.facturas = refrescarLista();

        System.out.println("fin busca todos");
    }
    
    //La forma de pago debe estar inicializada con la forma de pago por defecto del usuario actual
    public void doNuevo() {
        this.esNuevo = true;
        this.facturaActual = new Factura();
        this.facturaActual.setUsuario(autenticacionController.getUsuarioLogueado());
        this.datosFacturacion= cargarDatosFacturacion();
        //Forma de pago por defecto del usuario extraido de sus datos de facturacion
        this.facturaActual.setFormaPago(datosFacturacion.getFormaPagoPorDefecto());
        this.facturaActual.setImporte(0.0);
        this.facturaActual.setSumaTotal(0.0);

        this.facturaActual.setLineasDeFactura(new ArrayList<LineaFactura>()); //puse ArrayList por poner una implementacion de List con la que inicializar
        
    }
    
    public void doEditar(Factura factura) {
        this.esNuevo = false;
        this.facturaActual = factura;
    }


    public void doGuardarEditado() {
        if (this.esNuevo) {
            facturaDAO.crear(facturaActual); 
        } else {
            facturaDAO.actualizar(facturaActual);
            this.borrarPagos(facturaActual);
        }
        this.crearPagos();
        this.facturas = refrescarLista();
        this.facturaActual = null;
        this.esNuevo = false;
    }

    public void doCancelarEditado() {
        this.facturaActual = null;
        this.esNuevo = false;
    }
    public void doBorrar(Factura factura) {
        this.facturaDAO.eliminar(factura);
        this.facturas = refrescarLista();
        this.facturaActual = null;

    }
    
    public void crearPagos(){
        Date fechapago = facturaActual.getFechaEmision();
        Double importepago = 0.0;
        if(facturaActual.getImporte()!=0.0){
             importepago = facturaActual.getImporte()/facturaActual.getFormaPago().getNumeroPagos(); 
        }
        for(int i=0; i<facturaActual.getFormaPago().getNumeroPagos();i++){
            Pago pago = new Pago();
            pago.setFactura(this.facturaActual);
            pago.setEstadoPago(EstadoPago.PENDIENTE);
            pago.setFecha(fechapago);
            pago.setImporte(importepago);
            this.pagoDAO.crear(pago);
            fechapago = new Date(fechapago.getTime() + (1000 * 60 * 60 * 24 * facturaActual.getFormaPago().getPeriodicidad()));
        }
    }
    public void borrarPagos(Factura factura){

        List<Pago> pagos = this.pagoDAO.buscarPorFacturaPago(autenticacionController.getUsuarioLogueado(),factura);
        for(int i=0;i< pagos.size(); i++){
            this.pagoDAO.eliminar(pagos.get(i));
        }
    }
    private List<Factura> refrescarLista() {
        return facturaDAO.buscarConPropietario(autenticacionController.getUsuarioLogueado());
    }
    
    public List<Cliente> listadoClientes(){
        return clienteDAO.buscarTodosConPropietario(autenticacionController.getUsuarioLogueado());
    }
    public List<Pago> listadoPagos(){
        return pagoDAO.buscarConPropietario(autenticacionController.getUsuarioLogueado());
    }
    
    public List<FormaPago> listadoFormasPago() {
        return formaPagoDAO.buscarActivas();
    }
    
    public List<TipoIVA> listadoTipoIVA(){
        return this.tipoIVADAO.buscarActivos();
    }
    
    private DatosFacturacion cargarDatosFacturacion(){
        return datosFacturacionDAO.buscarConPropietario(autenticacionController.getUsuarioLogueado());
    }

    //Crear una linea de factura
    public void doNuevaLinea() {
        this.esNuevaLinea = true;
        this.lineaActual = new LineaFactura();
        this.lineaActual.setFactura(facturaActual);
        this.datosFacturacion = cargarDatosFacturacion();
        this.tipoIVA = datosFacturacion.getTipoIVAPorDefecto();
        this.lineaActual.setTipoIVA(tipoIVA);
        this.lineaActual.setCantidad(1.0);
        this.lineaActual.setPrecioUnitario(1.0);
        this.lineaActual.setPorcentajeDescuento(0.0);
        
   
    }
    
    public void doEditarLinea(LineaFactura lin){
        this.esNuevaLinea = false;
        this.lineaActual = lin;
        Double importe = facturaActual.getImporte()-(lineaActual.getCantidad()*lineaActual.getPrecioUnitario()
                        * ( 1-(lineaActual.getPorcentajeDescuento()) ));
        Double sumatotal = facturaActual.getSumaTotal()- lineaActual.getImporteTotal();
        facturaActual.setImporte(importe);
        facturaActual.setSumaTotal(sumatotal);
        
    }
    
    public void doGuardarEditadoLinea(){
        lineaActual.setImporteTotal(
                (lineaActual.getCantidad()*lineaActual.getPrecioUnitario()) 
                        * ( 1-(lineaActual.getPorcentajeDescuento()) )
                        * ( 1+(lineaActual.getTipoIVA().getPorcentaje()) )
                        
            );  
        Double importe = facturaActual.getImporte()+(lineaActual.getCantidad()*lineaActual.getPrecioUnitario()
                        * ( 1-(lineaActual.getPorcentajeDescuento()) ));
        Double sumatotal = facturaActual.getSumaTotal()+ lineaActual.getImporteTotal();
        facturaActual.setImporte(importe);
        facturaActual.setSumaTotal(sumatotal);
        if(this.esNuevaLinea){
           facturaDAO.crearLineas(lineaActual);

        }else{
            facturaDAO.actualizarLineas(lineaActual);
        }
        this.facturaDAO.actualizar(facturaActual);
        this.lineasFacturaActual = refrescarListadoLineas();
        this.lineaActual=null;
        this.esNuevaLinea=false;
    }
        
    public void doCancelarEditadoLinea(){
        this.lineaActual=null;
        this.esNuevaLinea=false;
    }
    public void doEditarPago(Pago pago){
        this.pagoActual= pago; 
    }
    public void doGuardarPago(){
        this.pagoDAO.actualizar(this.pagoActual);
        this.pagoActual = null;
    }
    public void doCancelarEditadoPago(){
        this.pagoActual=null;
    }

    
    public void doBuscarLineasDeFactura(){
        this.lineasFacturaActual = refrescarListadoLineas();
    }
    
    public List<LineaFactura> refrescarListadoLineas(){
        if(this.facturaActual!=null){
            return facturaDAO.buscarPorIdConLineas(this.facturaActual.getId());
        }
        return Collections.EMPTY_LIST;
    }
    
    public void doBorradoLinea(LineaFactura linea){
        facturaDAO.borrarLineas(linea);
        Double importe = facturaActual.getImporte()-(linea.getCantidad()*linea.getPrecioUnitario()
                        * ( 1-(linea.getPorcentajeDescuento()) ));
        Double sumatotal = facturaActual.getSumaTotal()- linea.getImporteTotal();
        facturaActual.setImporte(importe);
        facturaActual.setSumaTotal(sumatotal);
        this.facturaDAO.actualizar(facturaActual);
        this.lineasFacturaActual = refrescarListadoLineas();
        this.lineaActual = null;

    }
   public void  setFacturaActualActualizar(Factura f){
       this.setFacturaActual(f);
       this.doBuscarLineasDeFactura();
   }
   
   
}
