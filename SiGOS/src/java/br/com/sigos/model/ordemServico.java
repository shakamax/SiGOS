/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

import java.util.Date;


/**
 *
 * @author 03728827142
 */
public class ordemServico {
    
    private int numOS;
    private Cliente cliente;
    private Date dtAbertura;
    private Date dtFechamento;
    private Date prazoEntrega;
    private Servico[] servicos;
    private Produto[] produtos;
    private LogOS[] logOS;
    private String garantia;
    private ListaEquipamento listaEquipamentos;
    private boolean status;

    public int getNumOS() {
        return numOS;
    }

    public void setNumOS(int numOS) {
        this.numOS = numOS;
    }

    public Date getDtAbertura() {
        return dtAbertura;
    }

    public void setDtAbertura(Date dtAbertura) {
        this.dtAbertura = dtAbertura;
    }

    public Date getDtFechamento() {
        return dtFechamento;
    }

    public void setDtFechamento(Date dtFechamento) {
        this.dtFechamento = dtFechamento;
    }

    public Date getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(Date prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico[] getServicos() {
        return servicos;
    }

    public void setServicos(Servico[] servicos) {
        this.servicos = servicos;
    }

    public Produto[] getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto[] produtos) {
        this.produtos = produtos;
    }

    public LogOS[] getLogOS() {
        return logOS;
    }

    public void setLogOS(LogOS[] logOS) {
        this.logOS = logOS;
    }

    public ListaEquipamento getListaEquipamentos() {
        return listaEquipamentos;
    }

    public void setListaEquipamentos(ListaEquipamento listaEquipamentos) {
        this.listaEquipamentos = listaEquipamentos;
    }

    public void setProdutos(Produto prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
   
    
    
}
