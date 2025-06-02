package com.generation.projeto_final_bloco_02.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome do produto é obrigatório")
  private String nome;

  @NotBlank(message = "A descrição do produto é obrigatória")
  @Size(min = 10, max = 1000, message = "A descrição deve conter no minimo 10 e no maximo 1000 caracteres")
  private String descricao;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @NotNull(message = "O preço é obrigatório")
  @Positive(message = "O preço deve ser maior que zero")
  private BigDecimal preco;

  private Integer estoque = 0;

  private String fabricante;

  private boolean requer_receita = false;

  private String foto_produto;

  @ManyToOne
  @JsonIgnoreProperties("produtos") // muda
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

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public Integer getEstoque() {
    return estoque;
  }

  public void setEstoque(Integer estoque) {
    this.estoque = estoque;
  }

  public String getFabricante() {
    return fabricante;
  }

  public void setFabricante(String fabricante) {
    this.fabricante = fabricante;
  }

  public boolean isRequer_receita() {
    return requer_receita;
  }

  public void setRequer_receita(boolean requer_receita) {
    this.requer_receita = requer_receita;
  }

  public String getFoto_produto() {
    return foto_produto;
  }

  public void setFoto_produto(String foto_produto) {
    this.foto_produto = foto_produto;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  

}
