/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TraduccionSemantica;

/**
 *
 * @author Ariel
 */
public class RS_CONTROL extends RS{
    private String tipo;
    public RS_CONTROL(String pTipo){
        tipo=pTipo;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String pTipo){
        tipo=pTipo;
    }
}
