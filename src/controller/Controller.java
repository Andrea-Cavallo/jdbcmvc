package controller;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import exception.FieldException;
import exception.FormatErrorException;
import exception.RecordNonTrovatoException;
import exception.RequiredFieldException;
import model.Campionato;
import model.Classifica;
import model.Giocatore;
import model.Marcatori;
import model.Partita;
import model.Serie;
import model.Squadra;
import service.ServiceCampionato;
import service.ServiceClassifica;
import service.ServiceGiocatore;
import service.ServiceMarcatori;
import service.ServicePartita;
import service.ServiceSerie;
import service.ServiceSquadra;
import utility.Utility;
import view.View;

public class Controller {

	public static void main(String[] args) {

		int scelta = 0;
		do {
			// controller decide la view da visualizzaare in base all input..
			// il visualizza menu mi restitusce una scelta che deve esser valida
			View.getInstance().visualizzaMenu();
			scelta = Utility.getInstance().leggiIntero("inserisci la scelta del menu");
			System.out.println("");
			eseguiOperazione(scelta);

			if (scelta == 31)
				break;

			System.out.println("");
			System.out.println("ripetere l'operazione? a) si - b)altro tasto per tornare al menu");

			String risposta = Utility.getInstance().leggiStringa("");

			if (risposta.equalsIgnoreCase("si"))

				eseguiOperazione(scelta);

		} while (scelta != 31);
	}

	public static void eseguiOperazione(int n) {

		ServiceSquadra sService = new ServiceSquadra();
		ServiceGiocatore gService = new ServiceGiocatore();
		ServiceMarcatori mService = new ServiceMarcatori();
		ServicePartita pService = new ServicePartita();
		ServiceClassifica classService = new ServiceClassifica();
		ServiceCampionato campService = new ServiceCampionato();
		ServiceSerie serieServ = new ServiceSerie();

		switch (n) {
		case 1:
			// squadra s = View me lo restiusce gia lui perche se te la fai resture...
			Squadra s = new Squadra();

			s = View.getInstance().mascheraInserimentoSquadra();

			try {
				sService.insert(s);

			} catch (FormatErrorException | RequiredFieldException e1) {
				System.err.println("Errore sulla validazione dei dati ");
				System.out.println(e1.getField());
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore sull inserimento dei dati ");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 2:
			int idSquadra = Utility.getInstance().leggiIntero("Inserisci id della squadra da cancellare: ");
			try {
				sService.delete(idSquadra);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			String s1 = Utility.getInstance().leggiStringa("Inserisci Nome Squadra da cercare");
			try {

				Squadra temp = sService.findByName(s1);
				System.out.println(temp);
			} catch (SQLException e6) {
				e6.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (RecordNonTrovatoException e) {
				System.err.println(e.getDescrizione());
			}
			break;
		case 4:

			Squadra temp = View.getInstance().mascheraUpdateSquadra();

			try {

				if (temp.getNome() != null && !temp.getNome().trim().equals("")) {
					Squadra temp2 = sService.findByName(temp.getNome());
					if (temp.getCitta() != null && !temp.getCitta().trim().equals("")) {
						temp2.setCitta(temp.getCitta());

					}

					if (temp.getCapienzaStadio() != null) {
						temp2.setCapienzaStadio(temp.getCapienzaStadio());
					}

				} else {
					System.out.println("Il nome deve essere valorizzato!");
				}
			} catch (ClassNotFoundException e5) {
				e5.printStackTrace();
			} catch (SQLException e5) {
				e5.printStackTrace();
			} catch (RecordNonTrovatoException e5) {
				System.err.println("Non esiste una squadra con quel nome!");
				e5.printStackTrace();
			}

			;
			break;
		case 5:
			try {
				sService.findAll();
				System.out.println();
			} catch (SQLException e4) {
				e4.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 6:
			Giocatore g = new Giocatore();

			g = View.getInstance().mascheraInserimentoGiocatore();

			try {
				gService.inserisciGiocatore(g);

			} catch (RequiredFieldException e1) {
				System.err.println("Errore sulla validazione dei dati ");
				System.out.println(e1.getField());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 7:
			int idgioc = Utility.getInstance().leggiIntero("Inserisci l id del giocatore da Cancellare dal Database: ");
			try {
				gService.deleteGiocatore(idgioc);
			} catch (SQLException e3) {
				e3.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 8:

			try {
				gService.visualizzaGiocatori();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 9:

			int n3 = Utility.getInstance().leggiIntero("Inserisci l'id del giocatore da cercare: ");
			try {
				gService.ricercaGiocatore(n3);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (RecordNonTrovatoException e) {
				System.out.println(e.getDescrizione());
				e.printStackTrace();
			}

			break;
		case 10:
			String scelta1 = "";

			scelta1 = Utility.getInstance().leggiStringa(
					"Inserisci il campo che vuoi modificare del giocatore: \n nome --- cognome --- idsquadra, Nota X per uscire: ");

			if (scelta1.equalsIgnoreCase("nome")) {
				String nuovoNome = Utility.getInstance().leggiStringa("Inserisci il nuovo Nome del giocatore: ");
				int id = Utility.getInstance().leggiIntero("Inserisci l 'id del giocatore che vuoi modificare: ");
				try {
					gService.updateGiocatoreNome(nuovoNome, id);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else if (scelta1.equalsIgnoreCase("cognome")) {
				String nuovoCognome = Utility.getInstance().leggiStringa("Inserisci il nuovo cognome del giocatore: ");
				int id = Utility.getInstance().leggiIntero("Inserisci l 'id del giocatore che vuoi modificare: ");
				try {
					gService.updateGiocatoreCognome(nuovoCognome, id);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else if (scelta1.equalsIgnoreCase("idsquadra")) {
				int idSquadra1 = Utility.getInstance().leggiIntero("Inserisci il nuovo id Squadra del giocatore: ");
				int id = Utility.getInstance().leggiIntero("Inserisci l 'id del giocatore che vuoi modificare: ");
				try {
					gService.updateGiocatoreIdSquadra(idSquadra1, id);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			;
			break;
		case 11:
			Marcatori m = new Marcatori();

			m = View.getInstance().mascheraInserimentoMarcatori();

			try {
				mService.inserisciMarcatore(m);

			} catch (FormatErrorException e) {
				System.err.println("Errore sulla validazione dei dati ");
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore sull inserimento dei dati ");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 12:
			int idGioc = Utility.getInstance().leggiIntero("Inserisci l 'id del Giocatore ");
			ArrayList<Marcatori> mList = null;
			try {
				mList = mService.ricercaPerIdMarcatori(idGioc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(mList);
			break;

		case 13:
			int numeroGol = Utility.getInstance().leggiIntero("Inserisci il numero dei gol: ");
			try {
				mService.mostraMarcatoriPerGol(numeroGol);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (RecordNonTrovatoException e) {
				System.out.println(e.getDescrizione());
				e.printStackTrace();
			}
			break;
		case 14:
			int idPassato = Utility.getInstance().leggiIntero("Inserisci L'id del marcatore da eliminare: ");
			try {
				mService.deleteMarcatore(idPassato);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 15:
			int retiNuove = Utility.getInstance().leggiIntero("Correggi le reti inserisci il nuovo valore: ");
			int retiVecchie = Utility.getInstance().leggiIntero("Al posto delle reti_ ");
			try {
				mService.updateGolFatti(retiNuove, retiVecchie);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 16:
			try {
				mService.creaViewDeiBomber();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			int toPnr = Utility.getInstance().leggiIntero("Limita la visualizzazione, Voui vedere i TOP 10? , 5? 1? ");
			try {
				mService.visualizzaIprimi10Bomber(toPnr);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
			} catch (RecordNonTrovatoException e) {
				e.printStackTrace();
			}
			break;
		case 17:
			try {
				mService.mediaGolFatti();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 18:
			Classifica c = new Classifica();

			c = View.getInstance().mascheraInserimentoClassifica();

			try {
				classService.inserisciClassifica(c);
				System.out.println("l'operazione ha avuto un esito positivo");
			} catch (FormatErrorException e) {

				System.err.println("Errore sulla validazione dei dati ");
				e.printStackTrace();
			} catch (SQLException e) {

				System.err.println("Errore sull inserimento dei dati ");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 19:
			int idPass = Utility.getInstance().leggiIntero("Inserisci l 'id della Classifica ");
			ArrayList<Classifica> cList = null;
			try {
				cList = classService.ricercaPerIdClassifica(idPass);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(cList);
			break;
		case 20:
			int puntiNuovi = Utility.getInstance().leggiIntero("Aggiorna il punteggio : ");
			int idClass = Utility.getInstance().leggiIntero("Inserisci l'id della Classifica da modificare ");
			try {
				classService.updateClassifica(puntiNuovi, idClass);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 21:
			String descrizione = Utility.getInstance()
					.leggiStringa("Inserisci la Serie per visualizzare la classifica");

			try {
				classService.visualizzaClassificaPerSerie(descrizione);

			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 22:
			Partita p = new Partita();

			p = View.getInstance().mascheraInserimentoPartita();

			try {
				pService.inserisciPartita(p);

			} catch (FieldException e) {
				e.printStackTrace();
				System.err.println("Errore sulla validazione dei dati ");

			} catch (SQLException e) {
				System.err.println("Errore sull inserimento dei dati ");

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 23:
			try {
				pService.ricercaPartitaConDate();
			} catch (SQLSyntaxErrorException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 24:
			View.getInstance().inizioGioco();
			break;
		case 25:
			Serie s3 = new Serie();
			s3 = View.getInstance().mascheraInserimentoSerie();

			try {
				serieServ.inserisciSerie(s3);

			} catch (FieldException e) {
				e.printStackTrace();
				System.err.println("Errore sulla validazione dei dati ");

			} catch (SQLException e) {
				System.err.println("Errore sull inserimento dei dati ");

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 26:
			String nome = Utility.getInstance().leggiStringa("Inserisci una nuova Descrizione per la Serie: ");
			int id = Utility.getInstance().leggiIntero("Inserisci l 'iD della serie da modificare..");
			try {
				serieServ.modificaDescrizione(nome, id);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 27:
			Campionato camp = new Campionato();

			camp = View.getInstance().mascheraInserimentoCampionato();

			try {
				campService.inserisciCampionato(camp);

			} catch (FieldException e) {
				e.printStackTrace();
				System.err.println("Errore sulla validazione dei dati ");

			} catch (SQLException e) {
				System.err.println("Errore sull inserimento dei dati ");

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 28:
			int nuovoanno = Utility.getInstance().leggiIntero("Inserisci il nuovo anno: ");
			int id1 = Utility.getInstance().leggiIntero("Inserisci l 'iD del campionato da modificare..");
			try {
				campService.modificaAnno(nuovoanno, id1);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;
		case 29:
			int idPassato1 = Utility.getInstance().leggiIntero("Inserisci l 'id del Campionato ");
			ArrayList<Campionato> campList = null;
			try {
				campList = campService.ricercaCampionato(idPassato1);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(campList);
			break;
		case 30:
			int idPassato2 = Utility.getInstance().leggiIntero("Inserisci L'id del campionato da eliminare: ");
			try {
				campService.deleteCampionato(idPassato2);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			break;

		default:
		}
	}
}
