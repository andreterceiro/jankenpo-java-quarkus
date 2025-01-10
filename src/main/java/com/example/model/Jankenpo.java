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

    public String determineWinner(String userMove, String machineMove) {
        if (userMove.equals(machineMove)) {
            return "Empate!";
        }

        return switch (userMove) {
            case "pedra" -> machineMove.equals("tesoura") ? "Você venceu!" : "Você perdeu!";
            case "papel" -> machineMove.equals("pedra") ? "Você venceu!" : "Você perdeu!";
            case "tesoura" -> machineMove.equals("papel") ? "Você venceu!" : "Você perdeu!";
            default -> "Jogada inválida!";
        };
    }
}