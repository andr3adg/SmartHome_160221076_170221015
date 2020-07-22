/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Diogo Venancio e André Gonçalves
 */
public class ClienteIllegalArgumentException extends IllegalArgumentException
{
    private ErrorCode errorCode;

    public ClienteIllegalArgumentException(ErrorCode errorCode) 
    {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() 
    {
        return errorCode;
    }
}
