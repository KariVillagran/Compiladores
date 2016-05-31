/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

/**
 *
 * @author kari
 * 
 * este es como el digrafo ue aparece ahi jajajaja
 */
public class RowTransition {
    
    private int stateName;
    private int lenguageElement;
    private int valueTransition;
    
    public RowTransition(int stateName, int lenguageElement, int valueTransition)
    {
        this.stateName = stateName;
        this.lenguageElement = lenguageElement;
        this.valueTransition = valueTransition;
    }

    /**
     * @return the stateName
     */
    public int getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(int stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the lenguageElement
     */
    public int getLenguageElement() {
        return lenguageElement;
    }

    /**
     * @param lenguageElement the lenguageElement to set
     */
    public void setLenguageElement(int lenguageElement) {
        this.lenguageElement = lenguageElement;
    }

    /**
     * @return the valueTransition
     */
    public int getValueTransition() {
        return valueTransition;
    }

    /**
     * @param valueTransition the valueTransition to set
     */
    public void setValueTransition(int valueTransition) {
        this.valueTransition = valueTransition;
    }
    
    
}
