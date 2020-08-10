#Author: Jessica Menyon jsmenyon@gmail.com
Feature: Site Shoestock
  Eu como usuario quero validar o site Shoestock

  Background: Acessar o site
    Given que eu esteja no "https://www.shoestock.com.br/" pelo "CHROME"

  Scenario: Realizar a busca do produto
    When pesquisar "Sandália"
    Then confirmo "Sandália"

  Scenario: Incluir produto no carrinho
    When pesquisar "Bolsa"
    And escolher produto 
    And coloco produto no carrinho
    Then valido produto no carrinho
