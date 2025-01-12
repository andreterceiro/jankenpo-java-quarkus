package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import com.example.model.Jankenpo;

@Path("/jankenpo")
public class JankenpoResource {

    private final Jankenpo jankenpo;

    @Inject
    Template form;

    @Inject
    Template process;

    @Inject
    public JankenpoResource(Jankenpo jankenpo) {
        this.jankenpo = jankenpo;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance form() {
        return form.instance();
    } 

    @POST
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance process(@FormParam("userMove") String userMove) {
        String error = "";
        String computerMove = "";
        String result = "";

        if (userMove == null || userMove.isEmpty() || !this.jankenpo.evaluateUserMove(userMove)) {
            error = "Por favor, informe sua jogada: pedra, papel ou tesoura.";
        } else {
            computerMove = this.jankenpo.getComputerMove();
            result = this.jankenpo.determineWinner(userMove, computerMove);
        }

        return process
            .data("winner", result)
            .data("userMove", userMove)
            .data("error", error)
            .data("computerMove", computerMove);
    }    
}
