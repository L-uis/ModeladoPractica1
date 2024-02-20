
/**
 * Clase que permite hacer el cobro de una sucripcion de Memeflix para cuatro dispositivos.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class MemeflixCuatroDispositivos implements CobroMemeflix{

  private final double MONTO = 200.00;

  private final String RECHAZADO = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

  private final String APROVADO = " a pagado " + MONTO + " a Memeflix por : Sucripcion de Memeflix para cuatro dispositivos";

  @Override
  public String cobro(Suscriptor suscriptor) {

    double saldoDisponible = suscriptor.getSaldo();

    String nombre = suscriptor.getNombre();

    if (saldoDisponible >= MONTO) {

      return nombre + APROVADO;
    
    } else {

      return RECHAZADO;
      
    }
  }

}
