package br.com.trabalhoia;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.azure.core.credential.AzureKeyCredential;

public class AnalisadorDeSentimentos {


    private static final String CHAVE_DO_SERVICO = "ChaveAzure";
    private static final String PONTO_DE_EXTREMIDADE = "PontoAzure";

    public static void main(String[] args) {
        TextAnalyticsClient client = new TextAnalyticsClientBuilder()
                .endpoint(PONTO_DE_EXTREMIDADE)
                .credential(new AzureKeyCredential(CHAVE_DO_SERVICO))
                .buildClient();

        String texto = "Achei o filme incrível, a história me prendeu do início ao fim!";

        System.out.println("Analisando a frase: '" + texto + "'");

        DocumentSentiment resultado = client.analyzeSentiment(texto);

        System.out.println("----------------------------------------");
        System.out.println("Sentimento Geral Detectado: " + resultado.getSentiment());
        System.out.println("Pontuação de Confiança:");
        System.out.printf("  Positivo: %.2f%n", resultado.getConfidenceScores().getPositive());
        System.out.printf("  Neutro:   %.2f%n", resultado.getConfidenceScores().getNeutral());
        System.out.printf("  Negativo: %.2f%n", resultado.getConfidenceScores().getNegative());
        System.out.println("----------------------------------------");
    }
}