/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt  
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 */
package GIZI;

/**
 *
 * @author acer
 */
public class Food {
    String nama, calorie, weight;
    int namaFood, calorieFood, weightFood;

    public Food() {}

    public void inputFood(String food) {
        this.nama = food;
    }

    public int namaFood() {
        this.namaFood = 0;
        if (this.nama.equals("1")) {
            this.namaFood = 1; 
        } else if (this.nama.equals("2")) {
            this.namaFood = 2; 
        } else {
            this.namaFood = 3; 
        }
        return this.namaFood;
    }

    public void inputcalorieFood(String calorie) {
        this.calorie = calorie;
    }

    public int calorieFood() {
        this.calorieFood = 0;
        if (this.calorie.equals("1")) {
            this.calorieFood = 266;
        } else if (this.calorie.equals("2")) {
            this.calorieFood= 294;
        } else {
            this.calorieFood= 360;
        }
        return this.calorieFood;
    }

    public void inputweight(String weight) {
        this.weight = weight;
    }

    public int weight() {
        this.weightFood = 0;
        if (this.weight.equals("1")) {
            this.weightFood = 100;
        } else if (this.weight.equals("2")) {
            this.weightFood = 120;
        } else {
            this.weightFood = 150;
        }
        return this.weightFood;
    }
    public String getNamaFoodString() {
        switch (this.namaFood) {
            case 1:
                return "PIZZA";
            case 2:
                return "BURGER";
            case 3:
                return "KFC";
            default:
                return "Unknown";
        }
    }
}
//    public static void main(String[] args) {
//        Food Food = new Food();
//        Food.inputFood("1");
//        Food.namaFood(); // Pastikan ini dipanggil untuk mengatur namaBarang
//        Food.calorieFood("1");
//        Food.weight("1");
//
//        System.out.println("NamaFood: " + Food.getNamaFoodString());
//        System.out.println("calorieFood: " + Food.calorieFood());
//        System.out.println("weightFood: " + Food.weight());
