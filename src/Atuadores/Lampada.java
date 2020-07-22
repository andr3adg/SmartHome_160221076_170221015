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
 * Regula a intensidade da luz, consoante um nível de intensidade entre 0 (desligada) e 20.
 * @author Diogo Venancio e André Gonçalves
 */
public class Lampada extends Atuador implements Serializable
{
    private int luz;
    private Divisao divisao;
    
    public Lampada(Divisao divisao,int luz) 
    {
        super("Lâmpada");
        
        this.luz = validateLuz(luz);
        this.divisao = divisao;
    }

    private int validateLuz(int luz) 
    {
        if (luz>20) 
        {
            throw new EquipamentoIllegalArgumentException(ErrorCode.LUZ_BETWEEN);
        }
        return luz;
    }
    
    @Override
    public Divisao getDivisao() 
    {
        return divisao;
    }
    
    @Override
    public int getLuz()
    {
        return luz;
    }

    @Override
    public void setLuz(int luz) 
    {
        if(luz>20 || luz<0)
            this.luz = 10;
        
        this.luz = luz;
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
        return false;
    }

    @Override
    public boolean isOn() 
    {
        return luz != 0;
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
               "\t\tNivel de Luminosidade: " + luz + "\n" +
               "\t\tEstá Ligado: " + isOn() + "\n";
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
