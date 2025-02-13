package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.bo.BOFactory;
import edu.ijse.mvc.finalproject.bo.impl.DietPlanBOImpl;
import edu.ijse.mvc.finalproject.dto.DietPlanDto;
import edu.ijse.mvc.finalproject.dto.tm.DietPlanTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageDietPlanController implements Initializable {
    private ObservableList<DietPlanTM> observableList = FXCollections.observableArrayList();
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<DietPlanTM> tblDeitPlan;

    @FXML
    private TableColumn<DietPlanTM, String> colId;

    @FXML
    private TableColumn<DietPlanTM, String> colName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAdminId;

    DietPlanBOImpl dietPlanBO = (DietPlanBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.DIET_PLAN);

    @FXML
    void btnAdd(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String adminId = txtAdminId.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText() + " days";

        DietPlanDto dietPlanDto = new DietPlanDto(id, adminId, name, duration, description);
        System.out.println(dietPlanDto);
        boolean b = dietPlanBO.add(dietPlanDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Diet Plan Added Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void btnDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        boolean b = dietPlanBO.delete(id);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Diet Plan Delete Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String adminId = txtAdminId.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText() + " days";

        DietPlanDto dietPlanDto = new DietPlanDto(id, adminId, name, duration, description);
        System.out.println(dietPlanDto);
        boolean b = dietPlanBO.update(dietPlanDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Diet Plan Update Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void tblCLick(MouseEvent event) {
        btnAdd.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);

        DietPlanTM selectedItem = tblDeitPlan.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);

        txtId.setText(selectedItem.getDiet_plan_id());
        txtName.setText(selectedItem.getName());
        txtDuration.setText(selectedItem.getDuration());
        txtDescription.setText(selectedItem.getDescription());
        txtAdminId.setText(selectedItem.getAdmin_id());
    }


    private void pageRefesh() {
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        txtAdminId.setText("");
        txtDescription.setText("");
        txtId.setText("");
        txtName.setText("");
        txtDuration.setText("");

        try {
            txtId.setText(dietPlanBO.getNextDeitPlanId());
            LoginController loginController = new LoginController();
            txtAdminId.setText(loginController.currentAdminId);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Diet Plan Id Set Error");
            alert.show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        loadTable();
    }
    public void loadTable() {
        ArrayList<DietPlanDto> dietPlanDtos = null;
        try {
            System.out.println("DietPlanController.loadTable 1" + dietPlanDtos);
            dietPlanDtos = dietPlanBO.loadTable();
            System.out.println("DietPlanController.loadTable 2" + dietPlanDtos);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        observableList = FXCollections.observableArrayList();

        for (DietPlanDto dietPlanDto : dietPlanDtos) {
            DietPlanTM dietPlanTM = new DietPlanTM(
                    dietPlanDto.getDiet_plan_id(),
                    dietPlanDto.getAdmin_id(),
                    dietPlanDto.getName(),
                    dietPlanDto.getDuration(),
                    dietPlanDto.getDescription()
            );
            observableList.add(dietPlanTM);
        }
        tblDeitPlan.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("diet_plan_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        pageRefesh();
    }

}
