package org.agh.ics.oop;


public class AnimalSzalony  extends Animal{
    //this is a starting animal
    public AnimalSzalony(int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy,
                         int satietyLevel,Vector2d position, int minMutation, int maxMutation) {
        super(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel,
                position, minMutation, maxMutation);
    }

    //this is a new animal
    public AnimalSzalony(Animal parent1, Animal parent2) {
        super(parent1,parent2);
    }

    @Override
    public void move() {
        double pareto = Math.random();

        if (pareto > 0.8) {
            this.activeGene = (int) (Math.random() * this.lenOfGenome);
            System.out.println(this.activeGene);
        }

        MapDirection newDirection = this.direction;

        try {
            for (int i = 0; i < this.genes.genes.get(activeGene % lenOfGenome); i++) {
                newDirection = newDirection.next();
            }

            int x = this.vector.x + newDirection.toUnitVector().x;
            int y = this.vector.y + newDirection.toUnitVector().y;
            this.direction = newDirection;
            this.vector = new Vector2d(x,y);
        } catch(NullPointerException e) {
            System.out.println("NullPointerException at move");
        }
    }
}
