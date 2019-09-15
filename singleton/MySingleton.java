package designPattern.singleton;


class Randomize{
	//oggetto privato
	private static Randomize instance;
	//stato dell'oggetto privato
	private static int random;	//costruttore privato
	private static int lower;
	private static int upper;
	private Randomize(){
		random=(int)(Math.random()*(upper-lower)+lower+1);//genera un numero intero compreso tra lower e upper
	}
	public static Randomize getInstance(){
		if(instance==null) instance=new Randomize();
		return instance;
	}
	public static void setBounds(int _lower, int _upper){
		if(instance==null){
			lower=_lower;
			upper=_upper;
		}else{
			System.err.println("Attenzione: è possibile settare l'intervallo solo prima di aver richiesto la prima volta l'istanza");
		}
	}
	public static void getNumber(){
		System.out.println(random);
	}
}

public class MySingleton{
	public static void main(String[] args){
		Randomize obj1, obj2;
		Randomize.setBounds(10,203);
		obj1=Randomize.getInstance();
		//Randomize.setBounds(10,203); //fallisce in esecuzione
		//obj=new Randomize(); //fallisce in compilazione
		obj1.getNumber();
		obj2=Randomize.getInstance();
		obj2.getNumber(); //obj1==obj2

	}
}