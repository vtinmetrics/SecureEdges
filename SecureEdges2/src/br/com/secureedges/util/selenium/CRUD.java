package br.com.secureedges.util.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CRUD extends TEST {
	private static WebDriver driver = null;
	public static WebDriverWait wait = null;
	@Test
	public void realizarCrud(){
		
		driver=Login.efetuarLogin("3359.027.068-31","12345");
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.navigate().refresh();
		driver.get("http://localhost:8080/SecureEdges/Pages/dispositivo/dispositivoPesquisa.xhtml");
		driver.findElement(By.id("formdispositivo:dtdispositivo:btnovo")).click();
		driver.findElement(By.id("j_idt28:inputDescricao")).sendKeys("Descrição Selenium");
		wait = new WebDriverWait(driver,2000); // espera 
		driver.findElement(By.id("j_idt28:j_idt46")).click();
		driver.get("http://localhost:8080/SecureEdges/Pages/dispositivo/dispositivoPesquisa.xhtml");
		driver.findElement(By.id("formdispositivo:dtdispositivo:3:btExcluir")).click();
		wait = new WebDriverWait(driver,5000); // espera 
		driver.findElement(By.id("j_idt28:btnExcluir")).click();
		driver.get("http://localhost:8080/SecureEdges/Pages/dispositivo/dispositivoPesquisa.xhtml");

		
		
		
	
		
	}


}
