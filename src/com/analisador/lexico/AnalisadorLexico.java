package com.analisador.lexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalisadorLexico
{
    private BufferedReader arquivo;
    private String arquivoNome;
    private String linha;
    private int numeroLinha;
    private int posicaoLinha;

    public AnalisadorLexico()
    {
        this.arquivo = null;
        this.arquivoNome = "";
        this.linha = "";
        this.numeroLinha = 0;
        this.posicaoLinha = 0;
    }

    public AnalisadorLexico(String nomeArquivo) throws IOException
    {
        this.arquivo = new BufferedReader(new FileReader(nomeArquivo));
        this.arquivoNome = nomeArquivo;
        this.linha = arquivo.readLine().concat("\n");
        this.numeroLinha = 1;
        this.posicaoLinha = 0;
    }

    public void abreArquivo(String nomeArquivo) throws IOException
    {
        this.arquivo = new BufferedReader(new FileReader(nomeArquivo));
        this.arquivoNome = nomeArquivo;
        this.linha = arquivo.readLine().concat("\n");
        this.numeroLinha = 1;
        this.posicaoLinha = 0;
    }

    public Token getToken()
    {
        Token token = null;
        EstadosAutomato estado = EstadosAutomato.NAO_ALFANUMERICO;
        String lexema = "";
        char ch;

        while(token == null)
        {
            switch(estado)
            {
                case NAO_ALFANUMERICO:
                    ch = getChar();
                    switch(ch)
                    {
                        case ';':
                            token =  new Token(Tokens.PTOVIR, ";", numeroLinha);
                            break;
                        case '(':
                            token = new Token(Tokens.ABREPAR, "(", numeroLinha);
                            break;
                        case ')':
                            token = new Token(Tokens.FECHAPAR, ")", numeroLinha);
                            break;
                        case '*':
                            token = new Token(Tokens.OPMULT, "*", numeroLinha);
                            break;
                        case '<':
                            token = new Token(Tokens.OPMENOR, "<", numeroLinha);
                            break;
                        case '+':
                            token = new Token(Tokens.OPSOMA, "+", numeroLinha);
                            break;
                        case '-':
                            token = new Token(Tokens.OPSUB, "-", numeroLinha);
                            break;
                        case '/':
                            token = new Token(Tokens.OPDIV, "/", posicaoLinha);
                            break;
                        case '!':
                            token = new Token(Tokens.OPNOTLOG, "!", numeroLinha);
                            break;
                        default:
                            if(ch == ' ' || ch == '\n' || ch == '\t')
                                continue;
                            else if(ch == 0)
                                token = new Token(Tokens.EOF, "", numeroLinha);
                            else if(ch == '"')
                            {
                                estado = EstadosAutomato.STRING;
                                lexema = String.valueOf(ch);
                            }
                            else if(Character.isLetter(ch))
                            {
                                estado = EstadosAutomato.IDENTIFICADOR;
                                lexema = String.valueOf(ch);
                            }
                            else if(Character.isDigit(ch))
                            {
                                estado = EstadosAutomato.NUM_INTEIRO;
                                lexema = String.valueOf(ch);
                            }
                            else
                            {
                                estado = EstadosAutomato.ERRO;
                                lexema = String.valueOf(ch);
                            }
                            break;
                    }
                break;

                case NUM_INTEIRO:
                    ch = getChar();
                    if(Delimitadores.isDelimiter(ch))
                    {
                        token = new Token(Tokens.NUMINT, lexema, numeroLinha);
                        posicaoLinha--;
                    }
                    else
                    {
                        if(ch == '.')
                        {
                            estado = EstadosAutomato.NUM_REAL_DECIMAL;
                            lexema += String.valueOf('.');
                            continue;
                        }
                        else if(ch == 'e')
                        {
                            estado = EstadosAutomato.NUM_REAL_EXP;
                            lexema += String.valueOf('e');
                            continue;
                        }
                        else if(Character.isDigit(ch))
                            lexema += String.valueOf(ch);
                        else
                        {
                            estado = EstadosAutomato.ERRO;
                            lexema += String.valueOf(ch);
                            continue;
                        }
                    }
                    break;

                case IDENTIFICADOR:
                    ch = getChar();
                    if(Delimitadores.isDelimiter(ch))
                    {
                        switch(lexema)
                        {
                            case "int":
                                token = new Token(Tokens.INT, lexema, numeroLinha);
                                break;
                            case "return":
                                token = new Token(Tokens.RETURN, lexema, numeroLinha);
                                break;
                            default:
                                token = new Token(Tokens.ID, lexema, numeroLinha);
                                break;
                        }
                        posicaoLinha--;
                    }
                    else
                    {
                        if(Character.isLetterOrDigit(ch) || ch == '_')
                            lexema += String.valueOf(ch);
                        else
                        {
                            estado = EstadosAutomato.ERRO;
                            lexema += String.valueOf(ch);
                            continue;
                        }
                    }
                    break;

                case ERRO:
                    ch = getChar();
                    if(Delimitadores.isDelimiter(ch))
                    {
                        token = new Token(Tokens.ERRO, lexema, numeroLinha);
                        posicaoLinha--;
                    }
                    else
                        lexema += String.valueOf(ch);
                    break;
            }
        }
        return token;
    }
}
