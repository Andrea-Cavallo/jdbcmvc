package service;

import java.sql.SQLException;
import crud.GiocatoreCrud;
import exception.RecordNonTrovatoException;
import exception.RequiredFieldException;
import model.Giocatore;

public class ServiceGiocatore {
	GiocatoreCrud gc = new GiocatoreCrud();

	public void controllaInserimentoGiocatore(String nome, String cognome) throws RequiredFieldException {

		if (nome == null || nome.trim().equals("")) {
			throw new RequiredFieldException("nome Giocatore mancante ");

		}
		if (cognome == null || cognome.trim().equals("")) {

			throw new RequiredFieldException("cognome Giocatore mancante ");
		}

	}

	public void inserisciGiocatore(Giocatore g) throws RequiredFieldException, ClassNotFoundException, SQLException {
		controllaInserimentoGiocatore(g.getNome(), g.getCognome());
		
			gc.inserisciGiocatore(g);
		
	}

	public void deleteGiocatore(int idgioc) throws SQLException, ClassNotFoundException {
		gc.deleteGiocatore(idgioc);
	}

	public void visualizzaGiocatori() throws SQLException, ClassNotFoundException {
		gc.visualizzaGiocatori();
	}

	public void ricercaGiocatore(int n3) throws  SQLException, ClassNotFoundException, RecordNonTrovatoException {
		gc.ricercaGiocatore(n3);
	}

	public void updateGiocatoreNome(String nuovoNome, int id) throws SQLException, ClassNotFoundException {
		gc.updateGiocatoreNome(nuovoNome, id);
	}

	public void updateGiocatoreCognome(String nuovoCognome, int id) throws SQLException, ClassNotFoundException {
		gc.updateGiocatoreCognome(nuovoCognome, id);
	}

	public void updateGiocatoreIdSquadra(int idSquadra, int id) throws SQLException, ClassNotFoundException {
		gc.updateGiocatoreIdSquadra(idSquadra, id);
	}
}
