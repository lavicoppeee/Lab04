package it.polito.tdp.lab04.DAO;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		StudenteDAO tdao=new StudenteDAO();
		tdao.getDatiStudente(151528);
		
		System.out.println(tdao.getDatiStudente(151528));
		
	}

}
