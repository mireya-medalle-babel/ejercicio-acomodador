package com.babel.ejercicioacomodador.service;

import com.babel.ejercicioacomodador.model.Seat;

import java.util.List;

public interface IOutputService {
    void showMenu();

    void askNumberOfTickets();


    void askIfOccupyReserve();


    void showErrorSelection();

    void printSeats(Seat[][] seats);
}
