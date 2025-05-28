package br.com.Breno_Daris.projetoeventos.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String cidade;
    private String telefone;
    private List<Evento> eventosInscritos;

    public Usuario(String nome, String email, String senha, String cidade, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
        this.telefone = telefone;
        this.eventosInscritos = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Evento> getEventosInscritos() {
        return eventosInscritos;
    }

    public void inscreverEvento(Evento evento) {
        if (!eventosInscritos.contains(evento)) {
            eventosInscritos.add(evento);
        }
    }

    public void cancelarInscricao(Evento evento) {
        eventosInscritos.remove(evento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
