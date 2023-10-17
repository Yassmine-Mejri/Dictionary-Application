import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateControler {

    Connection con = null;
PreparedStatement stmt=null;
ResultSet rs = null;

    @FXML
    private Hyperlink linklogin;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField passconftxtcr;

    @FXML
    private PasswordField passtxtcr;

    @FXML
    private TextField usertxtcr;

    @FXML
    void linkloginc(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginpage.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) loginbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
    
        }

    }

    @FXML
    void loginbtnc(ActionEvent event) {

        String insert = "insert into diclogin(username,password) values(?,?)";
        con = connectionOracle.getCon();
        try{
            
            String pas=passtxtcr.getText().toString();
            String conf=passconftxtcr.getText().toString();
            
         if((pas.compareTo(conf))==0){

            stmt = con.prepareStatement(insert);
            stmt.setString(1, usertxtcr.getText());
            stmt.setString(2, passtxtcr.getText());
            stmt.executeUpdate();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginpage.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) linklogin.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
         }else{
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(" Invalid Password!");
            alert1.showAndWait(); 
            usertxtcr.clear();
            passtxtcr.clear();
            passconftxtcr.clear();
         }
        }catch (Exception e) {
            e.printStackTrace();
        }
       
       
    } 

    

    

    


}