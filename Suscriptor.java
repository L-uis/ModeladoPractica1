
/**
 * 
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class Suscriptor implements Observador{

  private String nombre;

  private double saldo;

  private ServicioStreaming servicio;

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

  public Suscriptor(String nombre, double saldo){

    this.nombre = nombre;

    this.saldo = saldo;

  }

  public void actualizar() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
  }

}
