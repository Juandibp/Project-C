/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TraduccionSemantica;


public class RS_IF extends RS {
    private String exit_label;
    private String else_label;

    public RS_IF(String exit_label, String else_label) {
        this.exit_label = exit_label;
        this.else_label = else_label;
    }

    public String getExit_label() {
        return exit_label;
    }

    public void setExit_label(String exit_label) {
        this.exit_label = exit_label;
    }

    public String getElse_label() {
        return else_label;
    }

    public void setElse_label(String else_label) {
        this.else_label = else_label;
    }
}
