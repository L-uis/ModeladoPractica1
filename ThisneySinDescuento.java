
/**
 * Clase que permite hacer el cobro de una sucripcion sin descuento de Thisney+.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class ThisneySinDescuento implements CobroThisney{

  private final double MONTO = 160.00;

  private final String RECHAZADO = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

  private final String APROVADO = " a pagado " + MONTO + " a Thisney+ por : Sucripcion sin descuento de Thisney+";

  @Override
  public String cobro(Cliente suscriptor) {

    double saldoDisponible = suscriptor.getSaldo();

    String nombre = suscriptor.getNombre();

    if (saldoDisponible >= MONTO) {

      return nombre + APROVADO;
    
    } else {

      return RECHAZADO;
      
    }
    
  }
  
}
