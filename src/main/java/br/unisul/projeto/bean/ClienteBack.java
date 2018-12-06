package br.unisul.projeto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.unisul.projeto.dao.ClienteDao;
import br.unisul.projeto.domain.Advogado;
import br.unisul.projeto.domain.Cliente;
import br.unisul.projeto.util.SessionContext;


@ManagedBean(name = "clienteBack")
@ViewScoped
public class ClienteBack {

	Cliente domain = new Cliente();
	Cliente clienteEdita = new Cliente();
	List<Cliente> clienteList = new ArrayList<Cliente>();

	public void cadastra() {
		ClienteDao dao = new ClienteDao();
		dao.salvar(domain);
		Messages.addGlobalInfo("Cliente cadastrado com sucesso");
		domain = new Cliente();
		listar();
	}
	
	public void preEdita(ActionEvent evt) {
		domain = (Cliente) evt.getComponent().getAttributes().get("clienteEdita");
	}
	
	public void excluir(ActionEvent evt) {
		domain = (Cliente) evt.getComponent().getAttributes().get("clienteEdita");
		ClienteDao dao = new ClienteDao();
		dao.excluir(domain);
		listar();
	}

	public void salva() {
		ClienteDao dao = new ClienteDao();
		dao.salvar(domain);
	}

	public void edita(ActionEvent evt) {
		ClienteDao dao = new ClienteDao(); 
		dao.alterar(domain);
	}

	@PostConstruct
	public void listar() {
		try{
			Advogado log = (Advogado)SessionContext.getInstance().getAttribute("usuarioLogado");
			if (log==null) {
				return;
			}
			ClienteDao dao = new ClienteDao();
			clienteList = (ArrayList<Cliente>)dao.listarPorCliente();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar clientes");
			e.printStackTrace();
		}
	}
	
	public Cliente getDomain() {
		return domain;
	}
	
	public void setDomain(Cliente domain) {
		this.domain = domain;
	}
	
	public Cliente getClienteEdita() {
		return clienteEdita;
	}
	
	public void setClienteEdita(Cliente clienteEdita) {
		this.clienteEdita = clienteEdita;
	}
	
	public List<Cliente> getClienteList() {
		return clienteList;
	}
	
	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}
	
}
