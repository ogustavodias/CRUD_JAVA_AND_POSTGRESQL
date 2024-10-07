package main.domain;

import java.math.BigDecimal;

public class Product {

  private String codigo;
  private String nome;
  private BigDecimal preco;

  public Product(String codigo, String nome, BigDecimal preco) {
    this.codigo = codigo;
    this.nome = nome;
    this.preco = preco;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public String getNome() {
    return this.nome;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

}
