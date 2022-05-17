package it.polito.tdp.metroparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class MetroController {
	
	private Model model ;
	
    @FXML
    private TableColumn<Fermata, String> colFermata; //oggetti delle singole colonne
    //ho bisogno di dire alla colonna come estrarre la String dall'oggetto Fermata.
    //lo faccio nell'initialize

    @FXML
    private TableView<Fermata> tablePercorso; //oggetto che rappresenta ciascuna riga

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> boxArrivo;

    @FXML
    private ComboBox<Fermata> boxPartenza;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCerca(ActionEvent event) {

    	Fermata partenza = boxPartenza.getValue();
    	Fermata arrivo = boxArrivo.getValue();
    	
    	if(partenza!=null && arrivo!=null && !partenza.equals(arrivo)) {
    		List<Fermata> percorso = model.calcolaPercorso(partenza, arrivo) ;
    		
    		tablePercorso.setItems(FXCollections.observableArrayList(percorso));
    		
    		txtResult.setText("Percorso trovato "+percorso.size()+" stazioni\n");
    	} else {
    		txtResult.setText("Devi selezionare due stazioni, diverse tra loro\n");
    	}
    	
    	
    }

    @FXML
    void initialize() {
        assert boxArrivo != null : "fx:id=\"boxArrivo\" was not injected: check your FXML file 'Metro.fxml'.";
        assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'Metro.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Metro.fxml'.";
     
        colFermata.setCellValueFactory(new PropertyValueFactory<Fermata, String>("nome"));
    }

	public void setModel(Model m) {
		this.model = m ;
		List<Fermata> fermate = this.model.getFermate() ;
		boxPartenza.getItems().addAll(fermate) ;
		boxArrivo.getItems().addAll(fermate) ;
	}

}