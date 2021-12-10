package com.analisador.lexico;

public enum Tokens
{
    ERRO, EOF, PTOVIR, INT, ID, NUMINT,
    ABREPAR, FECHAPAR, 
    RETURN, OPMULT, OPSOMA, OPSUB,
    OPDIV, OPIGUAL, OPMENOR, OPNOTLOG;
    
    @Override
    public String toString()
    {
        switch(this)
        {
            case ERRO:
                return "Token inválido";
            case EOF:
                return "Final de Arquivo";
            case PTOVIR:
                return "';'";
            case INT:
                return "identificador 'int'";
            case ID:
                return "identificador";
            case NUMINT:
                return "número 'inteiro'";
            case ABREPAR:
                return "'('";
            case FECHAPAR:
                return "')'";
            case RETURN:
                return "comando 'return'";
            case OPMULT:
                return "operador '*'";
            case OPSOMA:
                return "operador '+'";
            case OPSUB:
                return "operador '-'";
            case OPDIV:
                return "operador '/'";
            case OPIGUAL:
                return "operador '=='";
            case OPMENOR:
                return "operador '<'";
            case OPANDLOG:
                return "operador '&&'";
            case OPNOTLOG:
                return "operador '!'";

            default:
                return "";
        }
    }
}
