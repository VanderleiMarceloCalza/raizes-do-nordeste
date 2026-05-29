package com.loja.bakend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido {
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Filial filial;

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
}