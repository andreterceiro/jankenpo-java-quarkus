package com.example.model;

public class Jankenpo {
    String[] validMoves = {"pedra", "papel", "tesoura"};
    
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
} 