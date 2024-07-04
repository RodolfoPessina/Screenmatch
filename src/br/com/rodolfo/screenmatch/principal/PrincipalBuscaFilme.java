package br.com.rodolfo.screenmatch.principal;

import br.com.rodolfo.screenmatch.exception.ErroDeConvercaoDeAnoExecption;
import br.com.rodolfo.screenmatch.modelos.Filme;
import br.com.rodolfo.screenmatch.modelos.Titulo;
import br.com.rodolfo.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalBuscaFilme {
    public static void main(String[] args) throws IOException, InterruptedException {

        String filme = "";
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        while (!filme.equalsIgnoreCase("sim")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("digite o nome do filme: ");
            filme = sc.nextLine();
            if (filme.equals("sim")){
                break;
            }

            String endereco = "http://www.omdbapi.com/?t=" + filme.replace(" ", "+") + "&apikey=94a137eb";
            try {

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);



                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(meuTituloOmdb);

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);

                FileWriter escrita = new FileWriter("filmes.txt");
                escrita.write(meuTitulo.toString());
                escrita.close();

                FileWriter escritaUm = new FileWriter("filmes.txt");
                escritaUm.write(gson.toJson(titulos));
                escritaUm.close();

            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Algum erro de argumento na busca, verifique o endereço");
            } catch (ErroDeConvercaoDeAnoExecption e) {
                System.out.println(e.getMessage());
            }
        }
    System.out.println("finalizou corretamente");
        System.out.println(titulos);

    }
}
