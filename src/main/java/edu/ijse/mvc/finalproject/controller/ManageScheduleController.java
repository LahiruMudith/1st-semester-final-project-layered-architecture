package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.DataValidate.DataValidate;
import edu.ijse.mvc.finalproject.bo.BOFactory;
import edu.ijse.mvc.finalproject.bo.ScheduleBO;
import edu.ijse.mvc.finalproject.bo.impl.ScheduleBOImpl;
import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.dto.ExerciseScheduleDto;
import edu.ijse.mvc.finalproject.dto.ScheduleDto;
import edu.ijse.mvc.finalproject.dto.tm.ExerciseScheduleTM;
import edu.ijse.mvc.finalproject.dto.tm.ScheduleTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


public class ManageScheduleController implements Initializable {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ScheduleTM, Integer> colCount;

    @FXML
    private TableColumn<ScheduleTM, String> colExerciseName;

    @FXML
    private TableColumn<ScheduleTM, Integer> colSet;

    @FXML
    private TableColumn<ExerciseScheduleDto, String> colId;

    @FXML
    private TableColumn<ExerciseScheduleDto, String> colName;

    @FXML
    private TableView<ExerciseScheduleTM> tblDescription;

    @FXML
    private TableView<ExerciseScheduleTM> tblMember;

    @FXML
    private MenuButton txtAdminId;

    @FXML
    private TextField txtCount;

    @FXML
    private ImageView imgAddExercise;

    @FXML
    private TextField txtDescription;

    @FXML
    private MenuButton txtExercise;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSet;

    DataValidate validate = new DataValidate();
    private ObservableList<ExerciseScheduleTM> tms = FXCollections.observableArrayList();
    ScheduleBO scheduleBO = (ScheduleBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.SCHEDULE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<ExerciseScheduleDto, String>("schedule_id"));
        colName.setCellValueFactory(new PropertyValueFactory<ExerciseScheduleDto, String>("schedule_name"));
        colExerciseName.setCellValueFactory(new PropertyValueFactory<ScheduleTM, String>("exercise_name"));
        colCount.setCellValueFactory(new PropertyValueFactory<ScheduleTM, Integer>("exercise_count"));
        colSet.setCellValueFactory(new PropertyValueFactory<ScheduleTM, Integer>("exercise_set"));

        try {
            pageRefesh();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void pageRefesh() throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        txtId.setEditable(false);

        clearData();

        try {
            txtId.setText(scheduleBO.getNextScheduleId());
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Schedule Id Set Error");
            alert.show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<ExerciseDto> exercise = scheduleBO.getExercise();
        MenuButton paymentPlan = (MenuButton) txtExercise;
        paymentPlan.getItems().clear();
        for(ExerciseDto exerciseDto : exercise){
            MenuItem menuItem = new MenuItem(exerciseDto.getExercise_name());
            menuItem.setOnAction(event -> {
                txtExercise.setText(exerciseDto.getExercise_name());
                txtExercise.setId(exerciseDto.getExercise_id());
            });
            paymentPlan.getItems().add(menuItem);
        }
        ArrayList<AdminDto> admin = scheduleBO.getAdmin();
        MenuButton paymentPlanMenuBtn = (MenuButton) txtAdminId;
        for(AdminDto adminDto : admin){
            MenuItem menuItem = new MenuItem(adminDto.getName());
            menuItem.setOnAction(event -> {
                txtAdminId.setText(adminDto.getName());
                txtAdminId.setId(adminDto.getAdmin_id());
            });
            paymentPlanMenuBtn.getItems().add(menuItem);
        }

        loadTable();
    }

    private void loadTable() {
        try {
            ArrayList<ExerciseScheduleDto> exerciseScheduleDtos = scheduleBO.getSchedule();
            ObservableList<ExerciseScheduleTM> dtos = FXCollections.observableArrayList();
            Set<String> uniqueScheduleIds = new HashSet<>();

            for (ExerciseScheduleDto exerciseScheduleDto : exerciseScheduleDtos) {
                if (!uniqueScheduleIds.contains(exerciseScheduleDto.getSchedule_id())) {
                ExerciseScheduleTM exerciseScheduleTM = new ExerciseScheduleTM(
                        exerciseScheduleDto.getSchedule_id(),
                        exerciseScheduleDto.getExercise_id(),
                        exerciseScheduleDto.getExercise_name(),
                        exerciseScheduleDto.getSchedule_name(),
                        exerciseScheduleDto.getExercise_set(),
                        exerciseScheduleDto.getExercise_count(),
                        exerciseScheduleDto.getAdmin_id()
                    );

                    uniqueScheduleIds.add(exerciseScheduleDto.getSchedule_id());

                    dtos.add(exerciseScheduleTM);
                }
                tblMember.setItems(dtos);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Schedule Table Load Error");
            alert.show();
        }
    }

    @FXML
    void btnAddExercise(MouseEvent event) {
        String scheduleId = txtId.getText();
        String scheduleName = txtName.getText();
        String adminId = txtAdminId.getId();
        String exerciseId = txtExercise.getId();
        String exerciseName = txtExercise.getText();
        int exerciseSet = Integer.parseInt(txtSet.getText());
        int exerciseCount = Integer.parseInt(txtCount.getText());

        ExerciseScheduleTM exerciseScheduleTM = new ExerciseScheduleTM(
                scheduleId,
                exerciseId,
                exerciseName,
                scheduleName,
                exerciseCount,
                exerciseSet,
                adminId
        );
        tms.add(exerciseScheduleTM);
        for (ExerciseScheduleTM tm : tms) {
            System.out.println(tm.getExercise_name());
        }

        tblDescription.setItems(tms);
    }

    @FXML
    void btnAddNewExercise(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExerciseAdd.fxml"));
        try {
            Parent load = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(load));
            stage.setResizable(true);
            stage.initModality(Modality.WINDOW_MODAL);
            Window underWindow = btnAdd.getScene().getWindow();
            stage.initOwner(underWindow);

            stage.showAndWait();
            pageRefesh();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Exercise Add Menu Load Fail");
            alert.show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAdd(ActionEvent event) {
        if (txtName.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please Fill Name").show();
        }else {
            String scheduleId = txtId.getText();
            String scheduleName = txtName.getText();
            String adminId = txtAdminId.getId();

            ArrayList<ExerciseScheduleDto> exerciseScheduleDtos = new ArrayList<>();

            for (ExerciseScheduleTM tm : tms){
                ExerciseScheduleDto exerciseScheduleDto = new ExerciseScheduleDto(
                        tm.getSchedule_id(),
                        tm.getExercise_id(),
                        tm.getExercise_name(),
                        tm.getSchedule_name(),
                        tm.getExercise_count(),
                        tm.getExercise_set(),
                        tm.getAdmin_id()
                );
                exerciseScheduleDtos.add(exerciseScheduleDto);
            }
            ScheduleDto scheduleDto = new ScheduleDto(scheduleId, scheduleName, adminId, exerciseScheduleDtos);

            try {
                boolean b = scheduleBO.save(scheduleDto);
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Schedule Add Successfully.").show();
                    pageRefesh();
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Schedule Add Fail.").show();
                    pageRefesh();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Schedule Added Fail");
                alert.show();
            }
        }
    }

    @FXML
    void btnDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        boolean b = scheduleBO.delete(id);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Schedule Delete Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        if (txtName.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please Fill Name").show();
        }else {
            String scheduleId = txtId.getText();
            String exerciseName = txtName.getText();
            String adminId = txtAdminId.getId();

            ArrayList<ExerciseScheduleDto> exerciseScheduleDtos = new ArrayList<>();

            for (ExerciseScheduleTM tm : tms){
                ExerciseScheduleDto exerciseScheduleDto = new ExerciseScheduleDto(
                        tm.getSchedule_id(),
                        tm.getExercise_id(),
                        tm.getExercise_name(),
                        tm.getSchedule_name(),
                        tm.getExercise_count(),
                        tm.getExercise_set(),
                        tm.getAdmin_id()
                );
                exerciseScheduleDtos.add(exerciseScheduleDto);
            }
            ScheduleDto scheduleDto = new ScheduleDto(scheduleId, exerciseName, adminId, exerciseScheduleDtos);
            try {
                boolean b = scheduleBO.update(scheduleDto);
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Schedule Update Successfully").show();
                    pageRefesh();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Schedule Update Fail");
                alert.show();
            }
        }
    }

    @FXML
    void tblClick(MouseEvent event) throws SQLException, ClassNotFoundException {
        clearData();
        btnAdd.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);

        ArrayList<ExerciseScheduleDto> exerciseScheduleDtos = scheduleBO.getSchedule();
        String selectedId = tblMember.getSelectionModel().getSelectedItem().getSchedule_id();
        ObservableList<ExerciseScheduleTM> dtos = FXCollections.observableArrayList();

        for (ExerciseScheduleDto exerciseScheduleDto : exerciseScheduleDtos) {
            if (exerciseScheduleDto.getSchedule_id().equals(selectedId)) {
                ExerciseScheduleTM exerciseScheduleTM1 = new ExerciseScheduleTM(
                        exerciseScheduleDto.getSchedule_id(),
                        exerciseScheduleDto.getExercise_id(),
                        exerciseScheduleDto.getExercise_name(),
                        exerciseScheduleDto.getSchedule_name(),
                        exerciseScheduleDto.getExercise_count(),
                        exerciseScheduleDto.getExercise_set(),
                        exerciseScheduleDto.getAdmin_id()
                );
                dtos.add(exerciseScheduleTM1);
            }
        }
        for (ExerciseScheduleTM exerciseScheduleTM : dtos) {
                txtId.setText(exerciseScheduleTM.getSchedule_id());
                txtName.setText(exerciseScheduleTM.getSchedule_name());
                txtAdminId.setText(exerciseScheduleTM.getAdmin_id());
        }
        tblDescription.setItems(dtos);
    }

    public void clearData(){
        txtName.clear();
        txtId.clear();
        txtAdminId.setText("");
        txtExercise.setText("");
        txtSet.clear();
        txtCount.clear();
        tblDescription.getItems().clear();
    }
}
