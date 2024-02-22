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

    tiposDeSuscripcion.add("Sucripcion de Momazon normal");

    tiposDeSuscripcion.add("Sucripcion de Momazon premium");
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

      clienteActual.anadirRegistro(clienteActual.getNombre() + " bienvenido a Momazon");

      cobro(clienteActual);

    } else if (suscriptoresInactivos.contains(suscriptor)) {

      suscriptoresActivos.add(suscriptor);

      suscriptoresInactivos.remove(suscriptor);

      Cliente clienteActual = suscriptor.getCliente();

      clienteActual.anadirRegistro("Bienvenido de vuelta " + clienteActual.getNombre());
    }
  }

  public void remover(Cliente c, String tipoDeSuscripcion){
      Suscriptor suscriptor = new Suscriptor(c,tipoDeSuscripcion);
    if(suscriptoresActivos.contains(suscriptor)){
        suscriptoresActivos.remove(suscriptor);
        suscriptoresInactivos.add(suscriptor);
    }
  }

  public void notificar(){

  }

  @Override
  public void cobro(Cliente cliente) {
    Suscriptor buscaSuscriptor = new Suscriptor(cliente);

    int indiceDelSuscriptor = suscriptoresActivos.indexOf(buscaSuscriptor);

    Suscriptor suscriptor = suscriptoresActivos.get(indiceDelSuscriptor); 
    
    String tipoSuscripcion = suscriptor.getTipoSuscripcion();
    
    if (tipoSuscripcion.equals("Sucripcion de Momazon normal")){
      
      cobro = new MomazonNormal();

    }if (tipoSuscripcion.equals("Sucripcion de Momazon premium")) {
      
      cobro = new MomazonPremium();
    }
    
    String estadoDelCobro = cobro.cobro(cliente);

    String rechazado = "El pago a sido rechazado, se cancelara la suscripcion del servicio";

    if (estadoDelCobro.equals(rechazado)) {

      cliente.anadirRegistro(rechazado);
      
      suscriptor.setTipoDeSuscripcion("Inactivo");

      this.remover(cliente);

    } else {

      cliente.anadirRegistro(estadoDelCobro);

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
