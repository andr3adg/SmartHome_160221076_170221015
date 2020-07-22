/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atuadores;

import Exceptions.EquipamentoIllegalArgumentException;
import Exceptions.ErrorCode;
import java.io.Serializable;
import projetopoo.Divisao;
import projetopoo.Tipo;

/**
 * Regula a temperatura de uma divisão (entre 16º a 28º)
 * @author Diogo Venancio e André Gonçalves
 */
public class ArCondicionado extends Atuador implements Serializable
{    
    private double temp;
    private boolean ligar;
    private final Divisao divisao;
    
    public ArCondicionado(Divisao divisao,double temp) 
    {
        super("Ar Condicionado");
        
        this.temp = validateTemperatura(temp);
        this.ligar = true;
        this.divisao = divisao;
    }

    private double validateTemperatura(double temp) 
    {
        if (temp<16 && temp>28) 
        {
            throw new EquipamentoIllegalArgumentException(ErrorCode.TEMP_BETWEEN);
        }
        return temp;
    }
    
    @Override
    public Divisao getDivisao() 
    {
        return divisao;
    }
    
    @Override
    public double getTemp() 
    {
        return temp;
    }

    @Override
    public void setTemp(double temp)
    {
        if(temp<16 && temp>28)
            this.temp = 23.0;
        this.temp = temp;   
    }

    @Override
    public boolean isLigar() 
    {
        return ligar;
    }

    @Override
    public void setLigar(boolean ligar) 
    {
        this.ligar = ligar;
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
    public String toString() 
    {
        return super.toString() + "\t\t" + getNome() + "\n" + 
               "\t\tTemperatura: " + temp + "ºC\n" +
               "\t\tEstá Ligado: " + isOn() + "\n";
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
    public int getTemporizador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTemporizador(int temporizador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLigarTomada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLigarTomada(boolean ligar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tirarVideo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
