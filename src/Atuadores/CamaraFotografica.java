/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atuadores;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import projetopoo.Divisao;
import projetopoo.Tipo;

/**
 * Obtém uma fotografia quando existe uma deteção de movimento, de acordo com o formato configurado pelo cliente
 * @author Diogo Venancio e André Gonçalves
 */
public class CamaraFotografica extends Atuador implements Serializable
{
    private Divisao divisao;
    private boolean flash;
    
    public CamaraFotografica(Divisao divisao) 
    {
        super("Camara Fotografica");
        this.divisao = divisao;
        this.flash = false;
    }

    @Override
    public Divisao getDivisao()
    {
        return divisao;
    }

    public void setDivisao(Divisao divisao) 
    {
        this.divisao = divisao;
    }

    @Override
    public boolean isFlash() 
    {
        return flash;
    }

    @Override
    public void setFlash(boolean flash) 
    {
        this.flash = flash;
    }
        
    /*
     * Ao ser ligado o módulo de alarme, este tira uma foto á divisão ao qual foi detetado movimento
    */
    @Override
    public void tirarFoto()
    {
        new java.util.Timer().schedule( 
        new java.util.TimerTask() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    Image image = null;
                    
                    URL url = new URL("https://img1.madeiramadeira.com.br/prd/artely/219326/conjunto-sala-de-estar-com-painel-rack-e-mesa-de-centro-requinte-artely-canela-90-170609_amp.jpg");
                    image = ImageIO.read(url);
                    JFrame frame = new JFrame("Foto");
                    frame.setSize(800, 800);
                    JLabel label = new JLabel(new ImageIcon(image));
                    frame.add(label);
                    frame.setVisible(true);   
                } catch (IOException e) 
                {
                    e.printStackTrace();
                } 
            }
        }, 
        3000 
        );
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
