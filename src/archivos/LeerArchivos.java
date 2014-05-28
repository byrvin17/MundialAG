/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import mundialag.Equipos;

/**
 *
 * @author Byron Lima
 * @author Gabriela Rodríguez
 */
public class LeerArchivos {

    RandomAccessFile archivo;
    String arreglo[] = null;
    public List<Equipos> paises = new ArrayList<Equipos>();
    public List<Equipos> p = new ArrayList<Equipos>();

    public LeerArchivos(List<Equipos> listaPaises) {
        p = listaPaises;
    }

    public RandomAccessFile getArchivo() {
        return archivo;
    }

    public void setArchivo(RandomAccessFile archivo) {
        this.archivo = archivo;
    }

    public String[] getArreglo() {
        return arreglo;
    }

    public void setArreglo(String[] arreglo) {
        this.arreglo = arreglo;
    }

    public List<Equipos> getP() {
        return p;
    }

    public void setP(List<Equipos> p) {
        this.p = p;
    }

    public LeerArchivos() {
        try {

            archivo = new RandomAccessFile(System.getProperty("user.dir") + "/src/archivos/Mundial.csv", "rw");
            // archivo = new RandomAccessFile("C:\\Users\\Freddy\\Desktop\\respaldo.doc", "rw");
        } catch (Exception e) {
        }
    }

    public void GuardarDatos(String nombre, String ranking, String grupo, String numCopas, String partido1, String partido2, String partido3) throws IOException {
        Equipos eqmundial = new Equipos();
        try {
            eqmundial.setNombre(nombre);
            eqmundial.setRanking(ranking);
            eqmundial.setGrupo(grupo);
            eqmundial.setNumCopas(numCopas);
            eqmundial.setPartido1(partido1);
            eqmundial.setPartido1(partido2);
            eqmundial.setPartido1(partido3);
            try {
                archivo.seek(archivo.length());
                archivo.writeBytes(eqmundial.getNombre() + "," + eqmundial.getRanking() + "," + eqmundial.getGrupo() + "," + eqmundial.getNumCopas() + "," + eqmundial.getPartido1() + "," + eqmundial.getPartido2() + "," + eqmundial.getPartido3() + "\n");
                JOptionPane.showMessageDialog(null, "Ha sido registrado con exito");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "El archivo no se puede escribir");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos al Uso del Sistema");
        }
    }

    public void leerdatos() throws FileNotFoundException {
        Equipos emundial = null;
        String nombre = "";
        String ranking = "";
        String grupo = "";
        String numCopas = "";
        String partido1 = "";
        String partido2 = "";
        String partido3 = "";
        File direccion = new File(System.getProperty("user.dir") + "/src/archivos/Mundial.csv");
        
        if (direccion.exists()) {
            BufferedReader archivoEntrad;
            archivoEntrad = new BufferedReader(new FileReader(direccion));
            try {
                arreglo = new String[6];
                String linea;
                while (((linea = archivoEntrad.readLine()) != null)) {
                    System.out.println(linea);
                    emundial = new Equipos();
                    arreglo = linea.split(",");
                    nombre = arreglo[0];
                    ranking = arreglo[1];
                    grupo = arreglo[2];
                    numCopas = arreglo[3];
                    partido1 = arreglo[4];
                    partido2 = arreglo[5];
                    partido3 = arreglo[6];
                    emundial.setNombre(nombre);
                    emundial.setRanking (ranking);
                    emundial.setGrupo(grupo);
                    emundial.setNumCopas(numCopas);
                    emundial.setPartido1(partido1);
                    emundial.setPartido2(partido2);
                    emundial.setPartido3(partido3);
                    this.paises.add(emundial);
                }
                archivoEntrad.close();
            } catch (IOException ee) {
                System.out.println("Error al leer el archivo " + archivo + ":" + ee.toString());
                System.exit(1);
            }
        }
    }

    public Equipos buscar(String datoBuscar) throws FileNotFoundException {
        this.leerdatos();
        List<Equipos> listaP = this.paises;
        Equipos pais = new Equipos();
        for (Equipos pa : listaP) {
            if (pa.getNombre().trim().equals(datoBuscar.trim())) {
                pais = pa;
            }
        }
        return pais;
    }

    public List<Equipos> buscartodos() throws FileNotFoundException {
        this.leerdatos();
        List<Equipos> listaEquipos = this.paises;
        List<Equipos> pa = new ArrayList<Equipos>();
        for (Equipos l3 : listaEquipos) {
            pa.add(l3);
        }
        return pa;
    }
}