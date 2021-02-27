package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.RecordNonTrovatoException;
import model.Marcatori;
import utility.Connessione;

public class MarcatoriCrud {

	public void inserisciMarcatore(Marcatori m) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "INSERT INTO campionatodb.marcatori(gol_fatti,cartellini_gialli,cartellini_rossi,id_giocatore,id_campionato) VALUES(?,?,?,?,?) ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, m.getGol_fatti());
			ps.setInt(2, m.getCartellini_gialli());
			ps.setInt(3, m.getCartellini_rossi());
			ps.setInt(4, m.getId_giocatore());
			ps.setInt(5, m.getId_campionato());

			ps.executeUpdate();

			System.out.println("Record is updated to marcatori table!");
			ResultSet rs = ps.getGeneratedKeys();
			// scorro l elemento
			rs.next();

			System.out.println("L' ID è --" + rs.getInt(1));
		}finally {
			Connessione.chiudi_connessione(con);
		}
	}


	public ArrayList<Marcatori> ricercaPerIdMarcatori(int idGiocatore) throws SQLException {
		ArrayList<Marcatori> listaMarc = new ArrayList<>();

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "SELECT * FROM MARCATORI AS M WHERE M.ID_GIOCATORE = ? ";


			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// il risultato della query e' di tipo RESULTSET un oggetto di questo tipo
			// allora
			ps.setInt(1, idGiocatore);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			while (fine) {

				int golFatti = res.getInt("m.gol_fatti");
				int cartellini_gialli = res.getInt("m.cartellini_gialli");
				int cartellini_rossi = res.getInt("m.cartellini_rossi");
				int idcampionato = res.getInt("id_campionato");

				Marcatori m = new Marcatori();

				m.setCartellini_gialli(cartellini_gialli);
				m.setCartellini_rossi(cartellini_rossi);
				m.setGol_fatti(golFatti);
				m.setId_campionato(idcampionato);

				listaMarc.add(m);

				fine = res.next();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			Connessione.chiudi_connessione(con);
		}
		return listaMarc;
	}

	// QUERY CHE MOSTRA IL NOME, COGNOME DI TUTTI GIOCATORI CHE HANNO
	// SEGNATO UN NUMERO DI GOL MAGGIORE A QUELLO PASSATO COME ARGOMENTO
	public void mostraMarcatoriPerGol(int gol) throws SQLException, ClassNotFoundException, RecordNonTrovatoException{
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "SELECT DISTINCT giocatore.nome,giocatore.cognome,marcatori.gol_fatti from marcatori "
					+ "inner join giocatore on marcatori.id_giocatore= giocatore.id "
					+ "group by giocatore.nome,giocatore.cognome,marcatori.gol_fatti " + "having gol_fatti > ?  ";

			ArrayList<String> listaGioc = new ArrayList<>();
			ArrayList<Integer> listaCart = new ArrayList<>();

			PreparedStatement ps = con.prepareStatement(sql);
			// il risultato della query e' di tipo RESULTSET un oggetto di questo tipo
			// allora
			ps.setInt(1, gol);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			if (!fine)
				throw new RecordNonTrovatoException("Marcatore non trovato!!");
			while (fine) {

				String nome = res.getString("giocatore.nome");
				String cognome = res.getString("giocatore.cognome");
				int gol1 = res.getInt("marcatori.gol_fatti");

				listaGioc.add(nome);
				listaGioc.add(cognome);
				listaCart.add(gol1);

				System.out.println(listaGioc + "----" + listaCart);

				fine = res.next();
			}
		}  finally {
			Connessione.chiudi_connessione(con);
		}

	}

	// QUERY PER MOSTRARE NOME COGNOME GIOCATORE - LA SUA SQUADRA - IN BASE AI CART
	// ROSSI..
	public void mostraMarcatoriSquadraCartRossi() throws SQLException,ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "SELECT giocatore.cognome, marcatori.cartellini_rossi,squadra.nome " + "FROM MARCATORI  "
					+ "LEFT JOIN GIOCATORE ON MARCATORI.ID_GIOCATORE=GIOCATORE.ID "
					+ "INNER JOIN SQUADRA ON GIOCATORE.ID_SQUADRA=SQUADRA.ID "
					+ "where marcatori.cartellini_rossi is null ";
			ArrayList<String> listaGioc = new ArrayList<>();
			ArrayList<Integer> listaCart = new ArrayList<>();
			ArrayList<String> listaSquadra = new ArrayList<>();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			while (fine) {

				String cognome = res.getString("giocatore.cognome");
				String squadra = res.getString("squadra.nome");

				int cartellinirossi = res.getInt("marcatori.cartellini_rossi");

				listaGioc.add(cognome);
				listaSquadra.add(squadra);
				listaCart.add(cartellinirossi);

				System.out.println(listaGioc + ",Squadra/e " + listaSquadra + "---" + "Nessun cartellino Rosso!");

				fine = res.next();
			}
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public void deleteMarcatore(int id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "DELETE FROM campionatodb.marcatori  where marcatori.id = ?  ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Hai cancellato il  marcatore con  id : " + id);
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}

	// update gol fatti -->
	public void updateGolFatti(int retiNuove, int retiVecchie) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "UPDATE FROM campionatodb.marcatori SET marcatori.gol_fatti = ? where marcatori.gol_fatti = ?  ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, retiNuove);
			ps.setInt(2, retiVecchie);
			ps.executeUpdate();
			System.out.println("Hai modificato le reti fatte da: " + retiVecchie + ", a " + retiNuove);
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}

	// visualizza nome,cognome del giocatore che ha segnato il maggior nr di reti e
	// il nome della sua squadra
	// E limita al top di 10 ..

	public void creaViewDeiBomber() throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "CREATE VIEW  MIGLIORI_BOMBER AS(SELECT  (M.GOL_FATTI) AS MIGLIOR_GOLEADOR, G.NOME,G.COGNOME,S.NOME AS NOMESQUADRA\n"
					+ "FROM MARCATORI AS M INNER JOIN GIOCATORE AS G ON M.ID_GIOCATORE=G.ID \n"
					+ "INNER JOIN SQUADRA AS S ON G.ID_SQUADRA = S.ID GROUP BY G.NOME,G.COGNOME,NOMESQUADRA ,MIGLIOR_GOLEADOR\n"
					+ "HAVING  MAX(MIGLIOR_GOLEADOR)) ";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
		} finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void visualizzaIprimi10Bomber(int numeroLimite) throws SQLException,  RecordNonTrovatoException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "SELECT * FROM MIGLIORI_BOMBER  ORDER BY MIGLIORI_BOMBER.MIGLIOR_GOLEADOR DESC LIMIT  ? ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, numeroLimite);

			ResultSet res = ps.executeQuery();

			boolean fine = res.next();
			if (!fine)
				throw new RecordNonTrovatoException("La Top deve essere di un numero inferiore!!");
			while (fine) {

				String goleador = res.getString("migliori_bomber.miglior_goleador");
				String nome = res.getString("migliori_bomber.nome");
				String cognome = res.getString("migliori_bomber.cognome");
				String squadra = res.getString("migliori_bomber.nomesquadra");

				System.out.println(nome + ", " + cognome + " [" + squadra + "]" + ", con numero GOL: " + goleador);

				fine = res.next();
			}
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public void mediaGolFatti() throws SQLException,  ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "SELECT AVG(MARCATORI.GOL_FATTI) as MEDIAGOL FROM campionatodb.MARCATORI";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet res = ps.executeQuery();

			boolean fine = res.next();
			while (fine) {

				double golMedia = res.getDouble("mediagol");

				System.out.println(" Media total gol fatti: " + " [" + golMedia + "]");

				fine = res.next();
			}
		} finally {
			Connessione.chiudi_connessione(con);
		}

	}
}
