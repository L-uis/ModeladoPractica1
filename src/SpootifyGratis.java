
/**
 * Clase que permite hacer el cobro de una Suscripcion gratis de Spootify.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 * @version Febrero 2024
 */
public class SpootifyGratis implements CobroSpootify{

  private final double MONTO = 0.00;

  private final String RECHAZADO = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

  private final String APROVADO = " a pagado " + MONTO + " a Spootify por : Suscripcion gratis de Spootify";

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
