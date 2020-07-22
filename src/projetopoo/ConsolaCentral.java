/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import Atuadores.*;
import Sensores.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * As ações de todos aparelhos são controladas pela Consola Central, 
 * que funciona como cérebro de todas as ações, 
 * coordenando os aparelhos através das informações recebidas pelos sensores instalados na residência.
 * @author Diogo Venancio e André Gonçalves
 */
public class ConsolaCentral implements Serializable
{
    private Map<Integer, Cliente> utilizadores; //<id, cliente>
    private Map<String, Equipamento> equipamentos; //<nome, equipamento>
    private HashMap<Divisao, List<Sensor>> divisaoSensor; //<divisao, lista de sensores>
    private HashMap<Divisao, List<Atuador>> divisaoAtuador; //<divisao, lista de atuadores>
    private HashMap<Divisao, List<Equipamento>> divisao; //<divisao, lista de equipamentos>
    
    private static ConsolaCentral sistema;

    private Dados dados;
    private Consola consola;
    private int pin = 0;
    
    public ConsolaCentral() 
    {
        utilizadores = new HashMap<>();
        divisaoSensor = new HashMap<>();
        divisaoAtuador = new HashMap<>();
        equipamentos = new HashMap<>();
        divisao = new HashMap<>();
        this.consola = new Consola();
        this.pin = pin;
    }

    /*
     * Retorna o pin para o modulo de alarme
    */
    public int getPin()
    {
        return pin;
    }
    
    /*
     * Altera o pin do módulo de alarme
     * @param pin int
    */
    public void setPin(int pin)
    {
        this.pin = pin;
    }
    
    /*
     * Inicio do programa
    */
    public void iniciarUI()
    {
        try 
        {
            consola.login();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(ConsolaCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obter instancia
     * @return sistema
    */
    public static ConsolaCentral getInstance()
    {
        if(sistema == null)
        {
            sistema = new ConsolaCentral();
        }
        return sistema;
    } 

    /*
     * ConsolaCentral default
    */
    public static void reporSistema()
    {
        sistema = new ConsolaCentral();
        
        Cliente cliente = new Cliente("Diogo", "Morada1", "drop@hotmail.com");     
        Cliente cliente1 = new Cliente("Andre", "Morada2", "adg@hotmail.com");   
        sistema.utilizadores.put(cliente.getId(), cliente);
        sistema.utilizadores.put(cliente1.getId(), cliente1);
        
        Divisao sala = new Divisao("Sala");
        Divisao quarto1 = new Divisao("Quarto1");
        Divisao quarto2 = new Divisao("Quarto2");
        Divisao banho = new Divisao("Banho");
        Divisao cozinha = new Divisao("Cozinha");
        
        List<Equipamento> listaEq = new ArrayList<>();

        Sensor movimentoquart = new Movimento(quarto2);
        Sensor movimentobanho = new Movimento(banho);
        Sensor movimentosala = new Movimento(sala);
        Sensor movimentoquart1 = new Movimento(quarto1);
        Sensor movimentocozi = new Movimento(cozinha);
        Sensor portaSala = new Porta(sala);
        Sensor portaCozi = new Porta(cozinha);
        Sensor temp = new Temperatura(quarto1);
        
        Atuador ArCon = new ArCondicionado(quarto1, 23);
        
        /** Quarto 1 **/
        List<Sensor> listaSeQuart = new ArrayList<>();
        List<Atuador> listaAtuQuart = new ArrayList<>();
        
        listaSeQuart.add(movimentoquart1);
        listaSeQuart.add(temp);
        listaAtuQuart.add(ArCon);
        sistema.divisaoSensor.put(quarto1, listaSeQuart);
        sistema.divisaoAtuador.put(quarto1, listaAtuQuart);
        
        /** Sala **/
        List<Sensor> listaSeSala = new ArrayList<>();
        
        listaSeSala.add(portaSala);
        listaSeSala.add(movimentosala);
        sistema.divisaoSensor.put(sala, listaSeSala);
        
        /** Quarto 2 **/
        List<Sensor> listaSeQuart2 = new ArrayList<>();
        
        listaSeQuart2.add(movimentoquart);
        sistema.divisaoSensor.put(quarto2, listaSeQuart2);
        
        /** Cozinha **/
        List<Sensor> listaSeCozi = new ArrayList<>();
        
        listaSeCozi.add(portaCozi);
        listaSeCozi.add(movimentocozi);
        sistema.divisaoSensor.put(cozinha, listaSeCozi);
        
        /** Casa de Banho **/
        List<Sensor> listaSeBanho = new ArrayList<>();
        
        listaSeBanho.add(movimentobanho);
        sistema.divisaoSensor.put(banho, listaSeBanho);
        
        listaEq.add(movimentoquart);
        listaEq.add(movimentobanho);
        listaEq.add(portaSala);
        listaEq.add(portaCozi);
        listaEq.add(temp);
        listaEq.add(ArCon);
        listaEq.add(movimentosala);
        listaEq.add(movimentoquart1);
        listaEq.add(movimentocozi);

        sistema.addEquipamento(movimentoquart);
        sistema.addEquipamento(movimentobanho);
        sistema.addEquipamento(portaSala);
        sistema.addEquipamento(portaCozi);
        sistema.addEquipamento(temp);
        sistema.addEquipamento(ArCon);
        sistema.addEquipamento(movimentosala);
        sistema.addEquipamento(movimentoquart1);
        sistema.addEquipamento(movimentocozi);

        /** Sala **/
        List<Equipamento> salaEqui = new ArrayList<>();
        salaEqui.add(sistema.getEquipamento(portaSala.getNome()));
        salaEqui.add(sistema.getEquipamento(movimentosala.getNome()));
        
        sistema.divisao.put(sala, salaEqui);
        sala.addEquipamento(listaEq.get(2));
        sala.addEquipamento(listaEq.get(6));
        
        /** Quarto **/
        List<Equipamento> quartEqui = new ArrayList<>();
        quartEqui.add(sistema.getEquipamento(temp.getNome()));
        quartEqui.add(sistema.getEquipamento(ArCon.getNome()));
        quartEqui.add(sistema.getEquipamento(movimentoquart1.getNome()));
        
        sistema.divisao.put(quarto1, quartEqui);
        quarto1.addEquipamento(listaEq.get(4));
        quarto1.addEquipamento(listaEq.get(5));
        quarto1.addEquipamento(listaEq.get(7));
        
        /** Quarto 2 **/
        List<Equipamento> quart2Equi = new ArrayList<>();
        quart2Equi.add(sistema.getEquipamento(movimentoquart.getNome()));
        
        sistema.divisao.put(quarto2, quart2Equi);
        quarto2.addEquipamento(listaEq.get(0));
        
        /** Cozinha **/
        List<Equipamento> coziEqui = new ArrayList<>();
        coziEqui.add(sistema.getEquipamento(portaCozi.getNome()));
        coziEqui.add(sistema.getEquipamento(movimentocozi.getNome()));
        
        sistema.divisao.put(cozinha, coziEqui);
        cozinha.addEquipamento(listaEq.get(3));
        cozinha.addEquipamento(listaEq.get(8));
        
        /** Casa de banho **/
        List<Equipamento> banhoEqui = new ArrayList<>();
        banhoEqui.add(sistema.getEquipamento(movimentobanho.getNome()));

        sistema.divisao.put(banho, banhoEqui);
        banho.addEquipamento(listaEq.get(1));
    }
    
    /*
     * Adicionar um sensor a uma divisão
     * @param div Divisao
     * @param sensor Sensor
    */
    public void addDivisaoSensor(Divisao div,Sensor sensor)
    {
        if(sistema.divisaoAtuador.get(div) == null)
        {
            List<Sensor> sen = new ArrayList<>();
            sen.add(sensor);
            sistema.divisaoSensor.put(div, sen);
        }
        else
        {
            sistema.divisaoSensor.get(div).add(sensor);
        }
    }
    
    /*
     * Remove um sensor a uma divisão
     * @param div Divisao
     * @param sensor Sensor
    */
    public void removeDivisaoSensor(Divisao div,Sensor sensor)
    {
        if(sistema.divisaoSensor.get(div) != null)
        {
            sistema.divisaoSensor.get(div).remove(sensor);
        }
    }
    
    /*
     * Adicionar um atuador a uma divisão
     * @param div Divisao
     * @param atuador Atuador
    */
    public void addDivisaoAtuador(Divisao div,Atuador atuador)
    {
        if(sistema.divisaoAtuador.get(div) == null)
        {
            List<Atuador> at = new ArrayList<>();
            at.add(atuador);
            sistema.divisaoAtuador.put(div, at);
        }
        else
        {
            sistema.divisaoAtuador.get(div).add(atuador);
        }
    }
    
    /*
     * Remove um atuador a uma divisão
     * @param div Divisao
     * @param sensor Sensor
    */
    public void removeDivisaoAtuador(Divisao div,Atuador atuador)
    {
        if(sistema.divisaoAtuador.get(div) != null)
        {
            sistema.divisaoAtuador.get(div).remove(atuador);
        }
    }
    
    /**
     * Verifica se adicionou Cliente
     * @param client Cliente
     * @return true or false
     */
    public boolean addClient(Cliente client)
    {
        return utilizadores.putIfAbsent(client.getId(), client) == null;
    }
    
    
    /**
     * Verifica se cliente já existe através do seu id
     * @param id ID do Cliente
     * @return true or false
     */
    public boolean haveClient(int id)
    {
        return utilizadores.containsKey(id);
    }
    
    /**
     * Obtem o cliente pelo id
     * @param id ID Cliente
     * @return Cliente or null
     */
    public Cliente getClient(int id)
    {
        return utilizadores.get(id);
    }
    
    /**
     * Verifica se adicionou Equipamento
     * @param equi Equipamento
     * @return true or false
     */
    public boolean addEquipamento(Equipamento equi)
    {
        return equipamentos.putIfAbsent(equi.getNome(), equi) == null;
    }
       
    /**
     * Obtem o equipamento pelo tipo
     * @param nome Nome do Equipamento
     * @return Equipamento or null
     */
    public Equipamento getEquipamento(String nome)
    {
        return equipamentos.get(nome);
    }
    
    /**
     * Login do cliente através do código do cliente
     * @param id ID do Cliente
     */
    public void loginClient(int id){
        dados = ConsolaCentral.getInstance().getClient(id);
    }
    
    /**
     * Verifica se tem login feito
     * @return true or false
     */
    public boolean haveLoggedIn(){
        return this.dados != null;
    }
    /**
     * Necessário login
     * @throws Exception Login necessário
     */
    public void loginRequired() throws Exception
    {
        if(!haveLoggedIn()) throw new Exception("É necessário realizar o login primeiro");
    }
    
    /**
     * Log out
     */
    public void logoutUser(){
        if(haveLoggedIn()) dados = null;
    }
    
    /**
     * Retorna o cliente atual
     * @return dados
     */
    public Dados getCurrentClient() 
    {
        return dados;
    }
    
    /*
     * Faz a listagem de todos os equipamentos de todas as divisões
    */
    public void listarDivisoes() 
    {  
        divisao.entrySet().stream().forEachOrdered((entry) -> 
        {
            Object currentKey = entry.getKey();
            Object currentValue = entry.getValue();            
            System.out.println(currentKey);
            System.out.println(currentValue);
        });
    }  
    
    /**
     * Adiciona um equipamento a uma divisão
     * @param div Divisão
     * @param equi Equipamento
     */
    public void addEquipamentoDivisao(Divisao div,Equipamento equi)
    {
        sistema.divisao.get(div).add(equi);
    }
    
    /*
     * Faz a filtragem de todos os atuadores dentro da divisão pretendida 
    */
    public void getEquipamentosAtuador(String nome)
    {   
        for(Map.Entry<Divisao, List<Atuador>> entry : divisaoAtuador.entrySet()) 
        {
            Divisao key = entry.getKey();
            List<Atuador> value = entry.getValue();
            
            if(key.getNome().equals(nome))
            {
                Iterator it = value.iterator();
                    
                while(it.hasNext())
                {
                    Equipamento obj = (Equipamento)it.next();
                    System.out.println(obj);
                                       
                }
            } 
        }
    }
    
    /*
     * Faz a filtragem de todos os atuadores dentro da divisão pretendida 
     * @return List<Atuador>
    */
    public List<Atuador> getDivEquipAtuador(String nome)
    {     
        List<Atuador> lista = new ArrayList<>();
        
        for(Map.Entry<Divisao, List<Atuador>> entry : divisaoAtuador.entrySet()) 
        {
            Divisao key = entry.getKey();
            List<Atuador> value = entry.getValue();

            if(key.getNome().equals(nome))
            {
                Iterator it = value.iterator();
                    
                while(it.hasNext())
                {
                    Atuador obj = (Atuador)it.next();
                    lista.add(obj);                     
                }
            } 
        }
        return lista;
    }
    
    /*
     * Faz a filtragem de todos os sensores dentro da divisão pretendida 
    */
    public void getEquipamentosSensor(String nome)
    {        
        for(Map.Entry<Divisao, List<Sensor>> entry : divisaoSensor.entrySet()) 
        {
            Divisao key = entry.getKey();
            List<Sensor> value = entry.getValue();
            
            if(key.getNome().equals(nome))
            {
                Iterator it = value.iterator();
                    
                while(it.hasNext())
                {
                    Equipamento obj = (Equipamento)it.next();
                    System.out.println(obj);                  
                }
            } 
        }
    }
    /*
     * Faz a filtragem de todos os sensores dentro da divisão pretendida 
     * @return List<Sensor>
    */
    public List<Sensor> getDivEquipSensor(String nome)
    {     
        List<Sensor> lista = new ArrayList<>();
        
        for(Map.Entry<Divisao, List<Sensor>> entry : divisaoSensor.entrySet()) 
        {
            Divisao key = entry.getKey();
            List<Sensor> value = entry.getValue();

            if(key.getNome().equals(nome))
            {
                Iterator it = value.iterator();
                    
                while(it.hasNext())
                {
                    Sensor obj = (Sensor)it.next();
                    lista.add(obj);                     
                }
            } 
        }
        return lista;
    }
    
    /*
     * Faz um filtro da divisão pretendida e adiciona o equipamento pretendido
     * @param div Divisão
     * @param equi Equipamento
    */
    public void getDivisao(String nome, Equipamento equi)
    {
        divisao.entrySet().stream().map((entry) -> 
                entry.getKey()).filter((key) -> 
                        (key.getNome().equals(nome))).forEachOrdered((key) -> 
                        {
                            addEquipamentoDivisao(key,equi);
                            key.addEquipamento(equi);
                        });  
    }
    
    /*
     * Retorna uma divisão consoante o nome
     * @param nome Nome da Divisão pretendida
     * @return divisão
    */
    public Divisao getDiv(String nome)
    {         
        for (Divisao key : divisao.keySet() )
        {
            if(key.getNome().equals(nome))
            {
                return key;
            } 
        }   
        return null;
    }
    
    /*
     * Retorna todos os equipamentos dentro da divisão pretendida 
     * @return List<Equipamento>
    */
    public List<Equipamento> getDivEquip(String nome)
    {     
        List<Equipamento> lista = new ArrayList<>();
        
        for(Map.Entry<Divisao, List<Equipamento>> entry : divisao.entrySet()) 
        {
            Divisao key = entry.getKey();
            List<Equipamento> value = entry.getValue();

            if(key.getNome().equals(nome))
            {
                Iterator it = value.iterator();
                    
                while(it.hasNext())
                {
                    Equipamento obj = (Equipamento)it.next();
                    lista.add(obj);                     
                }
            } 
        }
        return lista;
    }
    
    /*
     * Módulo da Temperatura
    */
    public void moduloTemp()
    {
        for(Map.Entry<Divisao, List<Atuador>> entry : divisaoAtuador.entrySet()) 
        {
            Divisao key = entry.getKey();
            
            if(key.getTemp() > 25 || key.getTemp() < 21)
            {
                for(Atuador eq : entry.getValue())
                {
                    if(eq.getNome().equals("Ar Condicionado"))
                    {                        
                        for(int i = 16;i<28;i++)
                        {
                            double total = (key.getTemp() + i) / 2;
                            
                            if(total>22 && total<24)
                            {
                                eq.setTemp(i);
                                getDivisaoTemp(key.getNome(),i);
                                break;
                            } 
                        }                 
                    }
                }
            }
        }  
    }
    
    /*
     * Gerar Divisões aleatórias
    */
    public Divisao gerarDivisao()
    {
        List<Map.Entry<Divisao, List<Equipamento>>> entries = new ArrayList<>(divisao.entrySet());
        Collections.shuffle(entries);
        for (Map.Entry<Divisao, List<Equipamento>> entry : entries) 
        {
            return entry.getKey();
        }
        return null;
    }
    
    /*
     * Módulo de Alarme
    */
    public void moduloAlarme(int pin)
    {
        if(pin != 0)
        {
            List<Map.Entry<Divisao, List<Atuador>>> entries = new ArrayList<>(divisaoAtuador.entrySet());
            Collections.shuffle(entries);
            for (Map.Entry<Divisao, List<Atuador>> entry : entries) 
            {
                Divisao key = entry.getKey();

                System.out.println("");
                System.out.println("Detetado movimento");
                System.out.print("Divisão: " + key.getNome());    
                            
                Scanner stdin = new Scanner(System.in);
        
                System.out.println("\n");
                System.out.print("Insira o pin: ");
                int pin1 = stdin.nextInt();
                
                if(pin != pin1)
                {
                    System.out.println("");
                    System.out.println("PIN inválido!");
                }  
                else
                {
                    for(Atuador eq : entry.getValue())
                    {
                        if(eq.getNome().equals("Camara Fotografica"))
                        {   
                            eq.tirarFoto();
                            System.out.println("");
                            System.out.println("Alarme desligado!");
                            this.pin = 0;
                            break;
                        }
                        else if(eq.getNome().equals("Camara Video"))
                        {
                            eq.tirarVideo();
                            System.out.println("");
                            System.out.println("Alarme desligado!");
                            this.pin = 0;
                            break;
                        }
                        else
                        {
                            System.out.println("");
                            System.out.println("Divisão sem camaras!");
                            this.pin = 0;
                            break;
                        }
                                
                    }
                } 
                break;
            }  
        }        
    }
    
    /*
     * Módulo de Luminosidade
    */
    public void moduloLuminosidade()
    {
        for(Map.Entry<Divisao, List<Atuador>> entry : divisaoAtuador.entrySet()) 
        {
            Divisao key = entry.getKey();
            
            if(key.getLuz()> 80 || key.getLuz()< 20)
            {
                for(Atuador eq : entry.getValue())
                {
                    if(eq.getNome().equals("Lâmpada"))
                    {                                                 
                        for(int i = 1;i<100;i++)
                        {
                            int total = (key.getLuz() + (i * 100)/20)/2;
                            
                            if(total>32 && total<60)
                            {
                                eq.setLuz(i);
                                getDivisaoLuz(key.getNome(),i);
                                break;
                            } 
                        }                 
                    }
                } 
            }  
        }
    }
    
    
    /*
     * Altera a temperatura ambiente de uma Divisão consoante a temperatura inserida
     * @param nome Nome da Divisão pretendida
     * @param temp Temperatura
    */
    public void getDivisaoTemp(String nome, double temp)
    {
        for(Map.Entry<Divisao, List<Equipamento>> entry : divisao.entrySet()) 
        {
            Divisao key = entry.getKey();
            
            if (key.getNome().equals(nome))
            {
                if(key.getTemp() != 0.0)
                {
                    double total = (key.getTemp()+temp)/2;
                    key.setTemp(total);
                }
                else
                {
                    key.setTemp(temp);
                }
            }
        }  
    }
    
    /*
     * Altera a luminosidade de uma Divisão consoante o nivel inserido
     * @param nome Nome da Divisão pretendida
     * @param luz Luminosidade
    */
    public void getDivisaoLuz(String nome, int luz)
    {
        for(Map.Entry<Divisao, List<Equipamento>> entry : divisao.entrySet()) 
        {
            Divisao key = entry.getKey();
            
            if (key.getNome().equals(nome))
            {
                if(key.getLuz()!= 0)
                {
                    int total = (key.getLuz() + (luz * 100)/20)/2;
                    key.setLuz(total);
                }
                else
                {
                    int total = (luz * 100)/20;
                    key.setLuz(total);
                }
            }
        }  
    }
    
    /**
     * Guardar ConsolaCentral para um ficheiro
     * @param dest Caminho do ficheiro
     * @throws IOException Ficheiro
     */
    public void save(File dest) throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(dest));        
        os.writeObject(sistema);
        os.close();
    }
    
    /**
     * Carrega ConsolaCentral já existente num ficheiro
     * @param source Caminho do ficheiro
     * @return sistema
     * @throws IOException Ficheiro
     * @throws ClassNotFoundException Classe não encontrada
     */
    public static ConsolaCentral load(File source) throws IOException, ClassNotFoundException
    {
        try 
        {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(source));
            sistema = (ConsolaCentral) is.readObject();
            is.close();
            return sistema;
        }
        catch(IOException e) 
        {
            throw new IOException("Unable to make a valid filename for " + source.getName());
        }
    }
    
}
