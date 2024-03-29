
/**
 * Clase que permite hacer el cobro de una Suscripcion premium de Momazon Prime Video.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 * @version Febrero 2024
 */
public class MomazonPremium implements CobroMomazon{
  
  private final double MONTO = 150.00;

  private final String RECHAZADO = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

  private final String APROVADO = " a pagado " + MONTO + " a Momazon Prime Video por : Suscripcion premium de Momazon Prime Video";

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
