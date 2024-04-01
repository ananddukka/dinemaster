package com.example.dinemaster.controller;

import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestContoller 
public class  ChefController{
    @Autowired
    private ChefJpaService chefJpaService;

    @GetMapping("/restaurants/chefs")
        public ArrayList<Chef> getChefs(){
            return chefJpaService.getChefs();
        }
    @GetMapping("/restaurants/chefs/{chefId}")
    public Chef getChefById(@PathVariable("chefId") int chefId) {
        return cheffJpaService.getChefById(chefId);
    }

    @PostMapping("/restaurants/chefs")
    public Chef addChef(@RequestBody Chef chef){
        return chefJpaService.addChef(chef);
    }

    @PutMapping("/restaruants/chefs/{chefId}")
    public Chef updateChef(@PathVariable("chefId") int chefId, @RequestBody Chef chef) {
        return chefJpaService.updateChef(chefId , chef);
    }

    @DeleteMapping("/restaurants/chefs/{chefId}")
    public void deleteChef(@PathVariable int chefId) {
        chefJpaService.deleteChef(chefId);
    }

    @GetMapping("/chefs/{chefId}/restaurant")
    public Restaurant getChefRestaurant(@PathVariable int chefId){
        return chefJpaService.getChefRestaurant(chefId);
    }
    
}