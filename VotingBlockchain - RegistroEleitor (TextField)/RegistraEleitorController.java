package Controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Main.Main;
import Model.Eleitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistraEleitorController implements Initializable {
	
	@FXML
    private TextField codigoEleitorLabel;
    @FXML
    private TextField nomeEleitorLabel;
    @FXML
    private TextField nomePaiLabel;
    @FXML
    private TextField nomeMaeLabel;
    @FXML
    private TextField naturalidadeLabel;
    @FXML
    private TextField dataNascLabel;
    @FXML
    private TextField dataEmissaoLabel;
    @FXML
    private TextField situacaoLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private TextField sexualidadeLabel;
    @FXML
    private PasswordField passwordLabel;
    
    
    EleitorController CtrEleitor = new EleitorController();
    Eleitor objEleitor = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Eleitor objEleitor) {
                if (newScreen.equals("register")) {

                }
            }
        });
    }
    
    @FXML
    private void confirmaCadastroAction(ActionEvent event) throws ParseException {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    	
    	String codigo = codigoEleitorLabel.getText();
    	int userID = Integer.parseInt(codigo);
    	String nomeEleitor = nomeEleitorLabel.getText();
    	String nomePai = nomePaiLabel.getText();
    	String nomeMae = nomeMaeLabel.getText();
    	String naturalidade = naturalidadeLabel.getText();
    	String dataNascText = dataNascLabel.getText();
    	Date dataNasc = formatter.parse(dataNascText);
    	String dataEmissaoText = dataEmissaoLabel.getText();
    	Date dataEmissao = formatter.parse(dataEmissaoText);
    	String situacao = situacaoLabel.getText();
    	String email = emailLabel.getText();
    	String password = passwordLabel.getText();
    	String sexo = sexualidadeLabel.getText();
    	
    	try {
    		CtrEleitor.addEleitor(new Eleitor(nomeEleitor, nomePai, nomeMae, sexo, naturalidade, userID, password, dataNasc, dataEmissao, situacao, email));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar!");
        }
    	
        Main.changeScreen("main");
    }
    
    
}
