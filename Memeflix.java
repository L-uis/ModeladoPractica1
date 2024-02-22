
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Memeflix implements ServicioStreaming{

  private LinkedList<Suscriptor> suscriptoresActivos;

  private LinkedList<Suscriptor> suscriptoresInactivos;

  private LinkedList<String> recomendaciones;

  private LinkedList<String> tiposDeSuscripcion;

  private CobroMemeflix cobro;

  public Memeflix(){

    suscriptoresActivos = new LinkedList<Suscriptor>();
    
    suscriptoresInactivos = new LinkedList<Suscriptor>();
    
    recomendaciones = new LinkedList<String>();
    
    tiposDeSuscripcion = new LinkedList<>();

    tiposDeSuscripcion.add("Sucripcion de Memeflix para un dispositivo");

    tiposDeSuscripcion.add("Sucripcion de Memeflix para dos dispositivos");

    tiposDeSuscripcion.add("Sucripcion de Memeflix para cuatro dispositivos");

    recomendaciones.add("Luther: Cae la noche");

    recomendaciones.add("La sociedad de la nieve");
    
    recomendaciones.add("One Piece");

  }

  @Override
  public void registrar(Cliente cliente, String tipoDeSuscripcion) {

    if (!tiposDeSuscripcion.contains(tipoDeSuscripcion)) {

      throw new IllegalArgumentException("Tipo de suscripcion invalido");

    }

    Suscriptor suscriptor = new Suscriptor(cliente, tipoDeSuscripcion);

    if (!suscriptoresActivos.contains(suscriptor) && !suscriptoresInactivos.contains(suscriptor) ) {
      
      suscriptoresActivos.add(suscriptor);

      Cliente clienteActual = suscriptor.getCliente();

      clienteActual.anadirRegistro(clienteActual.getNombre() + " bienvenido a Memeflix");

      cobro(clienteActual);

    } else if (suscriptoresInactivos.contains(suscriptor)) {

      suscriptoresActivos.add(suscriptor);

      suscriptoresInactivos.remove(suscriptor);

      Cliente clienteActual = suscriptor.getCliente();

      clienteActual.anadirRegistro("Bienvenido de vuelta " + clienteActual.getNombre());
    }

  }
  
  public void remover(Cliente cliente, String tipoDeSuscripcion){

    Suscriptor suscriptor = new Suscriptor(cliente, tipoDeSuscripcion);
    
    if(suscriptoresActivos.contains(suscriptor)){
    
      suscriptoresActivos.remove(suscriptor);
    
      suscriptoresInactivos.add(suscriptor);
    
    }

  }

  public void notificar(){
    
    for(Suscriptor s : suscriptoresActivos){
    
      Cliente cliente = s.getCliente();
    
      try {
    
        cliente.actualizar(getRecomendacion());
    
      } catch (IOException e) {
    
        e.printStackTrace();
      }

    }

  }

  @Override
  public void cobro(Cliente cliente) {

    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

    Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor); 
    
    String tipoSuscripcion = suscriptor.getTipoSuscripcion();
    
    if (tipoSuscripcion.equals("Sucripcion de Memeflix para un dispositivo")){
      
      cobro = new MemeflixUnDispositivo();

    }if (tipoSuscripcion.equals("Sucripcion de Memeflix para dos dispositivos")) {
      
      cobro = new MemeflixDosDispositivos();

    }if (tipoSuscripcion.equals("Sucripcion de Memeflix para cuatro dispositivos")) {
      
      cobro = new MemeflixCuatroDispositivos();
    }

    String estadoDelCobro = cobro.cobro(cliente);

    String rechazado = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

    if (estadoDelCobro.equals(rechazado)) {

      cliente.anadirRegistro(rechazado);
      
      suscriptor.setTipoDeSuscripcion("Inactivo");

      this.remover(cliente, tipoSuscripcion);

    } else {

      cliente.anadirRegistro(estadoDelCobro);

    }
    
  }

  @Override
  public String getRecomendacion() {
    Random rand = new Random();
        
    int randomNumber = rand.nextInt(3);

    return "Esta es mi recomendacion de Memeflix:" + recomendaciones.get(randomNumber);
  
  }

  @Override
  public String getNombre() {
    return "Esto es Memeflix!!!!!";
  }

  public class Suscriptor {
    
    int antiguedad;
    
    String tipoSuscripcion;

    Cliente cliente;

    public Suscriptor(Cliente cliente){

      this.cliente = cliente;

    }

    public Suscriptor(Cliente cliente, String tipoDeSuscripcion){

      this.cliente = cliente;
      
      this.tipoSuscripcion = tipoDeSuscripcion;
      
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

    public void setTipoDeSuscripcion(String cadena){

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
