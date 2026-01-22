package com.example;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class LegacyExample {

    // O método finalize() foi removido por ser obsoleto e inseguro.
    // A limpeza de recursos deve ser feita com try-with-resources ou outros mecanismos.

    public static void main(String[] args) {

        // 1. Uso da nova API de Data/Hora (java.time)
        var now = LocalDate.now();
        var year = now.getYear();
        var month = now.getMonthValue();
        var day = now.getDayOfMonth();

        System.out.println("Date (API moderna): " + year + "-" + month + "-" + day);

        // 2. Substituição de StringBufferInputStream por ByteArrayInputStream com codificação definida
        try (var input = new ByteArrayInputStream("Texto de teste".getBytes(StandardCharsets.UTF_8))) {
            int data = input.read();
            while (data != -1) {
                System.out.print((char) data);
                data = input.read();
            }
            System.out.println(); // Adiciona uma nova linha para formatação
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Uso de interrupt() para sinalizar o encerramento de uma Thread
        var t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Running thread...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Restaura o status de interrupção e encerra o loop
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Thread finished.");
        });

        t.start();
        try {
            // Aguarda um pouco antes de interromper
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt(); // Forma correta de solicitar a parada da thread

        // 4. Uso de coleções modernas da Java Collections Framework
        var table = new HashMap<String, String>();
        table.put("key", "value");

        var list = new ArrayList<String>();
        list.add("item");

        System.out.println("Estruturas de dados modernas utilizadas.");
    }
}
