package com.example;

import java.util.Random;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/jankenpo")
public class JankenpoResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String play(@QueryParam("escolha") String userMove) {
        if (userMove == null || userMove.isEmpty()) {
            return "Por favor, informe sua jogada: pedra, papel ou tesoura.";
        }

        String[] moves = {"pedra", "papel", "tesoura"};
        String machineMove = moves[new Random().nextInt(moves.length)];

        String result = determineWinner(userMove.toLowerCase(), machineMove);
        return String.format("Você jogou: %s | Máquina jogou: %s | Resultado: %s", userMove, machineMove, result);
    }

    private String determineWinner(String userMove, String machineMove) {
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