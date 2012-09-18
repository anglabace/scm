/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.common.constant;


/**
 *
 * @author jinsite
 */
public enum Ethnics {
    African_American("African American"),
    Caucasian("Caucasian"),
    Chinese("Chinese"),
    Hispanic("Hispanic"),
    Indian("Indian"),
    Japanese("Japanese"),
    Korean("Korean"),
    Other("Other"),
    Unkown("Unkown");
    private String value;
    Ethnics(String v){
        value=v;
    }
    public String value()
    {
        return value;
    }
    public static Ethnics fromValue(String v){
        for(Ethnics e:Ethnics.values())
            if(e.value.equals(v))
                return e;
        throw new IllegalArgumentException(v);
    }
    public String getKey(){
        return this.name();
    }
    public String getValue(){
        return this.value;
    }

}
