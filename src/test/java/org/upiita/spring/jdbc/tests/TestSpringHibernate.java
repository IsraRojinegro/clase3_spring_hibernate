package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");
		//traemos el bean HibernateUsuarioDAO
		UsuarioDAO usuarioDAO = (UsuarioDAO)contexto.getBean("usuarioDAO");
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(3);
		usuario.setNombre("Isra");
		usuario.setEmail("isra@gmail.com");
		usuario.setPassword("123");
		
		
		usuarioDAO.creaUsuario(usuario);
		
		//usuario.setPassword("1234");
		//usuarioDAO.creaUsuario(usuario);
		
		System.out.println("datos guardados");
		
		System.out.println("Usuario encontrado: " + usuarioDAO.buscaUsuarioPorId(3));
		
		Usuario userCriterio = usuarioDAO.buscaPorEmailYPassword("isra@gmail.com", "123");
		System.out.println("Buscando por email y password....");
		System.out.println("Usario encontrado: " + userCriterio);
		
		//obtenemos lista usuario (con criterio like)
		List<Usuario> listUser = usuarioDAO.buscaPorNombre("%a%");
		System.out.println("Lista de Usuario: " + listUser);
		
	}

}
