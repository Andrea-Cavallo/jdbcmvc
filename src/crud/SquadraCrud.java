package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.RecordNonTrovatoException;
import model.Squadra;
import utility.Connessione;

public class SquadraCrud {

	public int insert(Squadra s) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		Connection con = null;
		try {

			con = Connessione.crea_connessione();

			String sql = "INSERT INTO squadra(nome,citta,nome_stadio,capienza_stadio) VALUES(?,?,?,?) ";

			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, s.getNome());
			ps.setString(2, s.getCitta());
			ps.setString(3, s.getNomeStadio());
			ps.setInt(4, s.getCapienzaStadio());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();

		} finally {
			Connessione.chiudi_connessione(con);
		}

		return rs.getInt(1);

	}

	public void deleteById(int idSquadraDaCancellare) throws SQLException, ClassNotFoundException {

		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "DELETE FROM squadra WHERE squadra.id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, idSquadraDaCancellare);
			ps.executeUpdate();
		} finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public Squadra findByName(String nome) throws SQLException, ClassNotFoundException, RecordNonTrovatoException {

		Connection con = null;

		try {
			Squadra s = new Squadra();
			con = Connessione.crea_connessione();

			String sql = "SELECT nome,citta,nome_stadio,capienza_stadio FROM squadra WHERE squadra.nome = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, nome);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();
			if (!fine)
				throw new RecordNonTrovatoException("Squadra non trovata!!");

			String citta = res.getString("citta");
			String nome_stadio = res.getString("nome_stadio");
			int capienza_stadio = res.getInt("capienza_stadio");

			Squadra squadra = new Squadra(nome, citta, nome_stadio, capienza_stadio);
			System.out.println(squadra);

			return s;

		} finally {
			Connessione.chiudi_connessione(con);
		}

	}

	public Squadra findById(int id) throws SQLException, ClassNotFoundException, RecordNonTrovatoException {

		Connection con = null;
		Squadra s = new Squadra();
		try {
			con = Connessione.crea_connessione();

			String sql = "SELECT nome,citta,nome_stadio,capienza_stadio FROM "
					+ "campionatodb.squadra WHERE squadra.id = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet res = ps.executeQuery();
			boolean fine = res.next();

			if (!fine)
				throw new RecordNonTrovatoException("Squadra non trovata!!");

			String nome = res.getString("nome");
			String citta = res.getString("citta");
			String nome_stadio = res.getString("nome_stadio");
			int capienza_stadio = res.getInt("capienza_stadio");

			s = new Squadra(nome, citta, nome_stadio, capienza_stadio);
			System.out.println(s);

		} finally {
			Connessione.chiudi_connessione(con);
		}
		return s;

	}

	public void update(Squadra team) throws SQLException, ClassNotFoundException {

		String sql = "update squadra set nome=?,citta=?,nomestadio=?,capienzaStadio=? where id=?";

		Connection con = null;

		try {
			con = Connessione.crea_connessione();

			PreparedStatement query = con.prepareStatement(sql);

			query.setString(1, team.getNome());
			query.setString(2, team.getCitta());
			query.setString(3, team.getNomeStadio());

			query.setInt(4, team.getCapienzaStadio());
			query.setInt(5, team.getId());

			query.executeUpdate();

		} finally {
			Connessione.chiudi_connessione(con);
		}
	}

	public ArrayList<Squadra> findAll() throws SQLException, ClassNotFoundException {
		ArrayList<Squadra> ret = new ArrayList<Squadra>();
		Connection con = null;
		try {
			con = Connessione.crea_connessione();

			String sql = "SELECT * FROM squadra order by nome";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Squadra s = makeSquadraFromResultSet(rs);
				ret.add(s);

			}
			return ret;

		} finally {
			Connessione.chiudi_connessione(con);
		}

	}

	private Squadra makeSquadraFromResultSet(ResultSet rs) throws SQLException {

		Squadra ret = new Squadra();
		ret.setId(rs.getInt("id"));
		ret.setNome(rs.getString("nome"));
		ret.setCitta(rs.getNString("citta"));
		ret.setNomeStadio(rs.getString("nome_stadio"));
		ret.setCapienzaStadio(rs.getInt("capienza_stadio"));

		return ret;
	}

}
