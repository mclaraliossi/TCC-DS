package br.com.accessone.accessone.service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.accessone.accessone.dto.CadastroEmpresaDTO;

import br.com.accessone.accessone.entity.Empresa;
import br.com.accessone.accessone.entity.RecursoAcessibilidade;
import br.com.accessone.accessone.entity.Role;
import br.com.accessone.accessone.entity.Usuario;

import br.com.accessone.accessone.repository.EmpresaRepository;
import br.com.accessone.accessone.repository.UsuarioRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Cadastra o responsável e a empresa.
    @Transactional
    public Empresa cadastrarEmpresa(CadastroEmpresaDTO dto) {

        String email = dto.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        String cnpj = dto.getCnpj().replaceAll("\\D", "");

        // Valida se a confirmação corresponde à senha.
        if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
            throw new IllegalArgumentException(
                "As senhas não coincidem."
            );
        }

        if (cnpj.length() != 14) {
            throw new IllegalArgumentException(
                "O CNPJ precisa conter 14 dígitos."
            );
        }

        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                "Este e-mail já está cadastrado."
            );
        }

        if (empresaRepository.existsByCnpj(cnpj)) {
            throw new IllegalArgumentException(
                "Este CNPJ já está cadastrado."
            );
        }

        // Primeiro cria a conta responsável.
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome().trim());
        usuario.setEmail(email);

        // Nunca salva senha em texto puro.
        usuario.setSenha(
            passwordEncoder.encode(dto.getSenha())
        );

        // O cadastro público só pode criar EMPRESA.
        usuario.setRole(Role.EMPRESA);

        usuarioRepository.save(usuario);

        // Depois cria a empresa vinculada ao usuário.
        Empresa empresa = new Empresa();

        empresa.setRazaoSocial(
            dto.getRazaoSocial().trim()
        );

        empresa.setInscricaoEstadual(
            dto.getInscricaoEstadual() == null
                ? null
                : dto.getInscricaoEstadual().trim()
        );

        empresa.setCnpj(cnpj);

        empresa.setEndereco(
            dto.getEndereco().trim()
        );

        empresa.setUsuario(usuario);

        return empresaRepository.save(empresa);
    }

    // Busca a empresa vinculada ao e-mail autenticado.
    @Transactional(readOnly = true)
    public Empresa buscarMinhaEmpresa(String email) {

        String normalizado = email
                .trim()
                .toLowerCase(Locale.ROOT);

        return empresaRepository
            .findByUsuario_Email(normalizado)
            .orElseThrow(() -> new IllegalArgumentException(
                "Não existe empresa vinculada a esta conta."
            ));
    }

    // Atualiza os recursos que a empresa oferece.
    @Transactional
    public void atualizarRecursos(
            String email,
            List<RecursoAcessibilidade> recursos) {

        Empresa empresa = buscarMinhaEmpresa(email);

        Set<RecursoAcessibilidade> selecionados =
            recursos == null
                ? new HashSet<>()
                : new HashSet<>(recursos);

        empresa.getRecursosAcessibilidade().clear();

        empresa.getRecursosAcessibilidade()
                .addAll(selecionados);
    }

    // Lista todas as empresas para o ADMIN.
    @Transactional(readOnly = true)
    public List<Empresa> listarTodas() {
        return empresaRepository
                .findAllByOrderByRazaoSocialAsc();
    }
}