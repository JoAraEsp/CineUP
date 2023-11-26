package com.example.cineup.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Asiento extends Circle {
    private boolean reservado;

    public Asiento() {
        super(10, Color.LIGHTGRAY);
        this.reservado = false;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void reservar() {
        this.reservado = true;
        this.setFill(Color.RED);
    }

    public void setX(double x) {
        this.setCenterX(x);
    }

    public void setY(double y) {
        this.setCenterY(y);
    }

    public double getX() {
        return this.getCenterX();
    }

    public double getY() {
        return this.getCenterY();
    }
}
