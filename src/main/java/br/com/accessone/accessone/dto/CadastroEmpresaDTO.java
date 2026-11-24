package br.com.accessone.accessone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CadastroEmpresaDTO {

    // Dados do responsável

    @NotBlank(message = "Informe o nome do responsável.")
    @Size(max = 150)
    private String nome;

    @NotBlank(message = "Informe o e-mail.")
    @Email(message = "Informe um e-mail válido.")
    private String email;

    @NotBlank(message = "Informe a senha.")
    @Size(min = 8, max = 72,
          message = "A senha deve ter de 8 a 72 caracteres.")
    private String senha;

    @NotBlank(message = "Confirme sua senha.")
    private String confirmarSenha;

    // Dados da empresa

    @NotBlank(message = "Informe a razão social.")
    @Size(max = 200)
    private String razaoSocial;

    private String inscricaoEstadual;

    @NotBlank(message = "Informe o CNPJ.")
    private String cnpj;

    @NotBlank(message = "Informe o endereço da empresa.")
    private String endereco;
}