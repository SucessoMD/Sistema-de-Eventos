package br.com.Breno_Daris.projetoeventos.utils;

import br.com.Breno_Daris.projetoeventos.models.Evento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventFileManager {
    private static final String FILE_PATH = "events.data";

    public static void salvarEventos(List<Evento> eventos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Evento evento : eventos) {
                writer.write(evento.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public static List<Evento> carregarEventos() {
        List<Evento> eventos = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            return eventos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    eventos.add(Evento.fromFileString(line));
                } catch (Exception e) {
                    System.err.println("Erro ao ler evento: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
        }
        return eventos;
    }
} 