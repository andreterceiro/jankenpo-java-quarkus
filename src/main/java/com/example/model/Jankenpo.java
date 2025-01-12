package com.example.model;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Jankenpo {
    String[] validMoves = {"PAPER", "ROCK", "SCISSORS"};
    
    public Boolean evaluateUserMove(String userMove) {
        for (String validMove: this.validMoves) {
            if (userMove.equals(validMove)) {
                return true;
            }
        }
        return false;
    }

    public String getComputerMove() {
        return this.validMoves[new Random().nextInt(this.validMoves.length)];
    } 

    public String determineWinner(String userMove, String machineMove) {
        if (userMove.equals(machineMove)) {
            return "Draw!";
        }

        return switch (userMove) {
            case "ROCK" -> machineMove.equals("SCISSORS") ? "You win!" : "You lost!";
            case "PAPER" -> machineMove.equals("ROCK") ? "You win!" : "You lost!";
            case "SCISSORS" -> machineMove.equals("PAPER") ? "You win!" : "You lost!";
            default -> "Invalid choice!";
        };
    }
}