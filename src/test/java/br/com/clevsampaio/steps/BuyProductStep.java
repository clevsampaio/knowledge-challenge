package br.com.clevsampaio.steps;

import br.com.clevsampaio.pageobjects.BuyProductPageObject;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BuyProductStep {
    private final BuyProductPageObject object;
    private final Faker faker;

    public BuyProductStep(WebDriver webDriver) {
        object = new BuyProductPageObject(webDriver);
        faker = new Faker();
    }

    public BuyProductStep pesquisarProduto(String produto) {
        object.searchTextField().setText(produto);
        object.submitSearchButton().click();
        object.firstResultProductButton().click();

        return this;
    }

    public BuyProductStep adicionarProdutoAoCarrinho() {
        Assert.assertEquals(object.productPriceLabel().getText(), "$27.00");
        object.addToCartButton().click();

        return this;
    }

    public BuyProductStep resumoDaCompra() {
        object.layerToCartButton().click();
        Assert.assertEquals(object.totalPriceLabel().getText(), "$29.00");
        object.proceedToCheckoutButton().click();

        return this;
    }

    public BuyProductStep realizarCadastro() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        object.createAnAccountTextField().setText(faker.internet().safeEmailAddress());
        object.createAnAccountButton().click();
        object.genderCheckBox().click();
        object.customerFirstNameTextField().setText(firstName);
        object.customerLastNameTextField().setText(lastName);
        object.passwordTextField().setText(faker.internet().password());
        object.firstNameTextField().setText(firstName);
        object.lastNameTextField().setText(lastName);
        object.addressTextField().setText(faker.address().streetAddress());
        object.cityTextField().setText(faker.address().city());
        object.stateComboBox().selectByIndex(6);
        object.postalCodeTextField().setText(faker.number().digits(5));
        object.phoneMobileTextField().setText(faker.phoneNumber().cellPhone());
        object.registerButton().click();

        return this;
    }

    public BuyProductStep confirmarEndereco() {
        object.processAddressButton().click();

        return this;
    }

    public BuyProductStep definirRemessa() {
        object.termsOfServiceCheckBox().click();
        object.processCarrierButton().click();

        return this;
    }

    public BuyProductStep realizarPagamento() {
        object.payByBankWireButton().click();
        object.iConfirmMyOrderButton().click();
        Assert.assertEquals(object.orderConfirmationLabel().getText(), "Your order on My Store is complete.");

        return this;
    }

    public BuyProductStep desconectar() {
        object.signOutButton().click();
        Assert.assertEquals(object.authenticationLabel().getText(), "AUTHENTICATION");

        return this;
    }
}