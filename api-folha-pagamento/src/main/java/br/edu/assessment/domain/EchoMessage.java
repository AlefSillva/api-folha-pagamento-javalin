package br.edu.assessment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EchoMessage {
    @JsonProperty("mensagem")
    private String mensagem;

    public EchoMessage() {}

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
