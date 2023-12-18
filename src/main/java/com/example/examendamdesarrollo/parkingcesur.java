package com.example.examendamdesarrollo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.Period;
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
    private TableColumn<Coche, String> tcMatricula;
    @FXML
    private TableColumn<Coche, String> tcModelo;
    @FXML
    private TableColumn<Coche, String> tcEntrega;
    @FXML
    private TableColumn<Coche, String> tcSalida;
    @FXML
    private TableColumn<Coche , String> tcCliente;
    @FXML
    private TableColumn<Coche, String> tcTarifa;
    @FXML
    private TableColumn<Coche, String> tcCoste;
    @FXML
    private ChoiceBox<Cliente> cbCliente;

    private ObservableList<Coche> entradas = FXCollections.observableArrayList();
    @FXML
    private TableView<Coche> tvParking;

    private final ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private Label lblAbout;

    public void initialize() {


        if(Session.getClientes().isEmpty()) {
            ArrayList<Coche> temp = new ArrayList<>();

            LocalDate fechaSalida = LocalDate.of(2023,12,30);

            Cliente cliente = new Cliente();
            cliente.setId(123);
            cliente.setNombre("Raul");
            cliente.setCorreo("Raul@yopmail.com");

            temp.add(new Coche("13133D","Citroen",cliente, LocalDate.now(),fechaSalida,"Standard",8));
            Session.setCoches(temp);
        }



        entradas.addAll( Session.getCoches());

        ObservableList<String> modelos = FXCollections.observableArrayList();
        modelos.addAll("Citroen", "Toyota", "Ford", "Honda", "Nissan", "Chevrolet", "Hyundai", "BMW", "Mercedes", "Audi");
        CBModelo.setItems(modelos);
        CBModelo.getSelectionModel().selectFirst();

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(new Cliente(1,"Raul","Raul@yopmail.com"),
                new Cliente(2,"Carmen","Carmen@gmail.com"),
                new Cliente(2,"Carlos","Carlos@gmail.com"),
                new Cliente(2,"Leo","Leo@gmail.com")
        );
        cbCliente.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                if(cliente!=null) return cliente.getNombre();
                else return null;
            }
            @Override
            public Cliente fromString(String s) {
                return null;
            }
        });
        cbCliente.setItems(clientes);
        cbCliente.getSelectionModel().selectFirst();

        tcMatricula.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getMatricula() ));
        tcModelo.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getModelocoche() ));
        tcCliente.setCellValueFactory( (fila) -> {
            String cliente = String.valueOf(fila.getValue().getCliente().getNombre());
            return new SimpleStringProperty(cliente);
        });

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

        rbOferta.setToggleGroup(toggleGroup);
        RBLarga.setToggleGroup(toggleGroup);
        RBStandard.setToggleGroup(toggleGroup);



        tvParking.setItems(entradas);
    }

    @FXML
    public void Anadir(ActionEvent actionEvent) {


        LocalDate fechaEntrada = dateEntrada.getValue();
        LocalDate fechaSalida = dateSalida.getValue();

        Period periodo = Period.between(fechaEntrada,fechaSalida);

        Integer dias = periodo.getDays();

        double precioDiario = 0;
        String tarifaElegida = "";

        if (RBStandard.isSelected()) {
            precioDiario = 8;
            tarifaElegida = "Standard";
        } else if (rbOferta.isSelected()) {
            precioDiario = 6;
            tarifaElegida = "Oferta";
        } else if (RBLarga.isSelected()) {
            precioDiario = 2;
            tarifaElegida = "Larga Duración";
        }

        double coste = dias * precioDiario;

        if (camposValidos()) {
            Coche nuevaCliente = new Coche();
            nuevaCliente.setMatricula(txtMatricula.getText());
            nuevaCliente.setModelocoche(CBModelo.getValue());
            nuevaCliente.setCliente(cbCliente.getValue());
            nuevaCliente.setSalida(dateSalida.getValue());
            nuevaCliente.setEntrega(dateEntrada.getValue());
            nuevaCliente.setTarifa(tarifaElegida);
            nuevaCliente.setCoste((int)coste);

            tvParking.getItems().add(nuevaCliente);


            limpiarCampos();
        } else {
            mostrarAlerta("Faltan campos", "Todos los campos son obligatorios.");
        }

        lblCoste.setText(coste +"€");
    }

    @FXML
    public void Exit(ActionEvent actionEvent) {
        // Obtiene el Stage actual
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        // Cierra la ventana
        stage.close();
    }


    @FXML
    public void AlertaCreador(Event Event) {
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


