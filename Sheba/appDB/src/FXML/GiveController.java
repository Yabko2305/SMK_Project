package FXML;

import AppCopy.Main;
import Res.DataContainer;
import Res.PillContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class GiveController {
    public static Stage stage;

    private static Connection conn;
    private static Statement stat;

    @FXML TableView<PillContainer> table;

    @FXML ChoiceBox<String> choiseBox;

    @FXML Button give;

    @FXML TableColumn p1;
    @FXML TableColumn p2;
    @FXML TableColumn p3;
    @FXML TableColumn n1;
    @FXML TableColumn n2;
    @FXML TableColumn n3;
    @FXML TableColumn eMail;

    @FXML
    public void initialize() throws SQLException {
        Main m = new Main();
        createConnection();

        give.setOnAction(event -> {
            if(choiseBox.getValue() != null){
                givePills();
            }
        });
    }

    private void createConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost:3306/test?" + "useSSL=false&amp;" + "useUnicode=true&amp;" + "characterEncoding=utf8";
        final String name = "root";
        final String pass = "kamelot";

        conn = DriverManager.getConnection(URL, name, pass);
        stat = conn.createStatement();
        stat.executeUpdate("USE test");

        createTable();
    }
    private void createTable() {
        p1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        p2.setCellValueFactory(new PropertyValueFactory<>("p2"));
        p3.setCellValueFactory(new PropertyValueFactory<>("p3"));
        n1.setCellValueFactory(new PropertyValueFactory<>("n1"));
        n2.setCellValueFactory(new PropertyValueFactory<>("n2"));
        n3.setCellValueFactory(new PropertyValueFactory<>("n3"));
        eMail.setCellValueFactory(new PropertyValueFactory<>("eMail"));

        refreshTable();
    }

    private void refreshTable() {
        ArrayList<PillContainer> list = new ArrayList<>();

        try {
            ResultSet resultSet = stat.executeQuery("SELECT * FROM Pills");
            while(resultSet.next()){
                list.add(new PillContainer(resultSet.getString("p1"), resultSet.getString("p2"), resultSet.getString("p3"), resultSet.getInt("n1"), resultSet.getInt("n2"), resultSet.getInt("n3"), resultSet.getString("eMail")));
            }
        } catch (SQLException e) {
            System.out.println("Error in refreshTable");
        }

        table.getItems().clear();
        for(PillContainer p: list)
            table.getItems().add(p);
        if(list.size()==0)  table.getItems().add(new PillContainer("null", "null", "null", 0, 0, 0, "null"));

        refreshChoice(list);
    }
    private void refreshChoice(ArrayList<PillContainer> list){
        choiseBox.getItems().clear();
        for(PillContainer p: list){
            choiseBox.getItems().add(p.getEMail());
        }
    }

    private void givePills() {
        String thisEMail = choiseBox.getValue();
        int newN1=0, newN2=0, newN3=0;
        try{
            ResultSet resultSet = stat.executeQuery("SELECT * FROM Pills WHERE eMail ='"+ thisEMail +"'");
            while (resultSet.next()){
                if(resultSet.getInt("n1") != 0){
                    System.out.println("Give pill "+resultSet.getString("p1"));
                    newN1 = resultSet.getInt("n1") - 1;
                }
                if(resultSet.getInt("n2") != 0){
                    System.out.println("Give pill "+resultSet.getString("p2"));
                    newN2 = resultSet.getInt("n2") - 1;
                }
                if(resultSet.getInt("n3") != 0){
                    System.out.println("Give pill "+resultSet.getString("p3"));
                    newN3 = resultSet.getInt("n3") - 1;
                }
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Pills SET n1 = ?, n2 = ?, n3 = ? WHERE eMail = ?");
                preparedStatement.setInt(1, newN1);
                preparedStatement.setInt(2, newN2);
                preparedStatement.setInt(3, newN3);
                preparedStatement.setString(4, thisEMail);
                preparedStatement.execute();

                if(newN1 == 9 && newN2 == 0 && newN3 == 0){
                    System.out.println("Empty!");
                }
            }
        } catch (Exception e){
            System.out.println("Error at givePills");
            System.out.println(e.getMessage());
        }
        refreshTable();
    }
}
