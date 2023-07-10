package com.exercise.hotel.ui;

public class ConsolePrinterImpl implements ConsolePrinter{
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
