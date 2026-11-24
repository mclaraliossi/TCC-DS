package br.com.accessone.accessone.entity;

public enum RecursoAcessibilidade {

    ATENDIMENTO_LIBRAS("Atendimento em Libras"),

    INFORMACOES_BRAILLE("Informações em Braille"),

    DESCRICAO_AUDIO("Descrições por áudio"),

    ATENDIMENTO_ASSISTIDO("Atendimento assistido"),

    TEXTO_AMPLIADO("Informações com texto ampliado");

    private final String descricao;

    RecursoAcessibilidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}