/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

import java.util.List;


/**
 *
 * @author 01678797154
 */
public class Produto {

    /**
     * @return the categoria
     */
    private String nome;
    private double Valor;
    private int Quantidade;
    private String descricao;
    private String codigo;
    private List<Categoria> categoria;
    private String categorias; 
    private String id_cat; 
    private String localizacao;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getId_cat() {
        return id_cat;
    }

    public void setId_cat(String id_cat) {
        this.id_cat = id_cat;
    }


    
    
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the Valor
     */
    public double getValor() {
        return Valor;
    }

    /**
     * @param Valor the Valor to set
     */
    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    /**
     * @return the Quantidade
     */
    public int getQuantidade() {
        return Quantidade;
    }

    /**
     * @param Quantidade the Quantidade to set
     */
    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param Descricao the descricao to set
     */
    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    
    
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    
//    public String CatString() {
//        
//        String cat = "";
//        
//        int Tamanho = this.getCategoria().length;
//        int a = 0;
//        while (a < Tamanho){
//            cat = "" + getCategoria()[a].getDescricao() + ", ";
//            
//            a++;
//        }
//        
//        return cat;
//    }

    public void setCategoria(List<Categoria> categorias) {
        
        this.categoria = categorias;
    }
    
    public List<Categoria> getCategoria() {
        return categoria;
    }

    
    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }
    
    
    
    
    public void Clear () {
        this.ID = 0;
        this.Quantidade = 0;
        this.Valor = 0;
        this.categoria = null;
        this.codigo = "";
        this.descricao = "";
        this.localizacao = "";
        this.nome = "";
        
    }
    
}
