package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToxicHell extends Portal{
    public ToxicHell(int width, int height) {
        super(width, height);
    }

    @Override
    public void addPlants(int numPlants){ //preferred fields are actually not preferred
        preferredFields.clear();
        int n = (int) ((this.upperRight.x + 1) * (this.upperRight.y + 1) * 0.2);
        if(toxicFields.size() < n){
            super.addPlants(numPlants);
        }else {
            List<Integer> l = new ArrayList<>(toxicFields.values());
            l.sort(Collections.reverseOrder());
            if (l.size() > n) {
                int r = l.get(n - 1);
                for (Vector2d v : toxicFields.keySet()) {
                    if (toxicFields.get(v) > r) {
                        preferredFields.add(v);
                    }
                }
            }
            int x = 0;
            int y = 0;
            double a;
            int b;
            boolean f, f2;

            if (numEmpty > numPlants) {
                for (int i = 0; i < numPlants; i++) {
                    boolean flag = true;
                    while (flag) {
                        a = Math.random();
                        if (a < 0.2) {
                            b = (int) (Math.random() * preferredFields.size());
                            x = preferredFields.get(b).x;
                            y = preferredFields.get(b).y;
                        } else {
                            f = false;
                            while (!f) {
                                x = (int) (Math.random() * (upperRight.x + 1));
                                y = (int) (Math.random() * (upperRight.y + 1));
                                f2 = true;
                                for (Vector2d v : preferredFields) {
                                    if (v.x == x && v.y == y) {
                                        f2 = false;
                                    }
                                }
                                if (f2) {
                                    f = true;
                                }
                            }
                        }
                        Vector2d vec = new Vector2d(x, y);

                        if (!isOccupiedByGrass(vec)) {
                            plants.put(vec, new Plant(vec));
                            numOfGrass += 1;
                            flag = false;
                        }
                    }
                }
            }
        }

    }
}
