package br.com.yaman.steps;

import org.openqa.selenium.By;

import br.com.yaman.pages.PageObjects;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	PageObjects metodos = new PageObjects();

	@Given("^que eu esteja no \"([^\"]*)\" pelo \"([^\"]*)\"$")
	public void que_eu_esteja_no_pelo(String arg1, String arg2) throws Throwable {
		metodos.abrirSite(arg1, arg2);

	}

	@Then("^incluo produto no carrinho$")
	public void incluo_produto_no_carrinho() throws Throwable {
		metodos.inserirCarrinho("2", "3", "inserir produto no carrinho");
		metodos.validarCarrinho("R$ 34,90", "validar carrinho");
		metodos.fecharBrowser();
	}

	@When("^escolher produto$")
	public void escolher_produto() throws Throwable {
		metodos.escolherPorPosicao("1", "selecionando produto desejado");
	}

	@When("^pesquisar \"([^\"]*)\"$")
	public void pesquisar(String arg1) throws Throwable {

		metodos.PesquisarProduto(arg1, "pesquisando produto");
	}

	@Then("^coloco produto no carrinho$")
	public void coloco_produto_no_carrinho() throws Throwable {
		metodos.clicarConfirmar("confirmando produto no carrinho");
	}

	@Then("^confirmo \"([^\"]*)\"$")
	public void confirmo(String arg1) throws Throwable {
		metodos.validarItemPorPosicao("2", arg1, "validar produto");
		metodos.fecharBrowser();

	}

	@Then("^valido produto no carrinho$")
	public void valido_produto_no_carrinho() throws Throwable {
		metodos.validarCarrinho("R$ 299,90", "validando valor do produto");
		metodos.fecharBrowser();
	}

}
