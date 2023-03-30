package com.example.demo;

import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DBInitializer implements CommandLineRunner {
    @PersistenceContext
    EntityManager em;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        List<FoodCategory> foodCategoryList = em.createQuery("from FoodCategory", FoodCategory.class).setMaxResults(1).getResultList();
        FoodCategory meatCategory = new FoodCategory();
        FoodCategory veggiesCategory = new FoodCategory();
        if (foodCategoryList.isEmpty()) {
            meatCategory.setName("Meat");
            em.persist(meatCategory);

            veggiesCategory.setName("Veggies");
            em.persist(veggiesCategory);
        }

        List<Food> foodList = em.createQuery("from Food", Food.class).setMaxResults(1).getResultList();
        if (foodList.isEmpty()) {
            Food steak = new Food();
            steak.setCategory(meatCategory.getName());
            steak.setName("Steak");
            steak.setPrice(new BigDecimal("3.99"));
            steak.setDescription("Medium-rare pork steak");
            steak.setImagePath("https://res.cloudinary.com/dtbhkxrkn/image/upload/v1668771100/steakImage_jrthpa.jpg");
            steak.setQuantity(1);
            em.persist(steak);

            Food chicken = new Food();
            chicken.setCategory(meatCategory.getName());
            chicken.setName("Chicken");
            chicken.setPrice(new BigDecimal("2.99"));
            chicken.setDescription("Crispy chicken");
            chicken.setImagePath("https://res.cloudinary.com/dtbhkxrkn/image/upload/v1668771078/chickenImage_mhnr8h.png");
            chicken.setQuantity(1);
            em.persist(chicken);

            Food carrots = new Food();
            carrots.setCategory(veggiesCategory.getName());
            carrots.setName("Carrots");
            carrots.setPrice(new BigDecimal("1.50"));
            carrots.setDescription("Chopped with olive oil");
            carrots.setImagePath("https://res.cloudinary.com/dtbhkxrkn/image/upload/v1668771084/carrotsImage_mydx7s.jpg");
            carrots.setQuantity(1);
            em.persist(carrots);

            Food apple = new Food();
            apple.setCategory(veggiesCategory.getName());
            apple.setName("Apple");
            apple.setPrice(new BigDecimal("1.20"));
            apple.setDescription("Sweet red apple");
            apple.setImagePath("https://res.cloudinary.com/dtbhkxrkn/image/upload/v1668771088/appleImage_gsdumf.jpg");
            apple.setQuantity(1);
            em.persist(apple);

            List<Food> meatList = new ArrayList<>();
            meatList.add(steak);
            meatList.add(chicken);
            meatCategory.setFoods(meatList);

            List<Food> veggiesList = new ArrayList<>();
            veggiesList.add(carrots);
            veggiesList.add(apple);
            veggiesCategory.setFoods(veggiesList);
        }

        List<User> userList = em.createQuery("from User", User.class).setMaxResults(1).getResultList();
        if (userList.isEmpty()) {
            User user1 = new User();
            user1.setCity("Ovchepolci");
            user1.setFirstName("Bat");
            user1.setLastName("Georgi");
            user1.setUsername("Igracha");
            user1.setUserRole(UserRoles.ROLE_ADMIN);
            user1.setPassword(passwordEncoder.encode("123456"));
            em.persist(user1);

            User user2 = new User();
            user2.setCity("Ovchepolci");
            user2.setFirstName("Bat");
            user2.setLastName("Vanio");
            user2.setUsername("Igrachkata");
            user2.setUserRole(UserRoles.ROLE_USER);
            user2.setPassword(passwordEncoder.encode("654321"));
            em.persist(user2);
        }

    }
}
