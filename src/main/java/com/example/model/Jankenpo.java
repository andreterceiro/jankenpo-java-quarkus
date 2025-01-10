package com.example.model;

import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Jankenpo {
    String[] validMoves = {"PEDRA", "PAPEL", "TESOURA"};
    
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
            return "Empate!";
        }

        return switch (userMove) {
            case "PEDRA" -> machineMove.equals("TESOURA") ? "Você venceu!" : "Você perdeu!";
            case "PAPEL" -> machineMove.equals("PEDRA") ? "Você venceu!" : "Você perdeu!";
            case "TESOURA" -> machineMove.equals("PAPEL") ? "Você venceu!" : "Você perdeu!";
            default -> "Jogada inválida!";
        };
    }
}