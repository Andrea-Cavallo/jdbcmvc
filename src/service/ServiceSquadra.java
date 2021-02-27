package service;

import java.sql.SQLException;
import java.util.ArrayList;

import crud.SquadraCrud;
import exception.FormatErrorException;
import exception.RecordNonTrovatoException;
import exception.RequiredFieldException;
import model.Squadra;

public class ServiceSquadra {
	SquadraCrud sc = new SquadraCrud();

	public void validateInsert(Squadra s)// valida
			throws FormatErrorException, RequiredFieldException {

		if (s.getNome() == null || s.getNome().trim().equals("")) {
			throw new RequiredFieldException("nome Squadra mancante ");

		}
		if (s.getCitta() == null || s.getCitta().trim().equals("")) {

			throw new RequiredFieldException("citta mancante  ");
		}
		if (s.getNomeStadio() == null || s.getNomeStadio().trim().equals("")) {
			throw new RequiredFieldException("nome Stadio mancante");
		}
		if (s.getCapienzaStadio() < 1000) {
			throw new RequiredFieldException("capienza spettatori deve essere almeno di 1000");
		}

		if (s.getNome().length() > 30) {
			throw new FormatErrorException("nome squadra la lunghezza è maggiore di 30 Impossibile");
		}
		if (s.getCitta().length() > 35) {
			throw new FormatErrorException("nome città deve essere minore di 35 ");
		}

	}


	public void insert(Squadra s) //inserisci
			throws SQLException, FormatErrorException, RequiredFieldException, ClassNotFoundException {
		validateInsert(s);
		sc.insert(s);
	}

	public void delete(int id) throws SQLException, ClassNotFoundException {
		sc.deleteById(id);
	}

	public Squadra findById(int s1) throws SQLException, ClassNotFoundException, RecordNonTrovatoException {
		return sc.findById(s1);

	}

	public Squadra findByName(String s3)
			throws SQLException, ClassNotFoundException, RecordNonTrovatoException {
		return sc.findByName(s3);

	}

	public ArrayList<Squadra> findAll() throws SQLException, ClassNotFoundException {
		return sc.findAll();
	}

	public void updateName(String s, int n)
			throws SQLException, ClassNotFoundException, RecordNonTrovatoException {
		Squadra temp = sc.findById(n); // nn esiste una squadra con quell id e nel controller mi gestisco qst tre ecc
		// trovata la classe temp
		temp.setNome(s);
		sc.update(temp);

	}

	public void updateCitta(String nuovacitta, int id)
			throws SQLException, ClassNotFoundException, RecordNonTrovatoException {
		Squadra temp = sc.findById(id);
		temp.setCitta(nuovacitta);
		sc.update(temp);
	}

	public void updateCapienza(int capienza, int id)
			throws SQLException, ClassNotFoundException, RecordNonTrovatoException {
		Squadra temp = sc.findById(id); // nn esiste una squadra con quell id e nel controller mi gestisco qst tre ecc
		// trovata la classe temp
		temp.setCapienzaStadio(capienza);
		sc.update(temp);
	}

	public void updateNomeStadio(String nomeStadio, int id)
			throws SQLException, ClassNotFoundException, RecordNonTrovatoException {
		Squadra temp = sc.findById(id); // nn esiste una squadra con quell id e nel controller mi gestisco qst tre ecc
		// trovata la classe temp
		temp.setNomeStadio(nomeStadio);
		sc.update(temp);
	}

}
