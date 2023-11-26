package com.example.cineup.models;

import com.example.cineup.CineController;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Cliente extends Circle {
    private String id;
    private String nombre;
    private SistemaReserva sistemaReserva;
    private CineController controller;

    public Cliente(String id, String nombre, SistemaReserva sistemaReserva, CineController controller) {
        super(20);
        this.id = id;
        this.nombre = nombre;
        this.sistemaReserva = sistemaReserva;
        this.controller = controller;
        this.setFill(Color.BLUE);
    }

    public void seleccionarAsiento() {
        Asiento asiento = sistemaReserva.reservarAsiento();
        if (asiento != null) {
            moverClienteAAsiento(asiento);
        } else {
            // Manejar el caso de no haber asientos disponibles
        }
    }

    private void moverClienteAAsiento(Asiento asiento) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(this);

        transition.setToX(asiento.getX());
        transition.setToY(asiento.getY());
        transition.play();
    }
}