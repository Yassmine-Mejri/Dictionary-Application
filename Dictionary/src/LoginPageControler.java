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

public class LoginPageControler {

    Connection con = null;
PreparedStatement stmt=null;
PreparedStatement stmt1=null;
ResultSet rs = null;

    @FXML
    private Button adminbtn;

    @FXML
    private Hyperlink linkaccount;

    @FXML
    private Button loginbtnu;

    @FXML
    private PasswordField passtxt;

    @FXML
    private TextField usertxt;

    @FXML
    void adminbtnc(ActionEvent event) {

        String adname="admin";
        String adpass="admin";
    try{
        if(usertxt.getText().equals(adname) && passtxt.getText().equals(adpass) ){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainSceneDic.fxml"));
        loader.getController();
        Parent root = loader.load();
        Stage window= (Stage) adminbtn.getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }else{
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setHeaderText(" Invalid Username and Password!");
        alert1.showAndWait(); 
        usertxt.clear();
        passtxt.clear();
     }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    void linkaccountc(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) linkaccount.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void loginbtnuc(ActionEvent event) {

        String query = "Select * from diclogin ";
        con = connectionOracle.getCon();
        try{
            stmt = con.prepareStatement(query);
            rs= stmt.executeQuery();
            while(rs.next()){
                if((usertxt.getText().compareTo(rs.getString("username"))==0) && (passtxt.getText().compareTo(rs.getString("password"))==0) ){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("user.fxml"));
                    loader.getController();
                    Parent root = loader.load();
                    Stage window= (Stage) loginbtnu.getScene().getWindow();
                    Scene scene = new Scene(root);
                    window.setScene(scene);
                    window.show();
            }
        }
        
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

}
