package br.unisul.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.unisul.projeto.domain.Advogado;
import br.unisul.projeto.util.HibernateUtil;

public class AdvogadoDao {

	public void excluir(Advogado domain) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(domain);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void alterar(Advogado domain) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.merge(domain);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public Advogado salvar(Advogado domain) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		Advogado novo = null;
		try {
			t = sessao.beginTransaction();
			novo = (Advogado) sessao.merge(domain);
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			throw (e);
		} finally {
			sessao.close();
		}
		return novo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Advogado> listar(Integer cd){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Advogado.class);
			consulta.add(Restrictions.eq("cd", cd));
			List<Advogado> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Advogado> listarPorAdvogado(Integer cdAdvogado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Advogado.class);
		//	consulta.add(Restrictions.eq("cd",cdAdvogado));
			List<Advogado> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}

}
