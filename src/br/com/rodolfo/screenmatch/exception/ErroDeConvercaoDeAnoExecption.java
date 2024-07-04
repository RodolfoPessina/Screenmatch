package br.com.rodolfo.screenmatch.exception;

public class ErroDeConvercaoDeAnoExecption extends RuntimeException {
    private String mensagem;

    public ErroDeConvercaoDeAnoExecption(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
