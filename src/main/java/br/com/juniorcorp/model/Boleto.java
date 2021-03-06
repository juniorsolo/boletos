package br.com.juniorcorp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Entity(name="boleto")
public class Boleto implements Serializable{
	
	private static final long serialVersionUID = 8661116577363423092L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@NotBlank
	@Column(length=15)
	private String cpf;
	
	@Column(length=30)
	private String email;
	
	@NonNull
	@Column(length=60)
	private String nome;
	
	private Integer totalParcelas;
	
	@Column(length=150)
	private String descricao;
	
	@NonNull
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	@Temporal(value=TemporalType.DATE)
	private Date dataPrimeiraParcela;
	
	@Temporal(value=TemporalType.DATE)
	@CreationTimestamp
	private Date dataCriacao;
	
	@Column(length=14)
	private String telResidencial;
	
	@Column(length=15)
	private String celular;
	
	@OneToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE},	orphanRemoval=true )	
	private List<Parcela> parcelas;
	
	@Transient
	private BigDecimal valor;
	
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
	public List<Parcela> getParcelas() {
		return parcelas;
	}
	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getTotalParcelas() {
		return totalParcelas;
	}
	public void setTotalParcelas(Integer totalParcelas) {
		this.totalParcelas = totalParcelas;
	}
	
	
}
