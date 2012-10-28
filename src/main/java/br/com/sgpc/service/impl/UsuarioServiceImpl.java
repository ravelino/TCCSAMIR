package br.com.sgpc.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.UsuarioDao;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.LoginService;
import br.com.sgpc.service.UsuarioService;

/**
 * Implementacao de {@link LoginService}
 * @author Guilherme Gambeti
 * @since 25/10/2012
 *
 */
@Service( value = "usuarioService" )
public class UsuarioServiceImpl implements UsuarioService {

	private static final long serialVersionUID = 2453278820347973699L;
	
	@Resource( name = "usuarioDao" )
	private UsuarioDao usuarioDao;
	
	@Override
	public boolean verificarSeLoginExiste(final String login) {
		
		boolean existe = procurarUsuarioPeloLogin( login ) != null;
		return existe;
	}
	
	@Override
	public Usuario procurarUsuarioPeloLogin(final String login) {
		
		if( login == null ) {
			throw new IllegalArgumentException(); // TODO: colocar bundle pelo eclipse na app
		}
		
		final String query = "select u from Usuario u where u.login = :login ";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", login);		
		
		return usuarioDao.pesquisarObjetoPorParametro(query, params);
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
}