/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 * Enum que guarda todas as mensagens de erro
 * @author Diogo Venancio e André Gonçalves
 */
public enum ErrorCode 
{
    NAME_CANT_BE_NULL, 
    ADDRESS_CANT_BE_NULL, 
    EMAIL_CANT_BE_NULL,
    TEMP_BETWEEN,
    LUZ_BETWEEN,
    SIREN_BETWEEN;
    
    @Override
    public String toString()
    {
        switch (this) 
        {
            case NAME_CANT_BE_NULL:
                return "O nome do cliente não pode estar vazio";
            case ADDRESS_CANT_BE_NULL:
                return "A morada do cliente não pode estar vazia";
            case EMAIL_CANT_BE_NULL:
                return "Email do cliente não pode estar vazio";
            case TEMP_BETWEEN:
                return "Temperatura entre 16ºC e 28ºC";
            case LUZ_BETWEEN:
                return "Luminosidade entre 0 e 20";
            case SIREN_BETWEEN:
                return "Som entre 0 e 10";
        }
        return "";
    }
    
}
