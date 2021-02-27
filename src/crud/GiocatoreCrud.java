package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.RecordNonTrovatoException;
import model.Giocatore;
import utility.Connessione;

public class GiocatoreCrud {
	
 Giocatore g = new Giocatore();

	public void inserisciGiocatore(Giocatore g) throws SQLException, ClassNotFoundException {


		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "INSERT INTO campionatodb.giocatore(nome,cognome,id_squadra) VALUES(?,?,?) ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, g.getNome()); 
			ps.setString(2, g.getCognome());
			ps.setInt(3, g.getId_squadra());

			ps.executeUpdate();

			System.out.println("Record is updated to CAMPIONATODB.Giocatore table!");
			ResultSet rs = ps.getGeneratedKeys();
			// scorro l elemento
			rs.next();

			System.out.println("L' ID è --" + rs.getInt(1));

		} finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void deleteGiocatore(int id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "DELETE FROM campionatodb.giocatore WHERE giocatore.id=? ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Hai cancellato il giocatore con id: " + id);
		} finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public void visualizzaGiocatori() throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "SELECT * FROM campionatodb.giocatore ";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); 

			while (rs.next()) {
				System.out.println("id - " + rs.getInt(1));
				System.out.println("Nome- " + rs.getString(2));
				System.out.println("Cognome - " + rs.getString(3));
				System.out.println("id_squadra - " + rs.getInt(4));
				System.out.println("------------------");
			} 
		} finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public Giocatore ricercaGiocatore(int idCercato) throws SQLException, ClassNotFoundException, RecordNonTrovatoException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			Giocatore g = new Giocatore();
			String sql = "SELECT * FROM giocatore WHERE giocatore.id =? ";


			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, idCercato);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			if (!fine)
				throw new RecordNonTrovatoException("Gicatore  non trovato!!");
			while (fine) {

				String nome = res.getString("giocatore.nome");
				String cognome = res.getString("giocatore.cognome");
				int idSquadra = res.getInt("giocatore.id_squadra");

				g.setNome(nome);
				g.setCognome(cognome);
				g.setId_squadra(idSquadra);

				fine = res.next();
			}
		} finally {
			Connessione.chiudi_connessione(con);
		}

		return g;

	}

	public void updateGiocatoreNome(String nuovoNome, int id) throws SQLException, ClassNotFoundException {


		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "UPDATE  campionatodb.giocatore SET giocatore.nome= ? WHERE giocatore.id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, nuovoNome);
			ps.setInt(2, id);
			ps.executeUpdate();
		} finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public void updateGiocatoreCognome(String nuovoCognome, int id) throws SQLException, ClassNotFoundException {


		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "UPDATE  campionatodb.giocatore SET giocatore.cognome= ? WHERE giocatore.id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, nuovoCognome);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Hai modificato il giocatore con id:  " + id + ", con il nuovo cognome " + nuovoCognome);
		} finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void updateGiocatoreIdSquadra(int idSquadra, int id) throws SQLException, ClassNotFoundException {


		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "UPDATE  campionatodb.giocatore SET giocatore.id_squadra= ? WHERE giocatore.id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, idSquadra);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Hai modificato il giocatore con id:  " + id
					+ ", che gioca nella nuova Squadra con id:  " + idSquadra);

		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}
}
