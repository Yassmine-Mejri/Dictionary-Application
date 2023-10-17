
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class MainSceneControler implements Initializable {


    Connection con = null;
    PreparedStatement stmt=null;
    PreparedStatement stmt1=null;
    ResultSet rs = null;



    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;
    @FXML
    private TableColumn<English, String> col_1;

    @FXML
    private TableColumn<English, String> col_2;

    @FXML
    private TableColumn<English, String> col_3;

    @FXML
    private TableColumn<English, String> col_4;

    @FXML
    private TableView<English> tbl1; 

    @FXML
    private Button logoutad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    

    public ObservableList<English> getEnglish(){
        ObservableList<English> english = FXCollections.observableArrayList();

        String query = "Select * from English";
        con = connectionOracle.getCon();
        try{
            stmt = con.prepareStatement(query);
            rs= stmt.executeQuery();
            while(rs.next()){
                English en = new English();
                en.setMot_E(rs.getString("Mot_E"));
                en.setType_E(rs.getString("Type_E"));
                en.setExemple_E(rs.getString("Exemple_E"));
                en.setSynonyme_E(rs.getString("Synonyme_E"));
                english.add(en);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return english;
    }

    public void showEnglish(){
        ObservableList<English> list = getEnglish();
        tbl1.setItems(list);
        col_1.setCellValueFactory(new PropertyValueFactory<English, String>("Mot_E"));
        col_2.setCellValueFactory(new PropertyValueFactory<English, String>("Type_E"));
        col_3.setCellValueFactory(new PropertyValueFactory<English, String>("Exemple_E"));
        col_4.setCellValueFactory(new PropertyValueFactory<English, String>("Synonyme_E"));
    }


    @FXML
    private TableColumn<French, String> col_11;

    @FXML
    private TableColumn<French, String> col_22;

    @FXML
    private TableColumn<French, String> col_33;

    @FXML
    private TableColumn<French, String> col_44;

    @FXML
    private TableView<French> tbl2;

    public ObservableList<French> getFrench(){
        ObservableList<French> french = FXCollections.observableArrayList();
        String query = "Select * from French";
        con = connectionOracle.getCon(); 
        try{
            stmt = con.prepareStatement(query);
            rs= stmt.executeQuery();
            while(rs.next()){
                French fr = new French();
                fr.setMot_F(rs.getString("Mot_F"));
                fr.setType_F(rs.getString("Type_F"));
                fr.setExemple_F(rs.getString("Exemple_F"));
                fr.setSynonyme_F(rs.getString("Synonyme_F"));
                french.add(fr);
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return french;
    }
    public void showFrench(){
        ObservableList<French> list = getFrench();
        tbl2.setItems(list);
        col_11.setCellValueFactory(new PropertyValueFactory<French, String>("Mot_F"));
        col_22.setCellValueFactory(new PropertyValueFactory<French, String>("Type_F"));
        col_33.setCellValueFactory(new PropertyValueFactory<French, String>("Exemple_F"));
        col_44.setCellValueFactory(new PropertyValueFactory<French, String>("Synonyme_F"));
    }

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private TextField txt4;

    @FXML
    private TextField txt5;

    @FXML
    private TextField txt6;

    @FXML
    void btn1c(ActionEvent event) {
        btn1.setOnAction(e -> {
            showEnglish();
            showFrench();
        });
     
    }

    @FXML
    void btn2c(ActionEvent event) {
        String insertE = "insert into English(Mot_E,Type_E,Exemple_E,Synonyme_E) values(?,?,?,?)";
        String insertF = "insert into French(Mot_F,Type_F,Exemple_F,Synonyme_F) values(?,?,?,?)";
        con = connectionOracle.getCon();

        try{
            stmt = con.prepareStatement(insertE);
            stmt.setString(1, txt1.getText());
            stmt.setString(2, txt2.getText());
            stmt.setString(3, txt3.getText());
            stmt.setString(4, txt4.getText());
            stmt.executeUpdate();
            

            stmt1 = con.prepareStatement(insertF);
            stmt1.setString(1, txt4.getText());
            stmt1.setString(2, txt5.getText());
            stmt1.setString(3, txt6.getText());
            stmt1.setString(4, txt1.getText());
            stmt1.executeUpdate();
            
            showEnglish();
            showFrench();
            clear();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        
       
    }

    @FXML
    void btn3c(ActionEvent event) {
        String updateE="update English set mot_E=?, type_E=?, exemple_E=?, synonyme_E=? where mot_E=?";
        String updateF="update French set mot_F=?, type_F=?, exemple_F=?, synonyme_F=? where mot_F=?";
        con=connectionOracle.getCon();
        try{
            stmt=con.prepareStatement(updateE);
            stmt.setString(1, txt1.getText());
            stmt.setString(2, txt2.getText());
            stmt.setString(3, txt3.getText());
            stmt.setString(4, txt4.getText());

            stmt.setString(5,txt1.getText() );
            stmt.executeUpdate();
            stmt1 = con.prepareStatement(updateF);
            stmt1.setString(1, txt4.getText());
            stmt1.setString(2, txt5.getText());
            stmt1.setString(3, txt6.getText());
            stmt1.setString(4, txt1.getText()); 

            stmt1.setString(5, txt4.getText());
            stmt1.executeUpdate();
            showEnglish();
            showFrench();
            
           
        }catch(SQLException e){
            throw new RuntimeException();

        }

    }
    @FXML
    void getDataE(MouseEvent event) {
        English en =tbl1.getSelectionModel().getSelectedItem();

        txt1.setText(en.getMot_E());
        txt2.setText(en.getType_E());
        txt3.setText(en.getExemple_E());
        
        btn2.setDisable(true);
    }

    @FXML
    void getDataF(MouseEvent event) {
        French fr =tbl2.getSelectionModel().getSelectedItem();
        txt4.setText(fr.getMot_F());
        txt5.setText(fr.getType_F());
        txt6.setText(fr.getExemple_F());
       
        btn2.setDisable(true);
    }


    @FXML
    void btn4c(ActionEvent event) {
        String deleteE = "delete from English where Mot_E=?";
        String deleteF = "delete from French where Mot_F=?";
        con = connectionOracle.getCon();
        try{
            stmt = con.prepareStatement(deleteE);
            stmt.setString(1,txt1.getText());
            stmt.executeQuery();
            
            stmt1 = con.prepareStatement(deleteF);
            stmt1.setString(1,txt4.getText());
            stmt1.executeQuery();
            showFrench();
            showEnglish();
            clear();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    void clear(){
        txt1.setText(null);
        txt2.setText(null);
        txt3.setText(null);
        txt4.setText(null);
        txt5.setText(null);
        txt6.setText(null);
        btn2.setDisable(false);
    }

    @FXML
    private Button filebtn;


    @FXML
    void filebtn(ActionEvent event) {
    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("file.fxml"));
        loader.getController();
        Parent root = loader.load();

        Stage window= (Stage) filebtn.getScene().getWindow();

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }


    @FXML
    void logoutadc(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginpage.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) logoutad.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






