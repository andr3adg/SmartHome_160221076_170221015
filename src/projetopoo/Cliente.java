/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import Exceptions.ClienteIllegalArgumentException;
import Exceptions.ErrorCode;
import java.io.Serializable;
import java.util.Objects;

/**
 * A classe cliente é a classe responsável por guardar a informação pessoal dos residentes da casa.
 * @author Diogo Venancio e André Gonçalves
 */
public class Cliente implements Dados, Serializable
{
    private static int count = 1;
    private final int id;
    private String name;
    private String address;
    private String email;
    
    /**
     * Instâncía um novo cliente sem o registar no ficheiro da aplicação.
     * @param name Nome
     * @param address Morada
     * @param email Email
     */
    public Cliente(String name, String address, String email) 
    {
        this.id = count++;
        this.name = validateName(name);
        this.address = validateAddress(address);
        this.email = validateEmail(email);
    }
    
    private String validateName(String name) 
    {
        if (name == null) {
            throw new ClienteIllegalArgumentException(ErrorCode.NAME_CANT_BE_NULL);
        }
        return name;
    }
    
    private String validateAddress(String address) 
    {
        if (address == null) {
            throw new ClienteIllegalArgumentException(ErrorCode.ADDRESS_CANT_BE_NULL);
        }
        return address;
    }
    
    private String validateEmail(String email) 
    {
        if (email == null) {
            throw new ClienteIllegalArgumentException(ErrorCode.EMAIL_CANT_BE_NULL);
        }
        return email;
    }
    
    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    @Override
    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public int getId() 
    {
        return id;
    }

    @Override
    public String getEmail() 
    {
        return email;
    }

    @Override
    public String getAddress() 
    {
        return address;
    }

    @Override
    public void setName(String name) 
    {
        this.name = name;
    }

    @Override
    public String getName() 
    {
        return name;
    }

    
    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     * Neste caso um cliente é identificado uniquivocamente pelo seu id
     * @param obj Cliente a comparar
     * @return valor lógico da operação de comparação
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.email, other.email);
    }
    
    /**
     * toString()
     * @return str
     */
    @Override
    public String toString() 
    {
        String str = "";
        str += String.format(this.name);
        return str;
    }
    
}
