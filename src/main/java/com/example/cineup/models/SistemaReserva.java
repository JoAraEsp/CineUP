package com.example.cineup.models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class SistemaReserva extends Rectangle {
    private final Asiento[] asientos = new Asiento[35];

    public SistemaReserva() {
        super(30, 30);
        this.setFill(javafx.scene.paint.Color.RED);
    }

    public synchronized Asiento reservarAsiento() {
        for (Asiento asiento : asientos) {
            if (!asiento.isReservado()) {
                asiento.reservar();
                return asiento;
            }
        }
        return null; // No hay asientos disponibles
    }

    public void inicializarAsientos(Pane cinePane) {
        for (int i = 0; i < asientos.length; i++) {
            Asiento asiento = new Asiento();
            asiento.setX(100 + (i % 7) * 30); // Organizar asientos en filas
            asiento.setY(200 + (i / 7) * 30); // Organizar asientos en columnas
            asientos[i] = asiento;
            cinePane.getChildren().add(asiento);
        }
    }
}
