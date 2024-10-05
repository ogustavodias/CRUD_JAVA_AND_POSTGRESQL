package main.domain;

public class Customer {

  private String cpf;
  private String nome;

  public Customer(String cpf, String nome) {
    this.cpf = cpf;
    this.nome = nome;
  }

  public String getCpf() {
    return this.cpf;
  }

  public String getNome() {
    return this.nome;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}
