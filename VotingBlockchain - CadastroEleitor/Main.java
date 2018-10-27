/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.Eleitor;
import Model.cadastroCandidato;
import Model.cadastroEleitor;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private static Stage stage;
    private static Scene mainScene;
    private static Scene profileScene;
    private static Scene votingScene;
    private static Scene confirmaScene;
    private static Scene saibaScene;
    private static Scene cadastroScene;

    cadastroCandidato cadCandidato;
    cadastroEleitor cadEleitor;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //cadCandidato =  new cadastroCandidato();
        //cadEleitor =  new cadastroEleitor();
        stage = primaryStage;

        primaryStage.setTitle("VotingBlockchain - E - Voting Blockchain System ");

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
        mainScene = new Scene(fxmlMain, 1200, 600);

        Parent fxmlProfile = FXMLLoader.load(getClass().getResource("/View/Perfil.fxml"));
        profileScene = new Scene(fxmlProfile, 1200, 600);

        Parent fxmlVoting = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
        votingScene = new Scene(fxmlVoting, 1200, 600);

        Parent fxmlConfirma = FXMLLoader.load(getClass().getResource("/View/Confirma.fxml"));
        confirmaScene = new Scene(fxmlConfirma, 1200, 600);
        
        Parent fxmlSaiba = FXMLLoader.load(getClass().getResource("/View/Saiba Mais.fxml"));
        saibaScene = new Scene(fxmlSaiba, 1200, 600);

        Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("/View/Cadastro.fxml"));
        cadastroScene = new Scene(fxmlCadastro, 1200, 600);
        
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/Img/icon.png"));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
    }

    public static void changeScreen(String scr, Eleitor objEleitor) {
        switch (scr) {
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", objEleitor);
                break;

            case "profile":
                stage.setScene(profileScene);
                notifyAllListeners("profile", objEleitor);
                break;

            case "voting":
                stage.setScene(votingScene);
                notifyAllListeners("voting", objEleitor);
                break;
            
            case "confirm":
                stage.setScene(confirmaScene);
                notifyAllListeners("confirm", objEleitor);
                break;
                
            case "learn":
                stage.setScene(saibaScene);
                notifyAllListeners("learn", objEleitor);
                break;
                
            case "register":
                stage.setScene(cadastroScene);
                notifyAllListeners("register", objEleitor);
                break;

        }
    }

    public static void changeScreen(String scr) {
        changeScreen(scr, null);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {

        void onScreenChanged(String newScreen, Eleitor objEleitor);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    public static void notifyAllListeners(String newScreen, Eleitor objEleitor) {
        for (OnChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, objEleitor);
        }
    }
}
