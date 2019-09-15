package designPattern.singleton;

 class Fib {       // classe che implementa un Singleton
  private static Fib obj = new Fib();  // istanza di Fib
  private int[] x = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233};
  private int i;

  private Fib() {                     // costruttore privato
    i = 3;
  }
  public static Fib getInstance() {   // metodo della classe
    return obj;                       // restituisce l'unica istanza
  }
  public int getValue() {
    if (i<11) i++;
    return x[i-1];
  }
  public void revert() {
    i = 0;
  }
}

public class TestFib {

  public static void main(String[] args) {
    // richiede una istanza di Fib
    Fib f = Fib.getInstance();
    
    System.out.print("f "+f.getValue());
    System.out.println(" "+f.getValue());
    
    // richiede una nuova istanza
    Fib f2 = Fib.getInstance();
    
    System.out.print("f2 "+f2.getValue());
    System.out.println(" "+f2.getValue());
        
    // Si ha un errore a compile-time con:
    // Fib f3 = (Fib) f2.clone();
    // Fib f4 = new Fib();
  }
}

