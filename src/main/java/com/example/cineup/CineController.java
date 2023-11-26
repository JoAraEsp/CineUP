package com.example.cineup;

import com.example.cineup.models.Cliente;
import com.example.cineup.models.Empleado;
import com.example.cineup.models.SistemaReserva;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class CineController {

    @FXML
    private Pane cinePane;

    private final SistemaReserva sistemaReserva = new SistemaReserva();
    private final Empleado empleado = new Empleado();

    public void initialize() {
        cinePane.getChildren().addAll(sistemaReserva, empleado);
        sistemaReserva.inicializarAsientos(cinePane);

        // Crear e inicializar clientes para demostración
        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente("Cliente" + i, "Cliente " + i, sistemaReserva, this);
            cliente.setTranslateX(i * 40); // Posición inicial de los clientes
            cliente.setTranslateY(50);
            cinePane.getChildren().add(cliente);

            cliente.setOnMouseClicked(event -> cliente.seleccionarAsiento());
        }
    }
}
