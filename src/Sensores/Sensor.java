/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensores;

import java.io.Serializable;
import projetopoo.Divisao;
import projetopoo.Equipamento;
import projetopoo.Tipo;

/**
 *
 * @author Diogo Venancio e André Gonçalves
 */
public abstract class Sensor extends Equipamento implements Serializable
{
    public Sensor(String nome) 
    {
        super(nome, Tipo.SENSORES);
    }

    @Override
    public String toString()
    {
        return super.toString() + "\tSensor \n";
    }
    
    public abstract int getLuz();
    public abstract void setLuz(int luz);
    public abstract boolean isMovimento();
    public abstract void setMovimento(boolean movimento);
    public abstract double getTemp();
    public abstract void setTemp(double temp);
    public abstract boolean isAberta();
    public abstract void setAberta(boolean aberta);
    
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
