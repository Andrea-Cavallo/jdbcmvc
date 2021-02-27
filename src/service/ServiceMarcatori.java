package service;

import java.sql.SQLException;
import java.util.ArrayList;
import crud.MarcatoriCrud;
import exception.FormatErrorException;
import exception.RecordNonTrovatoException;
import model.Marcatori;

public class ServiceMarcatori {
	MarcatoriCrud mc = new MarcatoriCrud();

	public void controllaInserimentoMarcatore(int gol_fatti, int cartellini_gialli, int cartellini_rossi)
			throws FormatErrorException {

		if (gol_fatti > 500) {
			throw new FormatErrorException("Non puoi inserire più di 500 gol ");
		}
		if (cartellini_gialli > 100 || cartellini_rossi > 100) {
			throw new FormatErrorException("I cartellini non possono essere più di 100 hai sbagliato nell inserimento");
		}
	}

	public void inserisciMarcatore(Marcatori m) throws FormatErrorException, SQLException, ClassNotFoundException {

		controllaInserimentoMarcatore(m.getGol_fatti(), m.getCartellini_gialli(), m.getCartellini_rossi());
		mc.inserisciMarcatore(m);

	}

	public ArrayList<Marcatori> ricercaPerIdMarcatori(int idGioc) throws  SQLException {
		return mc.ricercaPerIdMarcatori(idGioc);

	}

	public void mostraMarcatoriPerGol(int numeroGol) throws SQLException, ClassNotFoundException, RecordNonTrovatoException {
		mc.mostraMarcatoriPerGol(numeroGol);

	}

	public void deleteMarcatore(int idPassato) throws SQLException, ClassNotFoundException {
		mc.deleteMarcatore(idPassato);
	}

	public void updateGolFatti(int retiNuove, int retiVecchie) throws SQLException, ClassNotFoundException {
		mc.updateGolFatti(retiNuove, retiVecchie);
	}

	public void creaViewDeiBomber() throws SQLException, ClassNotFoundException {
		mc.creaViewDeiBomber();
	}

	public void visualizzaIprimi10Bomber(int limit) throws  SQLException, ClassNotFoundException, RecordNonTrovatoException {
		mc.visualizzaIprimi10Bomber(limit);
	}

	public void mediaGolFatti() throws SQLException, ClassNotFoundException {
		mc.mediaGolFatti();
	}

}
