package com.babel.ejercicioacomodador.app;

import com.babel.ejercicioacomodador.config.Config;
import com.babel.ejercicioacomodador.model.Seat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppAcomodador {
    public void run() {
        Config config = new Config();
        int rows = config.getDefaultRows();
        int cols = config.getDefaultCols();
        int middleSeat = cols/2;
        Seat[][] seats;

        seats = inicializeSeats(rows, cols, middleSeat);

//        printSeats(seats);

        int tickets = 4;

        Seat[][] prueba = inicializeSeats(3, cols, middleSeat);
        for (int i = 0; i < prueba.length; i++) {
            for (int j = 0; j < prueba[i].length; j++) {
                if (i==0 && (j==1 || j==4)) {
                    prueba[i][j].setOccupied(true);
                }

            }

        }

        System.out.println("//////////");
        printSeats(prueba);

        for (int i = 0; i < prueba.length ; i++) {
            availableAdjacentSeats(prueba[i], tickets);
            for (int j = 0; j < prueba[i].length; j++) {
                if (i == 0) {
                    prueba[i][j].setOccupied(true);
                }
            }
        }
        System.out.println("////////PRUEB//////////");
        printSeats(prueba);


        System.out.println("////////RESERVA////////////");




//        printReservedSeats(seats, tickets);
//
//        occupyReservedSeats(seats, tickets);
//
//        System.out.println("////////////////////////////");
//
//        printSeats(seats);
//
//        System.out.println("////////////////////////////");
//
//        int tickets2 = 2;
//
//        printReservedSeats(seats, tickets2);


    }

    private boolean enoughAvailableSeats(Seat[] row, int tickets) {
        int cont = 0;
        int max = cont;

        for (int i = 0; i < row.length; i++) {
            if (!row[i].isOccupied()) {
                cont++;
            }

            if (cont > max) {
                max = cont;
            }
        }

        System.out.println(max);

        return max >= tickets;
    }

    private void occupyReservedSeats(Seat[][] seats, int tickets) {
        boolean keepOccuping = true;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                boolean isRowOccupied = occupiedRow(seats[i]);
                if (!isRowOccupied && keepOccuping) {
                    seats[i] = occupyUnoccupiedRow(seats[i], tickets);
                    keepOccuping = false;
                    i++;
                }
            }
        }
    }


    private static void printReservedSeats(Seat[][] seats, int tickets) {
        boolean keepReserving = true;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                boolean isRowOccupied = occupiedRow(seats[i]);
                boolean isRowAvailable = availableAdjacentSeats(seats[i], tickets);

                if (!isRowOccupied && keepReserving ) {
                    reserveUnoccupiedRow(seats[i], tickets);
                    keepReserving = false;
                    i++;
                } else if (isRowOccupied) {

                }

                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
    }

    private static void reserveOccupiedRow(Seat[] seats, int tickets) {
        List<Integer> seatsToReserve = getSeatsToReserveInUnoccupiedRow(tickets);

    }

    private static List<Integer> getSeatsToReserveInUnoccupiedRow(int tickets) {
        List<Integer> seatsToReserve = new ArrayList<>();
        for (int i = 1; i <= tickets; i++) {
            seatsToReserve.add(i);
        }

        return seatsToReserve;
    }


//    private static void reserveUnoccupiedRow(Seat[] row, int tickets) {
//        List<Integer> seatsToReserve = getSeatsToReserveInUnoccupiedRow(tickets);
//
//        for (int i = 0; i < row.length; i++) {
//            if (seatsToReserve.contains(row[i].getSeatNumber())) {
//                System.out.print("[R]");
//            }
//        }
//    }

    private Seat[]  occupyUnoccupiedRow(Seat[] seats, int tickets) {
        List<Integer> seatsToOccupy = getSeatsToReserveInUnoccupiedRow(tickets);

        for (int i = 0; i < seats.length; i++) {
            if (seatsToOccupy.contains(seats[i].getSeatNumber())) {
                seats[i].setOccupied(true);
            }
        }

        return seats;
    }

    private static void reserveUnoccupiedRow(Seat[] seats, int tickets) {
        List<Integer> seatsToOccupy = getSeatsToReserveInUnoccupiedRow(tickets);

        for (int i = 0; i < seats.length; i++) {
            if (seatsToOccupy.contains(seats[i].getSeatNumber())) {
                seats[i].setOccupied(true);
            }
        }

    }

    private static boolean availableAdjacentSeats(Seat[] row, int tickets) {

        int numberAvailableSeats = 0;
        int max = 0;

        for (Seat s : row) {
            if (!s.isOccupied()) {
                numberAvailableSeats++;
                if (max < numberAvailableSeats) {
                    max = numberAvailableSeats;
                }
            } else {
                numberAvailableSeats = 0;
            }
        }

        System.out.println(max);
        return max >= tickets;
    }




    private static boolean occupiedRow(Seat[] row) {
        boolean isRowOccupied = false;
        for (int i = 0; i < row.length; i++) {
            if (row[i].isOccupied()) {
                isRowOccupied = true;
                break;
            }
        }

        return isRowOccupied;
    }

    private static void printSeats(Seat[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length ; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
    }

    private static  Seat[][] inicializeSeats(int rows, int cols, int middleSeat) {
        List<Integer> oddSeats = getOddSeatsNumbers(cols);
        List<Integer> evenSeats = getEvenSeatsNumbers(cols);

        System.out.println("===== ESCENARIO // PANTALLA ======");

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
