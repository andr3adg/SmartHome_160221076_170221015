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
 * Permite detetar se uma porta foi ou não aberta
 * @author Diogo Venancio e André Gonçalves
 */
public class Porta extends Sensor implements Serializable
{
    private boolean aberta;
    private final Divisao divisao;
    
    public Porta(Divisao divisao) 
    {
        super("Porta aberta");
        this.aberta = aberta;
        this.divisao = divisao;
    }

    @Override
    public Divisao getDivisao() {
        return divisao;
    }
    
    @Override
    public boolean isAberta() {
        return aberta;
    }

    @Override
    public void setAberta(boolean aberta) {
        this.aberta = aberta;
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
        return false;
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
                                  "\t\tEstá aberta? " + isOn() + "\n";
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
    public double getTemp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTemp(double temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
