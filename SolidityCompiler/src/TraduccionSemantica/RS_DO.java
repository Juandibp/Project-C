/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TraduccionSemantica;
import static TraduccionSemantica.TIPO_DO.*;
/**
 *
 * @author adria
 */
public class RS_DO extends RS{
    
    private TIPO_DO tipo;
    private String valor;

    public RS_DO(String valor) {
        this.valor = valor;
    }
    
    private void setDir() {
        tipo = TIPO_DO.DIR;
    }
    
    private void setConst() {
        tipo = TIPO_DO.CONST;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TIPO_DO getTipo() {
        return tipo;
    }  
}
