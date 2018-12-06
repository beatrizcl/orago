package br.unisul.projeto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 479569714251227504L;

		@Id
		@GeneratedValue(strategy= GenerationType.AUTO)
		private Integer cd;
		
		@Column(name = "nmcliente")
		private String nmcliente;
		
		@Column(name = "cpfcliente")
		private String cpfcliente;
		
		@Column(name = "telefonecliente")
		private String telefonecliente;
		
		@Column(name = "emailcliente")
		private String emailcliente;
		
		@Column(name = "ufcliente")
		private String ufcliente;
		
		@Column(name = "bairrocliente")
		private String bairrocliente;
		
		@Column(name = "cepcliente")
		private String cepcliente;
		
		@Column(name = "cidadecliente")
		private String cidadecliente;


		public Integer getCd() {
			return cd;
		}

		public void setCd(Integer cd) {
			this.cd = cd;
		}

		public String getNmcliente() {
			return nmcliente;
		}

		public void setNmcliente(String nmcliente) {
			this.nmcliente = nmcliente;
		}

		public String getCpfcliente() {
			return cpfcliente;
		}

		public void setCpfcliente(String cpfcliente) {
			this.cpfcliente = cpfcliente;
		}

		public String getTelefonecliente() {
			return telefonecliente;
		}

		public void setTelefonecliente(String telefonecliente) {
			this.telefonecliente = telefonecliente;
		}

		public String getEmailcliente() {
			return emailcliente;
		}

		public void setEmailcliente(String emailcliente) {
			this.emailcliente = emailcliente;
		}

		public String getUfcliente() {
			return ufcliente;
		}

		public void setUfcliente(String ufcliente) {
			this.ufcliente = ufcliente;
		}

		public String getBairrocliente() {
			return bairrocliente;
		}

		public void setBairrocliente(String bairrocliente) {
			this.bairrocliente = bairrocliente;
		}

		public String getCepcliente() {
			return cepcliente;
		}

		public void setCepcliente(String cepcliente) {
			this.cepcliente = cepcliente;
		}

		public String getCidadecliente() {
			return cidadecliente;
		}

		public void setCidadecliente(String cidadecliente) {
			this.cidadecliente = cidadecliente;
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
			Cliente other = (Cliente) obj;
			if (cd == null) {
				if (other.cd != null)
					return false;
			} else if (!cd.equals(other.cd))
				return false;
			return true;
		}

}
