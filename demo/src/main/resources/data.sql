use food_guro_app;
insert into user (city, first_name, last_name, password, username, user_role) values ("Ovchepolci","Bat","Georgi",123456,"Igracha","ROLE_ADMIN");
insert into user (city, first_name, last_name, password, username, user_role) values ("Ovchepolci","Bat","Vanio","$10$dtEeu1rgrXMz4xNslUsmvO2CXhTSxE5Vkdes/YaQmX6hoqU2YB0p.","Igrachkata","ROLE_USER");
insert into food_category (name) values ("Meat");
insert into food (category_id, name, price, description) values (1,"Steak",3.99,"Medium-rare pork steak");
insert into food (category_id, name, price, description) values (1,"Chicken",2.99,"Crispy chicken");
insert into food_category (name) values ("Vegies");
insert into food (category_id, name, price, description) values (2,"Carrots",1.50,"Chopped with olive oil");
insert into food (category_id, name, price, description) values (2,"Apple",1.20,"Sweet red apple");