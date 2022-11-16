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
            steak.setFoodCategory(meatCategory);
            steak.setName("Steak");
            steak.setPrice(new BigDecimal("3.99"));
            steak.setDescription("Medium-rare pork steak");
            steak.setImage(new ArrayList<>());
            em.persist(steak);

            Food chicken = new Food();
            chicken.setFoodCategory(meatCategory);
            chicken.setName("Chicken");
            chicken.setPrice(new BigDecimal("2.99"));
            chicken.setDescription("Crispy chicken");
            chicken.setImage(new ArrayList<>());
            em.persist(chicken);

            Food carrots = new Food();
            carrots.setFoodCategory(veggiesCategory);
            carrots.setName("Carrots");
            carrots.setPrice(new BigDecimal("1.50"));
            carrots.setDescription("Chopped with olive oil");
            carrots.setImage(new ArrayList<>());
            em.persist(carrots);

            Food apple = new Food();
            apple.setFoodCategory(veggiesCategory);
            apple.setName("Apple");
            apple.setPrice(new BigDecimal("1.20"));
            apple.setDescription("Sweet red apple");
            apple.setImage(new ArrayList<>());
            em.persist(apple);

            List<ImageDataBase> steakImagesList = em.createQuery("from ImageDataBase", ImageDataBase.class).setMaxResults(1).getResultList();
            List<ImageDataBase> chickenImagesList = em.createQuery("from ImageDataBase", ImageDataBase.class).setMaxResults(1).getResultList();
            List<ImageDataBase> carrotsImagesList = em.createQuery("from ImageDataBase", ImageDataBase.class).setMaxResults(1).getResultList();
            List<ImageDataBase> imageDataBaseList4 = em.createQuery("from ImageDataBase", ImageDataBase.class).setMaxResults(1).getResultList();

            ImageDataBase steakImage = new ImageDataBase();
            ImageDataBase chickenImage = new ImageDataBase();
            ImageDataBase carrotsImage = new ImageDataBase();
            ImageDataBase appleImage = new ImageDataBase();

            steakImage.setName("SteakImage");
            em.persist(steakImage);

            chickenImage.setName("ChickenImage");
            em.persist(chickenImage);

            carrotsImage.setName("CarrotsImage");
            em.persist(carrotsImage);

            appleImage.setName("AppleImage");
            em.persist(appleImage);

            steakImagesList.add(steakImage);
            steak.getImage().addAll(steakImagesList);

            chickenImagesList.add(chickenImage);
            chicken.getImage().addAll(chickenImagesList);

            carrotsImagesList.add(carrotsImage);
            carrots.getImage().addAll(carrotsImagesList);

            imageDataBaseList4.add(appleImage);
            apple.getImage().addAll(imageDataBaseList4);
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
