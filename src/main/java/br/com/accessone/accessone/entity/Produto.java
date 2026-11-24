package br.com.accessone.accessone.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduto;

    @NotBlank(message = "Informe o nome do produto.")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String nomeProduto;

    @NotBlank(message = "Informe a marca.")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String marcaProduto;

    @NotBlank(message = "Informe a descrição.")
    @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String descricaoProduto;

    @DecimalMin(
        value = "0.0",
        message = "O preço não pode ser negativo."
    )
    private Double precoProduto;

    private LocalDate validadeProduto;

    private LocalDate datafabricacaoProduto;

    @NotBlank(message = "Informe o lote.")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String loteProduto;

    @NotBlank(message = "Informe o país de origem.")
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String paisorigemProduto;

    // Campo antigo, mantido para compatibilidade.
    // Será preenchido automaticamente pelo sistema.
    @Column(nullable = false, length = 100)
    private String empresaProduto;

    // Nova associação: o produto pertence a uma empresa.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Por enquanto, a imagem é informada por URL.
    @Column(length = 500)
    @Size(max = 500)
    @Pattern(
        regexp = "(?i)^(https?://.+)?$",
        message = "Use um link começando com http:// ou https://."
    )
    private String imagemUrl;

    // Informações nutricionais

    private Double porcaoProduto;

    private String valorenergeticoProduto;

    private String carboidratosProduto;

    private String proteinasProduto;

    private String gordurastotaisProduto;

    private String gordurassaturadasProduto;

    private String fibrasProduto;

    private String sodioProduto;

    @NotBlank(message = "Informe os ingredientes.")
    @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String ingredientesProduto;

    @NotBlank(
        message = "Informe os alergênicos ou indique 'Não contém'."
    )
    @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String alergicosProduto;
}