package br.unisul.projeto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.unisul.projeto.dao.LoginDao;
import br.unisul.projeto.domain.Advogado;
import br.unisul.projeto.util.SessionContext;

@ManagedBean(name = "loginBack")
@ViewScoped
public class LoginBack {
	
	private String login;
	private String senha;
	private Advogado advogado;
	
	
	public String doLogin(){
		try {
			LoginDao dao = new LoginDao();
			advogado = dao.buscarLoginSenha(login, senha);
			if(advogado != null){
				SessionContext.getInstance().setAttribute("usuarioLogado", advogado);
				return "/pages/agenda.xhtml?faces-redirect=true";
			}else{
				FacesContext.getCurrentInstance().validationFailed();
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	 public String logout() {
	      SessionContext.getInstance().encerrarSessao();
	      return "/root/login.xhtml?faces-redirect=true"; 
	    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}


}
