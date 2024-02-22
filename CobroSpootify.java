
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
   * @param cliente El cliente al que se le cobra el servicio.
   * @return el estado del cobro.
   */
  String cobro(Cliente cliente);
}
