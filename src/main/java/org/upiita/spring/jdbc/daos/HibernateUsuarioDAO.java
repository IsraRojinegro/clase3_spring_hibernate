package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

@Component("usuarioDAO")
public class HibernateUsuarioDAO implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Usuario buscaUsuarioPorId(Integer usuarioId) {
		Session session = sessionFactory.openSession();
		Usuario usuario;
		
		session.beginTransaction();
		//INICIA TRANSACTION
		usuario = (Usuario)session.get(Usuario.class, usuarioId);
		//obtenemos transaccion actual y guardamos cambios en la base
		session.getTransaction().commit();
		session.close();

		return usuario;
	}

	public void creaUsuario(Usuario usuario) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//INICIA TRANSACTION
		
		//session.save(usuario);
		session.saveOrUpdate(usuario);
		//obtenemos transaccion actual y guardamos cambios en la base
		session.getTransaction().commit();
		session.close();

	}

	public Usuario buscaPorEmailYPassword(String email, String password) {
		Session session = sessionFactory.openSession();
		Usuario usuario;
		
		session.beginTransaction();
		//formamos criterio de hibernate
		Criteria criteria = session.createCriteria(Usuario.class);
		
		criteria.add(Restrictions.and(Restrictions.eq("email", email), Restrictions.eq("password", password)));
		//criteria.add(Restrictions.or(Restrictions.eq("email", email), Restrictions.eq("password", password)));
		
		//CRITERIO QUE USA POR DEFAULT AND
		//criteria.add(Restrictions.eq("email", email));
		//criteria.add(Restrictions.eq("password", password));
		
		//obtiene un solo resultado, de lo contrario regresa NULL
		usuario = (Usuario) criteria.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return usuario;
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		Session session = sessionFactory.openSession();
		List<Usuario> listUsuario;
		
		session.beginTransaction();
		Criteria criterio = session.createCriteria(Usuario.class);
		criterio.add(Restrictions.like("nombre", nombre));
		
		listUsuario = criterio.list();
		
		session.getTransaction().commit();
		session.close();
		
		return listUsuario;
	}

}
