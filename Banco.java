
import java.util.Random;

/**
 * Clase que simula una cuenta bancaria.
 * 
 * @author Mata
 * @author Hermes
 * @author Steve
 * 
 */
public class Banco {

  private Cliente titular;
  
  private double saldo;

  private String numeroDeCuenta;

  /**
   * Contructor de la clase Banco, este crea una cuenta bancaria
   * dado un cliente y un saldo no negativo, si este es negativo
   * se crea la cuenta con saldo 0.
   * 
   * @param cliente El cliente que solicita la cuenta.
   * @param saldo El saldo inicial de la cuenta.
   */
  public Banco(Cliente cliente, double saldo){

    this.titular = cliente;

    if (saldo < 0) {
      
      System.out.println("Una cuenta no puede tener saldo negativo, se creara la cuenta con un saldo 0.");

      this.saldo = 0;

    }else{

      this.saldo = saldo;

    }

    this.numeroDeCuenta = generarNumeroDeCuenta();

  }

  /**
   * Metodo que simula un pago, si el cliente no tiene saldo
   * suficiente no se realiza el pago.
   * 
   * @param monto Monto que se descontara al hacer el pago.
   */
  public void hacerPago(double monto){

    if (monto >  saldo) {

      System.out.println("No cuentas con saldo suficiente");

    }else{

      saldo -= monto;

    }

  }

  /**
   * Metodo que devuelve el saldo de la cuenta.
   * 
   * @return El saldo de la cuenta.
   */
  public double getSaldo(){

    return this.saldo;

  }

  /**
   * Metodo privado de la clase, este simula la creacion
   * de un numero de cuenta.
   * 
   * @return Una cadena con un numero de 12 digitos.
   */
  private String generarNumeroDeCuenta() {

    Random r = new Random();

    int cantidad = r.nextInt(90000) + 10000;
    
    return String.valueOf(cantidad);

  }
  
}
