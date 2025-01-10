package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import com.example.model.Jankenpo;

@Path("/jankenpo")
public class JankenpoResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String play(@QueryParam("escolha") String userMove) {
        Jankenpo jankenpo = new Jankenpo();

        if (userMove == null || userMove.isEmpty() || ! jankenpo.evaluateUserMove(userMove) ) {
            return "Por favor, informe sua jogada: pedra, papel ou tesoura.";
        }

        String computerMove = jankenpo.getComputerMove();
        String result = jankenpo.determineWinner(userMove.toLowerCase(), computerMove);
        return String.format("Você jogou: %s | Máquina jogou: %s | Resultado: %s", userMove, computerMove, result);
    }
}