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
public class relatorioDash {
    private int qtdMax;
    private int qtdAbertas;
    private int qtdFechadas;
    private int qtdCanceladas;
    private double porcAbertas;
    private double porcFechadas;
    private double porcCanceladas;

    
    /**
     * @return the qtdMax
     */
    public int getQtdMax() {
        return qtdMax;
    }

    /**
     * @param qtdMax the qtdMax to set
     */
    public void setQtdMax(int qtdMax) {
        this.qtdMax = qtdMax;
    }

    /**
     * @return the qtdAbertas
     */
    public int getQtdAbertas() {
        return qtdAbertas;
    }

    /**
     * @param qtdAbertas the qtdAbertas to set
     */
    public void setQtdAbertas(int qtdAbertas) {
        this.qtdAbertas = qtdAbertas;
    }

    /**
     * @return the qtdFechadas
     */
    public int getQtdFechadas() {
        return qtdFechadas;
    }

    /**
     * @param qtdFechadas the qtdFechadas to set
     */
    public void setQtdFechadas(int qtdFechadas) {
        this.qtdFechadas = qtdFechadas;
    }

    /**
     * @return the qtdCanceladas
     */
    public int getQtdCanceladas() {
        return qtdCanceladas;
    }

    /**
     * @param qtdCanceladas the qtdCanceladas to set
     */
    public void setQtdCanceladas(int qtdCanceladas) {
        this.qtdCanceladas = qtdCanceladas;
    }

    /**
     * @return the porcAbertas
     */
    public double getPorcAbertas() {
        return porcAbertas;
    }

    /**
     * @param porcAbertas the porcAbertas to set
     */
    public void setPorcAbertas(double porcAbertas) {
        this.porcAbertas = porcAbertas;
    }

    /**
     * @return the porcFechadas
     */
    public double getPorcFechadas() {
        return porcFechadas;
    }

    /**
     * @param porcFechadas the porcFechadas to set
     */
    public void setPorcFechadas(double porcFechadas) {
        this.porcFechadas = porcFechadas;
    }

    /**
     * @return the porcCanceladas
     */
    public double getPorcCanceladas() {
        return porcCanceladas;
    }

    /**
     * @param porcCanceladas the porcCanceladas to set
     */
    public void setPorcCanceladas(double porcCanceladas) {
        this.porcCanceladas = porcCanceladas;
    }
    

    
    public void calcularPorcentagem(){
        
        this.porcAbertas = ((this.qtdAbertas * 100) / this.qtdMax);
        this.porcCanceladas = ((this.qtdCanceladas * 100) / this.qtdMax);
        this.porcFechadas = ((this.qtdFechadas * 100) / this.qtdMax);
        
    }
    
    
    
}
