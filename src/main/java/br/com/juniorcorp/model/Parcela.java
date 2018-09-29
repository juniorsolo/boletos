package br.com.juniorcorp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parcela")
public class Parcela {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numparcela", nullable=false)
	private Integer numeroParcela;
	
	@Column(name = "dtvencimento", nullable= false)
	private Date dataVencimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable= false)
	private StatusParcela status;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public StatusParcela getStatus() {
		return status;
	}

	public void setStatus(StatusParcela status) {
		this.status = status;
	}

	
}
