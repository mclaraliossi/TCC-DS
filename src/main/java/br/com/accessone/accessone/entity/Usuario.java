package br.com.accessone.accessone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    // Define se a conta é ADMIN ou EMPRESA.
    // EnumType.STRING salva o nome do tipo como texto no banco.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Relacionamento inverso com a empresa associada a esta conta.
    // O relacionamento é configurado pela entidade Empresa.
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Empresa empresa;
}
