/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atuadores;

import java.io.Serializable;
import projetopoo.Divisao;
import projetopoo.Equipamento;
import projetopoo.Tipo;

/**
 *
 * @author Diogo Venancio e André Gonçalves
 */
public abstract class Atuador extends Equipamento implements Serializable
{
    public Atuador(String nome) 
    {
        super(nome, Tipo.ATUADORES);
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\tAtuador \n";
    }
    
    public abstract double getTemp();
    public abstract void setTemp(double temp);
    public abstract boolean isLigar();
    public abstract void setLigar(boolean ligar);
    public abstract int getLuz();
    public abstract void setLuz(int luz);
    public abstract int getVolume();
    public abstract void setVolume(int volume);
    public abstract boolean isFlash();
    public abstract void setFlash(boolean flash);
    public abstract void tirarFoto();
    public abstract void tirarVideo();
    public abstract int getTemporizador();
    public abstract void setTemporizador(int temporizador);
    public abstract boolean isLigarTomada();
    public abstract void setLigarTomada(boolean ligar);
    
    @Override
    public abstract String getNomeEquip();
    @Override
    public abstract Tipo getTipoEquipamento();
    @Override
    public abstract Divisao getDivisao();
    @Override
    public abstract boolean infravermelhos();
    @Override
    public abstract boolean haveWifi();
    @Override
    public abstract boolean isOn();
    @Override
    public abstract boolean needEnergia(); 
}
