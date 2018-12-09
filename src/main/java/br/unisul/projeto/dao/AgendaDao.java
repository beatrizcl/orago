package br.unisul.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.unisul.projeto.domain.Agenda;
import br.unisul.projeto.util.HibernateUtil;

public class AgendaDao {

	public void excluir(Agenda domain) {
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

	public void alterar(Agenda domain) {
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

	public Agenda salvar(Agenda domain) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		Agenda novo = null;
		try {
			t = sessao.beginTransaction();
			novo = (Agenda) sessao.merge(domain);
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
	
	public List<Agenda> listar() {
		return listar(null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> listar(Integer cd){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Agenda.class);
			if (cd != null) {
				consulta.add(Restrictions.eq("cd", cd));
			}
			List<Agenda> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}
	
}
