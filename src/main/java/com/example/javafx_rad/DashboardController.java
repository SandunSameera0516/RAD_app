package com.example.javafx_rad;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{

    @FXML
    private TextField idTextField1;
    @FXML
    private TextField wasteTextfield1;
    @FXML
    private TextField objectTextfield1;
    @FXML
    private TextField containerTextField1;
    @FXML
    private TextField twasteTextField1;

    @FXML
    private TextField idTextField2;
    @FXML
    private TextField wasteTextfield2;
    @FXML
    private TextField objectTextfield2;
    @FXML
    private TextField containerTextField2;
    @FXML
    private TextField twasteTextField2;

    @FXML
    private Button insertData1;
    @FXML
    private Button delete1;
    @FXML
    private Button update1;
    @FXML
    private Button sum1;
    @FXML
    private Button print;

    @FXML
    private Button insertData2;
    @FXML
    private Button delete2;
    @FXML
    private Button update2;
    @FXML
    private Button sum2;

    @FXML
    private TableView<InfectiousWaste> infectiousTableView1;
    @FXML
    private TableColumn<InfectiousWaste, Integer> id1;
    @FXML
    private TableColumn<InfectiousWaste, String> tw1;
    @FXML
    private TableColumn<InfectiousWaste, String> ob1;
    @FXML
    private TableColumn<InfectiousWaste, String> tc1;
    @FXML
    private TableColumn<InfectiousWaste, Integer> wd1;

    @FXML
    private TableView<InfectiousWaste> nonInfectiousTableView;
    @FXML
    private TableColumn<InfectiousWaste, Integer> id2;
    @FXML
    private TableColumn<InfectiousWaste, String> tw2;
    @FXML
    private TableColumn<InfectiousWaste, String> ob2;
    @FXML
    private TableColumn<InfectiousWaste, String> tc2;
    @FXML
    private TableColumn<InfectiousWaste, Integer> wd2;

    @FXML
    PieChart pieChart;

    @FXML
    PieChart pieChart1;

    @FXML
    private LineChart<String, Integer> LineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    DatabaseConnection jdbc;

    ObservableList<PieChart.Data> piechartdata;
    ArrayList<Integer> cell = new ArrayList<Integer>();
    ArrayList<String> name = new ArrayList<String>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jdbc = new DatabaseConnection();
        addListForTable();
        showTable();
        addListForTables();
        showTables();

        showPieChart();
        pieChart.setData(piechartdata);


        //Line Chart(Infectious Waste)
        XYChart.Series series = new XYChart.Series();
        series.setName("Infectious Type of Waste");

        series.getData().add(new XYChart.Data("Sharp", 23));
        series.getData().add(new XYChart.Data("Chemical", 20));
        series.getData().add(new XYChart.Data("Pharmaceutical", 3));
        series.getData().add(new XYChart.Data("Pathological", 10));
        series.getData().add(new XYChart.Data("Genotoxic", 5));

        LineChart.getData().add(series);


        //Pie Chart(Amount of Waste)
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Infectional Waste", 35),
                        new PieChart.Data("Non Infectional Waste", 5));

        pieChart1.setData(pieChartData);

    }

    //Print table 1 PDF
    @FXML
    void PrintPDF(ActionEvent event) throws Exception {
        OutputStream file = new FileOutputStream(new File("C:/Users/Sandun Sameera/Desktop/file1.pdf"));
        Document document = new Document();
        PdfWriter.getInstance(document, file);
        document.setPageSize(PageSize.A4);
        document.open();
        document.add(new Paragraph("YOUR STRING HERE"));
        document.close();
        file.close();

    }

    //Print table 2 PDF
    @FXML
    void PrintFile(ActionEvent event) throws Exception {
        OutputStream file = new FileOutputStream(new File("C:/Users/Sandun Sameera/Desktop/file2.pdf"));
        Document document = new Document();
        PdfWriter.getInstance(document, file);
        document.setPageSize(PageSize.A4);
        document.open();
        document.add(new Paragraph("YOUR STRING HERE"));
        document.close();
        file.close();
    }

    //Add details for the Text Fields of Infectious Waste Details to Update or Delete the related details
    private void addListForTable() {
        infectiousTableView1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                insertData1.setDisable(true);
                update1.setDisable(false);
                delete1.setDisable(false);

                wasteTextfield1.setText(newSelection.getType_of_waste());
                objectTextfield1.setText(newSelection.getWaste_object());
                containerTextField1.setText(newSelection.getType_of_container());
                twasteTextField1.setText(String.valueOf(newSelection.getTotal_waste_per_day()));

            }else{
                insertData1.setDisable(false);
                update1.setDisable(true);
                delete1.setDisable(true);

                wasteTextfield1.setText("");
                objectTextfield1.setText("");
                containerTextField1.setText("");
                twasteTextField1.setText("");

            }
        });
    }

    //Add details for the Text Fields of Non_Infectious Waste Details to Update or Delete the related details
    private void addListForTables() {
        nonInfectiousTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                insertData2.setDisable(true);
                update2.setDisable(false);
                delete2.setDisable(false);

                wasteTextfield2.setText(newSelection.getType_of_waste());
                objectTextfield2.setText(newSelection.getWaste_object());
                containerTextField2.setText(newSelection.getType_of_container());
                twasteTextField2.setText(String.valueOf(newSelection.getTotal_waste_per_day()));

            }else{
                insertData2.setDisable(false);
                update2.setDisable(true);
                delete2.setDisable(true);

                wasteTextfield2.setText("");
                objectTextfield2.setText("");
                containerTextField2.setText("");
                twasteTextField2.setText("");

            }
        });
    }

    //Show Infectious Waste table in Infectious Waste tab
    public void showTable(){
        ObservableList<InfectiousWaste> list = getTableList();
        id1.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, Integer>("id"));
        tw1.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, String>("type_of_waste"));
        ob1.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, String>("waste_object"));
        tc1.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, String>("type_of_container"));
        wd1.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, Integer>("total_waste_per_day"));
        infectiousTableView1.setItems(list);

    }

    //Show Non_Infectious Waste table in Non_Infectious Waste tab
    public void showTables(){
        ObservableList<InfectiousWaste> list = getTableLists();
        id2.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, Integer>("id"));
        tw2.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, String>("type_of_waste"));
        ob2.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, String>("waste_object"));
        tc2.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, String>("type_of_container"));
        wd2.setCellValueFactory(new PropertyValueFactory<InfectiousWaste, Integer>("total_waste_per_day"));
        nonInfectiousTableView.setItems(list);

    }

    //Pie Chart(Infectious Object)
    public void showPieChart(){
        piechartdata = FXCollections.observableArrayList();
        Connection conn = jdbc.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = conn.prepareStatement("SELECT * FROM infectious_waste");
            rs = ps.executeQuery();

            while(rs.next()){
                piechartdata.add(new PieChart.Data(rs.getString("waste_object"), rs.getInt("total_waste_per_day")));
                name.add(rs.getString("waste_object"));
                cell.add(rs.getInt("total_waste_per_day"));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());

        }

    }


    //Update details on selected field in Infectious waste table
    @FXML
    private void updateData(ActionEvent event){
        Connection conn = jdbc.getConnection();

        try{
            InfectiousWaste product = infectiousTableView1.getSelectionModel().getSelectedItem();
            String query = "UPDATE infectious_waste SET type_of_waste = '"+ wasteTextfield1.getText() +"', " +
                    "waste_object = '"+ objectTextfield1.getText() +"', type_of_container = '"+ containerTextField1.getText() +"', " +
                    "total_waste_per_day = '"+ twasteTextField1.getText() +"' WHERE id = '"+ product.getId() +"'";

            executeQuery(query);
            showTable();

        }catch(Exception e){
            System.out.println(e.getMessage());

        }

    }

    //Update details on selected field in Non_Infectious waste table
    @FXML
    private void updateDatas(ActionEvent event){
        Connection conn = jdbc.getConnection();

        try{
            InfectiousWaste product = nonInfectiousTableView.getSelectionModel().getSelectedItem();
            String query = "UPDATE non_infectious_waste SET type_of_waste = '"+ wasteTextfield2.getText() +"', " +
                    "waste_object = '"+ objectTextfield2.getText() +"', type_of_container = '"+ containerTextField2.getText() +"', " +
                    "total_waste_per_day = '"+ twasteTextField2.getText() +"' WHERE id = '"+ product.getId() +"'";

            executeQuery(query);
            showTables();

        }catch(Exception e){
            System.out.println(e.getMessage());

        }

    }

    //Query Execution
    private void executeQuery(String query) {

        Connection conn = jdbc.getConnection();
        Statement st;
        System.out.println(query);
        try{
            st = conn.createStatement();
            st.executeUpdate(query);

        }catch(Exception e){
            System.out.println("Error while inserting record!");
            e.printStackTrace();

        }
    }

    //Delete details on selected field in Infectious waste table
    @FXML
    private void deleteData(ActionEvent event){
        Connection conn = jdbc.getConnection();

        try{
            InfectiousWaste product = infectiousTableView1.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM infectious_waste WHERE id = '"+ product.getId() +"'";

            executeQuery(query);
            showTable();

        }catch(Exception e){
            System.out.println(e.getMessage());

        }

    }

    //Delete details on selected field in the Non_Infectious waste table
    @FXML
    private void deleteDatas(ActionEvent event){
        Connection conn = jdbc.getConnection();

        try{
            InfectiousWaste product = nonInfectiousTableView.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM non_infectious_waste WHERE id = '"+ product.getId() +"'";

            executeQuery(query);
            showTable();


        }catch(Exception e){
            System.out.println(e.getMessage());

        }

    }

    /*Insert details from the Text Fields of Infectious Waste Details
      into table of infectious_waste in waste Database and show the details in Infectious waste table in Infectious Waste Tab */
    @FXML
    private void insertData(ActionEvent event){

        Connection conn = jdbc.getConnection();

        try {
            String type_of_waste = wasteTextfield1.getText();
            String waste_object = objectTextfield1.getText();
            String type_of_container = containerTextField1.getText();
            String total_waste_per_day = twasteTextField1.getText();
            Window owner = (Stage) wasteTextfield1.getScene().getWindow();

            if(type_of_waste.isEmpty() || waste_object.isEmpty() || type_of_container.isEmpty() || total_waste_per_day.isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Please fill the form correctly", "Form Error!");

            }else{
                PreparedStatement ps = conn.prepareStatement("INSERT INTO infectious_waste(type_of_waste,waste_object,type_of_container,total_waste_per_day) VALUES(?,?,?,?)");
                ps.setString(1, type_of_waste);
                ps.setString(2, waste_object);
                ps.setString(3, type_of_container);
                ps.setString(4, total_waste_per_day);

                int res = ps.executeUpdate();
                if(res > 0){
                    showAlert(Alert.AlertType.ERROR, owner, "Date insert successfully", "Success!");

                    wasteTextfield1.clear();
                    objectTextfield1.clear();
                    containerTextField1.clear();
                    twasteTextField1.clear();
                    showTable();

                }else {
                    showAlert(Alert.AlertType.ERROR, owner, "There were Errors while processing", "Form Error!");
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    /*Insert details from the Text Fields of Non_Infectious Waste Details
      into table of non_infectious_waste in waste Database and show the details in Non_Infectious waste table in Non_Infectious Waste Tab */
    @FXML
    private void insertDatas(ActionEvent event){

        Connection conn = jdbc.getConnection();

        try {
            String type_of_waste = wasteTextfield2.getText();
            String waste_object = objectTextfield2.getText();
            String type_of_container = containerTextField2.getText();
            String total_waste_per_day = twasteTextField2.getText();
            Window owner = (Stage) wasteTextfield2.getScene().getWindow();

            if(type_of_waste.isEmpty() || waste_object.isEmpty() || type_of_container.isEmpty() || total_waste_per_day.isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Please fill the form correctly", "Form Error!");

            }else{
                PreparedStatement ps = conn.prepareStatement("INSERT INTO non_infectious_waste(type_of_waste,waste_object,type_of_container,total_waste_per_day) VALUES(?,?,?,?)");
                ps.setString(1, type_of_waste);
                ps.setString(2, waste_object);
                ps.setString(3, type_of_container);
                ps.setString(4, total_waste_per_day);

                int res = ps.executeUpdate();
                if(res > 0){
                    showAlert(Alert.AlertType.ERROR, owner, "Date insert successfully", "Success!");

                    wasteTextfield2.clear();
                    objectTextfield2.clear();
                    containerTextField2.clear();
                    twasteTextField2.clear();
                    showTables();

                }else {
                    showAlert(Alert.AlertType.ERROR, owner, "There were Errors while processing", "Form Error!");
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    //Get sum for total_waste_per_day column in infectious_waste table in waste database
    @FXML
    private void sumColumn(ActionEvent event){
        Connection conn = jdbc.getConnection();
        Statement st;
        try
        {
            st = conn.createStatement();
            String query = "SELECT SUM(total_waste_per_day) FROM infectious_waste";
            ResultSet rs = st.executeQuery(query);
            String Countrun="";
            while(rs.next()){
                Countrun = rs.getString(1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Total");
                alert.setHeaderText("Total Count is: "+Countrun);

                alert.showAndWait();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //Get sum for total_waste_per_day column in non_infectious_waste table in waste database
    @FXML
    private void sumColumn1(ActionEvent event){
        Connection conn = jdbc.getConnection();
        Statement st;
        try
        {
            st = conn.createStatement();
            String query = "SELECT SUM(total_waste_per_day) FROM non_infectious_waste";
            ResultSet rs = st.executeQuery(query);
            String Countrun="";
            while(rs.next()){
                Countrun = rs.getString(1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Total");
                alert.setHeaderText("Total Count is: "+Countrun);

                alert.showAndWait();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //Show Alerts
    private void showAlert(Alert.AlertType alertType, Window owner, String please_fill_the_form_correctly, String s) {
        Alert alert = new Alert(alertType);
        alert.setContentText(please_fill_the_form_correctly);
        alert.setTitle(s);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(owner);
        alert.showAndWait();

    }

    //get table List from infectious_waste table in waste database
    private ObservableList<InfectiousWaste> getTableList() {
        ObservableList<InfectiousWaste> tableList = FXCollections.observableArrayList();
        Connection conn = jdbc.getConnection();
        String query = "SELECT * FROM infectious_waste";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            InfectiousWaste tables;
            while(rs.next()){
                tables = new InfectiousWaste(rs.getInt("id"), rs.getString("type_of_waste"), rs.getString("waste_object"), rs.getString("type_of_container"), rs.getInt("total_waste_per_day"));
                tableList.add(tables);
            }

        } catch (Exception e){
                System.out.println(e.getMessage());
        }

        return tableList;
    }

    //get table List from non_infectious_waste table in waste database
    private ObservableList<InfectiousWaste> getTableLists() {
        ObservableList<InfectiousWaste> tableLists = FXCollections.observableArrayList();
        Connection conn = jdbc.getConnection();
        String query = "SELECT * FROM non_infectious_waste";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            InfectiousWaste tables;
            while(rs.next()){
                tables = new InfectiousWaste(rs.getInt("id"), rs.getString("type_of_waste"), rs.getString("waste_object"), rs.getString("type_of_container"), rs.getInt("total_waste_per_day"));
                tableLists.add(tables);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return tableLists;
    }

}
 
