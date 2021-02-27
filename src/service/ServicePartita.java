package service;

import java.sql.SQLException;
import crud.PartitaCrud;
import exception.FieldException;
import exception.FormatErrorException;
import model.Partita;

public class ServicePartita {
	PartitaCrud pc = new PartitaCrud();

	public void controllaInserimentoPartita(int gol_squadra_casa, int gol_squadra_fuoricasa) throws FieldException {

		if (gol_squadra_casa > 500) {
			throw new FormatErrorException("Non puoi inserire più di 500 gol ");
		}
		if (gol_squadra_fuoricasa > 500) {
			throw new FormatErrorException("Non puoi inserire più di 500 gol ");
		}

	}

	public void ricercaPartitaConDate() throws SQLException, ClassNotFoundException {
		pc.ricercaPartitaConDate();
	}

	public void inserisciPartita(Partita p) throws FieldException, SQLException, ClassNotFoundException {
		controllaInserimentoPartita(p.getGol_squadra_casa(), p.getGol_squadra_fuoricasa());
		pc.inserisciPartita(p);

	}

}
