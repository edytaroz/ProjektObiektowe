package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GenomeKorekta extends Genome{
    public GenomeKorekta(int n) {
        super(n);
    }
    public GenomeKorekta(Animal parent1, Animal parent2){
        super(parent1,parent2);
        this.mutate();
    }

    @Override
    public void mutate(){
        int numOfGenes = (int) (Math.random() * (maxMutation - minMutation));
        numOfGenes += minMutation;
        List<Integer> numbers = new ArrayList<>();
        int n;
        int i = 0;
        boolean flag;
        while (i < numOfGenes){
            n = (int) (Math.random() * genes.size());
            flag = false;
            for (int j = 0; j < numbers.size(); j++){
                if (n == numbers.get(j)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                numbers.add(n);
                i += 1;
            }
        }
        i = 0;
        while (i < numOfGenes){
            double m = (Math.random());
            int gen = genes.get(numbers.get(i));
            if (m > 0.5){
                genes.set(numbers.get(i),(gen + 1)%8);
            } else {
                genes.set(numbers.get(i),(Math.abs(gen - 1))%8);
            }
            i += 1;
        }
    }
    public GenomeKorekta getGenes() {
        return this;
    }
}
