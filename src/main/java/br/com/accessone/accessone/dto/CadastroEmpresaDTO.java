package br.com.accessone.accessone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class CadastroEmpresaDTO {

    // Exige que o nome seja preenchido.
    @NotBlank
    // Limita o nome a 150 caracteres.
    @Size(max = 150)
    private String nome;

    // CPF informado no cadastro.
    @NotBlank
    private String cpf;

    // Telefone informado no cadastro.
    @NotBlank
    private String telefone;

    // E-mail obrigatório e com formato válido.
    @NotBlank
    @Email
    private String email;

    // Senha obrigatória com pelo menos 8 caracteres.
    // O serviço fará a codificação antes de salvar.
    @NotBlank
    @Size(min = 8, max = 72)
    private String senha;

    // Razão social da empresa.
    @NotBlank
    @Size(max = 200)
    private String razaoSocial;

    // Inscrição estadual, quando aplicável.
    private String inscricaoEstadual;

    // CNPJ da empresa.
    @NotBlank
    private String cnpj;

    // Endereço do estabelecimento.
    @NotBlank
    private String endereco;
}
