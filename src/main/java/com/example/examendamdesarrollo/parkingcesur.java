package com.example.examendamdesarrollo;

import com.example.examendamdesarrollo.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class parkingcesur {
    @FXML
    private TextField txtMatricula;
    @FXML
    private ComboBox<String> CBModelo;
    @FXML
    private RadioButton rbOferta;
    @FXML
    private RadioButton RBLarga;
    @FXML
    private RadioButton RBStandard;
    @FXML
    private DatePicker dateEntrada;
    @FXML
    private DatePicker dateSalida;
    @FXML
    private Label lblCoste;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnSalir;
    @FXML
    private TableColumn<Cliente, String> tcMatricula;
    @FXML
    private TableColumn<Cliente, String> tcModelo;
    @FXML
    private TableColumn<Cliente, String> tcEntrega;
    @FXML
    private TableColumn<Cliente, String> tcSalida;
    @FXML
    private TableColumn<Cliente, String> tcCliente;
    @FXML
    private TableColumn<Cliente, String> tcTarifa;
    @FXML
    private TableColumn<Cliente, String> tcCoste;
    @FXML
    private ChoiceBox<String> cbCliente;

    private ObservableList<Cliente> entradas = FXCollections.observableArrayList();
    @FXML
    private TableView<Cliente> tvParking;
    @FXML
    private Label lblAbout;

    public void initialize() {
        if(Session.getClientes().isEmpty()) {
            ArrayList<Cliente> temp = new ArrayList<>();
            temp.add(new Cliente("23323F", "Citroen", "Raul", LocalDate.now(), LocalDate.now(), "Standard",8));
            temp.add(new Cliente("12345G", "Toyota", "Laura", LocalDate.now(), LocalDate.now(), "Oferta",6));
            temp.add(new Cliente("67890H", "Ford", "Pedro", LocalDate.now(), LocalDate.now(), "Larga duración",2));
            Session.setClientes(temp);
        }

        entradas.addAll( Session.getClientes());

        ObservableList<String> modelos = FXCollections.observableArrayList();
        modelos.addAll("Citroen", "Toyota", "Ford", "Honda", "Nissan", "Chevrolet", "Hyundai", "BMW", "Mercedes", "Audi");
        CBModelo.setItems(modelos);
        CBModelo.getSelectionModel().selectFirst();

        ObservableList<String> clientes = FXCollections.observableArrayList();
        clientes.addAll("Raul","Laura","Pedro");
        cbCliente.setItems(clientes);
        cbCliente.getSelectionModel().selectFirst();

        tcMatricula.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getMatricula() ));
        tcModelo.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getModelocoche() ));
        tcCliente.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getCliente() ));

        tcEntrega.setCellValueFactory(fila -> {
            LocalDate fechaEntrega = fila.getValue().getEntrega();
            if (fechaEntrega != null) {
                return new SimpleStringProperty(fechaEntrega.toString());
            } else {
                return new SimpleStringProperty("");
            }
        });
        tcSalida.setCellValueFactory(fila -> {
            LocalDate fechaSalida = fila.getValue().getSalida();
            if (fechaSalida != null) {
                return new SimpleStringProperty(fechaSalida.toString());
            } else {
                return new SimpleStringProperty("");
            }
        });

        tcTarifa.setCellValueFactory((fila )-> new SimpleStringProperty(fila.getValue().getTarifa()));

        tcCoste.setCellValueFactory(fila -> {
            Integer coste = fila.getValue().getCoste();
            if (coste != null) {
                return new SimpleStringProperty(coste.toString());
            } else {
                return new SimpleStringProperty("");
            }
        });



        tvParking.setItems(entradas);
    }

    @FXML
    public void Anadir(ActionEvent actionEvent) {
        if (camposValidos()) {
            Cliente nuevaCliente = new Cliente();
            nuevaCliente.setMatricula(txtMatricula.getText());
            nuevaCliente.setModelocoche(CBModelo.getValue());
            nuevaCliente.setCliente(cbCliente.getValue());
            nuevaCliente.setSalida(dateSalida.getValue());
            nuevaCliente.setEntrega(dateEntrada.getValue());
            entradas.add(nuevaCliente);

             tvParking.getItems().add(nuevaCliente);

            limpiarCampos();
        } else {
            mostrarAlerta("Faltan campos", "Todos los campos son obligatorios.");
        }
    }

    @FXML
    public void Exit(ActionEvent actionEvent) {
        // Obtiene el Stage actual
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        // Cierra la ventana
        stage.close();
    }


    @FXML
    public void AlertaCreador(ActionEvent actionEvent) {
        mostrarAlerta("Creador", "Raúl Herrera Alba\nCiclo: 2ºDAM");
    }

    private boolean camposValidos() {
        return !txtMatricula.getText().isEmpty() &&
                CBModelo.getValue() != null &&
                cbCliente.getValue() != null &&
                (rbOferta.isSelected() || RBLarga.isSelected() || RBStandard.isSelected()) &&
                dateEntrada.getValue() != null &&
                dateSalida.getValue() != null;
    }

    private void limpiarCampos() {
        txtMatricula.clear();
        CBModelo.getSelectionModel().clearSelection();
        cbCliente.getSelectionModel().clearSelection();
        rbOferta.setSelected(false);
        RBLarga.setSelected(false);
        RBStandard.setSelected(false);
        dateEntrada.setValue(null);
        dateSalida.setValue(null);
        lblCoste.setText("");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
