use food_guro_app;
insert into user (city, first_name, last_name, password, username) values ("Ovchepolci","Bat","Georgi",313233,"Igracha");
insert into food_category (name) values ("Meat");
insert into food (category_id, name, price, description) values (1,"Steak",2100,"Some description");
insert into food (category_id, name, price, description) values (1,"Chicken",3100,"Some description");
insert into food_category (name) values ("Vegies");
insert into food (category_id, name, price, description) values (2,"Carrots",100,"Some description");
insert into food (category_id, name, price, description) values (2,"Apple",200,"Some description");