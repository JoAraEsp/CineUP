package com.example.cineup.threads;

import com.example.cineup.models.Cliente;
import com.example.cineup.models.SistemaReserva;
import javafx.application.Platform;

public class ClienteThread implements Runnable {
    private Cliente cliente;
    private SistemaReserva sistemaReserva;

    public ClienteThread(Cliente cliente, SistemaReserva sistemaReserva) {
        this.cliente = cliente;
        this.sistemaReserva = sistemaReserva;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            sistemaReserva.agregarClienteALaCola(cliente);
            cliente.aparecerEnSistemaReserva();
        });

        try {
            Thread.sleep(5000); // Espera para simular el tiempo en el área de reserva
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Platform.runLater(cliente::aparecerEnEmpleado);
    }
}
