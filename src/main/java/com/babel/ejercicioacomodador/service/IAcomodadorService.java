package com.babel.ejercicioacomodador.service;

import com.babel.ejercicioacomodador.model.Seat;

import java.util.List;
import java.util.Map;

public interface IAcomodadorService {
    void reserveSeats(Seat[][] seats, int tickets);

    void occupySeats(Seat[][] seats, int tickets);

    void cancelReserve(Seat[][] seats);

    void reserveMostCenteredSeatsInOccupiedRow(Seat[] row, int tickets);

    void reserveSeatsIfStarterSeatOdd(Seat[] row, int tickets, int seatsReserved, int starterSeat, int nexSeatReserve);

    int reserveSeatsIfStarterSeatEven(Seat[] row, int tickets, int starterSeat, int seatsReserved);

    boolean availableAdjacentSeats(Seat[] row, int tickets);

    boolean isOccupiedRow(Seat[] row);


    void reserveMostCenteredSeatsInUnoccupiedRow(Seat[] row, int tickets);

    void inicializeOccupyseats(Seat[][] seats, Map<Integer, List<Integer>> occupySeats, int rows, int cols);

    Seat[][] inicializeSeats(int rows, int cols, int middleSeat);

}
