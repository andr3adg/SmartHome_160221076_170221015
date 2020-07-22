/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atuadores;

import java.io.Serializable;
import projetopoo.Divisao;
import projetopoo.Tipo;

/**
 * Comunica com a consola e permite ligar qualquer aparelho que esteja lá ligado. 
 * Inclui um temporizador que permite desligar/ligar um aparelho ao fim de um pré-determinado tempo. 
 * A programação é feita através da consola por WiFi
 * @author Diogo Venancio e André Gonçalves
 */
public class Tomada extends Atuador implements Serializable
{
    public int temporizador;
    private boolean ligar;
    private final Divisao divisao;
    
    public Tomada(Divisao divisao, int temporizador) 
    {
        super("Tomada");
        this.temporizador = temporizador;
        this.ligar = true;
        this.divisao = divisao;
    }

    @Override
    public boolean isLigarTomada() 
    {
        return ligar;
    }

    @Override
    public void setLigarTomada(boolean ligar) 
    {
        this.ligar = ligar;
    }
    
    @Override
    public int getTemporizador() 
    {
        return temporizador;
    }

    @Override
    public void setTemporizador(int temporizador) 
    {
        this.temporizador = temporizador;
    }

    @Override
    public Tipo getTipoEquipamento() 
    {
        return super.getTipo();
    }

    @Override
    public boolean infravermelhos() 
    {
        return false;
    }

    @Override
    public boolean haveWifi() 
    {
        return true;
    }

    @Override
    public boolean isOn() 
    {
        return true;
    }

    @Override
    public boolean needEnergia() 
    {
        return true;
    }

    @Override
    public String getNomeEquip() 
    {
        return super.getNome();
    }

    @Override
    public Divisao getDivisao() 
    {
        return divisao;
    }

    @Override
    public double getTemp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTemp(double temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLigar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLigar(boolean ligar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLuz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLuz(int luz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getVolume() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVolume(int volume) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFlash() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFlash(boolean flash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tirarFoto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tirarVideo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
