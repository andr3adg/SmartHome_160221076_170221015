/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensores;

import java.io.Serializable;
import projetopoo.Divisao;
import projetopoo.Tipo;

/**
 * Regista a temperatura da divisão onde está colocado
 * @author Diogo Venancio e André Gonçalves
 */
public class Temperatura extends Sensor implements Serializable
{
    private final boolean energia;
    private final Divisao divisao;

    public Temperatura(Divisao divisao) 
    {
        super("Temperatura");
        this.energia = true;
        this.divisao = divisao;
    }

    @Override
    public double getTemp() 
    {
        if(divisao.getTemp()==0)
        {
            divisao.setTemp(23);
            return divisao.getTemp();
        }else
        {
            return divisao.getTemp();
        }
    }

    @Override
    public void setTemp(double temp) 
    {
        divisao.setTemp(temp);
    }

    @Override
    public Divisao getDivisao() 
    {
        return divisao;
    }
    
    @Override
    public Tipo getTipoEquipamento() 
    {
        return super.getTipo();
    }

    @Override
    public boolean haveWifi() 
    {
        return false;
    }

    @Override
    public boolean isOn() 
    {
        return true;
    }

    @Override
    public boolean needEnergia()
    {
        return energia;
    }
    
    @Override
    public boolean infravermelhos() 
    {
        return false;
    }
    
    @Override
    public String getNomeEquip()
    {
        return super.getNome();
    }
    
    @Override
    public String toString() 
    {        
        return super.toString() + "\t\t" + getNome() + "\n" + 
               "\t\tTemperatura: " + getTemp() + "ºC\n" +
               "\t\tEstá Ligado: " + isOn() + "\n" +
               "\t\tNecessita de Energia: " + needEnergia() + "\n";
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
    public boolean isMovimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMovimento(boolean movimento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAberta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAberta(boolean aberta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
