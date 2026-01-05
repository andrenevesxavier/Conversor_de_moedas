package br.com.conversorDeMoedas.requisicao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Requisicao {

    private String moedaBase;
    private String moedaDestino;
    private int cotacao;
    String moedasUsadas = "";
    String json;

    Scanner entrada = new Scanner(System.in);
    HttpClient client = HttpClient.newHttpClient();


    public void requisicao() throws IOException, InterruptedException {

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

            switch (opcao) {
                case 1:
                    moedasUsadas = "USD/ARS";
                case 2:
                    moedasUsadas = "ARS/USD";
                case 3:
                    moedasUsadas = "USD/BRL";
                case 4:
                    moedasUsadas= "BRL/USD";
                case 5:
                    moedasUsadas = "USD/EUR";
                case 6:
                    moedasUsadas = "EUR/USD";
                case 7:
                    System.out.println("Programa finalizado");
            }
            //Para o bloco while
            if (opcao == 7) {
                    break;
                }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/a68bd03a4e5ceb1c33cc089e/pair/" + moedasUsadas))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                json = response.body();

            } catch (Exception e) {

            }
        }
    }





}
