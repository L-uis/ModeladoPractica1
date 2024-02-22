
/**
 * Clase que permite hacer el cobro de una sucripcion normal de HVO Max.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public class HVONormal implements CobroHVO{

  private final double MONTO = 140.00;

  private final String RECHAZADO = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

  private final String APROVADO = " a pagado " + MONTO + " a HVO Max por : Suscripcion normal de HVO Max";

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