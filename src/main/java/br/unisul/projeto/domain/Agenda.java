package br.unisul.projeto.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {

	private static final long serialVersionUID = -9049205727786748063L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer cd;
	
	@Column(name = "dtAgenda")
	private Date dtAgenda;
	
	@Column(name = "dsPendencia")
	private String dsPendencia;
	
	@Column(name = "dsHora")
	private Date dsHora;
	
	@Column(name = "dsLocal")
	private String dsLocal;

	public Integer getCd() {
		return cd;
	}

	public void setCd(Integer cd) {
		this.cd = cd;
	}

	public Date getDtAgenda() {
		return dtAgenda;
	}

	public void setDtAgenda(Date dtAgenda) {
		this.dtAgenda = dtAgenda;
	}

	public String getDsPendencia() {
		return dsPendencia;
	}

	public void setDsPendencia(String dsPendencia) {
		this.dsPendencia = dsPendencia;
	}

	public Date getDsHora() {
		return dsHora;
	}

	public void setDsHora(Date dsHora) {
		this.dsHora = dsHora;
	}

	public String getDsLocal() {
		return dsLocal;
	}

	public void setDsLocal(String dsLocal) {
		this.dsLocal = dsLocal;
	}

}
