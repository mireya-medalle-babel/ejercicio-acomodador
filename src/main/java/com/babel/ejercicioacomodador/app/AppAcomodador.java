package com.babel.ejercicioacomodador.app;

import com.babel.ejercicioacomodador.config.Config;
import com.babel.ejercicioacomodador.model.Seat;
import com.babel.ejercicioacomodador.service.*;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AppAcomodador {
    private final IOutputService outputService;
    private final IInpuntService inputService;
    private final IAcomodadorService acomodadorService;

    public AppAcomodador(IOutputService outputService, IInpuntService inpuntService, IAcomodadorService acoomodadorService) {
        this.outputService = outputService;
        this.inputService = inpuntService;
        this.acomodadorService = acoomodadorService;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Config config = new Config();
        int rows = config.getDefaultRows();
        int cols = config.getDefaultCols();
        int middleSeat = cols/2;
        Seat[][] seats;
        seats = acomodadorService.inicializeSeats(rows, cols, middleSeat);

        boolean continueReserving = true;
        int chosenOption;
        int tickets;
        int acceptReserve;

        while (continueReserving) {
            outputService.showMenu();

            try {
                chosenOption = inputService.chosenOption(sc);

                if (chosenOption == 1) {
                    outputService.askNumberOfTickets();

                    try {
                        tickets = sc.nextInt();
                        acomodadorService.reserveSeats(seats, tickets);
                        outputService.printSeats(seats);
                        outputService.askIfOccupyReserve();
                        try {
                            acceptReserve = sc.nextInt();
                            if (acceptReserve == 1) {
                                acomodadorService.occupySeats(seats, tickets);
                            } else {
                                acomodadorService.cancelReserve(seats);
                            }
                            outputService.printSeats(seats);
                        } catch (Exception e) {
                            //nothing
                        }
                    } catch (Exception e) {
                        outputService.showErrorSelection();
                        sc.nextLine();
                    }
                } else if (chosenOption == 2) {
                    outputService.printSeats(seats);
                } else if (chosenOption == 3) {
                    continueReserving = false;
                }
            } catch (Exception e) {
                outputService.showErrorSelection();
                sc.nextLine();
            }
        }
    }
}
