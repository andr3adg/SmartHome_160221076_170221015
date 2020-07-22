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
 * Deteta a presença ou movimento de corpos numa certa área. 
 * Informa a consola sempre que é detetado movimento
 * @author Diogo Venancio e André Gonçalves
 */
public class Movimento extends Sensor implements Serializable
{
    private boolean movimento;
    private final Divisao divisao;

    public Movimento(Divisao divisao) 
    {
        super("Movimento");
        this.movimento = movimento;
        this.divisao = divisao;
    }

    @Override
    public Divisao getDivisao() 
    {
        return divisao;
    }
    
    @Override
    public boolean isMovimento() 
    {
        return movimento;
    }

    @Override
    public void setMovimento(boolean movimento)
    {
        this.movimento = movimento;
    }  
    
    
    @Override
    public Tipo getTipoEquipamento()
    {
        return super.getTipo();
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
        return false;
    }
    
    @Override
    public boolean infravermelhos() 
    {
        return true;
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
               "\t\tDetetou Movimento: " + movimento + "\n" +
               "\t\tEstá Ligado: " + isOn() + "\n" +
               "\t\tWifi: " + haveWifi() + "\n" +
               "\t\tInfravermelhos: " + infravermelhos()+ "\n";
    }

    @Override
    public int getLuz() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLuz(int luz) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public boolean isAberta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAberta(boolean aberta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

