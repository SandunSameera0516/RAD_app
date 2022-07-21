package com.example.javafx_rad;

public class InfectiousWaste {
    int id;
    String type_of_waste;
    String waste_object;
    String type_of_container;
    int total_waste_per_day;

    public InfectiousWaste(int id, String type_of_waste, String waste_object, String type_of_container, int total_waste_per_day){
        this.id = id;
        this.type_of_waste = type_of_waste;
        this.waste_object = waste_object;
        this.type_of_container = type_of_container;
        this.total_waste_per_day = total_waste_per_day;
    }

    public int getId(){
        return id;
    }
    public String getType_of_waste(){
        return type_of_waste;
    }
    public String getWaste_object(){
        return waste_object;
    }
    public String getType_of_container(){
        return type_of_container;
    }
    public int getTotal_waste_per_day(){
        return total_waste_per_day;
    }

} 
     
