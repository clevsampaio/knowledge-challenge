package br.com.clevsampaio.testcases;

import br.com.clevsampaio.steps.BuyProductStep;
import br.com.clevsampaio.utils.BaseTest;
import br.com.clevsampaio.utils.Property;
import br.com.clevsampaio.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class BuyProductTest extends BaseTest
{
    @Test(
            description = "Adicionar produto ao carrinho, cadastro e compra",
            priority = 1
    )
    public void test()
    {
        WebDriver driver = DriverManager.getDriver();
        driver.get(Property.get("url"));

        BuyProductStep step = new BuyProductStep(driver);
        step.pesquisarProduto("Blouse")
                .adicionarProdutoAoCarrinho()
                .resumoDaCompra()
                .realizarCadastro()
                .confirmarEndereco()
                .definirRemessa()
                .realizarPagamento()
                .desconectar();
    }
}