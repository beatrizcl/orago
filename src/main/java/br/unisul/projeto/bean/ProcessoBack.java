package br.unisul.projeto.bean;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.omnifaces.util.Messages;

import br.unisul.projeto.dao.AdvogadoDao;
import br.unisul.projeto.dao.ClienteDao;
import br.unisul.projeto.dao.ProcessoDao;
import br.unisul.projeto.domain.Advogado;
import br.unisul.projeto.domain.Cliente;
import br.unisul.projeto.domain.Processo;
import br.unisul.projeto.util.SessionContext;

@ManagedBean(name = "processoBack")
@ViewScoped
public class ProcessoBack {
	
	Processo domain = new Processo();
	Processo processoEdita = new Processo();
	List<Processo> processoList = new ArrayList<Processo>();
	List<Cliente> clienteList = new ArrayList<Cliente>();
	List<Advogado> advogadoList = new ArrayList<Advogado>();
	List<SelectItem> clienteSelectList = new ArrayList<SelectItem>();
	List<SelectItem> advogadoSelectList = new ArrayList<SelectItem>();
	
	public void cadastra() {
		ProcessoDao dao = new ProcessoDao();
//		Processo userImg = dao.salvar(domain);
//		Path origem = Paths.get(domain.getPathTemp());
//		Path destino = Paths.get("C://Users/ART3MIS/Documents/evidencias/"+ userImg.getCd() +".png");
//		try {
//			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
//		} catch (Exception e) {
//			Messages.addGlobalInfo("Erro");
//		}
		dao.salvar(domain);
		domain = new Processo();
		listar();
	}

	public void excluir(ActionEvent evt) {
		domain = (Processo) evt.getComponent().getAttributes().get("processoEdita");
		ProcessoDao dao = new  ProcessoDao();
		dao.excluir(domain);
		listar();
	}
		
	@PostConstruct
	public void listar() {
		try{
			ProcessoDao dao = new ProcessoDao();
			processoList = dao.listarPorProcesso();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar processos");
			e.printStackTrace();
		}
		listarCliente();
		listarAdvogado();
	}
	
	@PostConstruct
	public void listarCliente() {
		try {
			ClienteDao dao = new ClienteDao();
			clienteList = dao.listarPorCliente();
			clienteSelectList = new ArrayList<SelectItem>(clienteList.size());

			for (Cliente cliente : clienteList) {
			    clienteSelectList.add(new SelectItem(cliente.getCd(), cliente.getNmcliente()));
			}
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar cliente");
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listarAdvogado(){
		try {
			Advogado log = (Advogado)SessionContext.getInstance().getAttribute("usuarioLogado");
			if (log==null) {
				return;
			}
			AdvogadoDao dao = new AdvogadoDao();
			advogadoList = dao.listarPorAdvogado(log.getCd());
			advogadoSelectList = new ArrayList<SelectItem>(advogadoList.size());

			for (Advogado advogado : advogadoList) {
			    advogadoSelectList.add(new SelectItem(advogado.getCd(), advogado.getNmadvogado()));
			}

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar advogado");
			e.printStackTrace();
		}
	}

	public Processo getDomain() {
		return domain;
	}

	public void setDomain(Processo domain) {
		this.domain = domain;
	}

	public Processo getProcessoEdita() {
		return processoEdita;
	}

	public void setProcessoEdita(Processo processoEdita) {
		this.processoEdita = processoEdita;
	}

	public List<Processo> getProcessoList() {
		return processoList;
	}

	public void setProcessoList(List<Processo> processoList) {
		this.processoList = processoList;
	}

	public List<Cliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	public List<Advogado> getAdvogadoList() {
		return advogadoList;
	}

	public void setAdvogadoList(List<Advogado> advogadoList) {
		this.advogadoList = advogadoList;
	}

	public void preEdita(ActionEvent evt) {
		domain = (Processo) evt.getComponent().getAttributes().get("processoEdita");
	}
	
	public List<SelectItem> getClienteSelectList() {
		return clienteSelectList;
	}

	public void setClienteSelectList(List<SelectItem> clienteSelectList) {
		this.clienteSelectList = clienteSelectList;
	}

	public List<SelectItem> getAdvogadoSelectList() {
		return advogadoSelectList;
	}

	public void setAdvogadoSelectList(List<SelectItem> advogadoSelectList) {
		this.advogadoSelectList = advogadoSelectList;
	}

	public void edita(ActionEvent evt) {
		ProcessoDao dao = new ProcessoDao();
		dao.alterar(domain);
	}
	
}
