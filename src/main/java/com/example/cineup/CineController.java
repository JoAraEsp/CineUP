package com.example.cineup;

import com.example.cineup.models.Cliente;
import com.example.cineup.models.Empleado;
import com.example.cineup.models.SistemaReserva;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class CineController {

    @FXML private Pane cinePane;

    private SistemaReserva sistemaReserva;
    private Empleado empleado;

    public void initialize() {
        sistemaReserva = new SistemaReserva(50, 50, 35);
        empleado = new Empleado(200, 50);

        cinePane.getChildren().addAll(sistemaReserva, empleado);
        sistemaReserva.inicializarAsientos(cinePane);

        for (int i = 0; i < 100; i++) {
            Cliente cliente = new Cliente("Cliente" + i, "Cliente " + i, sistemaReserva, empleado);
            cinePane.getChildren().add(cliente);

            PauseTransition delay = new PauseTransition(Duration.seconds(i + 1));
            delay.setOnFinished(event -> cliente.aparecerEnSistemaReserva());
            delay.play();
        }
    }
}
