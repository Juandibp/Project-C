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
public class RS_RETURN extends RS{
    private String tipoReturn;
    private String nombreVar;

    public RS_RETURN() {
    }

    public RS_RETURN(String tipoReturn, String nombreVar) {
        this.tipoReturn = tipoReturn;
        this.nombreVar = nombreVar;
    }

    public String getTipoReturn() {
        return tipoReturn;
    }

    public void setTipoReturn(String tipoReturn) {
        this.tipoReturn = tipoReturn;
    }

    public String getNombreVar() {
        return nombreVar;
    }

    public void setNombreVar(String nombreVar) {
        this.nombreVar = nombreVar;
    }
}
