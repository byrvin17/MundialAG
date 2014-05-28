/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mundialag;


import archivos.*;
import vistas.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

/**
 *
 * @author Byron Lima
 * @author Gabriela Rodríguez
 */
public class TestEquiposFitness {

    private Configuration conf; //configuracion por efecto
    private SwappingMutationOperator swapper;
    private EquiposFitnessFunction fitnessFunction = null;
    public List equipos = new ArrayList();
    public List ranking = new ArrayList();
    public  List<Equipos> eq02 = new ArrayList<>();
    
    public List<Equipos> eq04(){
        return eq02;
    }
    private static final int MAX_ALLOWED_EVOLUTIONS = 1000; //generaciones
    private Chromosome equipoChromosome = null; 
    public String fitness01;
    public String pequipos01 = "";
    public String valor1 = "";
    public String valor2 = "";
    public String valor3 = "";

    public void initialize(String tipoEquipo) throws Exception {
        StringTokenizer st = new StringTokenizer(tipoEquipo);

        while (st.hasMoreElements()) {
            String genre = st.nextToken();
            ranking.add(genre);
        }

        equipos = this.cargarEquipos();

        conf = new DefaultConfiguration();
        Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST); //resetear la configuracion
        conf.setFitnessEvaluator(new DefaultFitnessEvaluator());
        conf.getGeneticOperators().clear(); //agrega caracteristicPROPERTY_FITEVAL_INSTas M y R

        swapper = new SwappingMutationOperator(conf);
        conf.addGeneticOperator(swapper);
        conf.setPreservFittestIndividual(true); //perservar la funcion de aptitud inicial
        conf.setPopulationSize(1000);
        conf.setKeepPopulationSizeConstant(false); //mantener el tamaño de la poblacion

        fitnessFunction = new EquiposFitnessFunction(equipos, ranking);

        conf.setFitnessFunction(fitnessFunction); //se fija la funcion fitness a la configuración
//cuatro pra el mundial
        Gene[] equiposGenes = new Gene[4];

        equiposGenes[0] = new IntegerGene(conf, 0, equipos.size() - 1);
        equiposGenes[1] = new IntegerGene(conf, 0, equipos.size() - 1);
        equiposGenes[2] = new IntegerGene(conf, 0, equipos.size() - 1);
        equiposGenes[3] = new IntegerGene(conf, 0, equipos.size() - 1);

        equipoChromosome = new Chromosome(conf, equiposGenes);
        //obtiene un valor del gen del cromosoma
        equiposGenes[0].setAllele(new Integer(0));
        equiposGenes[1].setAllele(new Integer(1));
        equiposGenes[2].setAllele(new Integer(2));
        equiposGenes[3].setAllele(new Integer(3));
        
        //plantilla de un cromosoma
        conf.setSampleChromosome(equipoChromosome);
        

    }

    private List cargarEquipos() {

        List<Equipos> l = new ArrayList<Equipos>();
        LeerArchivos ac = new LeerArchivos();

        try {
            ac.leerdatos();
            for (int i = 0; i < 32; i++) {
                l.add(ac.paises.get(i));
            }


        } catch (FileNotFoundException ex) {
            //Logger.getLogger(CrearGenotype.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;

    }

    public void testSelectFittestEquipos() throws Exception {

        equipos = this.cargarEquipos();

        Genotype population = Genotype.randomInitialGenotype(conf); //iniciar genotype aleatorio

        IChromosome bestSolutionSoFar = equipoChromosome;
        //datos por cada una de las generaciones

        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
            IChromosome candidateBestSolution = population.getFittestChromosome();

            if (candidateBestSolution.getFitnessValue() > bestSolutionSoFar.getFitnessValue()) {
                bestSolutionSoFar = candidateBestSolution;

            }
        }
        printSolution(bestSolutionSoFar, equipos);
        valor1 = valor1 + bestSolutionSoFar;
        
    }

    public void printSolution(IChromosome solution, List equipos) {
//        System.out.println("\n");
//        System.out.println("Fitness: " + solution.getFitnessValue());
       // this.setFitness(solution.getFitnessValue())
        fitness01 = String.valueOf(solution.getFitnessValue());
        System.out.println(solution.size());

        for (int i = 0; i < solution.size(); i++) {
            int index = (Integer) solution.getGene(i).getAllele();  
            Equipos equipo02 = (Equipos) equipos.get(index);
            eq02.add(equipo02);
           // System.out.println(equipo02.toString());
            pequipos01 =  pequipos01 +equipo02.toString();
        }
      System.out.println("\n =========");
    }
    
}
