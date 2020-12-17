
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.facturaaas.daos;
import es.uvigo.esei.dagss.facturaaas.entidades.Factura;
import es.uvigo.esei.dagss.facturaaas.entidades.LineaFactura;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author danid
 */
@Stateless
public class LineaDeFacturaDAOJPA extends GenericoDAOJPA<LineaFactura, Long> implements LineaDeFacturaDAO {

    @Override
    public List<LineaFactura> buscarConFactura(Factura factura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
