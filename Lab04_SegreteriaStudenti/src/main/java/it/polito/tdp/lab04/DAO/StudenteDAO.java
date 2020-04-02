package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;


import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	
	public Studente getDatiStudente(Integer matricola) {

		final String sql = "Select matricola, cognome, nome, CDS " + 
				"from studente " + 
				"where matricola= ? " ;
		Studente s =null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS= rs.getString("CDS");

				s= new Studente(matricola,cognome,nome,CDS);
				
				
			}

			conn.close();
			return s;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	public boolean esisteStudente(Integer mat) {
		String sql="Select * from studente where matricola=?";
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, mat);
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
	
	public List<Corso> getCorsiDelloStudente(Integer matricola){
		
		String sql= " Select c.codins, c.crediti, c.nome, c.pd " + 
				"FROM corso as c, iscrizione as i " + 
				"where c.codins=i.codins && i.matricola= ?  ";
		
		List<Corso> corsi= new ArrayList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
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
	
}
