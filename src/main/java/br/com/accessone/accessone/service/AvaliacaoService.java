package br.com.accessone.accessone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.accessone.accessone.entity.Avaliacao;
import br.com.accessone.accessone.entity.Empresa;
import br.com.accessone.accessone.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private EmpresaService empresaService;

    // Lista apenas avaliações da empresa autenticada.
    @Transactional(readOnly = true)
    public List<Avaliacao> listarDaMinhaEmpresa(String email) {

        Empresa empresa =
                empresaService.buscarMinhaEmpresa(email);

        return avaliacaoRepository
            .findByEmpresa_IdOrderByDataDesc(
                empresa.getId()
            );
    }
}