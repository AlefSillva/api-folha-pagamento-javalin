package br.edu.assessment.client;

public class Main {
    public static void main(String[] args) {
        try {
            String resultCriar = ApiClient.criarMensalista();
            System.out.println(resultCriar);

            String resultListar = ApiClient.listarMensalistas();
            System.out.println("Listagem:\n" + resultListar);

            String resultBuscar = ApiClient.buscarMensalistaPorMatricula(1001);
            System.out.println("Busca:\n" + resultBuscar);

            String resultStatus = ApiClient.consultarStatus();
            System.out.println("Status:\n" + resultStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
