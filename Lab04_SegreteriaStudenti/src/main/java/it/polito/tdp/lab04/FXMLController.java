package it.polito.tdp.lab04;

import java.net.URL;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	// Now add observability by wrapping it with ObservableList.
   // ObservableList<String> observableList = FXCollections.observableList(list);
	
	private ObservableList<String> corsi= FXCollections.observableArrayList();
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> btnCorsi;

    @FXML
    private Button btnIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void corsiDelloStudente(ActionEvent event) {

    	txtRisultato.setDisable(false);
    	String mTemp=txtMatricola.getText();
    	
    	if(mTemp.isEmpty()) {
    		txtRisultato.setText("Deviv inserire la matricola!");
    		return;
    	}
    	
    	Integer matricola;
    	
    	try {
    		matricola=Integer.parseInt(mTemp);
    	}catch(NumberFormatException e) {
    		
    		txtRisultato.setText("Devi inserire solo numeri");
    		return ;
    	}
    	
    	if(matricola<0 || matricola>999999) {
    		txtRisultato.setText("Devi inserire solo numeri tra 0 e 999999");
    		return ;
    	}
    	
    	if(!this.model.esisteStudente(matricola)) {
    		txtRisultato.setText("Lo studente non esiste");
    		return ;
    	}
    	
    	List<Corso> corsi=this.model.getCorsiDelloStudente(matricola);
    	
    	if(corsi.size()==0) {
    		txtRisultato.setText("Lo studente non è iscritto a nessun corso");
    		return;
    	}
    	
    	for(Corso c:corsi) {
    		txtRisultato.appendText(c.toString()+"\n");
    	}
    	
    }

    @FXML
    void datiStudente(ActionEvent event) {
    	
    	txtRisultato.setDisable(false);
    	String mTemp=txtMatricola.getText();
    	
    	//controllo che non sia vuoto
    	if(mTemp.isEmpty()) {
    		txtRisultato.setText("Devi inserire la matricola!");
    		return ;
    	}

    	Integer matricola;
    	
    	try {
    		matricola=Integer.parseInt(mTemp);
    		
    	}catch(NumberFormatException e){
    		
    		txtRisultato.setText("Devi inserire solo numeri");
    		return ;
    		
    	}
    	//controllo valore matricola
    	if(matricola<0 || matricola>999999) {
    		txtRisultato.setText("Devi inserire solo numeri tra 0 e 999999");
    		return ;
    	}
    	
    	//vedo se lo studente esiste
    	if(!this.model.esisteStudente(matricola)) {
    		txtRisultato.setText("Lo studente non esiste");
    		return ;
    	}
    	
    	Studente s=this.model.getDatiStudente(matricola);
    	
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();
    	
    }

    @FXML
    void iscrittiAlCorso(ActionEvent event) {

    	txtRisultato.setDisable(false);
    	String corso=btnCorsi.getValue();
    	
    	if(corso==null) {
    		txtRisultato.setText("Devi scegliere un corso");
    		return ;
    	}
    	
    			
    	List<Studente> studenti=this.model.getStudentiIscrittiAlCorso(new Corso(null,null,corso,null));
    	
    	if(studenti.size()==0) {
    		txtRisultato.setText("Il corso non ha studenti");
    		return;
    	}
    	for(Studente s: studenti) {
    		txtRisultato.appendText(s.toString()+"\n");
    	}
    	
    	
    }

    @FXML
    void iscrizione(ActionEvent event) {

    	txtRisultato.setDisable(false);
    	String corso=btnCorsi.getValue();
    	
    	if(corso==null) {
    		txtRisultato.setText("Devi scegliere un corso");
    		return ;
    	}
    	
        String mTemp=txtMatricola.getText();
    	
    	//controllo che non sia vuoto
    	if(mTemp.isEmpty()) {
    		txtRisultato.setText("Devi inserire la matricola!");
    		return ;
    	}

    	Integer matricola;
    	
    	try {
    		matricola=Integer.parseInt(mTemp);
    		
    	}catch(NumberFormatException e){
    		
    		txtRisultato.setText("Devi inserire solo numeri");
    		return ;
    		
    	}
    	//controllo valore matricola
    	if(matricola<0 || matricola>999999) {
    		txtRisultato.setText("Devi inserire solo numeri tra 0 e 999999");
    		return ;
    	}
    	
    	if(!this.model.esisteStudente(matricola)) {
    		txtRisultato.setText("Lo studente non esiste");
    		return ;
    	}
    	
        List<Studente> studenti=this.model.getStudentiIscrittiAlCorso(new Corso(null,null,corso,null));
    	
    	if(studenti.size()==0) {
    		txtRisultato.setText("Il corso non ha studenti");
    		return;
    	}
    	
    	if(!model.esisisteStudenteACorso(matricola, corso)) {
    		txtRisultato.setText("Lo studente non è iscritto al corso");
    	}
    	txtRisultato.setText("Lo studente è già iscritto al corso");
    }

    @FXML
    void initialize() {
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrittiCorso != null : "fx:id=\"btnIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMatricola != null : "fx:id=\"btnMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		txtRisultato.setDisable(true);
		
		List<Corso> totCorsi=model.getTuttiICorsi();
	
		for(Corso c: totCorsi) {
			corsi.add(c.getNome());
		}
		corsi.add("");
		btnCorsi.setItems(corsi);
		btnCorsi.setValue(""); 
		
		this.model=model;
		
	}
}
