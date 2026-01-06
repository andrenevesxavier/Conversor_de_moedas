package br.com.conversorDeMoedas.requisicao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AcessaJson {
    Gson gson = new GsonBuilder().create();
    double conversao;

    public AcessaJson(String json, double valor) {
        ApiNomes apiNomes = gson.fromJson(json, ApiNomes.class);
        conversao = apiNomes.conversion_rate() * valor;
        Requisicao requisicao = new Requisicao(apiNomes);
        String mensagemCotacao = " " + conversao;
        String mensagemValor = "" + valor + " ";
        System.out.println(mensagemValor + requisicao + mensagemCotacao);
    }
}
