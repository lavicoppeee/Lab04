package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c= new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(c);
				
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		// TODO
		String sql="Select * from corso where nome=?";
		
		Corso c = null;
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getNome());
			ResultSet rs = st.executeQuery(); 

			while (rs.next()) {

				String codins = rs.getString("codins");
			    int numeroCrediti = rs.getInt("crediti");
				int periodoDidattico = rs.getInt("pd");

			c=new Corso(codins,numeroCrediti,corso.getNome(),periodoDidattico);
			}

			conn.close();
			return c;
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "Select s.matricola, s.nome, s.cognome, s.CDS " + 
				"from studente as s, iscrizione as i, corso as c " + 
				"where c.codins=i.codins and s.matricola=i.matricola and c.nome= ? ";

		List<Studente> studenti = new ArrayList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getNome());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Studente s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
				studenti.add(s);
				
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean esisisteStudenteACorso(Integer mat, String nomeC) {
		
		String sql= "Select s.matricola, s.cognome, s.nome, s.CDS " + 
				"FROM corso as c, iscrizione as i, studente as s " + 
				"where c.codins=i.codins && s.matricola=i.matricola && c.nome=? && s.matricola=? ";
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, mat);
			st.setString(2, nomeC);
			ResultSet rs=st.executeQuery();
			
			conn.close();
			
		 if(rs.next()) {
			 return true;
		 }else {
			 conn.close();
			 return false;
		 }
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}

	
	}
	
	
 public boolean iscrizioneStudenteACorso(Integer mat, String nomeC) {
		
		String sql= "INSERT INTO iscrizione(matricola,codins) "+
		             "VALUES(?,?) "; 
		
		try {
			
			Corso c=this.getCorso(new Corso(null,null,nomeC,null));
			
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, mat);
			st.setString(2, c.getCodins());
			ResultSet rs=st.executeQuery();
			
			conn.close();
			return true;
			
		} catch(SQLException e) {
		
			return false;
			
		}

	
	}

}
