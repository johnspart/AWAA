/**
 * 
 */
package org.awaa.test.stepdefinitions;

import org.awaa.feature.utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * @author john.lopez
 *
 */
public class IngrsoAwaa {
	private Utils util = Utils.getInstance();
	private WebDriver driver = util.getWebDriver();

	@Given("^El usuario accede a awaa$")
	public void el_usuario_accede_a_awaa() throws Throwable {
		driver.get("http://localhost:3000/#");
	}

	@Then("^el usuario ingresa a la pagina de inicio$")
	public void el_usuario_ingresa_a_la_pagina_de_inicio() throws Throwable {
		new WebDriverWait(driver, 10);
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		alert.sendKeys("");
		alert.accept();
		Assert.assertEquals("Bienvenido", message);
	}
}
