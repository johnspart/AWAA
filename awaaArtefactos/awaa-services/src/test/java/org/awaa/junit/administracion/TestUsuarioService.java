/**
 * 
 */
package org.awaa.junit.administracion;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.TestCase;

/**
 * @author john
 *
 */
public class TestUsuarioService extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link org.awaa.services.administracion.impl.UsuarioServiceImpl#getUsuarios()}
	 * .
	 */
	// @Test
	public void testGetUsuarios() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link org.awaa.services.administracion.impl.UsuarioServiceImpl#sendEmailRestartPass(java.lang.String)}
	 * .
	 */
	// @Test
	public void testSendEmailRestartPass() {
		// fail("Not yet implemented");
	}

}
