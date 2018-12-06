package br.unisul.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.unisul.projeto.domain.Processo;
import br.unisul.projeto.util.HibernateUtil;

public class ProcessoDao {


	public void excluir(Processo domain) {
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

	public void alterar(Processo domain) {
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

	public Processo salvar(Processo domain) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		Processo novo = null;
		try {
			t = sessao.beginTransaction();
			novo = (Processo) sessao.merge(domain);
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
	public List<Processo> listarPorProcesso(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Processo.class);
			List<Processo> resultado = consulta.list();
			return resultado;
		} catch (Exception e) {
			throw(e);
		}finally{
			sessao.close();
		}
	}
	
}
