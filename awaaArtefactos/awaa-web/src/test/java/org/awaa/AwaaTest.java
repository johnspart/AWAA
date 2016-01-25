/**
 * 
 */
package org.awaa;

/**
 * @author john.lopez
 *
 */
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/org/awaa/feature/paginaInicio.feature" })
public class AwaaTest {

}
