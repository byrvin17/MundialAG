/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mundialag;

/**
 *
 * @author Byron Lima
 * @author Gabriela Rodríguez
 */
public class Equipos {
    
    
    private String nombre;
    private String ranking;
    private String grupo;
    private String numCopas;
    private String partido1;
    private String partido2;
    private String partido3;

    public Equipos(String nombre, String ranking, String grupo, String numCopas, String partido1, String partido2, String partido3) {
        this.nombre = nombre;
        this.ranking = ranking;
        this.grupo = grupo;
        this.numCopas = numCopas;
        this.partido1 = partido1;
        this.partido2 = partido2;
        this.partido3 = partido3;
    }

    public Equipos() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNumCopas() {
        return numCopas;
    }

    public void setNumCopas(String numCopas) {
        this.numCopas = numCopas;
    }

    public String getPartido1() {
        return partido1;
    }

    public void setPartido1(String partido1) {
        this.partido1 = partido1;
    }

    public String getPartido2() {
        return partido2;
    }

    public void setPartido2(String partido2) {
        this.partido2 = partido2;
    }

    public String getPartido3() {
        return partido3;
    }

    public void setPartido3(String partido3) {
        this.partido3 = partido3;
    }

    
    
    @Override
    public String toString() {
        return nombre+"\t"+ranking+"\t"+grupo+"\t"+numCopas+"\t"+partido1+"\t"+partido2+"\t"+partido3+"\n";
    }
    
}
