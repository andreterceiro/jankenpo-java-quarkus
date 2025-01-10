package com.example;

import jakarta.inject.Inject;

import com.example.model.Jankenpo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/jankenpo")
public class JankenpoResource {
    private final Jankenpo jankenpo;

    @Inject
    public JankenpoResource(Jankenpo jankenpo) {
        this.jankenpo = jankenpo;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String play(@QueryParam("escolha") String userMove) {
        if (userMove == null || userMove.isEmpty() || ! this.jankenpo.evaluateUserMove(userMove) ) {
            return "Por favor, informe sua jogada: pedra, papel ou tesoura.";
        }

        String computerMove = this.jankenpo.getComputerMove();
        String result = this.jankenpo.determineWinner(userMove, computerMove);
        return String.format("Você jogou: %s | Máquina jogou: %s | Resultado: %s", userMove, computerMove, result);
    }
}