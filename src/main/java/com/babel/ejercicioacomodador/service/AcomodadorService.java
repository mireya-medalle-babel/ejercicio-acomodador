package com.babel.ejercicioacomodador.service;

import com.babel.ejercicioacomodador.model.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcomodadorService implements IAcomodadorService {

    @Override
    public void reserveSeats(Seat[][] seats, int tickets) {
        boolean keepGoing = true;

        for (int i = 0; i < seats.length; i++) {
            Seat[] row = seats[i];
            if (keepGoing) {
                if (availableAdjacentSeats(row, tickets)) {
                    if (!isOccupiedRow(row)) {
                        reserveMostCenteredSeatsInUnoccupiedRow(row, tickets);
                    } else {
                        reserveMostCenteredSeatsInOccupiedRow(row, tickets);
                    }
                    keepGoing = false;
                }
            }
        }
    }

    @Override
    public void occupySeats(Seat[][] seats, int tickets) {
        for (int i = 0; i < seats.length; i++) {
            Seat[] row = seats[i];
            for (Seat seat : row) {
                if (seat.isReserved()) {
                    seat.setOccupied(true);
                    seat.setReserved(false);
                }
            }
        }
    }

    @Override
    public void cancelReserve(Seat[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            Seat[] row = seats[i];
            for (Seat seat : row) {
                if (seat.isReserved()) {
                    seat.setReserved(false);
                }
            }
        }
    }

    @Override
    public void reserveMostCenteredSeatsInOccupiedRow(Seat[] row, int tickets) {
        int starterSeat = bestSeatInOccupiedRow(row);
        System.out.println(starterSeat);
        int seatsReserved = 0;

        if (starterSeat%2==0) {
            seatsReserved = reserveSeatsIfStarterSeatEven(row, tickets, starterSeat, seatsReserved);
        }

        if (starterSeat%2!=0) {
            reserveSeatsIfStarterSeatOdd(row, tickets, seatsReserved, starterSeat, starterSeat);
        }
    }

    @Override
    public void reserveSeatsIfStarterSeatOdd(Seat[] row, int tickets, int seatsReserved, int starterSeat, int nexSeatReserve) {
        while (seatsReserved < tickets) {
            for (Seat seat : row) {
                if (seatsReserved < tickets && seat.getSeatNumber() == starterSeat) {
                    seat.setReserved(true);
                    seatsReserved++;
                    starterSeat = starterSeat+2;
                }
            }
        }
    }

    @Override
    public int reserveSeatsIfStarterSeatEven(Seat[] row, int tickets, int starterSeat, int seatsReserved) {
        for (Seat seat : row) {
            if (seat.getSeatNumber() >= starterSeat && seat.getSeatNumber()%2==0 && tickets > seatsReserved) {
                seat.setReserved(true);
                seatsReserved++;
            }
        }
        return seatsReserved;
    }

    private static int bestSeatInOccupiedRow (Seat[] row) {
        int occupiedSeats = 0;
        for (Seat seat : row) {
            if (seat.isOccupied()) {
                occupiedSeats++;
            }
        }
        System.out.println(occupiedSeats+1);

        return occupiedSeats+1;
    }

    @Override
    public boolean availableAdjacentSeats(Seat[] row, int tickets) {
        int numberAvailableSeats = 0;
        int max = 0;

        for (Seat seat : row) {
            if (!seat.isOccupied()) {
                numberAvailableSeats++;
                if (max < numberAvailableSeats) {
                    max = numberAvailableSeats;
                }
            } else {
                numberAvailableSeats = 0;
            }
        }
        return max >= tickets;
    }

    @Override
    public boolean isOccupiedRow(Seat[] row) {
        boolean isRowOccupied = false;
        for (Seat seat : row) {
            if (seat.isOccupied()) {
                isRowOccupied = true;
                break;
            }
        }
        return isRowOccupied;
    }

    @Override
    public void reserveMostCenteredSeatsInUnoccupiedRow (Seat[] row, int tickets) {
        while (tickets > 0) {
            for (Seat seat : row) {
                if (seat.getSeatNumber() == tickets) {
                    seat.setReserved(true);
                    tickets--;
                }
            }
        }
    }

    @Override
    public  Seat[][] inicializeSeats(int rows, int cols, int middleSeat) {
        List<Integer> oddSeats = getOddSeatsNumbers(cols);
        List<Integer> evenSeats = getEvenSeatsNumbers(cols);

        Seat[][] seats = new Seat[rows][cols];

        for (int i = 1; i <= rows; i++) {
            int contEvenNumbers = 0;
            int contOddNumbers = 0;

            for (int j = 1; j <= cols; j++) {
                seats[i-1][j-1] = new Seat();
                seats[i-1][j-1].setOccupied(false);
                seats[i-1][j-1].setRowNumber(i);

                if (j<= middleSeat) {
                    seats[i-1][j-1].setSeatNumber(oddSeats.get(contOddNumbers));
                    contOddNumbers++;
                } else {
                    seats[i-1][j-1].setSeatNumber(evenSeats.get(contEvenNumbers));
                    contEvenNumbers++;
                }
            }
        }
        return seats;
    }

    private static List<Integer> getOddSeatsNumbers(int cols) {
        List<Integer> oddSeats = new ArrayList<>();

        for (int i = cols; i >= 1; i--) {
            if (i%2 != 0) {
                oddSeats.add(i);
            }
        }
        return oddSeats;
    }

    private static List<Integer> getEvenSeatsNumbers(int cols) {
        List<Integer> evenSeats = new ArrayList<>();

        for (int i = 1; i <= cols; i++) {
            if (i%2 == 0) {
                evenSeats.add(i);
            }
        }
        return evenSeats;
    }
    
}
