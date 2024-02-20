
/**
 * Interface que permite hacer cobros para el servicio Momazon Prime Video.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public interface CobroMomazon {

  /**
   * Metodo que realiza el cobro al suscriptor.
   * 
   * @return el estado del cobro.
   */
  String cobro(Suscriptor suscriptor);
}
