package service;

import java.sql.SQLException;
import java.util.ArrayList;

import crud.CampionatoCrud;
import exception.FormatErrorException;
import model.Campionato;

public class ServiceCampionato {

	CampionatoCrud campcrud = new CampionatoCrud();

	public void controllaInserimentoCampionato(int anno) throws FormatErrorException {

		if (anno < 1980 || anno > 2050) {
			throw new FormatErrorException("L'anno deve essere Compreso tra 1980 e 2050 ");

		}
	}

	public void inserisciCampionato(Campionato camp) throws SQLException, FormatErrorException, ClassNotFoundException {
		controllaInserimentoCampionato(camp.getAnno());
		campcrud.inserisciCampionato(camp);
	}

	public void modificaAnno(int nuovoanno, int id1) throws SQLException, ClassNotFoundException {
		campcrud.modificaCampionato(nuovoanno, id1);
	}

	public ArrayList<Campionato> ricercaCampionato(int idPassato1) throws SQLException, ClassNotFoundException {
		return campcrud.ricercaPerIdCampionato(idPassato1);
	}

	public void deleteCampionato(int idPassato2) throws SQLException, ClassNotFoundException {
		campcrud.deleteCampionato(idPassato2);
	}

}
