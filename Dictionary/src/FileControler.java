import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class FileControler {

    Connection con = null;
    PreparedStatement stmt=null;
    PreparedStatement stmt1=null;
    ResultSet rs = null;
    @FXML
    private TableView<Objet> aff;
    @FXML
    private TableColumn<Objet, String> expE;

    @FXML
    private TableColumn<Objet, String>expF;


    @FXML
    private TableColumn<Objet, String>mot;

    @FXML
    private TableColumn<Objet, String>type;

    @FXML
    private TableColumn<Objet, String>word;

    @FXML
    private Button backbtn;

    @FXML
    void backbtn(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainSceneDic.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) backbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    
        public ObservableList<Objet> getObjet(){
            ObservableList<Objet> object = FXCollections.observableArrayList();
            String query = "Select * from French,English where mot_E=synonyme_F and mot_F=synonyme_E";
            con = connectionOracle.getCon(); 
            try{
                stmt = con.prepareStatement(query);
                rs= stmt.executeQuery();
                while(rs.next()){
                    Objet ob = new Objet(rs.getString("Mot_E"), rs.getString("Mot_F"), rs.getString("Type_F"),rs.getString("Exemple_E"),rs.getString("Exemple_F"));
                    object.add(ob);
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
            return object;
        }


        public void Affichage(){
            ObservableList<Objet> list = getObjet();
            aff.setItems(list);
            word.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_E"));
            mot.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_F"));
            type.setCellValueFactory(new PropertyValueFactory<Objet, String>("Type_E"));
            expE.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_E"));
            expF.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_F"));
        }
        @FXML
        private Button exportbtn;

        @FXML
        private Button importbtn;
        @FXML
        private TextArea expT;
    
        @FXML
        void exportbtn(ActionEvent event) throws Exception{
                String ch="";
                Object obj = null;
            try{

                File file =new File("import1.txt");
                ObjectInputStream ObjectInputStream = new ObjectInputStream(new FileInputStream(file));

                while ((obj=ObjectInputStream.readObject())!=null){
                if (obj instanceof Objet){
                    ch=ch+((Objet) obj).Affichage();
                }
                expT.setText(ch);
                }
                
                ObjectInputStream.close();

            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }






        
        }
        @FXML
        void importbtn(ActionEvent event) throws Exception {
            
                try{
                    File file =new File("import1.txt");
                    Affichage();
                    ObservableList<Objet> list = getObjet();
                    ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                    for (int i =0; i<list.size();i++){
                    ObjectOutputStream.writeObject(list.get(i));
                    System.out.println(list.get(i));
                    }
                    ObjectOutputStream.close();
                }catch(Exception ex){
                    throw new RuntimeException(ex);
                }

        }
}


