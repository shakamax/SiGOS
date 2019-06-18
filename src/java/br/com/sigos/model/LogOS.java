/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Guilherme
 */
public class LogOs {
    
    private int idLog;
    private int idOs;
    private Date DataHora;
    private String descricao;


    
    
    
    /**
     * @return the DataHora
     */
    public Date getDataHora() {
        return DataHora;
    }

    /**
     * @param DataHora the DataHora to set
     */
    public void setDataHora(Date DataHora) {
        this.DataHora = DataHora;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
     * @return the idLog
     */
    public int getIdLog() {
        return idLog;
    }

    /**
     * @param idLog the idLog to set
     */
    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    
    
    
}
