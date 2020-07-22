/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import Atuadores.*;
import Exceptions.ClienteIllegalArgumentException;
import Exceptions.EquipamentoIllegalArgumentException;
import Sensores.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A classe consola é a classe responsável por mostrar a informação ao cliente, funcionando como mostrador de “output” da Consola Central.
 * @author Diogo Venancio e André Gonçalves
 */
public class Consola implements Serializable
{    
    public Consola() 
    {
    }
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /*
     * Faz a validadção do email do cliente
    */
    public static boolean validate(String emailStr) 
    {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    
    /*
     * Login
    */
    public void login() throws InterruptedException
    {
        Scanner stdin = new Scanner(System.in);
        while(!ConsolaCentral.getInstance().haveLoggedIn())
        {
            System.out.println("----- Autenticação -----");
            System.out.println("1- Login");
            System.out.println("2- Registar");
            System.out.println("0- Sair");
            System.out.print("> ");
            int option = stdin.nextInt();
            switch(option){
                case 0:
                    try
                    {
                        ConsolaCentral.getInstance().save(new File("sistema.txt"));
                        System.exit(0);
                    }catch(IOException e)
                    {
                        System.err.println(e);
                        System.exit(1);
                    }
                    return;
                case 1:
                    System.out.print("\nNº Cliente: ");
                    ConsolaCentral.getInstance().loginClient(stdin.nextInt());
                    if(!ConsolaCentral.getInstance().haveLoggedIn()){
                        System.out.println("ID Inválido!\n");
                        break;
                    }
                    homePage();
                    break;
                case 2:
                    registarClient();
                    break;
                default:
                    System.out.println("Opção Invalida");
            }
        }
    }
    
    /*
     * Registar Cliente
    */
    public void registarClient()
    {
        Scanner stdin = new Scanner(System.in);
        System.out.println("");
        System.out.println("---Registo----");
        System.out.print("Nome:");
        try 
        {
            String nome = stdin.next();
        
            System.out.print("Morada:");
            String morada = stdin.next();
        
            System.out.print("E-mail:");
            String email = stdin.next();
        
            if(validate(email))
            {
                boolean resultado = ConsolaCentral.getInstance().addClient(new Cliente(nome, morada, email));
            
                if(resultado)
                {
                    System.out.println("Utilizador registado com sucesso!");
                }else
                {
                    System.out.println("Utilizador já existente!");
                }   
            }
        } catch (ClienteIllegalArgumentException e) 
        {
            System.out.println(e);
        }
    }

    /*
     * Página inicial
    */
    public void homePage() throws InterruptedException
    {
        int option = -1;
        Scanner stdin = new Scanner(System.in);
        while(option != 0)
        {
                System.out.println("\n");
                System.out.println("Bem vindo " + ConsolaCentral.getInstance().getCurrentClient());
                System.out.println("----- MENU -----");
                System.out.println("1- Adicionar Equipamento");
                System.out.println("2- Atuadores");
                System.out.println("3- Sensores");
                System.out.println("4- Wifi");
                System.out.println("5- Lista Dados");
                System.out.println("6- Módulos");
                System.out.println("0- Sair");
                System.out.print("> ");
                option = stdin.nextInt();
                switch(option)
                {
                    case 0:
                        ConsolaCentral.getInstance().logoutUser();
                        break;
                    case 1:
                        adicionarEquip();
                        break;
                    case 2:
                        atuadores();
                        break;
                    case 3:
                        sensores();
                        break;
                    case 4:
                        //wifi();
                        break;
                    case 5:
                        ConsolaCentral.getInstance().listarDivisoes();
                        break;
                    case 6:
                        menuModulos();
                        break;
                    default:
                        System.out.println("Opção Invalida");
                }
        }
    }
    
    /*
     * Adicionar Equipamento - Escolher qual tipo de Equipamento que pretende adicionar
    */
    public void adicionarEquip()
    {
        int option = -1;
        Scanner stdin = new Scanner(System.in);
        while(option != 0)
        {
            System.out.println("\n\n\n");
            System.out.println("----- MENU -----");
            System.out.println("1- Atuadores");
            System.out.println("2- Sensores");
            System.out.println("0- Sair");
            System.out.print("> ");
            option = stdin.nextInt();
            switch(option)
            {
                case 0:
                    break;
                case 1:
                    addAtuador();
                    option = 0;
                    break;
                case 2:
                    addSensor();
                    option = 0;
                    break;
                default:
                    System.out.println("Opção Invalida");
            }
        }
    }
    
    /*
     * Adicionar atuador - Selecionar o atuador pretendido
    */
    public void addAtuador()
    {
        int option = -1;
        Scanner stdin = new Scanner(System.in);
        while(option != 0)
        {
            System.out.println("\n\n\n");
            System.out.println("----- MENU -----");
            System.out.println("1- Lâmpada");
            System.out.println("2- Tomada");
            System.out.println("3- Ar Condicionado");
            System.out.println("4- Sirene");
            System.out.println("5- Câmara Fotográfica");
            System.out.println("6- Câmara de Vídeo");
            System.out.println("0- Sair");
            System.out.print("> ");
            option = stdin.nextInt();
            switch(option)
            {
                case 0:
                    break;
                case 1:
                    addDivisao("Lampada");
                    option = 0;
                    break;
                case 2:
                    addDivisao("Tomada");
                    break;
                case 3:
                    addDivisao("ArCondicionado");
                    option = 0;
                    break;
                case 4:
                    addDivisao("Sirene");
                    option = 0;
                    break;
                case 5:
                    addDivisao("CamaraFotografica");
                    option = 0;
                    break;
                case 6:
                    addDivisao("CamaraVideo");
                    option = 0;
                    break;
                default:
                    System.out.println("Opção Invalida");
            }
        }
    }
    
    /*
     * Adicionar sensor - Selecionar o sensor pretendido
    */
    public void addSensor()
    {
        int option = -1;
        Scanner stdin = new Scanner(System.in);
        while(option != 0)
        {
            System.out.println("\n\n\n");
            System.out.println("----- MENU -----");
            System.out.println("1- Temperatura");
            System.out.println("2- Luminosidade");
            System.out.println("3- Porta");
            System.out.println("4- Movimento");
            System.out.println("0- Sair");
            System.out.print("> ");
            option = stdin.nextInt();
            switch(option)
            {
                case 0:
                    break;
                case 1:
                    addDivisao("Temperatura");
                    option = 0;
                    break;
                case 2:
                    addDivisao("Luminosidade");
                    option = 0;
                    break;
                case 3:
                    addDivisao("Porta");
                    option = 0;
                    break;
                case 4:
                    addDivisao("Movimento");
                    option = 0;
                    break;
                default:
                    System.out.println("Opção Invalida");
            }
        }
    }
    
    /*
     * Adicionar um equipamento a uma divisão
    */
    public void addDivisao(String nome)
    {
        List<Equipamento> eq = new ArrayList<>();
        
        Scanner stdin = new Scanner(System.in);
        switch(nome)
        {
            case "Lampada":
                try 
                {
                    System.out.println("\n\n\n");
                    System.out.println(nome);
                    System.out.print("Nivel de Luminosidade(0-20): ");
                    int luz = stdin.nextInt();
        
                    System.out.println("");
                    System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                    System.out.print("Qual a divisão: ");
                    String div = stdin.next();

                    Atuador lampada = new Lampada(ConsolaCentral.getInstance().getDiv(div),luz);
                    
                    eq.add(lampada);
                    ConsolaCentral.getInstance().addEquipamento(lampada);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv(div), lampada);
                    
                    ConsolaCentral.getInstance().getDivisaoLuz(div, luz);
                    ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                    eq.remove(eq.get(0));
                } catch (EquipamentoIllegalArgumentException e) 
                {
                    System.out.println("\n" + e.getMessage());
                    addDivisao("Lampada");
                }
                break;
            case "Tomada":
                try 
                {
                    System.out.println("\n\n\n");
                    System.out.println(nome);
                    System.out.print("Nivel do Temporizador: ");
                    int tempori = stdin.nextInt();
        
                    System.out.println("");
                    System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                    System.out.print("Qual a divisão: ");
                    String div = stdin.next();

                    Atuador tomada = new Tomada(ConsolaCentral.getInstance().getDiv(div),tempori);
                    
                    eq.add(tomada);
                    ConsolaCentral.getInstance().addEquipamento(tomada);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv(div), tomada);
                    ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                    eq.remove(eq.get(0));
                } catch (EquipamentoIllegalArgumentException e) 
                {
                    System.out.println("\n" + e.getMessage());
                    addDivisao("Tomada");
                }   
                break;
            case "ArCondicionado":
                try 
                {
                    System.out.println("\n\n\n");
                    System.out.println(nome);
                    System.out.print("Temperatura(16-28): ");
                    int temp = stdin.nextInt();
         
                    System.out.println("");
                    System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                    System.out.print("Qual a divisão: ");
                    String div = stdin.next();
                    
                    Atuador Ar = new ArCondicionado(ConsolaCentral.getInstance().getDiv(div),temp);
                    
                    eq.add(Ar);
                    ConsolaCentral.getInstance().addEquipamento(Ar);
                
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv(div), Ar);
                    
                    ConsolaCentral.getInstance().getDivisaoTemp(div,(double)temp);
                    ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                    eq.remove(eq.get(0));
                } catch (EquipamentoIllegalArgumentException e) 
                {
                    System.out.println("\n" + e.getMessage());
                    addDivisao("ArCondicionado");
                } 
                break;
            case "Sirene":
                
                try 
                {
                    System.out.println("\n\n\n");
                    System.out.println(nome);
                    System.out.print("Volume do Som(0-10): ");
                    int som = stdin.nextInt();
            
                    System.out.println("");
                    System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                    System.out.print("Qual a divisão: ");
                    String div = stdin.next();
                    
                    Atuador sirene = new Sirene(ConsolaCentral.getInstance().getDiv(div),som);
                    
                    eq.add(sirene);
                    ConsolaCentral.getInstance().addEquipamento(sirene);
                    
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv(div), sirene);
                    
                    ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                    eq.remove(eq.get(0));
                }catch (EquipamentoIllegalArgumentException e) 
                {
                    System.out.println("\n" + e.getMessage());
                    addDivisao("Sirene");
                } 
                break;
            case "CamaraFotografica":
                System.out.println("\n\n\n");
                System.out.println(nome);
                System.out.println("");
                System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                System.out.print("Qual a divisão: ");
                String div = stdin.next();
                    
                Atuador camaraFoto = new CamaraFotografica(ConsolaCentral.getInstance().getDiv(div));
                
                eq.add(camaraFoto);
                ConsolaCentral.getInstance().addEquipamento(camaraFoto);
                
                ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv(div), camaraFoto);
                
                ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                eq.remove(eq.get(0));    
                break;
            case "CamaraVideo":
                System.out.println("\n\n\n");
                System.out.println(nome);
                System.out.println("");
                System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                System.out.print("Qual a divisão: ");
                div = stdin.next();
                    
                Atuador camaraVideo = new CamaraVideo(ConsolaCentral.getInstance().getDiv(div));
                
                eq.add(camaraVideo);
                ConsolaCentral.getInstance().addEquipamento(camaraVideo);   
                
                ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv(div), camaraVideo);
                
                ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                eq.remove(eq.get(0)); 
                break;
            case "Temperatura":
                System.out.println("\n\n\n");
                System.out.println(nome);
                System.out.println("");
                System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                System.out.print("Qual a divisão: ");
                div = stdin.next();
                    
                Sensor temper = new Temperatura(ConsolaCentral.getInstance().getDiv(div));
                
                ConsolaCentral.getInstance().addEquipamento(temper); 
                eq.add(temper);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv(div), temper);
                
                ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                eq.remove(eq.get(0));    
                break;
            case "Luminosidade":
                System.out.println("\n\n\n");
                System.out.println(nome);
                System.out.println("");
                System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                System.out.print("Qual a divisão: ");
                div = stdin.next();
                    
                Sensor lumino = new Luminosidade(ConsolaCentral.getInstance().getDiv(div));
                
                ConsolaCentral.getInstance().addEquipamento(lumino); 
                eq.add(lumino);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv(div), lumino);
                
                ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                eq.remove(eq.get(0)); 
                break;
            case "Porta":
                System.out.println("\n\n\n");
                System.out.println(nome);
                System.out.println("");
                System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                System.out.print("Qual a divisão: ");
                div = stdin.next();
                    
                Sensor porta = new Porta(ConsolaCentral.getInstance().getDiv(div));
                
                ConsolaCentral.getInstance().addEquipamento(porta); 
                eq.add(porta);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv(div), porta);
                
                ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                eq.remove(eq.get(0)); 
                break;
            case "Movimento":
                System.out.println("\n\n\n");
                System.out.println(nome);
                System.out.println("");
                System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
                System.out.print("Qual a divisão: ");
                div = stdin.next();
                    
                Sensor movimento = new Movimento(ConsolaCentral.getInstance().getDiv(div));
                
                ConsolaCentral.getInstance().addEquipamento(movimento); 
                eq.add(movimento);
                   
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv(div), movimento);
                
                ConsolaCentral.getInstance().getDivisao(div, eq.get(0));
                eq.remove(eq.get(0)); 
                break;
            case "":
                break;
            default:
                System.out.println("Opção Invalida");
        }
    }
    
    /*
     * Faz a filtragem de todos os atuadores dentro da divisão pretendida
    */
    public void atuadores()
    {
        System.out.println("\n\n\n");
        System.out.println("Atuadores");
        System.out.println("");
        System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
        System.out.print("Qual a divisão: ");
        
        Scanner stdin = new Scanner(System.in);
        String div = stdin.next();
        
        ConsolaCentral.getInstance().getEquipamentosAtuador(div);
    }
    
    /*
     * Faz a filtragem de todos os sensores dentro da divisão pretendida
    */
    public void sensores()
    {
        System.out.println("\n\n\n");
        System.out.println("Sensores");
        System.out.println("");
        System.out.println("(Quarto1/Quarto2/Cozinha/Sala/Banho)");
        System.out.print("Qual a divisão: ");
        
        Scanner stdin = new Scanner(System.in);
        String div = stdin.next();
        
        ConsolaCentral.getInstance().getEquipamentosSensor(div);
    }
    
    /*
     * Menu dos modulos
    */
    public void menuModulos()
    {
        int option = -1;
        Scanner stdin = new Scanner(System.in);
        while(option != 0)
        {
            System.out.println("\n\n\n");
            System.out.println("----- MENU MODULOS -----");
            System.out.println("1- Modulo Temperatura");
            System.out.println("2- Modulo Luminosidade");
            System.out.println("3- Modulo alarme");
            System.out.println("0- Sair");
            System.out.print("> ");
            option = stdin.nextInt();
            switch(option)
            {
                case 0:
                    break;
                case 1:
                    ConsolaCentral.getInstance().moduloTemp();
                    System.out.println("Aplicado com sucesso!");
                    option = 0;
                    break;
                case 2:
                    ConsolaCentral.getInstance().moduloLuminosidade();
                    System.out.println("Aplicado com sucesso!");
                    option = 0;
                    break;
                case 3:
                    if(ConsolaCentral.getInstance().getPin() == 0)
                    {
                        System.out.println("\n\n\n");
                        System.out.println("Modulo Alarme");
                        System.out.println("");
                        System.out.print("PIN a definir: ");

                        stdin = new Scanner(System.in);
                        int pin1 = stdin.nextInt();
                    
                        System.out.println("");
                        System.out.print("Introduza novamente: ");

                        stdin = new Scanner(System.in);
                        int pin2 = stdin.nextInt();
                    
                        if(pin1 == pin2)
                        {
                            ConsolaCentral.getInstance().setPin(pin2);
                        }
                    
                        System.out.println("");
                        System.out.print("PIN guardado com sucesso!");
                        option = 0;
                    }
                    else
                    {
                        System.out.println("\n\n");
                        System.out.println("Modulo está ativo!");
                        System.out.println("");
                        System.out.println("1- Ligar");
                        System.out.println("2- Alterar");
                        System.out.println("0- Sair");
                        System.out.print("> ");
                        option = stdin.nextInt();
                        switch(option)
                        {
                            case 0:
                                break;
                            case 1:
                                ConsolaCentral.getInstance().moduloAlarme(ConsolaCentral.getInstance().getPin());
                                option = 0;
                                break;
                            case 2:
                                System.out.println("Quer alterar pin? - Sim/Não");
                                System.out.print("> ");
                                String alterar = stdin.next();
                                switch(alterar)
                                {
                                    case "Sim":
                                        System.out.print("Insira codigo atual -> ");
                                        int pinatu = stdin.nextInt();
                                        if(pinatu == ConsolaCentral.getInstance().getPin())
                                        {
                                            System.out.println("");
                                            System.out.print("Insira novo codigo -> ");
                                            int pinnovo = stdin.nextInt();
                                        
                                            ConsolaCentral.getInstance().setPin(pinnovo);
                                            System.out.println("Pin alterado com sucesso!");
                                        }
                                        System.out.println("");         
                                }
                            option = 0;
                            break;
                        } 
                    }
                    break;
                default:
                    System.out.println("Opção Invalida");
            }
        }
    }
}
