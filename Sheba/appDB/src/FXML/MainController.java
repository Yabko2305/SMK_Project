package FXML;

import AppCopy.Main;
import Res.DataContainer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class MainController {
    public static Stage stage;

    private static Connection conn;
    private static Statement  stat;

    @FXML TableView<DataContainer> table;

    @FXML TextField fNameField;
    @FXML TextField sNameField;
    @FXML TextField eMailField;
    @FXML TextField pass1Field;
    @FXML TextField pass2Field;
    @FXML TextField mailCheckField;
    @FXML TextField passCheckField;

    @FXML TableColumn idColumn;
    @FXML TableColumn fNameColumn;
    @FXML TableColumn sNameColumn;
    @FXML TableColumn eMailColumn;
    @FXML TableColumn passColumn;

    @FXML Button saveButton;
    @FXML Button rebuiltDB;
    @FXML Button checkButton;

    @FXML
    public void initialize() throws SQLException {
        Main m = new Main();
        createConnection();
        createTable();
        refreshTable();

        saveButton.setOnAction(event -> {
            if( checkToBeFilled() ){
                saveToDB();
                clearFields();
            }
        });
        rebuiltDB.setOnAction(event -> {
            rebuiltDB();
        });
        checkButton.setOnAction(event -> {
            if(compareInputWithDB()){
                m.openGiveMenu(stage);
            }
        });

        listeners();
    }

    private void createConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:3306/test?" + "useSSL=false&amp;" + "useUnicode=true&amp;" + "characterEncoding=utf8";
        final String name = "root";
        final String pass = "kamelot";

        conn = DriverManager.getConnection(URL, name, pass);
        stat = conn.createStatement();
        stat.executeUpdate("USE test");
        stat.executeUpdate("CREATE TABLE IF NOT EXISTS Info(id MEDIUMINT NOT NULL AUTO_INCREMENT, fName CHAR(30) NOT NULL, sName CHAR(30) NOT NULL, eMail CHAR(30) NOT NULL, pass CHAR(30) NOT NULL, PRIMARY KEY (id))");
        stat.executeUpdate("CREATE TABLE IF NOT EXISTS Pills(id MEDIUMINT NOT NULL AUTO_INCREMENT, p1 CHAR(30) NOT NULL, n1 MEDIUMINT NOT NULL, p2 CHAR(30) NOT NULL, n2 MEDIUMINT NOT NULL, p3 CHAR(30) NOT NULL, n3 MEDIUMINT NOT NULL, eMail CHAR(30) NOT NULL, PRIMARY KEY (id))");
    }
    private void createTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fNameColumn.setCellValueFactory(new PropertyValueFactory<>("fName"));
        sNameColumn.setCellValueFactory(new PropertyValueFactory<>("sName"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        passColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));
    }

    private boolean checkToBeFilled() {
        int errors = 0;

        if(fNameField.getText().length() == 0){
            errors++;
            fNameField.setStyle("-fx-border-color: #FF0000");
        }

        if(sNameField.getText().length() == 0){
            errors++;
            sNameField.setStyle("-fx-border-color: #FF0000");
        }

        if(eMailField.getText().length() == 0){
            errors++;
            eMailField.setStyle("-fx-border-color: #FF0000");
        }

        if(!passCompare()){
            errors++;
        }

        return errors == 0;
    }

    private void saveToDB() {
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Info(fName, sName, eMail, pass) VALUES(?,?,?,?)");
            preparedStatement.setString(1, fNameField.getText());
            preparedStatement.setString(2, sNameField.getText());
            preparedStatement.setString(3, eMailField.getText());
            preparedStatement.setString(4, pass1Field.getText());
            preparedStatement.execute();

            PreparedStatement prepare = conn.prepareStatement("INSERT INTO Pills(eMail, p1, p2, p3, n1, n2, n3) VALUES(?,'null','null','null',0,0,0)");
            prepare.setString(1, eMailField.getText());
            prepare.execute();
        } catch (Exception e){
            System.out.println("Error at saveToDB");
            System.out.println(e.getMessage());
        }

        refreshTable();
    }

    private void refreshTable() {
        ArrayList<DataContainer> list = new ArrayList<>();

        try {
            ResultSet resultSet = stat.executeQuery("SELECT * FROM Info");
            while(resultSet.next()){
                list.add(new DataContainer( resultSet.getString("id"), resultSet.getString("fName"), resultSet.getString("sName"), resultSet.getString("eMail"), resultSet.getString("pass")));
            }
        } catch (SQLException e) {
            System.out.println("Error in refreshTable");
        }

        table.getItems().clear();
        for(DataContainer p: list)
            table.getItems().add(p);
        if(list.size()==0)  table.getItems().add(new DataContainer("null", "null", "null", "null", "null"));
    }

    private void clearFields() {
        fNameField.setText("");
        sNameField.setText("");
        eMailField.setText("");
        pass1Field.setText("");
        pass2Field.setText("");

        fNameField.setStyle("-fx-border-color: #FFFFFF");
        sNameField.setStyle("-fx-border-color: #FFFFFF");
        eMailField.setStyle("-fx-border-color: #FFFFFF");
        pass1Field.setStyle("-fx-border-color: #FFFFFF");
        pass2Field.setStyle("-fx-border-color: #FFFFFF");
    }

    private void listeners() {
        fNameField.setOnKeyReleased(event -> {
            if(fNameField.getText().length() > 0)
                fNameField.setStyle("-fx-border-color: #00FF00");
            else
                fNameField.setStyle("-fx-border-color: #FF0000");
        });

        sNameField.setOnKeyReleased(event -> {
            if(sNameField.getText().length() > 0)
                sNameField.setStyle("-fx-border-color: #00FF00");
            else
                sNameField.setStyle("-fx-border-color: #FF0000");
        });

        eMailField.setOnKeyReleased(event -> {
            if(eMailField.getText().length() > 0)
                eMailField.setStyle("-fx-border-color: #00FF00");
            else
                eMailField.setStyle("-fx-border-color: #FF0000");
        });

        pass1Field.setOnKeyReleased(event -> passCompare());
        pass2Field.setOnKeyReleased(event -> passCompare());
    }

    private boolean passCompare() {
        if(pass1Field.getText().length() > 0 && pass2Field.getText().length() > 0 && pass1Field.getText().compareTo(pass2Field.getText()) == 0) {
            pass1Field.setStyle("-fx-border-color: #00FF00");
            pass2Field.setStyle("-fx-border-color: #00FF00");
            return true;
        } else {
            pass1Field.setStyle("-fx-border-color: #FF0000");
            pass2Field.setStyle("-fx-border-color: #FF0000");
            return false;
        }
    }

    private boolean compareInputWithDB() {
        ArrayList<String> passList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Info WHERE eMail = ?");
            preparedStatement.setString(1, mailCheckField.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                passList.add(resultSet.getString("pass"));
            }
        } catch (Exception e){
            System.out.println("Error at compareInputWithDB");
            System.out.println(e.getMessage());
        }

        if(passList.isEmpty()){
            System.out.println("No such E-Mail");
            return false;
        } else {
            if(passList.contains(passCheckField.getText())){
                System.out.println("Success");
                return true;
            } else {
                System.out.println("Wrong password");
                return false;
            }
        }
    }

    private void rebuiltDB() {
        try {
            stat.executeUpdate("DROP TABLE IF EXISTS Info");
            stat.executeUpdate("DROP TABLE IF EXISTS Pills");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Info(id MEDIUMINT NOT NULL AUTO_INCREMENT, fName CHAR(30) NOT NULL, sName CHAR(30) NOT NULL, eMail CHAR(30) NOT NULL, pass CHAR(30) NOT NULL, PRIMARY KEY (id))");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Pills(id MEDIUMINT NOT NULL AUTO_INCREMENT, p1 CHAR(30) NOT NULL, n1 MEDIUMINT NOT NULL, p2 CHAR(30) NOT NULL, n2 MEDIUMINT NOT NULL, p3 CHAR(30) NOT NULL, n3 MEDIUMINT NOT NULL, eMail CHAR(30) NOT NULL, PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("Error at rebuiltDB");
            System.out.println(e.getMessage());
        }
        refreshTable();
    }
}
