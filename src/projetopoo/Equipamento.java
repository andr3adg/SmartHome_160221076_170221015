/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.io.Serializable;

/**
 * A classe equipamento guarda as informações relativas aos equipamentos existentes na casa, nomeadamente o seu nome e tipo de equipamento.
 * @author Diogo Venancio e André Gonçalves
 */
public abstract class Equipamento implements Serializable
{
    private String nome;
    private final Tipo tipo;

    public Equipamento(String nome,Tipo tipo) 
    {
        if(!nome.isEmpty() && !nome.equals(""))
            this.nome = nome;
        
        this.tipo = tipo;
    }
    
    public String getNome() 
    {
        return nome;
    }

    public Tipo getTipo() 
    {
        return tipo;
    }    
    
    @Override
    public String toString() 
    {
        return "Equipamento: \n";
    }
    
    public abstract String getNomeEquip();
    public abstract Tipo getTipoEquipamento();
    public abstract Divisao getDivisao();
    public abstract boolean infravermelhos();
    public abstract boolean haveWifi();
    public abstract boolean isOn();
    public abstract boolean needEnergia();
    
    
    
    
    
    
}
