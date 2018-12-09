package br.unisul.projeto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.unisul.projeto.dao.AdvogadoDao;
import br.unisul.projeto.domain.Advogado;

@ManagedBean(name = "advogadoBack")
@ViewScoped
public class AdvogadoBack {

	Advogado domain = new Advogado();
	Advogado advogadoEdita = new Advogado();
	List<Advogado> advogadoList = new ArrayList<Advogado>();
		
	public void cadastra() {
		AdvogadoDao dao = new AdvogadoDao();
		dao.salvar(domain);
		domain = new Advogado();
		listar();
	}
	
	

	public void excluir(ActionEvent evt) {
		domain = (Advogado) evt.getComponent().getAttributes().get("advogadoEdita");
		AdvogadoDao dao = new AdvogadoDao();
		dao.excluir(domain);
		listar();
	}

	public void preEdita(ActionEvent evt) {
		domain = (Advogado) evt.getComponent().getAttributes().get("advogadoEdita");

	}
	
	public void edita(ActionEvent evt) {
		
		AdvogadoDao dao = new AdvogadoDao();

		dao.alterar(domain);
	}
	
	
	@PostConstruct
	public void listar(){
		try{
			AdvogadoDao dao = new AdvogadoDao();
			advogadoList = (ArrayList<Advogado>)dao.listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar advogados");
			e.printStackTrace();
		}
	}

	public Advogado getDomain() {
		return domain;
	}

	public void setDomain(Advogado domain) {
		this.domain = domain;
	}

	public List<Advogado> getAdvogadoList() {
		return advogadoList;
	}

	public void setAdvogadoList(List<Advogado> advogadoList) {
		this.advogadoList = advogadoList;
	}

	public Advogado getAdvogadoEdita() {
		return advogadoEdita;
	}

	public void setAdvogadoEdita(Advogado advogadoEdita) {
		this.advogadoEdita = advogadoEdita;
	}

}
