package machine;

public class CoffeeRecipe {
    final int water;
    final int milk;
    final int beans;
    final int cost;

    public CoffeeRecipe(int water, int milk, int beans, int cost) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }

    public int water() {
        return water;
    }

    public int milk() {
        return milk;
    }

    public int beans() {
        return beans;
    }

    public int cost() {
        return cost;
    }
}
