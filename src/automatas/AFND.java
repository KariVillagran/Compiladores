/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automatas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author avillagran
 */
public class AFND {
    
    static ArrayList listStates = new ArrayList();
    static ArrayList diccionario = new ArrayList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //String [][] x={{"","0","1"},{"1","2","1,2"},{"2","2,3","3"},{"3","3","3"}}; 
        
        String [][] x={{"","a","b","c"},{"1","1,2,3","2,3","3"},{"1,2,3","1,2,3","2,3","3"},{"2,3","phi","2,3","3"},{"3","phi","phi","3"}};
        //String [][] x= IngresarMatriz.llenarMatrix();
        
        //System.out.println(getResult(x, "123", "q0"));
        
        listStates = getStates(x);
        diccionario = getDictionary(x);
        String [][] result = execMethod(x);
        result = getAFDphi(result);
        result = finishStates(result);
        
        for (int i = 0; i < result.length; i++)
        {
            System.out.println("----------------------");
            for (int j = 0; j < result[0].length; j++)
            {   
                System.out.println("Indice i:" + i + " j:" + j + " valor: " + result[i][j]);
            }
        }
        
        System.out.println("----------------------");
        System.out.println("Contenido de estados");
        for (int j = 0; j < listStates.size(); j++)
        {   
            System.out.println("valor: " + listStates.get(j));
        }
        System.out.println("----------------------");
        System.out.println("Contenido de diccionario");
        for (int j = 0; j < diccionario.size(); j++)
        {   
            System.out.println("valor: " + diccionario.get(j));
        }
        
        if(getResult(result, "a", "2,3"))
        {
            System.out.println("La cadena pertenece al lengaueje");
        }
        else
        {
            System.out.println("La cadena NO pertenece al lengaueje");
        }
        
        
    }    
    
    // automata finito deterministico
    public static boolean getResult(String[][] matriz,String word, String EstadoFinal)
    {
        boolean salida=false;
        int EstadoTransitorio=1;
        for (int i = 1; i <= word.length(); i++)
        {
            int j=i-1;
            for (int x = 1; x < matriz.length; x++)
            {
                if(word.substring(j,i).equals(matriz[0][x]))
                {
                    EstadoTransitorio=Integer.parseInt(matriz[EstadoTransitorio][x]); 
                    break;
                }
            }
        }        
        if(EstadoFinal.equals(matriz[EstadoTransitorio][0]))
        {
            salida = true;
        }
         return salida;
    }
    
    // obtiene la lista de estados del automata
    public static ArrayList getStates(String[][] matriz)
    {
        ArrayList estados = new ArrayList();
        
        for(int i = 1; i < matriz.length ; i++)
        {
            estados.add(matriz[i][0]);
        }        
        return estados;
    }
    
    // obtiene la lista de estados del automata
    public static ArrayList getDictionary(String[][] matriz)
    {
        ArrayList estados = new ArrayList();
        
        for(int i = 1; i < matriz[0].length ; i++)
        {
            estados.add(matriz[0][i]);
        }        
        return estados;
    }
    
    // obtiene el diccionario del automata
    public static void getNewStates(String[][] matriz)
    {       
        for (int i = 1; i < matriz.length; i++)
        {
            for (int x = 1; x < matriz[i].length; x++)
            {
                if(isValidString(matriz[i][x],matriz))
                {
                    listStates.add(matriz[i][x]);
                }
            }
        }
    }
    
    public static boolean isValidString(Object x,String[][] matriz)
    {
        boolean result = false;
        String error = "";
        try{
            if(!listStates.contains(x.toString()) && x.toString().equals("phi")== false) //&& Integer.parseInt(x.toString()) > listStates.size())
            {
                result = true;
            }
        }
        catch (Exception e)
        {
            error = e.getMessage();
        }
        return result;
    }
    
    // revela la nueva matriz con los estados descubiertos nuevos
    public static String[][] reveleStates(String[][] matriz)
    {
        String [][] newMatrix = new String [listStates.size()+1][matriz[0].length];
        for (int i = 1; i < matriz.length; i++)
        {
            for (int x = 1; x < matriz[i].length; x++)
            {
                if (listStates.contains(matriz[i][x]))
                {
                    //newMatrix[i][x] = Integer.toString(listStates.indexOf(matriz[i][x])+1);
                    newMatrix[i][x] = matriz[i][x];
                }
                else
                {
                    listStates.add(matriz[i][x]);
                    //newMatrix[i][x] = Integer.toString(listStates.indexOf(matriz[i][x])+1);
                    newMatrix[i][x] = matriz[i][x];
                }
            }
        }
        return newMatrix;
    }
    
    public static String[][] finishStates(String[][] matriz)
    {
        String [][] newMatrix = new String [listStates.size()+1][matriz[0].length];
        String error = "";
        for (int i = 1; i < matriz.length; i++)
        {
            for (int x = 1; x < matriz[i].length; x++)
            {
                int a = listStates.indexOf(matriz[i][x])+1;
                newMatrix[i][x] = Integer.toString(listStates.indexOf(matriz[i][x])+1);                    
            }
        }
        try
        {
            for (int i = 1; i < matriz.length; i++)
            {
                newMatrix[i][0] = listStates.get(i-1).toString();            
            }

            for (int i = 1; i < matriz[0].length; i++)
            {
                newMatrix[0][i] = diccionario.get(i-1).toString();            
            }
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        
        return newMatrix;
    }
    
    // agrega los saltos a la lista de estados nuevos
    public static String[][] addNewStates(String[][] matriz)
    {
        String error = "";
        for (int i = 0; i < listStates.size(); i++)
        {
            try 
            {
                if(listStates.get(i).toString().contains(","))
                {
                    String [] estados = listStates.get(i).toString().split(",");
                    for (int x = 0; x < estados.length; x++)
                    {
                        for (int j = 1; j < matriz[0].length; j++)
                        {
                            matriz[i+1][j] = unionConjuntos(matriz[i+1][j], matriz[Integer.parseInt(estados[x].toString())][j]);
                        }
                    }
                }
            }
            catch (Exception e)
            {
                error = e.getMessage();
            }
        }
        
        try
        {
            for (int i = 1; i < matriz.length; i++)
            {
                matriz[i][0] = listStates.get(i-1).toString();            
            }

            for (int i = 1; i < listStates.size(); i++)
            {
                matriz[0][i] = diccionario.get(i-1).toString();            
            }
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        
        return matriz;
    }
    
    
    public static String unionConjuntos(String q, String q1)
    {
        if(q != null)
        {
            if((q.trim().equals("") || q.isEmpty()))
            {
                q = q1;
            }
            else
            {
                q = q + "," + q1;
            }
        }
        else
        {
            q = q1;
        }
        return q;
    }
    
    public static String cleanState(String q,String[][] a)
    {
        String result = "";
        int j = 0;
        ArrayList lista = new ArrayList();
        
        // probando matriz
        if(q == null)
        {
            int prueba = 1;
        }
        
        if(q != null && q.contains(","))
        {
            String [] elementos= q.split(",");            
            for(int i = 0; i < elementos.length; i++)
            {
                lista.add(elementos[i]);
            }
            HashSet<String> hashSet = new HashSet<String>(lista);
            lista.clear();
            lista.addAll(hashSet);

            Collections.sort(lista);


            for (j = 0; j < lista.size(); j++)
            {
                if(!lista.get(j).equals("phi"))
                {
                    result = unionConjuntos(result, lista.get(j).toString());
                }
            }
        }
        else
        {
            result = q;
        }
        
        return result;
    }
    
    public static boolean isAFND(String[][] matriz)
    {
        boolean result = false;
        for (int i = 1; i < matriz.length; i++)
        {
            for (int x = 1; x < matriz[i].length; x++)
            {
                if(!listStates.contains(matriz[i][x]) && matriz[i][x] != null) //&& Integer.parseInt(matriz[i][x]) > listStates.size())
                {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    
    public static String [][] setAFND(String[][] matriz)
    {
        for (int i = 1; i < matriz.length; i++)
        {
            for (int x = 1; x < matriz[i].length; x++)
            {
                matriz[i][x] =  cleanState(matriz[i][x],matriz);
            }
        }
        return matriz;
    }
    
    public static String [][] getAFDphi(String [][] AFND)
    {
        String [][] result = new String [AFND.length+1][AFND[0].length];
        
        for (int i = 0; i < AFND.length; i++)
        {
            for (int x = 0; x < AFND[i].length; x++)
            {
                result[i][x] = AFND[i][x];
            }
        }        
        
        for (int i = 0; i < result[0].length; i++)
        {
            result[AFND.length][i] = "phi";
        }
        
        if(!listStates.contains("phi"))
        {
            listStates.add("phi");
        }
        
        return result;
    }
            
    public static String[][] execMethod(String[][] x)
    {
        getNewStates(x);
        String [][] result = addNewStates(reveleStates(x));
        result = setAFND(result);        
        if (isAFND(result))
        {
            result = execMethod(result);
        }
        return result;        
    }
}