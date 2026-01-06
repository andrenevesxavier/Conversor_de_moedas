package br.com.conversorDeMoedas.requisicao;

public record ApiNomes(String base_code, String target_code, double conversion_rate) {
}
