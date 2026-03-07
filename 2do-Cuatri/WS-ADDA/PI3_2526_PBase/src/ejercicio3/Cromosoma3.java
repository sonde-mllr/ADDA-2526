package ejercicio3;

import java.util.List;

import us.lsi.ag.RangeIntegerData;
import us.lsi.ag.agchromosomes.Chromosomes.ChromosomeType;

public class Cromosoma3 implements RangeIntegerData<Solucion3> {

    private final double maxGoalPosible;

    public Cromosoma3(String file) {
        Datos3.iniDatos(file);
        // Cota superior: en el mejor caso se llenan todos los contenedores
        this.maxGoalPosible = Datos3.getNumContenedores();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.RangeInteger;
    }

    @Override
    public Integer size() {
        // Un gen por elemento: a qué contenedor va asignado (0 = no asignado)
        return Datos3.getNumElementos();
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        int nc = Datos3.getNumContenedores();
        int ne = Datos3.getNumElementos();

        int[] tamAcum = new int[nc];
        double error = 0.0;
        double penalizacion = maxGoalPosible + 1;

        for (int i = 0; i < ne; i++) {
            int gen = value.get(i);
            if (gen == 0) continue; // 0 = no asignado

            int j = gen - 1; // índice contenedor 0-based

            if (!Datos3.getPuedeUbicarse(i, j)) {
                // Elemento asignado a tipo de contenedor incompatible
                error += 1;
            } else {
                tamAcum[j] += Datos3.getTamElemento(i);
            }
        }

        double goal = 0.0;
        for (int j = 0; j < nc; j++) {
            int cap = Datos3.getTamContenedor(j);
            int tam = tamAcum[j];

            if (tam > cap) {
                // Desbordamiento: penalizar el exceso
                error += tam - cap;
            } else if (tam == cap) {
                // Lleno exacto: bonus máximo
                goal += 2.0;
            } else if (tam > 0) {
                // Parcialmente lleno: gradiente bimodal
                // Si ratio > 0.5 conviene seguir llenando
                // Si ratio < 0.5 conviene vaciarlo y reasignar elementos
                double ratio = (double) tam / cap;
                goal += ratio;       // recompensa por lo que hay
                goal -= (1 - ratio); // penaliza el hueco restante
            }
            // tam == 0: no suma ni resta, el contenedor simplemente no se usa
        }

        return goal - penalizacion * error;
    }

    @Override
    public Solucion3 solution(List<Integer> value) {
        return Solucion3.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return Datos3.getNumContenedores(); // 1..nc (0 = no asignado)
    }

    @Override
    public Integer min(Integer i) {
        return 0; // 0 = no asignado
    }
}