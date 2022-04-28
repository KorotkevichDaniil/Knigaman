package com.example.knigaman.OtherClasses;

public class Books {
    int idBook, cost, year;
    String name, author, genre;

    public Books(   int idBook,  int cost, int year, String name, String author, String genre){
        this.author=author;
        this.cost=cost;
        this.idBook=idBook;
        this.genre=genre;
        this.name=name;
        this.year=year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getIdBook() {
        return idBook;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
