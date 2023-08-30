package br.edu.ufcg.computacao.psoft.commerce.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.ProdutoVolatilRepository;

@Service
public class ProdutoXXXXService {

    @Autowired
    private ProdutoVolatilRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.getAllProdutos();
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.getProdutoById(id);
    }

    public Produto createProduto(Produto produto) {
        validateProdutoAttributes(produto);
        return produtoRepository.createProduto(produto);
    }

    public Produto updateProduto(Long id, Produto produto) {
        validateProdutoAttributes(produto);
        Produto existingProduto = getProdutoById(id);

        existingProduto.setNome(produto.getNome());
        existingProduto.setCodigoBarras(produto.getCodigoBarras());
        existingProduto.setValor(produto.getValor());
        existingProduto.setFabricante(produto.getFabricante());

        return produtoRepository.updateProduto(id, existingProduto);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteProduto(id);
    }

    public Produto patchProduto(Long id, List<PatchOperation> patchOperations) {
        Produto produto = getProdutoById(id);

        for (PatchOperation operation : patchOperations) {
            if (operation.getPath().equals("/codigoBarras")) {
                produto.setCodigoBarras(operation.getValue());
            }
            if (operation.getPath().equals("/valor")) {
                produto.setValor((Double) operation.getValue());
            }
            // Implement other patch operations if needed
        }

        return produtoRepository.updateProduto(id, produto);
    }

    private void validateProdutoAttributes(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }

        if (produto.getCodigoBarras() == null || produto.getCodigoBarras().isEmpty()) {
            throw new IllegalArgumentException("Código de barras do produto é obrigatório.");
        }

        // Implement other attribute validation logic here

        if (!validateEAN13Barcode(produto.getCodigoBarras())) {
            throw new IllegalArgumentException("Código de barras inválido.");
        }

        if (produto.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do produto deve ser maior que zero.");
        }

        // Implement other validations according to business rules
    }

    private boolean validateEAN13Barcode(String barcode) {
        // Verificar se o código de barras tem o comprimento correto (13 dígitos)
        if (barcode.length() != 13) {
            return false;
        }

        // Extrair os 12 primeiros dígitos do código de barras
        String barcodeDigits = barcode.substring(0, 12);

        // Calcular o dígito de verificação esperado
        int expectedCheckDigit = calculateEAN13CheckDigit(barcodeDigits);

        // Obter o dígito de verificação real do código de barras
        int actualCheckDigit = Character.getNumericValue(barcode.charAt(12));

        // Comparar o dígito de verificação esperado com o real
        return expectedCheckDigit == actualCheckDigit;
    }

    private int calculateEAN13CheckDigit(String barcodeDigits) {
        int sum = 0;
        for (int i = 0; i < barcodeDigits.length(); i++) {
            int digit = Character.getNumericValue(barcodeDigits.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int remainder = sum % 10;
        return (10 - remainder) % 10;
    }
}