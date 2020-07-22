/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

/**
 * Tipos de Equipamentos
 * @author Diogo Venancio e André Gonçalves
 */
public enum Tipo 
{
    SENSORES, ATUADORES;
    
    @Override
    public String toString()
    {
        String str;
        switch(this)
        {
            case SENSORES:
                str = "Sensor";
                break;
            case ATUADORES:
                str = "Atuador";
                break;
            default:
                return null;
        }
        return str;
    }
}
