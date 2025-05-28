package br.com.Breno_Daris.projetoeventos.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Evento {
    private String nome;
    private String endereco;
    private Categoria categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<Usuario> participantes;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Evento(String nome, String endereco, Categoria categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(Usuario usuario) {
        if (!participantes.contains(usuario)) {
            participantes.add(usuario);
            usuario.inscreverEvento(this);
        }
    }

    public void removerParticipante(Usuario usuario) {
        if (participantes.remove(usuario)) {
            usuario.cancelarInscricao(this);
        }
    }

    public boolean isOcorrendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        return !horario.isAfter(agora) && !horario.plusHours(2).isBefore(agora); // Assume 2 horas de duração
    }

    public boolean jaOcorreu() {
        return horario.plusHours(2).isBefore(LocalDateTime.now());
    }

    public String getHorarioFormatado() {
        return horario.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nome, evento.nome) && Objects.equals(horario, evento.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, horario);
    }

    @Override
    public String toString() {
        return String.format("Evento: %s%nCategoria: %s%nData/Hora: %s%nEndereço: %s%nDescrição: %s%nStatus: %s",
                nome,
                categoria.getDescricao(),
                getHorarioFormatado(),
                endereco,
                descricao,
                isOcorrendoAgora() ? "ACONTECENDO AGORA" : (jaOcorreu() ? "JÁ OCORREU" : "PROGRAMADO")
        );
    }

    public String toFileString() {
        return String.format("%s;%s;%s;%s;%s",
                nome,
                endereco,
                categoria,
                horario.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                descricao);
    }

    public static Evento fromFileString(String line) {
        String[] parts = line.split(";");
        return new Evento(
                parts[0],
                parts[1],
                Categoria.valueOf(parts[2]),
                LocalDateTime.parse(parts[3]),
                parts[4]
        );
    }
}
    

