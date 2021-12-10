package com.analisador.lexico;

public class Delimitadores
{
    public static boolean isDelimiter(char ch)
    {
        if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || 
           ch == '(' || ch == ')' || ch == ';' || ch == '<' || 
           ch == '!' )
            return true;
        return false;
    }
}
