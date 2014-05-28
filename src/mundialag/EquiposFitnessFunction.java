/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mundialag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author Byron Lima
 * @author Gabriela Rodríguez
 */
public class EquiposFitnessFunction extends FitnessFunction {

    List equipos = new ArrayList();
    List ranking = new ArrayList();

    public EquiposFitnessFunction(List equipos, List ranking) {
        this.equipos = equipos;
        this.ranking = ranking;
    }

    @Override
    protected double evaluate(IChromosome chromosome) {
        double score = 0;
//        double imdbScore = 0;

        List dups = new ArrayList();
        int badSolution = 1;

        for (int i = 0; i < chromosome.size(); i++) {

            IntegerGene agene = (IntegerGene) chromosome.getGene(i);
            int index = (Integer) chromosome.getGene(i).getAllele(); //obtiene index entero de la poblacion
//para ver que equpio pasa y cual noo van los valore obtenidos numcopas ranking fifa
            if (dups.contains(index)) {
                badSolution = 0;
            } else {
                dups.add(index);
            }

            Equipos equipo = (Equipos) equipos.get(index);
            double genreScore = getGenreScore(equipo);
            if (genreScore == 0) {
                badSolution = 0;
            }
            score = (score + Integer.parseInt(equipo.getPartido1()) + Integer.parseInt(equipo.getPartido2())) + Integer.parseInt(equipo.getPartido3()) + (genreScore);
            

        }
        return (score * badSolution);
    }

    private int getGenreScore(Equipos equipo) {
        int copas = 0;
        int contadorR = 0;
        int total  = 0;
        
        //Copas
        if ((Integer.parseInt(equipo.getNumCopas())) > 0) {
            copas = Integer.parseInt(equipo.getNumCopas()) * 2;
        }
        //ranking 1 - 10 = 12
        //ranking 11 - 20 = 10
        //ranking 21 - 30 = 8
        //ranking 31 - 40 = 6
        //ranking 41 - 50 = 4
        //ranking 51 - 60 = 2
        
        //Ranking
        if (((Integer.parseInt(equipo.getRanking())) >= 1) && ((Integer.parseInt(equipo.getRanking()))<=10)){
            contadorR = contadorR + 12;
            
        }
        
        if (((Integer.parseInt(equipo.getRanking()))>= 11) && ((Integer.parseInt(equipo.getRanking())<=20))){
            contadorR = contadorR + 10;
        }
        
        if (((Integer.parseInt(equipo.getRanking()))>= 21) && ((Integer.parseInt(equipo.getRanking()))<=30)){
            contadorR = contadorR + 8;
        }
        
        if (((Integer.parseInt(equipo.getRanking()))>= 31) && ((Integer.parseInt(equipo.getRanking()))<=40)){
            contadorR = contadorR + 6;
        }
        
        if (((Integer.parseInt(equipo.getRanking()))>= 41) && ((Integer.parseInt(equipo.getRanking()))<=50)){
            contadorR = contadorR + 4;
        }
        
        if (((Integer.parseInt(equipo.getRanking()))>= 51) && ((Integer.parseInt(equipo.getRanking()))<=60)){
            contadorR = contadorR + 2;
        }
        
        //Partidos
        
                
        
        total = copas + contadorR;
        
        return total;
    }
}
