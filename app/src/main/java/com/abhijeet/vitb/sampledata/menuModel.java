package com.abhijeet.vitb.sampledata;

import com.airbnb.lottie.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class menuModel {
    private List<String> breakfast;
    private List<String> lunch;
    private List<String> snacks;
    private List<String> dinner;

    public menuModel(List<String> breakfast, List<String> lunch, List<String> snacks, List<String> dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.snacks = snacks;
        this.dinner = dinner;
    }

    public List<String> getBreakfast() {
        return breakfast;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public List<String> getSnacks() {
        return snacks;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public Map<String, List<String>> tojson(){
        Map<String, List<String>> modelJson = new HashMap<String,List<String>>();
        modelJson.put("breakfast", this.breakfast);
        modelJson.put("lunch", this.lunch);
        modelJson.put("snacks", this.snacks);
        modelJson.put("dinner", this.dinner);
        return  modelJson;
    }

    public static menuModel fromJson(Map<String, List<String>> data){
        menuModel temp = new menuModel(data.get("breakfast"),data.get("lunch"),data.get("snacks"),data.get("dinner"));
        return  temp;
    }


    // add from json response

}