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
    
    private ArrayList listStates;
    private ArrayList diccionario;
    private String [][] transitionTable;

    public AFND(String [][] transicion)
    {
        this.transitionTable = transicion;
        this.listStates = getStates();
    }
    
    // obtiene la lista de estados del automata
    public ArrayList getStates()
    {
        ArrayList estados = new ArrayList();
        
        for(int i = 1; i < this.getTransitionTable().length ; i++)
        {
            estados.add(this.getTransitionTable()[i][0]);
        }        
        return estados;
    }
    
    // obtiene la lista de estados del automata
    public ArrayList getDictionary()
    {
        ArrayList estados = new ArrayList();
        
        for(int i = 1; i < this.transitionTable[0].length ; i++)
        {
            estados.add(this.transitionTable[0][i]);
        }        
        return estados;
    }
    
    // obtiene el nuevo diccionario del automata
    public void getNewStates()
    {       
        for (int i = 1; i < this.transitionTable.length; i++)
        {
            for (int x = 1; x < this.transitionTable[i].length; x++)
            {
                if(isValidString(this.transitionTable[i][x],this.transitionTable))
                {
                    getListStates().add(this.transitionTable[i][x]);
                }
            }
        }
    }
    
    // Se considera un string valido aquel que no esta en la lista y no es estado "phi"
    public boolean isValidString(Object x,String[][] matriz)
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
    public String[][] reveleStates()
    {
        String [][] newMatrix = new String [getListStates().size()+1][this.transitionTable[0].length];
        for (int i = 1; i < this.transitionTable.length; i++)
        {
            for (int x = 1; x < this.transitionTable[i].length; x++)
            {
                if (getListStates().contains(this.transitionTable[i][x]))
                {
                    //newMatrix[i][x] = Integer.toString(listStates.indexOf(matriz[i][x])+1);
                    newMatrix[i][x] = this.transitionTable[i][x];
                }
                else
                {
                    getListStates().add(this.transitionTable[i][x]);
                    //newMatrix[i][x] = Integer.toString(listStates.indexOf(matriz[i][x])+1);
                    newMatrix[i][x] = this.transitionTable[i][x];
                }
            }
        }
        return newMatrix;
    }
    
    public void finishStates()
    {
        String error = "";
        for (int i = 1; i < this.transitionTable.length; i++)
        {
            for (int x = 1; x < this.transitionTable[i].length; x++)
            {
                int a = getListStates().indexOf(this.transitionTable[i][x])+1;
                
                this.transitionTable[i][x] = String.valueOf(getListStates().indexOf(this.transitionTable[i][x]));
            }
        }
        try
        {
            for (int i = 1; i < this.transitionTable.length; i++)
            {
                this.transitionTable[i][0] = getListStates().get(i-1).toString();            
            }
            for (int i = 1; i < this.transitionTable[0].length; i++)
            {
                this.transitionTable[0][i] = getDiccionario().get(i-1).toString();            
            }
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
    }
    
    // agrega los saltos a la lista de estados nuevos
    public String[][] addNewStates(String[][] matriz)
    {
        String error = "";
        for (int i = 0; i < getListStates().size(); i++)
        {
            try 
            {
                if(getListStates().get(i).toString().contains(","))
                {
                    String [] estados = getListStates().get(i).toString().split(",");
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
                matriz[i][0] = getListStates().get(i-1).toString();            
            }

            for (int i = 1; i < getListStates().size(); i++)
            {
                matriz[0][i] = getDiccionario().get(i-1).toString();            
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
    
    public boolean isAFND(String[][] matriz)
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
    
    public String [][] getAFDphi(String [][] AFND)
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
            getListStates().add("phi");
        }
        
        return result;
    }
            
    public String[][] execMethod(String[][] x)
    {
        getNewStates();
        String [][] result = addNewStates(reveleStates());
        result = setAFND(result);        
        if (isAFND(result))
        {
            result = execMethod(result);
        }
        return result;        
    }

    /**
     * @return the listStates
     */
    public ArrayList getListStates() {
        return listStates;
    }

    /**
     * @param listStates the listStates to set
     */
    public void setListStates(ArrayList listStates) {
        this.listStates = listStates;
    }

    /**
     * @return the diccionario
     */
    public ArrayList getDiccionario() {
        return diccionario;
    }

    /**
     * @param diccionario the diccionario to set
     */
    public void setDiccionario(ArrayList diccionario) {
        this.diccionario = diccionario;
    }

    /**
     * @return the transitionTable
     */
    public String[][] getTransitionTable() {
        return transitionTable;
    }

    /**
     * @param transitionTable the transitionTable to set
     */
    public void setTransitionTable(String[][] transitionTable) {
        this.transitionTable = transitionTable;
    }
}