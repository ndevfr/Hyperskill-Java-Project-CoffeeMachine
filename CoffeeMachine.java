package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int waterQuantity;
    private int milkQuantity;
    private int beansQuantity;
    private int cupsQuantity;
    private int moneyQuantity;
    private int nbrOfCoffee;

    private final Scanner scanner = new Scanner(System.in);

    CoffeeMachine() {
        this.waterQuantity = 400;
        this.milkQuantity = 540;
        this.beansQuantity = 120;
        this.cupsQuantity = 9;
        this.moneyQuantity = 550;
        this.nbrOfCoffee = 0;
    }

    void remaining() {
        System.out.println("The coffee machine has:");
        for (Ingredients ingredient : Ingredients.values()) {
            String legend = ingredient.getLegend();
            int quantity = this.getQuantity(ingredient);
            System.out.println(quantity + " " + legend);
        }
        System.out.println("$" + this.moneyQuantity + " of money");
    }

    void start() {
        boolean stop = false;
        while(!stop){
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String action = this.scanner.nextLine();
            switch(action) {
                case "buy":
                    this.buy();
                    break;
                case "fill":
                    this.fill();
                    break;
                case "take":
                    this.take();
                    break;
                case "clean":
                    this.clean();
                    break;
                case "remaining":
                    this.remaining();
                    break;
                case "exit":
                    stop = true;
                    break;
                default:
                    break;
            }
        }
    }

    void buy() {
        if(this.nbrOfCoffee == 10){
            System.out.println("I need cleaning!");
            return;
        }
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String type = this.scanner.nextLine();
        int waterQuantity;
        int milkQuantity = 0;
        int beansQuantity;
        int price;
        switch(type) {
            case "1":
                waterQuantity = 250;
                beansQuantity = 16;
                price = 4;
                break;
            case "2":
                waterQuantity = 350;
                milkQuantity = 75;
                beansQuantity = 20;
                price = 7;
                break;
            case "3":
                waterQuantity = 200;
                milkQuantity = 100;
                beansQuantity = 12;
                price = 6;
                break;
            default:
                return;
        }

        if(this.waterQuantity < waterQuantity){
            System.out.println("Sorry, not enough water!");
        } else if(this.milkQuantity < milkQuantity) {
            System.out.println("Sorry, not enough milk!");
        } else if(this.beansQuantity < beansQuantity) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if(this.cupsQuantity == 0){
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            this.waterQuantity -= waterQuantity;
            this.milkQuantity -= milkQuantity;
            this.beansQuantity -= beansQuantity;
            this.cupsQuantity--;
            this.nbrOfCoffee++;
            this.moneyQuantity += price;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    void fill() {
        for (Ingredients ingredient : Ingredients.values()) {
            String legend = ingredient.getLegend();
            System.out.println("Write how many " + legend + " you want to add:");
            int value = this.scanner.nextInt();
            this.addQuantity(ingredient, value);
        }
    }

    void clean() {
        this.nbrOfCoffee = 0;
        System.out.println("I have been cleaned!");
    }

    void take() {
        System.out.println("I gave you $" + this.moneyQuantity);
        this.moneyQuantity = 0;
    }

    int getQuantity(Ingredients ingredient) {
        return switch (ingredient) {
            case WATER -> this.waterQuantity;
            case MILK -> this.milkQuantity;
            case BEANS -> this.beansQuantity;
            case CUPS -> this.cupsQuantity;
        };
    }

    void addQuantity(Ingredients ingredient, int value) {
        switch(ingredient) {
            case WATER:
                this.waterQuantity += value;
                break;
            case MILK:
                this.milkQuantity += value;
                break;
            case BEANS:
                this.beansQuantity += value;
                break;
            case CUPS:
                this.cupsQuantity += value;
                break;
            default:
                break;
        }
    }

}