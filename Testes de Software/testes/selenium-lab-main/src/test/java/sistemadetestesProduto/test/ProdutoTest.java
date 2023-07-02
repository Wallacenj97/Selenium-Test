package sistemadetestes.produto.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import sistemadetestes.pageObject.ProdutoPO;

/**
 * Classe de testes automatizados para a funcionalidade de Produto.
 * Essa classe contém testes para verificar o correto funcionamento das funcionalidades de cadastro de produtos.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest extends BaseText {
    private static ProdutoPO produtoPage;

    /**
     * Configuração inicial dos testes.
     * Inicializa a página de objeto (Page Object) do Produto e o WebDriver antes da execução dos testes.
     */
    @BeforeClass
    public static void prepararTestes() {
        produtoPage = new ProdutoPO(driver);
    }

    /**
     * Teste de cadastro de produto com todos os campos preenchidos.
     * Verifica se um produto é cadastrado corretamente e se as informações aparecem na tabela de produtos.
     */
    @Test
    public void TC01_deveCadastrarProdutoComTodosOsCampos() {
        produtoPage.buttonCriar.click();
        produtoPage.buttonCriar.click();
        produtoPage.cadastrar("0205", "Arroz", "1", "18,00", "02/07/2023");
        String texto = ProdutoPage.tabela.getText();
        assertTrue(texto.contains("0205"));
        assertTrue(texto.contains("Arroz"));
        assertTrue(texto.contains("1"));
        assertTrue(texto.contains("18,00"));
        assertTrue(texto.contains("02/07/2023"));
    }

    /**
     * Teste de abertura do calendário ao clicar no ícone.
     * Verifica se o calendário é exibido corretamente ao clicar no ícone relacionado à data do produto.
     */
    @Test
    public void TC02_deveAbrirCalendarioAoClicarNoIcone() {
        produtoPage.buttonCriar.click();
        produtoPage.buttonCriar.click();
        produtoPage.abrirCalendario();
        assertTrue(produtoPage.calendarioEstaVisivel());
    }

    /**
     * Teste de tentativa de cadastro de produto com código já cadastrado.
     * Verifica se é exibida a mensagem de erro correta ao tentar cadastrar um produto com um código que já existe no sistema.
     */
    @Test
    public void TC03_naoDeveCadastrarProdutoComMesmoCodigo() {
        produtoPage.buttonCriar.click();
        produtoPage.buttonCriar.click();
        produtoPage.cadastrar("0205", "Arroz", "1", "18,00", "02/07/2023");
        String mensagem = produtoPage.getMensagemErro();
        assertEquals("Código já cadastrado", mensagem);
    }
}
