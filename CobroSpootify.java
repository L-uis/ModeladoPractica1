
/**
 * Interface que permite hacer cobros para el servicio Spotify.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface CobroSpootify {

  /**
   * Metodo que realiza el cobro al suscriptor.
   * 
   * @return el estado del cobro.
   */
  String cobro(Suscriptor suscriptor);
}
