package com.babel.ejercicioacomodador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private int rowNumber;
    private int seatNumber;
    private boolean isOccupied;
    private boolean isReserved;

    @Override
    public String toString() {
        if (isReserved) {
            return "[R]";
        } else if (isOccupied) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
}
