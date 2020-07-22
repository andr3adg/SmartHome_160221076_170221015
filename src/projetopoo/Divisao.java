/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contém a informação correspondente a cada divisão da residência, guardando uma lista com os equipamentos presentes na mesma.
 * @author Diogo Venancio e André Gonçalves
 */
public class Divisao implements Serializable
{
    private String nome;
    private double temp;
    private int luz;
    private List<Equipamento> equipamento;

    public Divisao(String nome) 
    {
        if(!nome.isEmpty() && !nome.equals(""))
            this.nome = nome;
        
        this.temp = temp;
        this.luz = luz;
        this.equipamento = new ArrayList<>();
    }

    public String getNome()
    {
        return nome;
    }

    public double getTemp() 
    {
        return temp;
    }

    public void setTemp(double temp) 
    {
        this.temp = temp;
    }

    public int getLuz() 
    {
        return luz;
    }

    public void setLuz(int luz) 
    {
        this.luz = luz;
    }

    public void addEquipamento(Equipamento equi)
    {
        equipamento.add(equi);
    }
    
    public void removeEquipamento(Equipamento equi)
    {
        equipamento.remove(equi);
    }
    
    /*
     * Listagem de todos os equipamentos
    */
    public List<Equipamento> getEquipamentos()
    {
        List<Equipamento> listaeq = new ArrayList<>();
        
        for(Equipamento eq : equipamento)
        {
            listaeq.add(eq);
        }
        return listaeq;
    }
    
    /*
     * Listagem de todos os equipamentos iguais ao equiapmento recebido
    */
    public List<Equipamento> getEquipamento(Equipamento equi)
    {     
        List<Equipamento> listaeq = new ArrayList<>();
        
        for(Equipamento eq : equipamento)
        {
            if(eq.equals(equi))
            {
                listaeq.add(eq);
            }
        }
        return listaeq;
    }
    
    @Override
    public String toString() 
    {
        return "Divisao: " + nome + "\n";
    }

    
    
    
}
