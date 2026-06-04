package com.loja.bakend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido {
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Filial filial;
	private String numeroSenha;
	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ItemPedido> itens;

	

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String status;
	private Double valorTotal;
	private LocalDateTime dataPedido;
	@Enumerated(EnumType.STRING)
	private CanalPedido canalPedido;
	private String codigoPagamento;
	private String statusPagamento;
	private LocalDateTime dataPagamento;
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Pedido() {
		this.dataPedido = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public List<ItemPedido> getItens() {

	    return itens;
	}

	public void setItens(
	        List<ItemPedido> itens) {

	    this.itens = itens;
	}

	public String getCodigoPagamento() {
	    return codigoPagamento;
	}

	public void setCodigoPagamento(String codigoPagamento) {
	    this.codigoPagamento = codigoPagamento;
	}

	public String getStatusPagamento() {
	    return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
	    this.statusPagamento = statusPagamento;
	}

	public LocalDateTime getDataPagamento() {
	    return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
	    this.dataPagamento = dataPagamento;
	}
	public CanalPedido getCanalPedido() {
	    return canalPedido;
	}

	public void setCanalPedido(CanalPedido canalPedido) {
	    this.canalPedido = canalPedido;
	}

	public String getNumeroSenha() {
		return numeroSenha;
	}

	public void setNumeroSenha(String numeroSenha) {
		this.numeroSenha = numeroSenha;
	}
	
}