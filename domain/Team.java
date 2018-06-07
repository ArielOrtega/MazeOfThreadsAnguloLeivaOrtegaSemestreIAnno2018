/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Arrays;

/**
 *
 * @author Maria
 */
public class Team {

    private int n;
    private Entity arreglo[];

    public Team(int n) {
        arreglo = new Entity[n];
    }

    public Entity[] getArreglo() {
        return arreglo;
    }

    public void setArreglo(Entity[] arreglo) {
        this.arreglo = arreglo;
    }

    public void insertar(Entity elemento) { //elemento
        for (int i = 0; i < n; i++) {
            if (arreglo[i] == null) { //encontro una celda libre
                arreglo[i] = elemento;
                i = n; //condicion de salida
                break; //en programacion se supone no se debe
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.deepHashCode(this.arreglo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (!Arrays.deepEquals(this.arreglo, other.arreglo)) {
            return false;
        }
        return true;
    }
    
    
}
