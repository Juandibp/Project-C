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
public class RS_WHILE extends RS{
    private String while_label;
    private String exit_label;

    public RS_WHILE(String while_label, String exit_label) {
        this.while_label = while_label;
        this.exit_label = exit_label;
    }
    
    public RS_WHILE(){
        
    }

    public String getWhile_label() {
        return while_label;
    }

    public void setWhile_label(String while_label) {
        this.while_label = while_label;
    }

    public String getExit_label() {
        return exit_label;
    }

    public void setExit_label(String exit_label) {
        this.exit_label = exit_label;
    }
}
