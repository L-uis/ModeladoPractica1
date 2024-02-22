
/**
 * Clase que permite hacer el cobro de una suscripcion de Memeflix.
 * Implementa la interface CobroMemeflix para definir el método cobro.
 * Usa una constante para el monto del cobro y dos cadenas para los 
 * mensajes de aprobado y rechazado.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 */
public interface CobroMemeflix {

  /**
   * Metodo que realiza el cobro al suscriptor de una suscripcion de Memeflix.
   * Compara el saldo disponible del suscriptor con el monto del cobro y 
   * devuelve un mensaje según el resultado.
   * 
   * @param cliente El cliente al que se le cobra el servicio.
   * @return El estado del cobro, si fue aprobado o rechazado.
   */
  String cobro(Cliente cliente);
  
}
