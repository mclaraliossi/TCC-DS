-- Consulte produtos que ainda não possuem empresa associada.

SELECT
    id_produto,
    nome_produto,
    empresa_produto,
    empresa_id
FROM produto
WHERE empresa_id IS NULL;

-- Consulte os IDs das empresas cadastradas.

SELECT
    id,
    razao_social,
    cnpj
FROM empresas;

-- Exemplo de vinculação manual:
-- Confira os IDs verdadeiros antes de executar.
--
-- UPDATE produto
-- SET empresa_id = 5
-- WHERE id_produto = 10;

-- Verifique quantos produtos continuam sem empresa.

SELECT COUNT(*)
FROM produto
WHERE empresa_id IS NULL;