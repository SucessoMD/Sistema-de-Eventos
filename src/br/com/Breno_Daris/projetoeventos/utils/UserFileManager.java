package br.com.Breno_Daris.projetoeventos.utils;

import br.com.Breno_Daris.projetoeventos.models.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {
    private static final String FILE_PATH = "users.data";

    public static void salvarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Usuario usuario : usuarios) {
                writer.write(String.format("%s;%s;%s;%s;%s",
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getCidade(),
                    usuario.getTelefone()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            return usuarios;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(";");
                    usuarios.add(new Usuario(
                        parts[0], // nome
                        parts[1], // email
                        parts[2], // senha
                        parts[3], // cidade
                        parts[4]  // telefone
                    ));
                } catch (Exception e) {
                    System.err.println("Erro ao ler usuário: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
        return usuarios;
    }
} 