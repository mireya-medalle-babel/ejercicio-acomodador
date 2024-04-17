package com.babel.ejercicioacomodador.service;

import com.babel.ejercicioacomodador.model.Seat;
import org.springframework.stereotype.Service;

@Service
public class OutputService implements IOutputService {

    public void showMenu() {
        System.out.println("======= BIENVENIDO =========" +
                "\n1. " +
                "\n2. Salir");
    }
    public void printSeats (Seat seat,int rows, int cols) {
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(seat);
            }
            System.out.println();
        }
    }

}
