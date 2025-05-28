package br.com.Breno_Daris.projetoeventos.models;

public enum Categoria {
    FESTA("Festa"),
    SHOW("Show"),
    EVENTO_ESPORTIVO("Evento Esportivo"),
    PALESTRA("Palestra"),
    WORKSHOP("Workshop"),
    CULTURAL("Evento Cultural"),
    ACADEMICO("Evento Acadêmico");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
} 