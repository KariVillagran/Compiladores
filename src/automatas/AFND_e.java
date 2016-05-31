/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

/**
 *
 * @author kari
 */
public class AFND_e {
    
    private String finalState;
    private String initialState;
    private String [][] transitionTable;
    
    public AFND_e(String [][] maquina)
    {
        this.transitionTable = maquina;
        this.finalState = this.transitionTable[this.transitionTable.length-1][0];
        this.initialState = this.transitionTable[1][0];
    }
    
    private void reset()
    {
        this.finalState = this.transitionTable[this.transitionTable.length-1][0];
        this.initialState = this.transitionTable[0][0];
    }
    
    public String getFinalState() {        
        return finalState;
    }

    
    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    /**
     * @return the transitionTable
     */
    public String[][] getTransitionTable() {
        return transitionTable;
    }

    public void setTransitionTable(String[][] transitionTable) {
        this.transitionTable = transitionTable;
        reset();
    }

    public void setStateInTable(String value,int row, int column)
    {
        this.transitionTable[row][column] = value;
        reset();
    }
    
    public int getLenghtTableTransition()
    {
        return this.transitionTable.length;
    }
    
    public int getCountColumn()
    {
        return this.transitionTable[0].length;
    }
    
    public void setRowFinalState(String value)
    {
        for(int column = 1 ; column < getCountColumn(); column++)
        {
            this.transitionTable[getLenghtTableTransition()-1][column] = this.transitionTable[getLenghtTableTransition()-1][column] + "," + value;
        }
    }
    
    public void addState(int row, String state)
    {
        for(int column = 1 ; column < getCountColumn(); column++)
        {
            this.transitionTable[row][column] = this.transitionTable[row][column] + "," + state;
        }
    }
    
    public void setRowFinalState()
    {
        for(int column = 1 ; column < getCountColumn(); column++)
        {
            this.transitionTable[getLenghtTableTransition()-1][column] = "phi";
        }
    }
    
    public int getIndex(String state)
    {
        int in = 0;
        for(int j = 0 ; j < this.getLenghtTableTransition() ; j++)
        {
            String valor = this.getTransitionTable()[j][0].toString();
            if(valor.equals(state))
            {
                in = j;
                continue;
            }
        }
        return in;
    }
}
