package com.example.dinemaster.service;

import com.example.dinemaster.model.chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.ChefJpaRepository;
import com.example.dinemaster.repository.chefRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefJpaService implements ChefRepository {
    @Autowired
    private ArrayList<Chef> getChefs() {
        List<Chef> chefsList = chefJpaRepository.findAll();
        ArrayList<Chef> chefs = new ArrayList<>(chefsList);
        return chefs;
    }

    @Override
    public Chef getChefById(int chefId) {
        try {
            Chef chef = chefJpaRepository.findById(chefId).get();
            return chef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chef addChef(Chef chef) {
        int restaurantId = chef.getRestaurant().getId();
        Restaurant restaurant = restaurantJpaService.getRestaurantById(restaurantId);
        chef.setRestaurant(restaurant);
        chefJpaRepository.save(chef);
        return chef;
    }

    @Override
    public Chef updateChef(int chefId, Chef chef) {
        try {
            Chef newChef = chefJpaRepository.findById(chefId).get();
            if (chef.getRestaurant() != null) {
                int restaurantId = chef.getRestaurant().getId();
                Restaurant restaurant = restaurantJpaService.getRestaurantById(restaurantId);
                newChef.setRestaurant(restaurant);
            }
            if (chef.getFirstName() != null) {
                newChef.setFirstName(chef.getFirstName());
            }
            if (chef.getLastName() != null) {
                newChef.setLastName(chef.getLastName());
            }
            if (chef.getExpertise() != null) {
                newChef.setExpertise(chef.getExpertise());
            }
            if (chef.getExperienceYears != 0) {
                newChef.setExperience(chef.getExperienceYears());
            }
            chefJpaRepository.save(newChef);
            return newChef;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}