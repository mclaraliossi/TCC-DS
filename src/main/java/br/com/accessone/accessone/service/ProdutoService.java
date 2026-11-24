package br.com.accessone.accessone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.accessone.accessone.entity.Empresa;
import br.com.accessone.accessone.entity.Produto;
import br.com.accessone.accessone.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EmpresaService empresaService;

    // Lista somente os produtos da empresa logada.
    @Transactional(readOnly = true)
    public List<Produto> listarDaEmpresa(String email) {

        Empresa empresa =
                empresaService.buscarMinhaEmpresa(email);

        return produtoRepository
                .findByEmpresa_IdOrderByIdProdutoDesc(
                    empresa.getId()
                );
    }

    // Conta os produtos da empresa.
    @Transactional(readOnly = true)
    public long contarDaEmpresa(String email) {

        Empresa empresa =
                empresaService.buscarMinhaEmpresa(email);

        return produtoRepository
                .countByEmpresa_Id(empresa.getId());
    }

    // Impede que a empresa consulte produto de terceiros.
    @Transactional(readOnly = true)
    public Produto buscarDaEmpresa(Long id, String email) {

        Empresa empresa =
                empresaService.buscarMinhaEmpresa(email);

        return produtoRepository
            .findByIdProdutoAndEmpresa_Id(
                id,
                empresa.getId()
            )
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Produto não encontrado nesta empresa."
            ));
    }

    // Cadastra ou atualiza um produto.
    @Transactional
    public Produto salvarParaEmpresa(
            Produto dados,
            String email) {

        Empresa empresa =
                empresaService.buscarMinhaEmpresa(email);

        Produto produto;

        if (dados.getIdProduto() == null) {

            // Novo produto.
            produto = new Produto();

        } else {

            // Produto existente: confere a empresa proprietária.
            produto = produtoRepository
                .findByIdProdutoAndEmpresa_Id(
                    dados.getIdProduto(),
                    empresa.getId()
                )
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado nesta empresa."
                    )
                );
        }

        // Copia apenas os campos que a empresa pode editar.
        produto.setNomeProduto(
            dados.getNomeProduto().trim()
        );

        produto.setMarcaProduto(
            dados.getMarcaProduto().trim()
        );

        produto.setDescricaoProduto(
            dados.getDescricaoProduto().trim()
        );

        produto.setPrecoProduto(
            dados.getPrecoProduto()
        );

        produto.setValidadeProduto(
            dados.getValidadeProduto()
        );

        produto.setDatafabricacaoProduto(
            dados.getDatafabricacaoProduto()
        );

        produto.setLoteProduto(
            dados.getLoteProduto().trim()
        );

        produto.setPaisorigemProduto(
            dados.getPaisorigemProduto().trim()
        );

        produto.setImagemUrl(
            dados.getImagemUrl()
        );

        // Informações nutricionais.
        produto.setPorcaoProduto(
            dados.getPorcaoProduto()
        );

        produto.setValorenergeticoProduto(
            dados.getValorenergeticoProduto()
        );

        produto.setCarboidratosProduto(
            dados.getCarboidratosProduto()
        );

        produto.setProteinasProduto(
            dados.getProteinasProduto()
        );

        produto.setGordurastotaisProduto(
            dados.getGordurastotaisProduto()
        );

        produto.setGordurassaturadasProduto(
            dados.getGordurassaturadasProduto()
        );

        produto.setFibrasProduto(
            dados.getFibrasProduto()
        );

        produto.setSodioProduto(
            dados.getSodioProduto()
        );

        produto.setIngredientesProduto(
            dados.getIngredientesProduto().trim()
        );

        produto.setAlergicosProduto(
            dados.getAlergicosProduto().trim()
        );

        // O vínculo é estabelecido no servidor.
        produto.setEmpresa(empresa);

        // Mantém o campo legado preenchido.
        produto.setEmpresaProduto(
            empresa.getRazaoSocial()
        );

        return produtoRepository.save(produto);
    }

    // Exclui somente produtos da empresa autenticada.
    @Transactional
    public void excluirDaEmpresa(Long id, String email) {

        Produto produto =
                buscarDaEmpresa(id, email);

        produtoRepository.delete(produto);
    }
}