package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

//default pełna losowość
public class AbstractGenome implements IGenome {
    List<Integer> genes;
    @Override
    public void mutate() {
        int numOfGenes = (int) (Math.random() * genes.size());
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
            n = (int) (Math.random() * 8);
            if (genes.get(numbers.get(i)) != n){
                genes.set(numbers.get(i),n);
                i += 1;
            }

        }
    }
}
