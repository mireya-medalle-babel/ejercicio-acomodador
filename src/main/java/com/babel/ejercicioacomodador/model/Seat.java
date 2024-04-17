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

    @Override
    public String toString() {
        return "["+seatNumber+isOccupied+"]";
    }
}
