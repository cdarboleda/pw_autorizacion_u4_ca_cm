package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Usuario buscarPorUsername(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT u FROM Usuario u WHERE u.username = :username";
		
		TypedQuery<Usuario> myQuery = this.entityManager.createQuery(sql, Usuario.class);
		myQuery.setParameter("username", username);
		return myQuery.getSingleResult();
	}

}
