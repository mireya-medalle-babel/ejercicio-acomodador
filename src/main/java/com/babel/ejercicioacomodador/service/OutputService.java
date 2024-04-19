package com.babel.ejercicioacomodador.service;

import com.babel.ejercicioacomodador.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputService implements IOutputService {

    @Override
    public void showMenu() {
        System.out.println("======= BIENVENIDO =========" +
                "\n1. Reservar butacas" +
                "\n2. Ver estado del teatro" +
                "\n3. Salir");
    }

    @Override
    public void askNumberOfTickets() {
        System.out.println("Introduza el número de butacas");
    }

    @Override
    public void askIfOccupyReserve() {
        System.out.println("¿Desea aceptar los sitios reservados? Pulse 1 para aceptar y cualquier otra tecla para rechazar");

    }

    @Override
    public void showErrorSelection() {
        System.out.println("Error de selección");
    }

    @Override
    public void printSeats(Seat[][] seats) {
        System.out.println("======== ESCENARIO =========");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length ; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
    }

}
