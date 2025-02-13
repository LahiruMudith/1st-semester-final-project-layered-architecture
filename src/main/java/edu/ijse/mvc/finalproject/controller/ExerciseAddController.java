package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.bo.BOFactory;
import edu.ijse.mvc.finalproject.bo.ExerciseAddBO;
import edu.ijse.mvc.finalproject.bo.ScheduleBO;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExerciseAddController implements Initializable {
    ScheduleBO scheduleBO = (ScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOType.SCHEDULE);
    ExerciseAddBO exerciseAddBO = (ExerciseAddBO) BOFactory.getInstance().getBO(BOFactory.BOType.EXERCISE_ADD);
    @FXML
    private Text txtError;

    @FXML
    private TextField txtDes;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    void btnAddPosition(ActionEvent event) throws SQLException, ClassNotFoundException {

        ExerciseDto exerciseDto = new ExerciseDto(
                txtName.getText(),
                txtId.getText(),
                txtDes.getText()
        );
        boolean b = exerciseAddBO.save(exerciseDto);
        if (b){
            Stage window = (Stage) txtError.getScene().getWindow();
            window.close();
        }else {
            txtError.setText("Exercise not added");
            txtName.clear();
            txtId.clear();
            txtDes.clear();
        }
    }

    @FXML
    void btnClose(MouseEvent event) {
        Stage window = (Stage) txtError.getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtName.setText(scheduleBO.getNextExerciseId());
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Exercise Id Set Error");
            alert.show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
