package service;

import java.sql.SQLException;

import crud.SerieCrud;
import exception.FieldException;
import exception.FormatErrorException;
import exception.RequiredFieldException;
import model.Serie;

public class ServiceSerie {
	SerieCrud sc = new SerieCrud();

	public void controllaInserimentoSerie(int id, String descrizione) throws FieldException {

		if (id > 500) {
			throw new FormatErrorException("Non puoi inserire più di 500 Serie ");
		}
		if (descrizione == null || descrizione.trim().equals("")) {
			throw new RequiredFieldException("Descrizione mancante ");

		}
	}

	public void inserisciSerie(Serie s1) throws FieldException, SQLException, ClassNotFoundException {
		controllaInserimentoSerie(s1.getId(), s1.getDescrizione());
		sc.inserisciSerie(s1);
	}

	public void modificaDescrizione(String nuovoNome, int id) throws SQLException, ClassNotFoundException {
		sc.updateSerie(nuovoNome, id);

	}

}
