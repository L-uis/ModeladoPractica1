
import java.io.IOException;
import java.util.LinkedList;

public class Memeflix implements ServicioStreaming{
  
  private String nombre;

  private LinkedList<Suscriptor> suscriptores;

  private LinkedList<String> recomendaciones;

  private CobroMemeflix cobro;

  @Override
  public void registrar(Suscriptor s, String tipoDeSuscripcion) {

  }
  
  public void remover(Suscriptor s){

  }

  public void notificar(){

  }

  @Override
  public void cobro(Suscriptor suscriptor) {

    String tipoSuscripcion = suscriptor.getTipoDeSuscripcionHVO();
    
    if (tipoSuscripcion.equals("Sucripcion de Memeflix para un dispositivo")) {

      cobro = new MemeflixUnDispositivo();

    }if (tipoSuscripcion.equals("Sucripcion de Memeflix para dos dispositivos")) {
      
      cobro = new MemeflixDosDispositivos();

    }if (tipoSuscripcion.equals("Sucripcion de Memeflix para cuatro dispositivos")) {
      
      cobro = new MemeflixCuatroDispositivos();
    }

    String estadoDelCobro = cobro.cobro(suscriptor);
    String rechazado = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

    if (estadoDelCobro.equals(rechazado)) {

      try {

        Escritor.escribirTXT(estadoDelCobro);

      } catch (IOException e) {

        System.out.println("Error: " + e);

      }

      this.remover(suscriptor);

    } else {

      try {

        Escritor.escribirTXT(estadoDelCobro);

      } catch (IOException e) {

        System.out.println("Error: " + e);

      }
      
    }
  }

  @Override
  public String getRecomendacion() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecomendacion'");
  }

  @Override
  public String getNombre() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
  }

  
}
