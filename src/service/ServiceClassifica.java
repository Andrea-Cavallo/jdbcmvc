package service;

import java.sql.SQLException;
import java.util.ArrayList;

import crud.ClassificaCrud;
import exception.FormatErrorException;
import model.Classifica;

public class ServiceClassifica {

	ClassificaCrud cs = new ClassificaCrud();

	public void controllaInserimentoClassifica(int id_campionato, int id_squadra, int posizione, int punti,
			int gol_fatti, int gol_subiti) throws FormatErrorException {

		if (posizione == 0 || posizione > 28) {
			throw new FormatErrorException("la posizione deve essere compresa tra 1 e 28 essendo le squadre 28 ");
		}
		if (punti > 160) {
			throw new FormatErrorException("Impossibile raggiungere questo punteggio! ");
		}
		if (gol_fatti > 1000) {
			throw new FormatErrorException("inserisci gol fatti < 1000 ");
		}
		if (gol_subiti > 1000) {
			throw new FormatErrorException("inserisci gol subiti < 1000 ");
		}
		
	

	}

	public void visualizzaClassificaPerSerie(String descrizione) throws  SQLException, ClassNotFoundException {
		cs.visualizzaClassificaPerSerie(descrizione);
	}

	public void inserisciClassifica(Classifica c) throws FormatErrorException, SQLException, ClassNotFoundException {
		controllaInserimentoClassifica(c.getId_campionato(), c.getId_squadra(), c.getPosizione(), c.getPunti(),
				c.getGol_fatti(), c.getGol_subiti());
		cs.inserisciClassifica(c);

	}

	public ArrayList<Classifica> ricercaPerIdClassifica(int idPass) throws SQLException, ClassNotFoundException {
		return cs.ricercaClassifica(idPass);
	}

	public void updateClassifica(int puntiNuovi, int idClass) throws SQLException, ClassNotFoundException {
		cs.updateClassifica(puntiNuovi, idClass);
	}
}
