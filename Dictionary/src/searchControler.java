import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class searchControler {
    Connection con = null;
    PreparedStatement stmt=null;
    PreparedStatement stmt1=null;
    ResultSet rs = null;
    @FXML
    private Button back;
    @FXML
    private TextField nbrtr;
    @FXML
    private RadioButton btn1;

    @FXML
    private RadioButton btn2;
    @FXML
    void btn1checked(ActionEvent event)  throws IOException{
        ObservableList<Objet> list = Number();
        listage.setItems(list);
        word.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_E"));
        mot.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<Objet, String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_E"));
        expF.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_F"));
        int ch=list.size();
        nbrtr.setText("N°: \t"+ch);
    
    }

    @FXML
    void btn2checked(ActionEvent event)  throws IOException{
        ObservableList<Objet> list = Number();
        listage.setItems(list);
        word.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_E"));
        mot.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<Objet, String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_E"));
        expF.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_F"));
        int ch=list.size();
        nbrtr.setText("N°: \t"+ch);

    }

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
    private TextField search;

    
    @FXML
    void filtrage(ActionEvent event) throws IOException {

        ObservableList<Objet> list = getObjetfiltrer();
        listage.setItems(list);
        word.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_E"));
        mot.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<Objet, String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_E"));
        expF.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_F"));


    }
    public  ObservableList<Objet> getObjetfiltrer() throws IOException{
        ObservableList<Objet> object = FXCollections.observableArrayList();
        File file = new File("liste.txt");
        BufferedReader  BufferedReader  =new BufferedReader(new FileReader(file));
        Scanner scan = new Scanner(file);
        while((BufferedReader.readLine())!=null){ 
            while(scan.hasNextLine()){
                String line =   scan.nextLine();
                String []details =line.split(",");
                Objet ob =new Objet();
                for(String det:details){
                   //System.out.println(det);
                    String mot_E=details[0];
                    String mot_F=details[1];
                    String type_E=details[2];
                    String exemple_E=details[3];
                    String exemple_F=details[4];
                    if(( search.getText().compareTo(mot_E)==0) ||(search.getText().compareTo(mot_F)==0)){
                
                        ob = new Objet(mot_E, mot_F, type_E, exemple_E, exemple_F);
                    
                    }
                
                
                }
                object.add(ob);
            }
        }                
        BufferedReader.close();
        scan.close();
        return object;
    }
    public  ObservableList<Objet> Number() throws IOException{
        ObservableList<Objet> object = FXCollections.observableArrayList();
        File file = new File("liste.txt");
        BufferedReader  BufferedReader  =new BufferedReader(new FileReader(file));
        Scanner scan = new Scanner(file);
        while((BufferedReader.readLine())!=null){ 
            while(scan.hasNextLine()){
                String line =   scan.nextLine();
                String []details =line.split(",");
                Objet ob =new Objet();
                for(String det:details){
                   //System.out.println(det);
                    String mot_E=details[0];
                    String mot_F=details[1];
                    String type_E=details[2];
                    String exemple_E=details[3];
                    String exemple_F=details[4];

                        ob = new Objet(mot_E, mot_F, type_E, exemple_E, exemple_F);

                
                
                }
                object.add(ob);
            }
        }                
        BufferedReader.close();
        scan.close();
        return object;
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
        listage.setItems(list);
        word.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_E"));
        mot.setCellValueFactory(new PropertyValueFactory<Objet, String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<Objet, String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_E"));
        expF.setCellValueFactory(new PropertyValueFactory<Objet, String>("Exemple_F"));
    }

    @FXML
    private TableView<Objet> listage;

    @FXML
    void select(MouseEvent event) throws Exception {
        
        try {
            ObservableList<Objet> list = getObjet();
            File file = new File("liste.txt");
            if (file.exists()) { // Check if file already exists
                file.delete(); // Delete it to start with a fresh file
            }
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < list.size(); i++) {
                System.out.println("dkhal lmara :"+i);
                printWriter.println(list.get(i).AffichageL()  );
            }
            Affichage();
            printWriter.close();
            System.out.println("File written successfully.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void backc(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user.fxml"));
            loader.getController();
            Parent root = loader.load();
            Stage window= (Stage) back.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
