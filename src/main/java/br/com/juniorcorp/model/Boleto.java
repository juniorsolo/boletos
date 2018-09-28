package br.com.juniorcorp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity(name="boleto")
public class Boleto {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@NotBlank
	@Max(15)
	@Column(length=15)
	private String cpf;
	
	@Column(length=30)
	private String email;
	
	@NonNull
	@Max(60)
	@Column(length=60)
	private String nome;
	
	@NonNull
	@Positive
	@Column(length=2)
	@Min(1)
	@Max(50)
	private Integer numParcelas;
	
	@Max(150)
	@Column(length=150)
	private String descricao;
	
	@NonNull
	@Temporal(value=TemporalType.DATE)
	private Date dataPrimeiraParcela;
	
	@Temporal(value=TemporalType.DATE)
	@CreationTimestamp
	private Date dataCriacao;
	
	@Max(14)
	@Column(length=14)
	private String telResidencial;
	
	@Max(15)
	@Column(length=15)
	private String celular;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNumParcelas() {
		return numParcelas;
	}
	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataPrimeiraParcela() {
		return dataPrimeiraParcela;
	}
	public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
		this.dataPrimeiraParcela = dataPrimeiraParcela;
	}
	public String getTelResidencial() {
		return telResidencial;
	}
	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
}
