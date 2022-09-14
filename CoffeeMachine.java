package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CoffeeRecipe ESPRESSO = new CoffeeRecipe(250, 0, 16, 4);
    private static final CoffeeRecipe LATTE = new CoffeeRecipe(350, 75, 20, 7);
    private static final CoffeeRecipe CAPPUCCINO = new CoffeeRecipe(200, 100, 12, 6);
    private static boolean poweredOn = true;
    private int availableWater;
    private int availableMilk;
    private int availableCoffeeBeans;
    private int availableCups;
    private int money;

    public CoffeeMachine() {
        this.availableWater = 400;
        this.availableMilk = 540;
        this.availableCoffeeBeans = 120;
        this.availableCups = 9;
        this.money = 550;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        while (poweredOn) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = SCANNER.nextLine();
            switch (action) {
                case "buy" -> coffeeMachine.buy();
                case "fill" -> coffeeMachine.fill();
                case "take" -> coffeeMachine.take();
                case "remaining" -> coffeeMachine.printState();
                case "exit" -> poweredOn = false;
                default -> throw new RuntimeException("I don't know what to do!");
            }
        }
    }

    private void printState() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water%n", this.availableWater);
        System.out.printf("%d ml of milk%n", this.availableMilk);
        System.out.printf("%d g of coffee beans%n", this.availableCoffeeBeans);
        System.out.printf("%d disposable cups%n", this.availableCups);
        System.out.printf("$%d of money%n", this.money);
    }

    private void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String option = SCANNER.nextLine();
        switch (option) {
            case "1" -> this.makeEspresso();
            case "2" -> this.makeLatte();
            case "3" -> this.makeCappuccino();
            case "back" -> doNothing();
            default -> throw new RuntimeException("No such coffee!");
        }
    }

    private void fill() {
        System.out.println("Write how many ml of water you want to add:");
        int water = SCANNER.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int milk = SCANNER.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeBeans = SCANNER.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        int cups = SCANNER.nextInt();
        this.updateState(water, milk, coffeeBeans, cups, 0);
        SCANNER.nextLine();
    }

    private void take() {
        System.out.printf("I gave you $%d%n", this.money);
        this.money = 0;
    }

    private void makeEspresso() {
        if (this.enoughResources(ESPRESSO)) {
            System.out.println("I have enough resources, making you a coffee!");
            this.updateState(-ESPRESSO.water(), -ESPRESSO.milk(), -ESPRESSO.beans, -1, ESPRESSO.cost());
        }
    }

    private void makeLatte() {
        if (this.enoughResources(LATTE)) {
            System.out.println("I have enough resources, making you a coffee!");
            this.updateState(-LATTE.water(), -LATTE.milk(), -LATTE.beans(), -1, LATTE.cost());
        }
    }

    private void makeCappuccino() {
        if (this.enoughResources(CAPPUCCINO)) {
            System.out.println("I have enough resources, making you a coffee!");
            this.updateState(-CAPPUCCINO.water(), -CAPPUCCINO.milk(), -CAPPUCCINO.beans(), -1, CAPPUCCINO.cost());
        }
    }

    private void doNothing() {
    }

    private boolean enoughResources(CoffeeRecipe coffeeRecipe) {
        boolean enoughResources = true;
        if (this.availableWater < coffeeRecipe.water()) {
            System.out.println("Sorry, not enough water!");
            enoughResources = false;
        } else if (this.availableMilk < coffeeRecipe.milk()) {
            System.out.println("Sorry, not enough milk!");
            enoughResources = false;
        } else if (this.availableCoffeeBeans < coffeeRecipe.beans()) {
            System.out.println("Sorry, not enough beans!");
            enoughResources = false;
        } else if (this.availableCups < 1) {
            System.out.println("Sorry, not enough cups!");
            enoughResources = false;
        }
        return enoughResources;
    }

    private void updateState(int water, int milk, int coffeeBeans, int cups, int money) {
        this.availableWater += water;
        this.availableMilk += milk;
        this.availableCoffeeBeans += coffeeBeans;
        this.availableCups += cups;
        this.money += money;
    }

    private record CoffeeRecipe(int water, int milk, int beans, int cost) {
    }
}
