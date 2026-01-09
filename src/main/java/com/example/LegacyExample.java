package com.example;

import java.io.StringBufferInputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

public class LegacyExample {

    // finalize(): Deprecated em Java 9 e removida em Java 18+
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing object...");
        super.finalize();
    }

    public static void main(String[] args) {

        // APIs antigas e problemáticas:

        // 1. Date.getYear(), getMonth(), getDay() — Deprecated
        Date date = new Date();
        int year = date.getYear();     // <--- OpenRewrite converte para LocalDate
        int month = date.getMonth();
        int day = date.getDay();

        System.out.println("Date (deprecated methods): " + year + "-" + month + "-" + day);

        // 2. StringBufferInputStream — Removida em Java 11+
        StringBufferInputStream input =
                new StringBufferInputStream("Texto de teste"); // <--- OpenRewrite troca para InputStream moderno

        try {
            int data = input.read();
            while (data != -1) {
                System.out.print((char) data);
                data = input.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Thread.stop() — Removido e inseguro
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("Running thread...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        t.start();
        t.stop(); // <--- OpenRewrite converte para interrupt()

        // 4. Hashtable e Vector — Substituídos por HashMap e ArrayList
        Hashtable<String, String> table = new Hashtable<>();
        table.put("key", "value"); // <--- OpenRewrite migra para HashMap

        Vector<String> list = new Vector<>();
        list.add("item"); // <--- OpenRewrite migra para ArrayList

        System.out.println("Legacy structures used.");
    }
}
