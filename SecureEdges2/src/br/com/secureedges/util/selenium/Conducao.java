package br.com.secureedges.util.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Conducao extends TEST {
	
	@Test
	public void realizarConducao(){
		driver = TEST.retornarDriverSeleniumChrome();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver=Login.efetuarLogin("335902706831","12345");
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.navigate().refresh();
		driver.findElement(By.id("frmVendaCad:tblProdutos:2:btAdicionar")).click();
		driver.findElement(By.id("frmFinalizar:btFinalizar")).click();
		driver.findElement(By.id("wvDlgFinVenda:frmPdl:btSalvar")).click();
		
		
		
	}
}

