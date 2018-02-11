package com.pendu.vue;

import java.io.File;
import com.pendu.controler.PenduControler;
import com.pendu.observer.Observable;
import com.pendu.observer.Observateur;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class Jeu implements Observateur {

	private Observable model;
	private PenduControler controler;

	@FXML
	private ImageView image;
	@FXML
	private Label nbMots;
	@FXML
	private Label score;
	@FXML
	private Label mot;
	@FXML
	private FlowPane panneauBoutons;

	private Button[] bouton;

	public void init(Observable model) {
		this.model = model;

		model.addObservateur(this);
		model.initpartie();

		controler = new PenduControler(model);

		nbMots.setText("Nombre de mots trouvés : " + model.getNbMotsTrouves());
		score.setText("Votre score actuel est de " + model.getPoints() + " pts");

		mot.setText(model.getMotTrouve());

		image.setImage(new Image(new File("Images/erreur0.jpg").toURI().toString()));

		char[] listBouton = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		int i = 0;
		bouton = new Button[26];
		for (char c : listBouton) {
			bouton[i] = new Button("" + c);
			bouton[i].setPrefSize(38, 25);
			bouton[i].setOnAction((e) -> controler.proposer(c));// listener : lors du clic proposer le caractére
																// correspondant c
			panneauBoutons.getChildren().add(bouton[i]);
			i++;
		}
	}

	private void reinit() {
		nbMots.setText("Nombre de mots trouvés : " + model.getNbMotsTrouves());
		score.setText("Votre score actuel est de " + model.getPoints() + " pts");

		mot.setText(model.getMotTrouve());

		image.setImage(new Image(new File("Images/erreur0.jpg").toURI().toString()));

		for (Button b : bouton) {
			b.setDisable(false);
		}
	}

	@Override
	public void update(String motTrouve, byte nbErreur, char lettrePropose[]) {
		mot.setText(motTrouve);
		image.setImage(new Image(new File("Images/erreur" + nbErreur + ".jpg").toURI().toString()));

		for (char c : lettrePropose) {
			for (Button b : bouton) {
				if (b.getText().equalsIgnoreCase(String.valueOf(c))) {
					b.setDisable(true);
				}
			}
		}
	}

	@Override
	public void updateFinManche(String mot) {

		Alert boiteDialog = new Alert(AlertType.INFORMATION);
		boiteDialog.setTitle("VICTOIRE");
		boiteDialog.setHeaderText("VICTOIRE! Bravo vous avez trouvé le mot : " + mot);
		boiteDialog.showAndWait();

		this.reinit();

	}

	@Override
	public String updateFinPartie(boolean estDansLesMeilleursScores, String mot, int points, int nbMotsTrouves) {
		String pseudo = "";
		if (!estDansLesMeilleursScores) {
			Alert boiteDialog = new Alert(AlertType.INFORMATION);
			boiteDialog.setTitle("PERDU");
			boiteDialog.setHeaderText("PERDU! Désolé vous n'avez pas trouvé le mot : " + mot
					+ ". Et vous n'êtes pas dans les meilleurs scores");
			boiteDialog.showAndWait();
		} else {
			TextInputDialog boiteDialog = new TextInputDialog("Pseudo");
			boiteDialog.setTitle("PERDU");
			boiteDialog.setHeaderText("PERDU! Désolé vous n'avez pas trouvé le mot : " + mot
					+ ".\nMais vous êtes dans les meilleurs scores! Merci d'indiquer votre Peudo!");
			pseudo = boiteDialog.showAndWait().get();
		}
		this.reinit();

		return pseudo;
	}
}
