package br.com.accessone.accessone.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(nullable = false, length = 50)
    private String nomeProduto;

    @Column(nullable = false, length = 50)
    private String marcaProduto;

    @Column(nullable = false, length = 100)
    private String descricaoProduto;

    private Double precoProduto;

    private LocalDate validadeProduto;

    private LocalDate datafabricacaoProduto;

    @Column(nullable = false, length = 50)
    private String loteProduto;

    @Column(nullable = false, length = 30)
    private String paisorigemProduto;

    // Tabela nutricional do produto

    private Double porcaoProduto;

    private String valorenergeticoProduto;

    private String carboidratosProduto;

    private String proteinasProduto;

    private String gordurastotaisProduto;

    private String gordurassaturadasProduto;

    private String fibrasProduto;

    private String sodioProduto;

    @Column(nullable = false, length = 100)
    private String ingredientesProduto;

    @Column(nullable = false, length = 100)
    private String alergicosProduto;



}
