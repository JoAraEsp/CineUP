package com.example.cineup.models;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.util.LinkedList;
import java.util.Queue;

public class SistemaReserva extends Rectangle {
    private final Asiento[] asientos;
    private final Queue<Cliente> colaClientes = new LinkedList<>();

    public SistemaReserva(double x, double y, int numAsientos) {
        super(30, 30, Color.RED);
        setTranslateX(x);
        setTranslateY(y);
        this.asientos = new Asiento[numAsientos];

        for (int i = 0; i < asientos.length; i++) {
            double asientoX = 100 + (i % 7) * 60;
            double asientoY = 200 + (i / 7) * 60;
            asientos[i] = new Asiento(asientoX, asientoY);
        }
    }

    public synchronized Asiento reservarAsiento(Cliente cliente) {
        while (colaClientes.peek() != cliente) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (Asiento asiento : asientos) {
            if (!asiento.isReservado()) {
                asiento.reservar();
                colaClientes.poll();
                notifyAll();

                if (todosLosAsientosOcupados()) {
                    vaciarSala();
                }

                return asiento;
            }
        }
        colaClientes.poll();
        notifyAll();
        return null;
    }

    private boolean todosLosAsientosOcupados() {
        for (Asiento asiento : asientos) {
            if (!asiento.isReservado()) {
                return false;
            }
        }
        return true;
    }

    private void vaciarSala() {
        PauseTransition wait = new PauseTransition(Duration.seconds(10)); // Tiempo antes de vaciar
        wait.setOnFinished(event -> Platform.runLater(() -> {
            for (Asiento asiento : asientos) {
                asiento.liberar();
            }
        }));
        wait.play();
    }

    public synchronized void agregarClienteALaCola(Cliente cliente) {
        colaClientes.add(cliente);
        notifyAll();
    }

    public synchronized void notificarLiberacionAsiento() {
        notifyAll(); // Notificar a los clientes que esperan un asiento
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

