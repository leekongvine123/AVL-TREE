/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_4;

/**
 *
 * @author Admin
 */
 
public class Phone {
    int Id;
    String name;
    int year;
    int amount;
    double price;

    public Phone() {
    }

    public Phone(int Id, String name, int year, int amount, double price) {
        this.Id = Id;
        this.name = name;
        this.year = year;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" + "Id=" + Id + ", name=" + name + ", year=" + year + ", amount=" + amount + ", price=" + price + '}';
    }
    
    
}
