package br.unisul.projeto.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.unisul.projeto.domain.Advogado;
import br.unisul.projeto.util.HibernateUtil;

public class LoginDao {

	
	public Advogado buscarLoginSenha(String login, String senha){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Advogado.class);
			consulta.add(Restrictions.eq("emailadv", login));
			consulta.add(Restrictions.eq("senhaadv", senha));
			Advogado resultado = (Advogado)consulta.uniqueResult();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}
	
}
