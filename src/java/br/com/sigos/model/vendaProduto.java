/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

import java.util.Date;

/**
 *
 * @author Guilherme
 */
public class vendaProduto {
    
    private int id;
    private Produto produto;
    private int idOs;
    private Date dtVenda;
    private double valor;
    private int qtd;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the idOs
     */
    public int getIdOs() {
        return idOs;
    }

    /**
     * @param idOs the idOs to set
     */
    public void setIdOs(int idOs) {
        this.idOs = idOs;
    }

    /**
     * @return the dtVenda
     */
    public Date getDtVenda() {
        return dtVenda;
    }

    /**
     * @param dtVenda the dtVenda to set
     */
    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    

    
}
