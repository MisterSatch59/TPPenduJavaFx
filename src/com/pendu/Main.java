package com.pendu;

import java.io.IOException;

import com.pendu.model.PenduModel;
import com.pendu.observer.Observable;
import com.pendu.vue.FenetrePrincipale;
import com.pendu.vue.Jeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;
	private Observable model;

	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		stagePrincipal.setTitle("Jeu du pendu");

		model = new PenduModel();

		initialisationConteneurPrincipal();
		initialisationContenu();
	}

	private void initialisationConteneurPrincipal() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("vue/FenetrePrincipale.fxml"));
		try {
			conteneurPrincipal = (BorderPane) loader.load();
			Scene scene = new Scene(conteneurPrincipal);
			stagePrincipal.setScene(scene);
			stagePrincipal.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FenetrePrincipale controller = loader.getController();
		controller.setMain(this);
	}

	private void initialisationContenu() {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("vue/Accueil.fxml"));
		try {
			AnchorPane conteneurPersonne = (AnchorPane) loader.load();
			conteneurPrincipal.setCenter(conteneurPersonne);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getStage() {
		return stagePrincipal;
	}

	public void jeuFenetre() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("vue/Jeu.fxml"));
		try {
			GridPane conteneurPersonne = (GridPane) loader.load();
			conteneurPrincipal.setCenter(conteneurPersonne);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Jeu controller = loader.getController();
		controller.init(model);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
