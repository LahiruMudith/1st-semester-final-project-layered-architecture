package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentDetailDto;
import edu.ijse.mvc.finalproject.dto.tm.PaymentDetailTM;
import edu.ijse.mvc.finalproject.model.HomePageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    HomePageModel homePageModel = new HomePageModel();
    LoginController loginController = new LoginController();

    @FXML
    private TableColumn<PaymentDetailTM, String> colDate;

    @FXML
    private TableColumn<PaymentDetailTM, String> colId;

    @FXML
    private TableColumn<PaymentDetailTM, String> colMonth;

    @FXML
    private TableColumn<PaymentDetailTM, String> colMethod;

    @FXML
    private TableColumn<PaymentDetailTM, String> colPrice;

    @FXML
    private TableView<PaymentDetailTM> tblMember;

    @FXML
    private Label countDietPlan;

    @FXML
    private Label countEmployee;

    @FXML
    private Label countMember;

    @FXML
    private Label countSchedule;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMemberName;

    @FXML
    private ComboBox<String> txtPaymentMethod;

    @FXML
    private ComboBox<String> txtMonth;

    @FXML
    private TextField txtPlanId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearchId;

    @FXML
    private Label lblError;

    private String memeberEmail = null;

    @FXML
    void btnMarkFee(ActionEvent event) {
        String id = txtId.getText();
        String memberName = txtMemberName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String paymentMethod = txtPaymentMethod.getValue();
        String month = txtMonth.getValue();
        String adminid = loginController.currentAdminId;
        Date date = Date.valueOf(LocalDate.now());
        try {
            String newPaymentPlanId = homePageModel.getNewPlanId();

            PaymentDetailDto paymentDetailDto = new PaymentDetailDto(
                    newPaymentPlanId,
                    id,
                    memberName,
                    date,
                    price,
                    paymentMethod,
                    month
            );
            System.out.println(paymentDetailDto);

            boolean b = homePageModel.markFee(paymentDetailDto, adminid);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Mark Successfully").show();
                String emailBody = memberName + " Your " +month+" Payment Mark Successfully. on " + date + LocalTime.now();
                sendMail("lahimudith@gmail.com", memeberEmail, emailBody);
                pageRefesh();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("New Plan Id Get Error");
            alert.show();
        }


    }

    private void sendMail(String from, String to, String body) {
        String subject = "Member Fee Mark Successfully";
        String PASSWORD = "dqon midx mlbl qswe";

        // Create a new Properties object to hold configuration settings for the SMTP (Simple Mail Transfer Protocol) connection
        Properties props = new Properties();

        // Enable SMTP authentication. This requires the server to authenticate the sender before sending emails.
        props.put("mail.smtp.auth", "true");

        // Enable STARTTLS, which upgrades an insecure connection to a secure one (TLS encryption).
        props.put("mail.smtp.starttls.enable", "true");

        // Specify the SMTP server host. For Gmail, it is "smtp.gmail.com".
        props.put("mail.smtp.host", "smtp.gmail.com");

        // Specify the port to use for SMTP. Port 587 is used for TLS connections. Alternatively, port 465 can be used for SSL.
        props.put("mail.smtp.port", "587");

        // Create a new session with the SMTP server using the configured properties.
        // The Authenticator is used to authenticate the sender's email using their email address (`from`) and app password (`PASSWORD`).
        Session session = Session.getInstance(props, new Authenticator() {

            // Replace with your email and app password
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, PASSWORD); // Replace with your email and password
            }
        });

        try {
            // Create a new MimeMessage object to represent the email message.
            Message message = new MimeMessage(session);

            // Set the sender's email address using the `from` parameter.
            message.setFrom(new InternetAddress(from));

            // Set the recipient(s) of the email. The `to` parameter is parsed to handle multiple recipients, if necessary.
            // `Message.RecipientType.TO` defines that this is the primary recipient (To field).
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set the subject of the email using the `subject` parameter.
            message.setSubject(subject);

            // Set the body (content) of the email using the `messageBody` parameter.
            message.setText(body);

            // Send the email message using the `Transport.send()` method.
            // This sends the email through the SMTP server configured in the session.
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }

    private void pageRefesh() {
        txtSearchId.clear();
        txtId.clear();
        txtPlanId.clear();
        txtMemberName.clear();
        txtPrice.clear();
        txtPaymentMethod.setValue(null);
        tblMember.getItems().clear();
    }

    @FXML
    void btnSearch(MouseEvent event) throws InterruptedException {
        lblError.setText("");
        String id = txtSearchId.getText();

        try {
            MemberDto memberDto = homePageModel.searchMember(id);
            if (memberDto == null) {
                lblError.setText("Can't Find Member");
            }

            txtId.setText(memberDto.getMember_id());
            txtMemberName.setText(memberDto.getName());
            txtPlanId.setText(memberDto.getPlan_id());
            txtPrice.setText(homePageModel.getPlanId(txtPlanId.getText()));
            memeberEmail = memberDto.getEmail();

            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Member Detail Load Error");
            alert.show();
        }
    }

    private void loadTable() {
        try {
            ArrayList<PaymentDetailTM> paymentDetailTMS = homePageModel.loadTable(txtId.getText());
            ObservableList<PaymentDetailTM> paymentDetailTMObservableList = FXCollections.observableArrayList();
            paymentDetailTMObservableList.addAll(paymentDetailTMS);
            tblMember.setItems(paymentDetailTMObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Member Table Load Error");
            alert.show();
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("payment_date"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));

        int memberCount = homePageModel.getMemberCount();
        int employeeCount = homePageModel.getEmployeeCount();
        int shceduleCount = homePageModel.getScheduleCount();
        int dietPlanCount = homePageModel.getDietPlanCount();
        countMember.setText(String.valueOf(memberCount));
        countEmployee.setText(String.valueOf(employeeCount));
        countSchedule.setText(String.valueOf(shceduleCount));
        countDietPlan.setText(String.valueOf(dietPlanCount));

        ObservableList<String> methods = FXCollections.observableArrayList("Bank Transfer", "Cash");
        txtPaymentMethod.setItems(methods);
        txtPaymentMethod.setOnAction(e->{
            String method = txtPaymentMethod.getSelectionModel().getSelectedItem();
            System.out.println(method);
        });

        ArrayList<String> month = new ArrayList<>(List.of("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
        ObservableList<String> objects = FXCollections.observableArrayList();
        objects.addAll(month);
        txtMonth.setItems(objects);
    }
}
