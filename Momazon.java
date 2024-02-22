import java.io.IOException;
import java.util.LinkedList;

public class Momazon implements ServicioStreaming{

  private LinkedList<Suscriptor> suscriptoresActivos;

  private LinkedList<Suscriptor> suscriptoresInactivos;

  private LinkedList<String> recomendaciones;

  private LinkedList<String> tiposDeSuscripcion;

  private CobroMomazon cobro;

  public Momazon(){
    
    suscriptoresActivos = new LinkedList<Suscriptor>();
    
    suscriptoresInactivos = new LinkedList<Suscriptor>();
    
    recomendaciones = new LinkedList<String>();
    
    tiposDeSuscripcion = new LinkedList<>();
  }
  @Override
  public void registrar(Cliente s, String tipoDeSuscripcion) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registrar'");
  }

  public void remover(Cliente s){

  }

  public void notificar(){

  }

  @Override
  public void cobro(Cliente suscriptor) {
    
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

  public class Suscriptor{
    int antiguedad;
    
    String tipoSuscripcion;

    Cliente cliente;

    public Suscriptor(Cliente cliente){
      this.cliente = cliente;
    }

    public Suscriptor(Cliente cliente, String tipoSuscripcion){
      
      this.cliente = cliente;

      this.tipoSuscripcion = tipoSuscripcion;

      this.antiguedad = 0;
    }

    private Cliente getCliente(){
      
      return this.cliente;

    }

    public String getTipoSuscripcion(){
      
      return this.tipoSuscripcion;

    }

    public int getAntiguedad(){
    
      return this.antiguedad;
    
    }

    public void setTipoSuscripcion(String cadena){

      this.tipoSuscripcion = cadena;

    }

    public void aumentarAntiguedad(){
      
      antiguedad++;

    }

    @Override
    public boolean equals(Object obj){
      if (obj instanceof Suscriptor) {
        
        Suscriptor suscriptor = (Suscriptor) obj;

        if (this.cliente.equals(suscriptor.getCliente())) {

          return true;
  
        }else{
  
          return false;
  
        }
      } else {

        return false;

      }
  }

  }

}
