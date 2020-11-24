/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigos.model;

/**
 *
 * @author Guilherme
 */
public class Execucao {
    
    private int idOs;
    private int idServ;
    private boolean status;
    private boolean autorizado;
    

    
    
    
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
     * @return the idServ
     */
    public int getIdServ() {
        return idServ;
    }

    /**
     * @param idServ the idServ to set
     */
    public void setIdServ(int idServ) {
        this.idServ = idServ;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the autorizado
     */
    public boolean isAutorizado() {
        return autorizado;
    }

    /**
     * @param autorizado the autorizado to set
     */
    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
    
    
    
    
}
