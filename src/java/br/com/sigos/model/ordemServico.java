/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

import java.util.Date;
import java.util.List;


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
    private List<Servico> servicos;
    private List<Produto> produtos;
    private List<LogOs> logOS;
    private LogOs log;
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


    public ListaEquipamento getListaEquipamentos() {
        return listaEquipamentos;
    }

    public void setListaEquipamentos(ListaEquipamento listaEquipamentos) {
        this.listaEquipamentos = listaEquipamentos;
    }


    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<LogOs> getLogOS() {
        return logOS;
    }

    public void setLogOS(List<LogOs> logOS) {
        this.logOS = logOS;
    }

    /**
     * @return the log
     */
    public LogOs getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(LogOs log) {
        this.log = log;
    }
    
    
    
    
   
    
    
}
