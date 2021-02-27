package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import service.ServiceSquadra;

public class Utility {
	private Utility() {
	};

	private static Utility instance = null;

	public static Utility getInstance() {
		if (instance == null)
			instance = new Utility();
		return instance;
	}

	Scanner input = new Scanner(System.in);
	ServiceSquadra service = new ServiceSquadra();

	public String leggiStringa(String messaggio) {

		System.out.println(messaggio);
		String stringa = input.nextLine();

		return stringa;
	}

	public int leggiIntero(String messaggio) {
		int i = 0;
		try {
			System.out.println(messaggio);
			i = Integer.parseInt(input.nextLine());

		} catch (Exception e) {
			System.out.println("Hai inserito lettere invece di numeri");
			i = leggiIntero(messaggio); // ricorsione
		}
		return i;
	}

	public Date leggiData() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/gg");

		String dataPartita;
		System.out.println("Inserisci la data della partita (aaaa/mm/gg) :");
		dataPartita = input.nextLine();
		try {

			Date dataPartita1 = (sdf.parse(dataPartita));
			Calendar cal = Calendar.getInstance(); // Riporta la data nel suo formato

			System.out.println("La data della partita è : " + cal.getTime());

			System.out.println("Il giorno della partita è : " + cal.get(Calendar.DAY_OF_MONTH));
			System.out.println("Il mese della partita è : " + (cal.get(Calendar.MONTH) + 1));
			System.out.println("L'anno della  partita è : " + cal.get(Calendar.YEAR));

			return dataPartita1;
		} catch (Exception e) {
			System.out.println("La data della partita non è corretta Inserire nel formato aaaa/mm/gg");
		}
		return null;
	}


}
