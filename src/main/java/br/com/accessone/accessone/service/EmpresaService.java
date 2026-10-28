package br.com.accessone.accessone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.accessone.accessone.dto.CadastroEmpresaDTO;
import br.com.accessone.accessone.entity.Empresa;
import br.com.accessone.accessone.entity.Role;
import br.com.accessone.accessone.entity.Usuario;
import br.com.accessone.accessone.repository.EmpresaRepository;
import br.com.accessone.accessone.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    // O Spring injeta as dependências necessárias.
    public EmpresaService(
            EmpresaRepository empresaRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Executa o cadastro dentro de uma transação.
    // Se ocorrer uma falha, as gravações são desfeitas.
    @Transactional
    public Empresa cadastrarEmpresa(CadastroEmpresaDTO dto) {

        // Normaliza os dados usados nas verificações.
        String email = dto.getEmail().trim().toLowerCase();
        String cpf = dto.getCpf().replaceAll("\\D", "");
        String cnpj = dto.getCnpj().replaceAll("\\D", "");

        // Impede o cadastro de um e-mail já existente.
        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                "Este e-mail já está cadastrado."
            );
        }

      
        // Impede o cadastro de um CNPJ já existente.
        if (empresaRepository.existsByCnpj(cnpj)) {
            throw new IllegalArgumentException(
                "Este CNPJ já está cadastrado."
            );
        }

        // Cria a conta de acesso da empresa.
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome().trim());
        usuario.setEmail(email);

        // Nunca salva a senha original diretamente.
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        // Toda conta criada pelo cadastro público será EMPRESA.
        // O formulário não pode escolher a função ADMIN.
        usuario.setRole(Role.EMPRESA);

        // Salva primeiro o usuário para gerar seu ID.
        usuarioRepository.save(usuario);

        // Cria o registro com os dados comerciais.
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(dto.getRazaoSocial().trim());
        empresa.setInscricaoEstadual(dto.getInscricaoEstadual());
        empresa.setCnpj(cnpj);
        empresa.setEndereco(dto.getEndereco().trim());

        // Vincula a empresa à conta criada.
        empresa.setUsuario(usuario);

        // Persiste a empresa e retorna o registro salvo.
        return empresaRepository.save(empresa);
    }

    // Busca a empresa vinculada ao e-mail autenticado.
    // A operação é somente de leitura.
    @Transactional(readOnly = true)
    public Empresa buscarMinhaEmpresa(String email) {
        return empresaRepository.findByUsuario_Email(
            email.trim().toLowerCase()
        ).orElseThrow(() -> new IllegalArgumentException(
            "Não foi encontrada uma empresa vinculada a esta conta."
        ));
    }
}
