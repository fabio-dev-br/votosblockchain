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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
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
    private DatePicker dataNascDatePicker;
    @FXML
    private DatePicker datadataEmissaoDatePicker;
    @FXML
    private TextField situacaoLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private PasswordField passwordLabel;
    @FXML
    private CheckBox checkMasculino;
    @FXML
    private CheckBox checkFeminino;
    
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
    
    public String selecionaSexualidade(ActionEvent event) {
    	String retorno = null;
    	
    	if (event.getSource() == checkMasculino) {
    		if (checkMasculino.isSelected()) {
    			retorno = "Masculino";
    		}
    	} else if (event.getSource() == checkFeminino) {
    		if (checkFeminino.isSelected()) {
    			retorno = "Feminino";
    		}
    	}
    	
    	return retorno;
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
    	String dataNascLabel = dataNascDatePicker.getAccessibleText();
    	Date dataNasc = formatter.parse(dataNascLabel);
    	String dataEmissaoLabel = datadataEmissaoDatePicker.getAccessibleText();
    	Date dataEmissao = formatter.parse(dataEmissaoLabel);
    	String situacao = situacaoLabel.getText();
    	String email = emailLabel.getText();
    	String password = passwordLabel.getText();
    	String sexo = selecionaSexualidade(event);
    	
    	try {
    		CtrEleitor.addEleitor(new Eleitor(nomeEleitor, nomePai, nomeMae, sexo, naturalidade, userID, password, dataNasc, dataEmissao, situacao, email));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar!");
        }
    	
        Main.changeScreen("main");
    }
    
    
}
