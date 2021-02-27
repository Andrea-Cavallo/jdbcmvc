package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Serie;
import utility.Connessione;

public class SerieCrud {
	public void inserisciSerie(Serie s) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "INSERT INTO campionatodb.serie(id,descrizione) VALUES(?,?) ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, s.getId());
			ps.setString(2, s.getDescrizione());
			ps.executeUpdate();

			System.out.println("Record is updated to Classifica table!");
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

			System.out.println("L' ID è --" + rs.getInt(1));
		} finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public void updateSerie(String nuovoNome, int id) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "UPDATE  campionatodb.serie SET serie.descrizione = ? WHERE serie.id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, nuovoNome);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Hai modificato la serie con id:  " + id + ", con il nuovo nome " + nuovoNome);

		}  finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void deleteSerie(int id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "DELETE FROM campionatodb.serie  where serie.id = ?  ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Hai cancellato la serie con  id : " + id);
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}
}
