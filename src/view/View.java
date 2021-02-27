package view;

import java.util.Random;
import java.util.Scanner;
import model.Campionato;
import model.Classifica;
import model.Giocatore;
import model.Marcatori;
import model.Partita;
import model.Serie;
import model.Squadra;

import utility.Utility;

public class View {

	private View() {
	};

	private static View instance = null;

	public static View getInstance() {
		if (instance == null)
			instance = new View();
		return instance;
	}

	static Scanner input = new Scanner(System.in);

	public Campionato mascheraInserimentoCampionato() {

		Campionato c = new Campionato();

		c.setId(Utility.getInstance().leggiIntero("Inserisci Id del Campionato"));
		c.setAnno(Utility.getInstance().leggiIntero("Inserisci l'anno del Campionato: "));
		c.setId_serie(Utility.getInstance().leggiIntero("Inserisci la serie del Campionato: "));
		return c;
	}

	public Squadra mascheraInserimentoSquadra() {

		Squadra squadra = new Squadra();

		squadra.setNome(Utility.getInstance().leggiStringa("Inserire nome della squadra: "));
		squadra.setCitta(Utility.getInstance().leggiStringa("Inserisci il nome della Città:  "));
		squadra.setNomeStadio(Utility.getInstance().leggiStringa("Inserisci il nome del campo:  "));
		squadra.setCapienzaStadio(Utility.getInstance().leggiIntero("Inserisci la capienza dello Stadio: "));

		return squadra;

	}

	public Squadra mascheraUpdateSquadra() {

		String scelta = "";
		Squadra squadra = new Squadra();

		System.out.println("Inserisci solo i valori che effettivamente vuoi modificare altrimenti premere Invio!");
		squadra.setNome(Utility.getInstance().leggiStringa("Inserire nome della squadra: "));
		squadra.setCitta(Utility.getInstance().leggiStringa("Inserisci il nome della Città:  "));
		squadra.setNomeStadio(Utility.getInstance().leggiStringa("Inserisci il nome del campo:  "));
		scelta = Utility.getInstance().leggiStringa("Vuoi modificare anche la capienza dello Stadio ");
		if (scelta.equalsIgnoreCase("si")) {
			squadra.setCapienzaStadio(Utility.getInstance().leggiIntero("Inserisci la capienza dello Stadio: "));
		}
		return squadra;

	}

	public Giocatore mascheraInserimentoGiocatore() {
		Giocatore g = new Giocatore();

		g.setNome(Utility.getInstance().leggiStringa("Inserire nome del Giocatore: "));
		g.setCognome(Utility.getInstance().leggiStringa("Inserisci il cognome del Giocatore:  "));
		g.setId_squadra(Utility.getInstance().leggiIntero("Inserisci l'Id della Squadra del Giocatore: "));

		return g;
	}

	public Partita mascheraInserimentoPartita() {

		Partita p = new Partita();

		p.setId_squadracasa(Utility.getInstance().leggiIntero("Inserisci L 'ID della Squadra di casa: "));
		p.setGol_squadra_casa(Utility.getInstance().leggiIntero("Inserisci Gol Squadra di casa: "));
		p.setId_squadrafuoricasa(Utility.getInstance().leggiIntero("Inserisci l 'ID della Squadra in trasferta: "));
		p.setGol_squadra_fuoricasa(Utility.getInstance().leggiIntero("Inserisci Gol Squadra fuori casa: "));
		p.setId_campionato(Utility.getInstance().leggiIntero("Inserisci l'ID del Campionato: "));
		p.setDatapartita(Utility.getInstance().leggiData());

		return p;

	}

	public Classifica mascheraInserimentoClassifica() {

		Classifica c = new Classifica();

		c.setId_campionato(Utility.getInstance().leggiIntero("Inserisci l'id del campionato: "));
		c.setId_squadra(Utility.getInstance().leggiIntero("Inserisci L'ID della squadra: "));
		c.setPosizione(Utility.getInstance().leggiIntero("Inserisci la posizione della Squadra: "));
		c.setPunti(Utility.getInstance().leggiIntero("Inserisci i punti della squadra: "));
		c.setGol_fatti(Utility.getInstance().leggiIntero("Inserisci i gol fatti dalla squadra: "));
		c.setGol_subiti(Utility.getInstance().leggiIntero("Inserisci i gol subiti dalla squadra: "));

		return c;

	}

	public Marcatori mascheraInserimentoMarcatori() {
		Marcatori m = new Marcatori();

		m.setGol_fatti(Utility.getInstance().leggiIntero("Inserisci i Gol fatti : "));
		m.setCartellini_gialli(Utility.getInstance().leggiIntero("Inserisci i Cartellini Gialli: "));
		m.setCartellini_rossi(Utility.getInstance().leggiIntero("Inserisci i Cartellini Rossi: "));
		m.setId_giocatore(Utility.getInstance().leggiIntero("Inserisci l'ID del giocatore: "));
		m.setId_campionato(Utility.getInstance().leggiIntero("Inserisci l ID del Campionato: "));

		return m;

	}

	public Serie mascheraInserimentoSerie() {
		Serie s = new Serie();

		s.setId(Utility.getInstance().leggiIntero("Inserisci l id : "));
		s.setDescrizione(Utility.getInstance().leggiStringa("Inserisci la Descrizione della Serie: "));
		return s;

	}

	public void visualizzaMenu() {
		System.out.println(
				"____________________________________________________________________________________________");
		System.out.println(
				"********************************************************************************************");
		System.out.println(
				"---------------------------QUERY SQUADRA----------------------------------------------------");
		System.out.println("1  =  Inserimento di una squadra di calcio");
		System.out.println("2  =  Cancellazione di una squadra di calcio. digitando l'id della squadra di calcio");
		System.out.println("3  =  Ricerca di una squadra di calcio per : Nome esatto della squadra ");
		System.out.println("4  =  Update nome di una Squadra di calcio con un nuovo nome ");
		System.out.println("5  =  Elenco di tutte le squadre di calcio");
		System.out.println(
				"----------------------------QUERY GIOCATORE-------------------------------------------------");
		System.out.println("6  =  Inserimento di un Giocatore ");
		System.out.println("7  =  Cancella un Giocatore");
		System.out.println("8  =  Visualizza i Giocatori");
		System.out.println("9  =  Ricerca di un Giocatore ");
		System.out.println("10 =  Update del Giocatore");
		System.out.println(
				"-----------------------------QUERY MARCATORI------------------------------------------------");
		System.out.println("11 =  Inserisci un marcatore nel DB : ");
		System.out.println("12 =  Ricerca per ID_giocatore - il Marcatore ");
		System.out.println("13 =  Mostra Marcatori in base al numero dei Gol ");
		System.out.println("14 =  Cancella Marcatore per ID  ");
		System.out.println("15 =  Update nr reti del marcatore ");
		System.out.println("16 =  Limita la TOP dei Migliori Bomber    ");
		System.out.println("17 =  Media dei Gol     ");
		System.out.println(
				"----------------------------QUERY CLASSIFICA-------------------------------------------------");
		System.out.println("18 =  Inserisci Classifica ");
		System.out.println("19 =  Ricerca Classifica");
		System.out.println("20 =  Update Classifica");
		System.out.println("21 =  Mostra Classifica in base alla Serie");
		System.out.println(
				"----------------------------QUERY PARTITA-----------------------------------------------------");
		System.out.println("22 =  Inserisci Partita");
		System.out.println("23 =  Ricerca partita con data *");
		System.out.println("24 =  Simula risultato partita passando due Squadre");
		System.out.println(
				"----------------------------QUERY SERIE-----------------------------------------------------");
		System.out.println("25 =  Aggiungi Serie");
		System.out.println("26=   Modifica nome della Serie");
		System.out.println(
				"----------------------------QUERY CAMPIONATO-----------------------------------------------------");
		System.out.println("27 =  Inserisci Campionato");
		System.out.println("28 =  Modifica Campionato ");
		System.out.println("29 =  Ricerca Campionato");
		System.out.println("30 =  Delete Campionato    ");

		System.out.println("31 = Esci Dal Programma");

		System.out.println(
				"**********************************************************************************************");

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// questi mi servono per la funzione Simula risultato... //

	static Random generator = new Random();
	public static int golSquadra1 = 0, golSquadra2 = 0;

	public void inizioGioco() {

		int golsquadraInCasa = generator.nextInt(5) + 1;
		int golsquadraFuoriCasa = generator.nextInt(5) + 1;
		String squadraInCasa = Utility.getInstance().leggiStringa("La squadra di casa");
		String squadraFuoriCasa = Utility.getInstance().leggiStringa("La squadra fuori Casa: ");
		if (squadraInCasa.equalsIgnoreCase("lazio")) {
			golsquadraInCasa = 0;
		} else if (squadraFuoriCasa.equalsIgnoreCase("lazio")) {
			golsquadraFuoriCasa = 0;
		}

		System.out.println();

		System.out.println("Il risultato della partita :  " + squadraInCasa + "--" + squadraFuoriCasa + " ("
				+ golsquadraInCasa + " : " + golsquadraFuoriCasa + ")");

	}

}
