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
 * Acionada a partir da consola, com o volume entre 0 e 10
 * @author Diogo Venancio e André Gonçalves
 */
public class Sirene extends Atuador implements Serializable     
{
    private int volume;
    private Divisao divisao;
    
    public Sirene(Divisao divisao,int volume) 
    {
        super("Sirene");
        
        this.volume = validateVolume(volume);
        this.divisao = divisao;
    }

    private int validateVolume(int volume) 
    {
        if (volume>10) 
        {
            throw new EquipamentoIllegalArgumentException(ErrorCode.SIREN_BETWEEN);
        }
        return volume;
    }    
    
    @Override
    public Divisao getDivisao()
    {
        return divisao;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
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
        return true;
    }

    @Override
    public boolean needEnergia() 
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
               "\t\tNivel de Som: " + volume + "\n";
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
