/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

/**
 * Guardar o Cliente atual da sessão
 * @author Diogo Venancio e André Gonçalves
 */
public interface Dados 
{
    public String getName();
    public void setName(String name);
    public String getEmail();
    public void setEmail(String email);
    public String getAddress();
    public void setAddress(String address);
    public int getId();
    
}
