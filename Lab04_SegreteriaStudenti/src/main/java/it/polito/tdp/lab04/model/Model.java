package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public List<Corso> getTuttiICorsi() {
		corsoDAO=new CorsoDAO();
		return corsoDAO.getTuttiICorsi();
	}
	
	public Corso getCorso(Corso corso) {
		corsoDAO=new CorsoDAO();
		return corsoDAO.getCorso(corso);
	}
	
	public Studente getDatiStudente(Integer mat) {
		studenteDAO= new StudenteDAO();
		return studenteDAO.getDatiStudente(mat);
		
	}
	
	public boolean esisteStudente(Integer mat){
		studenteDAO= new StudenteDAO();
		return studenteDAO.esisteStudente(mat);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		corsoDAO=new CorsoDAO();
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiDelloStudente(Integer matricola){
		studenteDAO= new StudenteDAO();
		return studenteDAO.getCorsiDelloStudente(matricola);
	}
	
	public boolean esisisteStudenteACorso(Integer mat, String nomeC) {
		corsoDAO=new CorsoDAO();
		return corsoDAO.esisisteStudenteACorso(mat, nomeC);
		
	}
}

