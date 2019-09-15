/*Simulazione di un pallottoliere in cui vi sono diversi partecipanti che attendono
l'estrazione di un numero. Se il numero assegnato corrisponde con quello estratto, viene tirato fuori dal match*/

package designPattern.observer.pallottoliere;

import java.util.Observable;
import java.util.Observer;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

class Pallottoliere extends Observable {

	public Pallottoliere() {
		System.out.println("Benvenuti al pallottoliere!");
	}
	public void estraiNumero() {
		int nuovaEstrazione = (int)(Math.random() * 90 + 1);
		System.out.println("-------------------------------------------------");
		System.out.println("E' stato estratto il numero " + nuovaEstrazione);
		System.out.println("----------------Partecipanti colpiti-------------");
		this.setChanged();
		this.notifyObservers(nuovaEstrazione);
	}
}

class Partecipanti implements Observer {

	private final int myNumber = (int)(Math.random() * 90 + 1);
	public Partecipanti() {
		//System.out.println("Aggiunto nuovo partecipante. Numero assegnato: " + myNumber);
	}
	public void update(Observable pallottoliere, Object extractedNumber) {

		if (myNumber == (int)extractedNumber) {
			System.out.println("colpito");
			pallottoliere.deleteObserver(this);
			Main.elencoPartecipanti.remove(this);
		}
	}
}

public class Main {
	public static LinkedList<Partecipanti> elencoPartecipanti = new LinkedList<Partecipanti>();
	private static Pallottoliere pallottoliere = new Pallottoliere();
	private static void aggiungiPartecipanti(final int numeroPartecipanti) {
		for (int i = 0; i < numeroPartecipanti; i++) {
			Partecipanti nuovo = new Partecipanti();
			elencoPartecipanti.add(nuovo);
			pallottoliere.addObserver(nuovo);
		}
	}
	public static void main(String[] args) {

		aggiungiPartecipanti(10000);
		while (elencoPartecipanti.size() > 0) {
			pallottoliere.estraiNumero();
			/*try{
				Thread.sleep(1000);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}*/
		}
	}
}