import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserControler {

    
    Connection con = null;
    PreparedStatement stmt=null;
    PreparedStatement stmt1=null;
    ResultSet rs = null;

    @FXML
    private Button backc;

    @FXML
    private RadioButton btnE;

    @FXML
    private RadioButton btnF;

    @FXML
    private TextArea disc;

    @FXML
    private TextField word;

    @FXML
    private Button logoutbtn;

    @FXML
    void btnEc(ActionEvent event) {
        String query = "Select * from English,French where Synonyme_E='"+word.getText()+"'";
        con = connectionOracle.getCon();
        try{
            stmt = con.prepareStatement(query);
            rs= stmt.executeQuery();
            while(rs.next()){
                disc.setText(" Mot: "+rs.getString("Mot_E")+"\n Type : "+rs.getString("type_E")+"\n Exemple Anglais : "+rs.getString("Exemple_E")+"\n Exemple Français : "+rs.getString("Exemple_F"));
            }
            disc.setEditable(false);
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnFc(ActionEvent event) {
        String query = "Select * from English,French where Synonyme_F='"+word.getText()+"'";
        con = connectionOracle.getCon();
        try{
            stmt = con.prepareStatement(query);
            rs= stmt.executeQuery();
            while(rs.next()){
                disc.setText(" Word: "+rs.getString("Mot_F")+"\n Type : "+rs.getString("type_F")+"\n French Example : "+rs.getString("Exemple_F")+"\n English Example  : "+rs.getString("Exemple_E"));
            }
            disc.setEditable(false);
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    void backc(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) backc.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    void logoutbtnc(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginpage.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) backc.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
