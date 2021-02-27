package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Classifica;
import utility.Connessione;

public class ClassificaCrud {

// MOSTRA CLASSIFICA IN BASE AI PUNTI DELLA SERIE A - B E C

	public void visualizzaClassificaPerSerie(String Serie) throws SQLException, ClassNotFoundException{

		String sql = "SELECT SERIE.DESCRIZIONE AS SERIE ,SQUADRA.NOME,CLASSIFICA.POSIZIONE,CLASSIFICA.PUNTI,CAMPIONATO.ID\n"
				+ "FROM SERIE INNER JOIN CAMPIONATO ON SERIE.ID = CAMPIONATO.ID_SERIE\n"
				+ "INNER JOIN CLASSIFICA ON  CAMPIONATO.ID = CLASSIFICA.ID_CAMPIONATO\n"
				+ "INNER JOIN SQUADRA ON CLASSIFICA.ID_SQUADRA = SQUADRA.ID\n"
				+ "GROUP BY SERIE,SQUADRA.NOME,CLASSIFICA.POSIZIONE,CLASSIFICA.PUNTI,CAMPIONATO.ID HAVING DESCRIZIONE = ? AND CAMPIONATO.ID BETWEEN 1 AND 3\n"
				+ "ORDER BY CLASSIFICA.POSIZIONE ASC;";

		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, Serie);

			ResultSet res = ps.executeQuery();

		
			boolean fine = res.next();
			
			if ( !fine) {System.out.println("Nessun risultato trovato!!");}
			
			
			while (fine) {
				String nomeSquadra = res.getNString("squadra.nome");
				int posizione = res.getInt("classifica.posizione");
				int punti = res.getInt("classifica.punti");

				System.out.println("Serie " + Serie.toUpperCase() + ", Nome Squadra: " + nomeSquadra + "[" + posizione
						+ "]" + ", punti: " + "[" + punti + "]");
				fine = res.next();
			}
		}  finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void inserisciClassifica(Classifica c) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "INSERT INTO campionatodb.classifica(id_campionato,id_squadra,posizione,punti,gol_fatti,gol_subiti) VALUES(?,?,?,?,?,?) ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, c.getId_campionato());
			ps.setInt(2, c.getId_squadra());
			ps.setInt(3, c.getPosizione());
			ps.setInt(4, c.getPunti());
			ps.setInt(5, c.getGol_fatti());
			ps.setInt(5, c.getGol_subiti());
			ps.executeUpdate();

			System.out.println("Record is updated to Classifica table!");
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

			System.out.println("L' ID è --" + rs.getInt(1));
		}  finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public void deleteClassifica(int id) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "DELETE FROM campionatodb.classifica  where classifica.id = ?  ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Hai cancellato la classifica con  id : " + id);
		}finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public ArrayList<Classifica> ricercaClassifica(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Classifica> listaClassifica = new ArrayList<>();

		Connection con = null;
		Classifica c = new Classifica();

		try {
			con = Connessione.crea_connessione();

			String sql = "SELECT * FROM " + "campionatodb.classifica WHERE classifica.id = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			while (fine) {
				int idcampionato = res.getInt("id_campionato");
				int idsquadra = res.getInt("id_squadra");
				int posizione = res.getInt("posizione");
				int punti = res.getInt("punti");
				int golfatti = res.getInt("gol_fatti");
				int golsubiti = res.getInt("gol_subiti");

				c.setId_campionato(idcampionato);
				c.setId_squadra(idsquadra);
				c.setPosizione(posizione);
				c.setPunti(punti);
				c.setGol_fatti(golfatti);
				c.setGol_subiti(golsubiti);

				System.out.println(c);
				listaClassifica.add(c);
				fine = res.next();
				
			}

			return listaClassifica;

		}  finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void updateClassifica(int punti, int id) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "UPDATE  campionatodb.classifica SET classifica.punti= ? WHERE classifica.id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, punti);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Hai modificato la classifica con id:  " + id + ", con i punti " + punti);
		} finally {
			Connessione.chiudi_connessione(con);
		}
	}

}
