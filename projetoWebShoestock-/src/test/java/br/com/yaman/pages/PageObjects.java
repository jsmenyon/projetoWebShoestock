package br.com.yaman.pages;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class PageObjects {

	WebDriver driver;

	public void abrirSite(String appUrl, String navegador) {
		String opcao = navegador;

		switch (opcao) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(appUrl);
			driver.manage().window().maximize();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(appUrl);
			driver.manage().window().maximize();
			break;

		}

	}

	public void PesquisarProduto(String texto, String descricaoPasso) throws InterruptedException {
		Thread.sleep(2000);
		WebElement pesquisar = driver.findElement(By.name("q"));
		pesquisar.sendKeys(texto);
		driver.findElement(
				By.cssSelector("#header-content > header > div > div > section.search > section > form > div > button"))
				.click();
	}

	public void esperarElemento(int tempo, WebElement prod, String descricaoPasso) {
		WebDriverWait wait = new WebDriverWait(driver, tempo);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By) prod));
	}

	public void validarItemPorPosicao(String posicao, String produto, String descricaoPasso) {
		String prod = driver.findElement(By.cssSelector("#item-list > div.wrapper > div:nth-child(" + posicao
				+ ") > div.item-card__description > a.item-card__description__product-name > span")).getText();

		Assert.assertTrue(prod.contains(produto));
		System.out.println(prod);

	}
	
	public void clicarConfirmar(String descricaoPasso) {
		driver.findElement(By.cssSelector("#buy-button-now > span")).click();
	}

	public void escolherPorPosicao(String posicao, String descricaoPasso) {
		driver.findElement(By.cssSelector("#item-list > div.wrapper > div:nth-child("+posicao+") > div.item-card__description > a.item-card__description__product-name > span")).click();                     
		
	}

	public void validarPorDescricao(String descricao, String posicao, String descricaoPasso) {

		String produto = driver.findElement(By.cssSelector("#item-list > div > div:nth-child(" + posicao
				+ ") > div.item-card__description > a.item-card__description__product-name > span")).getText();

		assertEquals(descricao, produto);
	}

	public void inserirCarrinho(String posicao, String tamanho, String descricaoPasso) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#item-list > div > div:nth-child(" + posicao
				+ ") > div.item-card__description > a.item-card__description__product-name > span")).click();
		Thread.sleep(3000);
		driver.findElement(
				By.cssSelector("#buy-box > section.product-size-selector > div > ul > li:nth-child(" + tamanho + ")"))
				.click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#buy-button-now >span")).click();

	}

	public void fecharBrowser() {
		System.out.println("Teste finalizado com sucesso!!!");
		driver.quit();
	}

	public void validarCarrinho(String texto, String descricaoPasso) throws InterruptedException {
		Thread.sleep(5000);
		String valor = driver.findElement(By.cssSelector(
				"body > div.main > div.cart.breakpoint.main > div.cart__summary > div.summary > ul > li:nth-child(3) > div.summary__item-value > div:nth-child(1)"))
				.getText();
		Thread.sleep(2000);
		assertEquals(texto, valor);

	}

	public void Handle() {
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		System.out.println(s1);
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);

			}
		}

	}

}
