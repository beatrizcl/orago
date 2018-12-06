package br.unisul.projeto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "advogado")
public class Advogado implements Serializable {

	private static final long serialVersionUID = 1919071954860217861L;

	// nome, email, oab, area de atuação, login e senha

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer cd;
	
	@Column(name = "nmadvogado")
	private String nmadvogado;
	
	@Column(name = "cdoab")
	private String cdoab;
	
	@Column(name = "emailadv")
	private String	emailadv;
	
	@Column(name = "senhaadv")
	private String	senhaadv;
	
	//Serve para utilizar no que irá ficar em memória, não ira para o banco de dados
		@Transient
		private String pathTemp;
		
		
		public String getPathTemp() {
			return pathTemp;
		}
		public void setPathTemp(String pathTemp) {
			this.pathTemp = pathTemp;

		}

	public Integer getCd() {
		return cd;
	}

	public void setCd(Integer cd) {
		this.cd = cd;
	}

	public String getNmadvogado() {
		return nmadvogado;
	}

	public void setNmadvogado(String nmadvogado) {
		this.nmadvogado = nmadvogado;
	}
	

	public String getCdoab() {
		return cdoab;
	}

	public void setCdoab(String cdoab) {
		this.cdoab = cdoab;
	}

	public String getEmailadv() {
		return emailadv;
	}

	public void setEmailadv(String emailadv) {
		this.emailadv = emailadv;
	}

	public String getSenhaadv() {
		return senhaadv;
	}

	public void setSenhaadv(String senhaadv) {
		this.senhaadv = senhaadv;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd == null) ? 0 : cd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Advogado other = (Advogado) obj;
		if (cd == null) {
			if (other.cd != null)
				return false;
		} else if (!cd.equals(other.cd))
			return false;
		return true;
	}

}
