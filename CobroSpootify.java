
/**
 * Interface que permite hacer cobros para el servicio Spootify.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public interface CobroSpootify {

  /**
   * Metodo que realiza el cobro al suscriptor de una suscripcion de Spootify.
   * Compara el saldo disponible del suscriptor con el monto del cobro y 
   * devuelve un mensaje según el resultado.
   * 
   * @param cliente El cliente al que se le cobra el servicio.
   * @return El estado del cobro, si fue aprobado o rechazado.
   */
  String cobro(Cliente cliente);
  
}
