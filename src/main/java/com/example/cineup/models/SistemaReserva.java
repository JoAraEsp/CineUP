package com.example.cineup.models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class SistemaReserva extends Rectangle {
    private final Asiento[] asientos;

    public SistemaReserva(double x, double y, int numAsientos) {
        super(30, 30, javafx.scene.paint.Color.RED);
        setTranslateX(x);
        setTranslateY(y);
        this.asientos = new Asiento[numAsientos];

        for (int i = 0; i < asientos.length; i++) {
            double asientoX = 100 + (i % 7) * 60;
            double asientoY = 200 + (i / 7) * 60;
            asientos[i] = new Asiento(asientoX, asientoY);
        }
    }

    public synchronized Asiento reservarAsiento() {
        for (Asiento asiento : asientos) {
            if (!asiento.isReservado()) {
                asiento.reservar();
                return asiento;
            }
        }
        return null;
    }

    public void inicializarAsientos(Pane cinePane) {
        Rectangle fondoSala = new Rectangle(600, 300, Color.DARKSLATEGRAY);
        fondoSala.setTranslateX(70);
        fondoSala.setTranslateY(180);
        cinePane.getChildren().add(fondoSala);

        for (Asiento asiento : asientos) {
            cinePane.getChildren().add(asiento);
        }
    }
}
