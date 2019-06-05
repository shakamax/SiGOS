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
class LogOS {
    private Funcionario funcionario;
    private Date DataHora;
    private String descricao;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataHora() {
        return DataHora;
    }

    public void setDataHora(Date DataHora) {
        this.DataHora = DataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
