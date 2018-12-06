package br.unisul.projeto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "processo")
public class Processo implements Serializable {

	private static final long serialVersionUID = 2788588414734431610L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer cd;

	@Column(name = "nrprocesso")
	private Integer nrprocesso;

	@Column(name = "area")
	private String area;
	
	@Column(name = "descprocesso")
	private String descprocesso;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Advogado advogado;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Cliente cliente;
	
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
	
	public Integer getNrprocesso() {
		return nrprocesso;
	}

	public void setNrprocesso(Integer nrprocesso) {
		this.nrprocesso = nrprocesso;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescprocesso() {
		return descprocesso;
	}

	public void setDescprocesso(String descprocesso) {
		this.descprocesso = descprocesso;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Processo other = (Processo) obj;
		if (cd == null) {
			if (other.cd != null)
				return false;
		} else if (!cd.equals(other.cd))
			return false;
		return true;
	}

}
