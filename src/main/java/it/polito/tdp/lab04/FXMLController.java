package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FXMLController {
	
	Model model;
	private List<Corso> corsi;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> CmbCorsi;

    @FXML
    private TextField TxtCognome;

    @FXML
    private TextField TxtMatricola;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextArea TxtRisultati;

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	TxtRisultati.clear();
    	
    	if(TxtMatricola.getText() != "" && TxtMatricola.getText() != null) {
    		try{
    			int matricola = Integer.parseInt(TxtMatricola.getText());
    			Studente s = new Studente(matricola, "", "", "");
    	    	List<Corso> corsi = this.model.getCorsiDelloStudente(s);
    	    	
    	    	for(Corso c : corsi) {
            		TxtRisultati.appendText(c.toString() + "\n");
            	}
    	    	
    		} catch (NumberFormatException n){
    			n.printStackTrace();
    			TxtRisultati.setText("inserisci una matricola valida");
    			TxtCognome.clear();
    	    	TxtNome.clear();
    	    	TxtMatricola.clear();
    			return;
    		}
    	}else {
    		TxtRisultati.setText("inserisci una matricola valida");
    		TxtCognome.clear();
        	TxtNome.clear();
        	TxtMatricola.clear();
        	return;
    	}
    	
    }
    
    @FXML
    void handleCheck(ActionEvent event) {
    	List<Studente> studenti = this.model.getAllStudenti();
    	int mat = 0;
    	
    	if(TxtMatricola.getText() != "" && TxtMatricola.getText() != " ") {
    		try{ 
    			mat = Integer.parseInt(TxtMatricola.getText());
    		} catch(NumberFormatException n){
    			n.printStackTrace();
    			TxtRisultati.setText("inserisci una matricola valida");
    			TxtCognome.clear();
    	    	TxtNome.clear();
    	    	TxtMatricola.clear();
    			return;
    		}
    	}else {
    		TxtRisultati.setText("inserisci una matricola valida");
    		TxtCognome.clear();
        	TxtNome.clear();
        	TxtMatricola.clear();
        	return;
    	}
    	
    	for(Studente s : studenti) {
    		if(s.getMatricola() == mat) {
    			TxtCognome.setText(s.getCognome());
    			TxtNome.setText(s.getNome());
    		}
    	}
    	
    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) {
    	TxtRisultati.clear();
    	Corso corso;
    	if(CmbCorsi.getValue() != null && CmbCorsi.getValue().getCodins() != " ") {
    		corso = CmbCorsi.getValue();
    		List<Studente> studenti = this.model.getStudentiIscrittiAlCorso(corso);
        	
    		for(Studente s : studenti) {
        		TxtRisultati.appendText(s.toString() + "\n");
        	}
    	} else {
    		TxtRisultati.setText("inserisci un corso");
			
    	}
 
    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    }
    
    void popolaCombo() {
    	corsi = model.getTuttiICorsi();
    	
    	CmbCorsi.getItems().add(new Corso(" ", 0, " ", 0));
    	 
    	for(Corso c : corsi) {
    		CmbCorsi.getItems().add(c);
    	}
    }

    @FXML
    void handleReset(ActionEvent event) {
    	TxtCognome.clear();
    	TxtNome.clear();
    	TxtMatricola.clear();
    	TxtRisultati.clear();
    }

    @FXML
    void initialize() {
        assert CmbCorsi != null : "fx:id=\"CmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtCognome != null : "fx:id=\"TxtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtMatricola != null : "fx:id=\"TxtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtNome != null : "fx:id=\"TxtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert TxtRisultati != null : "fx:id=\"TxtRisultati\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	popolaCombo();
    }

}
