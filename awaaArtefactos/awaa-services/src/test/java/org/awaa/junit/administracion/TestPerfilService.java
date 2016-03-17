package org.awaa.junit.administracion;

import static org.awaa.test.constant.Security.USER_ACTION;

import org.awaa.repository.administracion.PerfilRepository;
import org.awaa.services.administracion.PerfilService;
import org.awaa.services.administracion.impl.PerfilServiceImpl;
import org.awaa.test.data.administracion.DatosPerfil;
import org.awaa.utils.beans.administracion.Perfil;
import org.awwa.utils.exeptions.BusinessExeption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestPerfilService {

	@InjectMocks
	private PerfilService perfilService = new PerfilServiceImpl();
	@Mock
	private PerfilRepository PerfilRepository;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAlmacenarPerfil() throws BusinessExeption {
		Perfil perfil = DatosPerfil.getPerfils().get(0);
		perfil.setIdPerfil(null);
		Perfil perfil2 = DatosPerfil.getPerfils().get(0);
		Mockito.when(perfilService.almacenarPerfil(perfil, USER_ACTION.getCodigo())).thenReturn(perfil);
		perfil2 = perfilService.getPerfilById(perfil.getIdPerfil());
		System.out.println("ID=" + perfil2.getIdPerfil());
	}

	/**
	 * @Test public void testRemoverPerfil() {
	 * 
	 *       }
	 * 
	 * @Test public void testGetPerfiles() {
	 * 
	 *       }
	 * 
	 * @Test public void testGetPermisosPerfil() { }
	 **/

}
