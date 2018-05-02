/**
 * Sample Skeleton for 'PowerOutages.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import id.polito.tdp.poweroutages.bean.Nerc;
import it.polito.tdp.poweroutages.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="NERC"
    private ChoiceBox<Nerc> NERC; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleSearch(ActionEvent event) {
    	txtLog.clear();
    	if(!txtYears.getText().isEmpty() && !txtHours.getText().isEmpty()) {
    		
	    	int anni;
			int ore;
			try {
				anni = Integer.parseInt(txtYears.getText());
				ore = Integer.parseInt(txtHours.getText());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			}
			
			String result = model.worstCaseAnalysis(anni, ore, NERC.getValue());
	    	txtLog.appendText(result);
		
    	} else 
    		txtLog.appendText("Compila tutti i campi!");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert NERC != null : "fx:id=\"NERC\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        
    }

	public void setModel(Model m) {
		this.model = m;
		NERC.getItems().addAll(this.model.getNercList());
	}
}
