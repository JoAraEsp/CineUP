package com.example.cineup.models;

import javafx.scene.shape.Polygon;

public class Empleado extends Polygon {
    public Empleado() {
        getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        this.setFill(javafx.scene.paint.Color.GREEN);
    }
}
