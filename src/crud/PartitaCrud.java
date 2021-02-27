package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exception.RecordNonTrovatoException;
import model.Partita;
import utility.Connessione;

public class PartitaCrud {

	public void inserisciPartita(Partita p) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {

			con = Connessione.crea_connessione();

			String sql = "INSERT INTO campionatodb.partita(squadra_casa,gol_squadra_casa,squadra_fuoricasa,gol_squadrafuoricasa,id_campionato,datapartita) VALUES(?,?,?,?,?,?) ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, p.getId_squadracasa());
			ps.setInt(2, p.getGol_squadra_casa());
			ps.setInt(3, p.getId_squadrafuoricasa());
			ps.setInt(4, p.getGol_squadra_fuoricasa());
			ps.setInt(5, p.getId_campionato());
			ps.setDate(6, (java.sql.Date) p.getDatapartita());

			ps.executeUpdate();

			System.out.println("Record is updated to CAMPIONATODB.partita table!");
			ResultSet rs = ps.getGeneratedKeys();
			// scorro l elemento
			rs.next();

			System.out.println("L' ID è --" + rs.getInt(1));

		} finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public void ricercaPartitaConDate() throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "SELECT  S.NOME,P.GOL_SQUADRA_CASA ,S1.NOME ,P.GOL_SQUADRAFUORICASA, P.DATAPARTITA "
					+ "from PARTITA AS P, SQUADRA AS S, SQUADRA AS S1 \n"
					+ "WHERE P.SQUADRA_CASA = S.ID AND S1.ID = P.SQUADRA_FUORICASA AND \n"
					+ " P.DATAPARTITA BETWEEN '2018/05/05' AND '2020/10/10' ";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			while (fine) {
				String squadracasa = res.getString("s.nome");
				int golsquadracasa = res.getInt("p.gol_squadra_casa");
				String squadrafuoricasa = res.getString("s1.nome");
				int golsquadrafuoricasa = res.getInt("p.gol_squadrafuoricasa");
				Date dataPartita = res.getDate("p.datapartita");

				System.out.println("[ " + squadracasa + " - " + squadrafuoricasa +" ]" +" "
						+ ", Risultato : " + "[" + golsquadracasa + " - " + golsquadrafuoricasa + "]"
						+ ", disputata nella data " + dataPartita);
				fine = res.next();
			}
		} finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public Partita ricercaPartita(int idCercato)
			throws SQLException, RecordNonTrovatoException, ClassNotFoundException {
		Partita p = new Partita();

		Connection con = null;
		try {
			con = Connessione.crea_connessione();
			String sql = "SELECT * FROM partita WHERE partita.id =? ";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, idCercato);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			if (!fine) {
				throw new RecordNonTrovatoException("Partita non trovata!!");
			}
			while (fine) {

				int squadracasa = res.getInt("squadra_casa");
				int golsquadracasa = res.getInt("gol_squadra_casa");
				int squadrafuoricasa = res.getInt("squadra_fuoricasa");
				int golsquadrafuoricasa = res.getInt("gol_squadrafuoricasa");
				int idcampionato = res.getInt("id_campionato");
				Date datapartita = res.getDate("data_partita");

				p.setId_squadracasa(idCercato);
				p.setId_squadracasa(squadracasa);
				p.setGol_squadra_casa(golsquadracasa);
				p.setId_squadrafuoricasa(squadrafuoricasa);
				p.setGol_squadra_fuoricasa(golsquadrafuoricasa);
				p.setId_campionato(idcampionato);
				p.setDatapartita(datapartita);
				fine = res.next();
			}
		} finally {
			Connessione.chiudi_connessione(con);
		}
		return p;

	}

}
