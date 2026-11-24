package br.com.accessone.accessone.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 200)
    private String razaoSocial;

    private String inscricaoEstadual;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false)
    private String endereco;

    // Cada empresa pertence a uma conta de usuário.
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
        name = "usuario_id",
        nullable = false,
        unique = true
    )
    private Usuario usuario;

    // Recursos de acessibilidade disponíveis na empresa.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "empresa_recursos_acessibilidade",
        joinColumns = @JoinColumn(name = "empresa_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "recurso", nullable = false, length = 60)
    private Set<RecursoAcessibilidade> recursosAcessibilidade =
            new HashSet<>();
}