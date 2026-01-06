package br.com.conversorDeMoedas.principal;

import br.com.conversorDeMoedas.requisicao.AcessaJson;
import br.com.conversorDeMoedas.requisicao.Requisicao;

import java.io.IOException;

public class Principal {
    public static void main() throws IOException, InterruptedException {
        Requisicao requisicao = new Requisicao();
        requisicao.Requisicao();
    }
}
