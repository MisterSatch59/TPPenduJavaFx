package com.pendu.vue;

import com.pendu.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FenetrePrincipale {

	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	public void regles() {
		// TODO
		System.out.println("Règles!!");
	}

	@FXML
	public void nouveau() {
		this.main.jeuFenetre();
	}

	@FXML
	public void scores() {
		// TODO
		System.out.println("Scores!!");
	}

	@FXML
	public void info() {
		Alert boiteDialog = new Alert(AlertType.INFORMATION);
		boiteDialog.setTitle("INFORMATIONS");
		boiteDialog.setHeaderText("Auteur : OLTENOS\nVersion : 1.1");
		boiteDialog.showAndWait();
	}

	@FXML
	public void quitter() {
		this.main.getStage().close();
	}

}
