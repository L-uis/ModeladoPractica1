
/**
 * Interface que permite hacer cobros para el servicio HVO+.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public interface CobroHVO {

  /**
   * Metodo que realiza el cobro al suscriptor de una suscripcion de HVO Max.
   * Compara el saldo disponible del suscriptor con el monto del cobro y 
   * devuelve un mensaje segun el resultado.
   * 
   * @param cliente El cliente al que se le cobra el servicio.
   * @return El estado del cobro, si fue aprobado o rechazado.
   */
  String cobro(Cliente cliente);

}