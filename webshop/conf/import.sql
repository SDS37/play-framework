insert into Employee (name, position, username, password) values ('Salvador', 'administrator', 'admin01', 'admin1');

insert into Category (name, description) values ('Books', 'Entertainment articles');
insert into Category (name, description) values ('Computing', 'Electronic articles');
insert into Category (name, description) values ('Toys', 'Articles for kids');
insert into Category (name, description) values ('Gardening', 'Garden articles');
insert into Category (name, description) values ('Electronics', 'Electronic articles');
insert into Category (name, description) values ('Clothes', 'Wardrobe articles');
insert into Category (name, description) values ('Men', 'All for men');
insert into Category (name, description) values ('Women', 'All for women');
insert into Category (name, description) values ('Children', 'All for children');
insert into Category (name, description) values ('Sports & Outdoors', 'Sports articles');
insert into Category (name, description) values ('Films', 'Entertainment articles');

insert into Product (name, description, cost, RRP, quantityInStock) values ('The hobbit', 'Fantasy book for young readers', 18.00, 20.00, 200);
insert into Product (name, description, cost, RRP, quantityInStock) values ('The Witcher', 'Fantasy book for young readers', 18.00, 20.00, 200);
insert into Product (name, description, cost, RRP, quantityInStock) values ('The book', 'Funky book for young readers', 18.00, 20.00, 200);
insert into Product (name, description, cost, RRP, quantityInStock) values ('The other book', 'Other book for young readers', 18.00, 20.00, 200);
insert into Product (name, description, cost, RRP, quantityInStock) values ('Macbook', 'laptop', 1800.00, 2000.00, 300);
insert into Product (name, description, cost, RRP, quantityInStock) values ('Airbook', 'Light laptop', 1900.00, 1920.00, 200);
insert into Product (name, description, cost, RRP, quantityInStock) values ('The hobbit', 'Fantasy movie', 18.00, 20.00, 200);
insert into Product (name, description, cost, RRP, quantityInStock) values ('The LoR', 'Fantasy movie', 18.00, 20.00, 200);

insert into Product_Category (product_id, category_id) values (1, 1);
insert into Product_Category (product_id, category_id) values (2, 1);
insert into Product_Category (product_id, category_id) values (3, 1);
insert into Product_Category (product_id, category_id) values (4, 1);
insert into Product_Category (product_id, category_id) values (5, 2);
insert into Product_Category (product_id, category_id) values (6, 2);
insert into Product_Category (product_id, category_id) values (7, 11);
insert into Product_Category (product_id, category_id) values (8, 11);

insert into User (name, email, address, phoneNumber, username, password) values ('The administrator', 'admin@yahoo.com', 'yers 10', '074-911-1265', 'admin01', 'admin1');
insert into User (name, email, address, phoneNumber, username, password) values ('Bjorn', 'bjorn@yahoo.com', 'hötorget 10', '074-911-1265', 'master', 'anotherpassword');
insert into User (name, email, address, phoneNumber, username, password) values ('John', 'john@yahoo.com', 'Yeahvägen 15', '074-911-7654', 'yeahman', 'yeah');
