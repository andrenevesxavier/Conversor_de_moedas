package br.com.conversorDeMoedas.requisicao;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Requisicao {

    private String moedaBase;
    private String moedaDestino;
    private double cotacao;
    private double quantidade;
    private double cotacaoUsuario;
    private String moedasUsadas;
    private String json;
    Scanner entrada = new Scanner(System.in);
    HttpClient client = HttpClient.newHttpClient();


    public Requisicao(ApiNomes apiNomes) {
        this.moedaBase = apiNomes.base_code();
        this.moedaDestino = apiNomes.target_code();
        this.cotacao = apiNomes.conversion_rate();
    }

    @Override
    public String toString() {
        return moedaBase + " É EQUIVALENTE A " + moedaDestino;
    }



    public Requisicao() {
    }

    public void Requisicao() throws IOException, InterruptedException {

        int opcao = 0;

        while (opcao != 7) {
            System.out.println("*****************************************");
            System.out.println("Seja bem-vindo ao conversor de moeda");
            System.out.println();
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Dólar =>> Euro");
            System.out.println("6) Euro =>> Dólar");
            System.out.println("7) Sair");
            opcao = entrada.nextInt();

            System.out.println("*****************************************");


            //Tentei utilizar o switch para deixar o código mais fácil de se entender mais não funcionou
            // sempre um case atribuia o valor a variavel "moedasUsadas" independente da entrada do usuario
//            switch (opcao) {
//                case 1:
//                    moedasUsadas = "USD/ARS";
//                case 2:
//                    moedasUsadas = "ARS/USD";
//                case 3:
//                    moedasUsadas = "USD/BRL";
//                case 4:
//                    moedasUsadas = "BRL/USD";
//                case 5:
//                    moedasUsadas = "USD/EUR";
//                case 6:
//                    moedasUsadas = "EUR/USD";
//                case 7:
//                    moedasUsadas = "";
//                    System.out.println("Programa finalizado");
//                default:
//                    System.out.println("entrada invalida");
//            }

            if (opcao == 1) {
                moedasUsadas = "USD/ARS";
            }
            if (opcao == 2) {
                moedasUsadas = "ARS/USD";
            }
            if (opcao == 3) {
                moedasUsadas = "USD/BRL";
            }
            if (opcao == 4) {
                moedasUsadas = "BRL/USD";
            }
            if (opcao == 5) {
                moedasUsadas = "USD/EUR";
            }
            if (opcao == 6) {
                moedasUsadas = "EUR/USD";
            }
            //Para sair do bloco while
            if (opcao == 7) {
                moedasUsadas = "";
                System.out.println("Programa finalizado");
                break;
            }
            if (opcao > 7 || opcao < 0) {
                System.out.println("Digite uma opção válida");
            } else {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://v6.exchangerate-api.com/v6/a68bd03a4e5ceb1c33cc089e/pair/" + moedasUsadas))
                        .build();
                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    json = response.body();
                    System.out.println("Digite a quantia que deseja converter");
                    quantidade = entrada.nextDouble();
                    AcessaJson acessaJson = new AcessaJson(json, quantidade);
                    cotacaoUsuario = acessaJson.conversao;
                } catch (InputMismatchException e) {
                    System.out.println("erro de digitação");
                    System.out.println(e.getMessage());
                }
            }

        }

    }
}
