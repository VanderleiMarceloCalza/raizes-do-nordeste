package com.loja.bakend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Cliente {
	
	@ManyToOne
	private Filial filial;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Boolean participaFidelidade = false;
    
    public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	private String nome;
    @Column(unique = true)
    private String email;
    
    private String senha;
    private String tipo;
    private Integer pontos = 0;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public String getTipo() {
		
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getPontos() {
	    return pontos;
	}

	public void setPontos(Integer pontos) {
	    this.pontos = pontos;
	}

	public Boolean getParticipaFidelidade() {
		return participaFidelidade;
	}

	public void setParticipaFidelidade(Boolean participaFidelidade) {
		this.participaFidelidade = participaFidelidade;
	}
}