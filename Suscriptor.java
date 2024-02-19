import java.util.LinkedList;

/**
 * Clase que simula un suscriptor.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class Suscriptor implements Observador{

  private String nombre;

  private double saldo;

  private LinkedList<ServicioStreaming> servicios;

  private ServicioStreaming servicioActual;

  private int antiguedadMemeflix;

  private int antiguedadMomazon;

  private int antiguedadSpootify;

  private int antiguedadHVO;

  private int antiguedadThisney;

  private String tipoDeSuscripcionMemeflix;

  private String tipoDeSuscripcionMomazon;

  private String tipoDeSuscripcionSpootify;

  private String tipoDeSuscripcionHVO;

  private String tipoDeSuscripcionThisney;

  private String recomendacion;

  /**
   * Constructor de la clase suscriptor.
   * 
   * @param nombre El nombre del suscriptor.
   * @param saldo El saldo del suscriptor.
   */
  public Suscriptor(String nombre, double saldo){

    this.nombre = nombre;

    this.saldo = saldo;

  }

  /**
   * Metodo que suscribe al suscriptor a un servicio de streaming.
   * 
   * @param servicio El servicio al que se desea suscribir.
   */
  public void agregarSuscripcion(ServicioStreaming servicio, String tipoDeSuscripcion){

    servicio.registrar(this,tipoDeSuscripcion);

    servicios.add(servicio);
    
  }

  /**
   * Metodo que desuscribe al suscriptor de un servicio de streaming.
   * 
   * @param servicio
   */
  public void eliminarSuscripcion(ServicioStreaming servicio){

    int posicion;

    posicion = servicios.indexOf(servicio);
  
    servicios.remove(posicion);
  }

  public String getNombre(){
  
    return this.nombre;
  
  }

  public double getSaldo(){

    return this.saldo;

  }
  
  public int getAntiguedadMemeflix(){

    return antiguedadMemeflix;
  
  }

  public int getAntiguedadHVO(){
  
    return antiguedadHVO;
  
  }
  
  public int getAntiguedadMomazon(){
  
    return antiguedadMomazon;
  
  }
  
  public int getAntiguedadSpootify(){
  
    return antiguedadSpootify;
  
  }
  
  public int getAntiguedadThisney(){
  
    return antiguedadThisney;
  
  }
  
  public void setAntiguedadMemeflix(int nuevaAntiguedad){
  
    this.antiguedadMemeflix = nuevaAntiguedad;
  
  }
  
  public void setAntiguedadHVO(int nuevaAntiguedad){
  
    this.antiguedadHVO = nuevaAntiguedad;
  
  }
  
  public void setAntiguedadMomazon(int nuevaAntiguedad){
  
    this.antiguedadMomazon = nuevaAntiguedad;
  
  }
  
  public void setAntiguedadSpootify(int nuevaAntiguedad){
  
    this.antiguedadSpootify = nuevaAntiguedad;
  
  }
  
  public void setAntiguedadThisney(int nuevaAntiguedad){
  
    this.antiguedadThisney = nuevaAntiguedad;
  
  }

  
  public String getTipoDeSuscripcionHVO(){
  
    return tipoDeSuscripcionHVO;
  
  }
  
  public String getTipoDeSuscripcionMemeflix(){
  
    return tipoDeSuscripcionMemeflix;
  
  }
  
  public String getTipoDeSuscripcionMomazon(){
  
    return tipoDeSuscripcionMomazon;
  
  }
  
  public String getTipoDeSuscripcionSpootify(){
  
    return tipoDeSuscripcionSpootify;
  
  }
  
  public String getTipoDeSuscripcionThisney(){
  
    return tipoDeSuscripcionThisney;
  
  }
  
  public void setTipoDeSuscripcionHVO(String nuevoTipoDeSuscripcion){
  
    this.tipoDeSuscripcionHVO = nuevoTipoDeSuscripcion;
  
  }
  
  public void setTipoDeSuscripcionMemeflix(String nuevoTipoDeSuscripcion){
  
    this.tipoDeSuscripcionMemeflix = nuevoTipoDeSuscripcion;
  
  }
  
  public void setTipoDeSuscripcionMomazon(String nuevoTipoDeSuscripcion){
  
    this.tipoDeSuscripcionMomazon = nuevoTipoDeSuscripcion;
  
  }
  
  public void setTipoDeSuscripcionSpootify(String nuevoTipoDeSuscripcion){
  
    this.tipoDeSuscripcionSpootify = nuevoTipoDeSuscripcion;
  
  }
  
  public void setTipoDeSuscripcionThisney(String nuevoTipoDeSuscripcion){
  
    this.tipoDeSuscripcionThisney = nuevoTipoDeSuscripcion;
  
  }
  
  public void actualizarServicio(String servicio){

    for (ServicioStreaming servicioStreaming : servicios) {

      String nombreDelServicio = servicioStreaming.getNombre();

      if (nombreDelServicio.equals(servicio)) {
        servicioActual = servicioStreaming;
      }
    
    }
  
  }

  @Override
  public void actualizar() {
 
    this.recomendacion = servicioActual.getRecomendacion();
    
    verRecomendacion();
  
  }

  public void verRecomendacion(){

  }
}
