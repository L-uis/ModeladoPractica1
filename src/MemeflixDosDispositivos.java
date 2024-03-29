
/**
 * Clase que permite hacer el cobro de una Suscripcion de Memeflix para dos dispositivos.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 * @version Febrero 2024
 */
public class MemeflixDosDispositivos implements CobroMemeflix{
  
  private final double MONTO = 170.00;

  private final String RECHAZADO = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

  private final String APROVADO = " a pagado " + MONTO + " a Memeflix por : Suscripcion de Memeflix para dos dispositivos";

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
