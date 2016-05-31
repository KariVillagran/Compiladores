/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.util.ArrayList;

/**
 *
 * @author kari
 */
public class Thompson {
    
    private String expresionRegular; 
    int [] diccionario = {0,1};
    String [] alfabeto = {"+","*","(",")"};
    int phar = 0;
    ArrayList<RowTransition> digraph = new ArrayList<RowTransition>();    
    
    public Thompson(String regularExpresion)
    {
        this.expresionRegular = regularExpresion;
    }
    
    public void recognizeString()
    {
        char [] word = this.expresionRegular.toCharArray();
        for(char character:word)
        {
            
        }
        
    }

    /**
     * @return the expresionRegular
     */
    public String getExpresionRegular() {
        return expresionRegular;
    }

    /**
     * @param expresionRegular the expresionRegular to set
     */
    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }
    
    public String[][] concat(String[][] a, String[][] b)
    {
        String[][] result = new String[a.length + b.length][a[0].length];
        
        System.arraycopy(a,0,result,0,a.length);
        System.arraycopy(b, 1, result, a.length, b.length-1);
        
        return result;
    }
    
    public String[][] union(String[][] a, String[][] b)
    {
        AFND_e result = new AFND_e(new String[(a.length-1) + (b.length-1) + 3][a[0].length]);
        AFND_e maquina1 = new AFND_e(a);
        AFND_e maquina2 = new AFND_e(b);
        
        // se pone el nombre a los estados creados inicial y final
        result.setStateInTable("qInit", 1 ,0);
        result.setStateInTable("qFinal", result.getLenghtTableTransition()-1 ,0);
        
        // Elimina los encabezados del diccionario
        maquina1.setTransitionTable(dropDictionary(maquina1.getTransitionTable()));
        maquina2.setTransitionTable(dropDictionary(maquina2.getTransitionTable()));
        
        // Agrega a la fila del estadofinal la transicion al estado final de la maquina resultante
        maquina1.setRowFinalState(result.getFinalState());
        maquina2.setRowFinalState(result.getFinalState());
        
        // agrega al estado inicial la salida a cualquiera de las maquinas unidas
        result.setTransitionTable(putAddStateInit(result.getTransitionTable(), maquina1.getInitialState(), maquina2.getInitialState()));
        result.setTransitionTable(putDictionary(result.getTransitionTable(), getDictionary(a)));
        
        System.arraycopy(maquina1.getTransitionTable(),0,result.getTransitionTable(),2,maquina1.getLenghtTableTransition());
        System.arraycopy(maquina2.getTransitionTable(), 0, result.getTransitionTable(), maquina1.getLenghtTableTransition()+2, maquina2.getLenghtTableTransition());        
        
        result.setRowFinalState();
        return result.getTransitionTable();
    }
    
    public String[][] closing(String[][] a) 
    {
        AFND_e result = new AFND_e(new String[(a.length-1)+3][a[0].length]);
        AFND_e maquina1 = new AFND_e(a);
        
        result.setStateInTable("qInit", 1 ,0);
        result.setStateInTable("qFinal", result.getLenghtTableTransition()-1 ,0);
        
        maquina1.setTransitionTable(dropDictionary(maquina1.getTransitionTable()));
        result.setTransitionTable(putDictionary(result.getTransitionTable(), getDictionary(a)));
        
        result.setTransitionTable(putAddStateInit(result.getTransitionTable(), maquina1.getInitialState(), result.getFinalState()));
        
        System.arraycopy(maquina1.getTransitionTable(),0,result.getTransitionTable(),2,maquina1.getLenghtTableTransition());
        
        result.addState(result.getIndex(maquina1.getFinalState()), result.getFinalState());
        
        result.setRowFinalState();
        
        return result.getTransitionTable();
    }
    
    public static ArrayList<String> getDictionary(String[][] matriz)
    {
        ArrayList estados = new ArrayList();
        
        for(int i = 1; i < matriz[0].length ; i++)
        {
            estados.add(matriz[0][i]);
        }        
        return estados;
    }
    
    public String[][] putDictionary(String[][] matriz, ArrayList<String> dictionary)
    {
        for(String value:dictionary)
        {
            matriz[0][dictionary.indexOf(value) + 1] = value;
        }
        return matriz;
    }
    
    public String[][] dropDictionary(String [][] matriz)
    {
        String [][] result = new String[matriz.length-1][matriz[0].length-1];        
        System.arraycopy(matriz, 1, result, 0, matriz.length-1);
        return result;
    }
    
    public String[][] putAddStateInit(String[][] matriz, String m1, String m2)
    {
        for(int i = 1; i < matriz[0].length; i++)
        {
            matriz[1][i] = m1 + "," + m2;
        }
        return matriz;
    }
    
    public String[][] putAddStateFinal(String[][] matriz, String initState, String finalState)
    {
        for(int i = 1; i < matriz[0].length; i++)
        {
            matriz[0][i] = finalState;
        }
        return matriz;
    }
    
    
    
    
}
