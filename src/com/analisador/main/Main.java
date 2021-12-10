package com.analisador.main;

import java.io.IOException;

import com.analisador.analisadorlexico;

public class Main
{
    public static void main(String args[])
    {
        AnalisadorLexico analisador = new AnalisadorLexico();
        try
        {
            analisador.abreArquivo("teste.txt");

            Token token;

            token = analisador.getToken();
            while(token.getToken() != Tokens.EOF)
            {
                System.out.println(token);
                token = analisador.getToken();
            }
            System.out.println(token);

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
