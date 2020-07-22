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
 * Mede a luz ambiente de uma divisão
 * @author Diogo Venancio e André Gonçalves
 */
public class Luminosidade extends Sensor implements Serializable
{
    private final Divisao divisao;
    
    public Luminosidade(Divisao divisao) 
    {
        super("Luminosidade");
        this.divisao = divisao;
    }

    @Override
    public Divisao getDivisao() 
    {
        return divisao;
    }

    @Override
    public int getLuz() 
    {
        return divisao.getLuz();
    }

    @Override
    public void setLuz(int luz)
    {
        divisao.setLuz(luz);
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
    public String getNomeEquip()
    {
        return super.getNome();
    }

    @Override
    public String toString() 
    {
        return super.toString() + "\t\t" + getNome() + "\n" + 
               "\t\tNivel de Luminosidade: " + getLuz() + "%\n" +
               "\t\tEstá Ligado: " + isOn() + "\n" +
               "\t\tNecessita de Energia: " + needEnergia() + "\n";
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

    @Override
    public boolean isAberta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAberta(boolean aberta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
