package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Campionato;
import utility.Connessione;

public class CampionatoCrud {

	public void inserisciCampionato(Campionato c) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "INSERT INTO campionatodb.campionato(id,anno,id_serie) VALUES(?,?,?) ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, c.getId());
			ps.setInt(2, c.getAnno());
			ps.setInt(3, c.getId_serie());
			ps.executeUpdate();

			System.out.println("Record is updated to Classifica table!");
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

			System.out.println("L' ID è --" + rs.getInt(1));
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public void modificaCampionato(int anno, int id) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "UPDATE  campionatodb.campionato SET campionato.anno = ? WHERE campionato.id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, anno);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Hai modificato l'anno del Campionato con id:  " + id + ", con  " + anno);

		}  finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void deleteCampionato(int id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "DELETE FROM campionatodb.campionato  where campionato.id = ?  ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Hai cancellato il  campionato con  id : " + id);
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public ArrayList<Campionato> ricercaPerIdCampionato(int idCampionato) throws SQLException, ClassNotFoundException {
		ArrayList<Campionato> listaCamp = new ArrayList<>();

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "SELECT * FROM CAMPIONATO AS C WHERE C.ID= ? ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, idCampionato);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			while (fine) {

				int id = res.getInt("id");
				int anno = res.getInt("anno");
				int idserie = res.getInt("id_serie");

				Campionato c = new Campionato();

				c.setId(id);
				c.setAnno(anno);
				c.setId_serie(idserie);

				listaCamp.add(c);
				fine = res.next();
			}
		}  finally {
			Connessione.chiudi_connessione(con);
		}
		return listaCamp;
	}

}
