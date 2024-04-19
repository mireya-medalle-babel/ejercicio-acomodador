package com.babel.ejercicioacomodador.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InputService implements IInpuntService {
    @Override
    public int chosenOption(Scanner sc) {
        return sc.nextInt();
    }

}
