package com.example.cineup.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.TranslateTransition;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Cliente extends Circle {
    private String id;
    private String nombre;
    private SistemaReserva sistemaReserva;
    private Empleado empleado;

    public Cliente(String id, String nombre, SistemaReserva sistemaReserva, Empleado empleado) {
        super(5, Color.BLUE);
        this.id = id;
        this.nombre = nombre;
        this.sistemaReserva = sistemaReserva;
        this.empleado = empleado;
        setVisible(false);
    }

    public void aparecerEnSistemaReserva() {
        setTranslateX(sistemaReserva.getTranslateX());
        setTranslateY(sistemaReserva.getTranslateY() + 30);
        setVisible(true);

        esperarYDesaparecer(3, this::aparecerEnEmpleado);
    }

    private void aparecerEnEmpleado() {
        setTranslateX(empleado.getTranslateX());
        setTranslateY(empleado.getTranslateY() + 30);
        setVisible(true);

        esperarYDesaparecer(3, () -> {
            Asiento asiento = sistemaReserva.reservarAsiento();
            if (asiento != null) {
                aparecerEnAsiento(asiento);
            }
        });
    }

    private void aparecerEnAsiento(Asiento asiento) {
        setTranslateX(asiento.getCenterX());
        setTranslateY(asiento.getCenterY());
        setVisible(true);

        PauseTransition wait = new PauseTransition(Duration.seconds(30));
        wait.setOnFinished(event -> {
            setVisible(false);
            asiento.liberar();
        });
        wait.play();
    }

    private void esperarYDesaparecer(int segundos, Runnable siguienteAccion) {
        PauseTransition wait = new PauseTransition(Duration.seconds(segundos));
        wait.setOnFinished(event -> {
            setVisible(false);
            siguienteAccion.run();
        });
        wait.play();
    }
}
