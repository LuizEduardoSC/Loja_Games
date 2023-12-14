package com.generation.lojagames.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto") // CREATE TABLE tb_produto
public class Produto {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;
	
	@Column(length = 100)
	@NotBlank(message = "O Atributo nome é obrigatório!")
	@Size(min = 5, max = 100, message = "O Atributo nome deve conter no mínimo 5 e no máximo 100 caracteres.")
	private String nome;
	
	@Column(length = 1000)
	@NotBlank(message = "O Atributo descricao é obrigatório!")
	@Size(min = 10, max = 1000, message = "O Atributo descricao deve conter no mínimo 10 e no máximo 1000 caracteres.")
	private String descricao;
	
	@Column(length = 1000)
	@NotBlank(message = "O Atributo plataforma é obrigatório!")
	@Size(min = 10, max = 1000, message = "O Atributo plataforma deve conter no mínimo 10 e no máximo 1000 caracteres.")
	private String plataforma;
	
	@NotNull(message = "Digite a quantidade!")
	private Integer quantidade;
	
	@NotNull(message = "Digite o preço!")
	@DecimalMin(value = "0.1", inclusive = true)
	private double preco;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	
}
