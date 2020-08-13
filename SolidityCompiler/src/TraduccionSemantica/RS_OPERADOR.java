/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TraduccionSemantica;

/**
 *
 * @author adria
 */
public class RS_OPERADOR extends RS{
    private String operador;

    public RS_OPERADOR() {
    }

    public RS_OPERADOR(String operador) {
        this.operador = operador;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
}
