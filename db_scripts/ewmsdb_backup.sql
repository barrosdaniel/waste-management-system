/* Create database */
CREATE SCHEMA `ewmsdb`;

/* Create tables */
USE `ewmsdb`;

CREATE TABLE `addresses` (
   `address_id` int unsigned NOT NULL AUTO_INCREMENT,
   `street_address` varchar(64) NOT NULL,
   `suburb` varchar(64) NOT NULL,
   `state` varchar(32) NOT NULL,
   `postal_code` varchar(32) NOT NULL,
   `country` varchar(32) NOT NULL,
   `address_type` varchar(32) NOT NULL,
   PRIMARY KEY (`address_id`),
   UNIQUE KEY `address_id_UNIQUE` (`address_id`)
);

CREATE TABLE `customers` (
   `customer_id` int unsigned NOT NULL AUTO_INCREMENT,
   `first_name` varchar(64) NOT NULL,
   `last_name` varchar(64) NOT NULL,
   `mobile` varchar(64) NOT NULL,
   `email` varchar(128) NOT NULL,
   `customer_address_id` int unsigned NOT NULL,
   PRIMARY KEY (`customer_id`),
   UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
   KEY `FK_address_id_idx` (`customer_address_id`),
   CONSTRAINT `FK_address_id` FOREIGN KEY (`customer_address_id`) REFERENCES `addresses` (`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

 CREATE TABLE `collections` (
   `collection_id` int unsigned NOT NULL AUTO_INCREMENT,
   `booking_date` date NOT NULL,
   `collection_date` date NOT NULL,
   `csr_customer_id` int unsigned NOT NULL,
   `csr_address_id` int unsigned NOT NULL,
   `cancelled` tinyint NOT NULL,
   PRIMARY KEY (`collection_id`),
   UNIQUE KEY `collection_id_UNIQUE` (`collection_id`),
   KEY `FK_csr_customer_id_idx` (`csr_customer_id`),
   KEY `FK_csr_address_id_idx` (`csr_address_id`),
   CONSTRAINT `collections_ibfk_1` FOREIGN KEY (`csr_customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT `collections_ibfk_2` FOREIGN KEY (`csr_address_id`) REFERENCES `addresses` (`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE `items` (
   `item_id` int unsigned NOT NULL AUTO_INCREMENT,
   `item_collection_id` int unsigned NOT NULL,
   `category` varchar(32) NOT NULL,
   `type` varchar(32) NOT NULL,
   `description` varchar(64) NOT NULL,
   `quantity` int unsigned NOT NULL,
   PRIMARY KEY (`item_id`),
   UNIQUE KEY `item_id_UNIQUE` (`item_id`),
   KEY `FK_collection_id_idx` (`item_collection_id`),
   CONSTRAINT `FK_collection_id` FOREIGN KEY (`item_collection_id`) REFERENCES `collections` (`collection_id`)
);

/* Add test data */
INSERT INTO `addresses` 
(`street_address`, `suburb`, `state`, `postal_code`, `country`, `address_type`)
VALUES
('1 Main Street', 'Prendiville', 'NSW', '2000', 'Australia', 'Residential'),
('100 Elm Avenue', 'Simpsonfield', 'VIC', '3000', 'Australia', 'Commercial'),
('321 Oak Lane', 'Yelp River', 'QLD', '4000', 'Australia', 'Residential'),
('123 Maple Court', 'Highcourt', 'QLD', '4000', 'Australia', 'Commercial'),
('555 Pine Road', 'Zamien', 'NSW', '2000', 'Australia', 'Residential'),
('777 Cedar Street', 'Ingridly', 'VIC', '3000', 'Australia', 'Commercial'),
('555 Birch Avenue', 'North York', 'QLD', '4000', 'Australia', 'Residential'),
('97 Willow Lane', 'Harrows', 'VIC', '3000', 'Australia', 'Commercial'),
('32 Juniper Court', 'Bridgeville', 'NSW', '2000', 'Australia', 'Residential'),
('15 Spruce Road', 'Kent Town', 'QLD', '4000', 'Australia', 'Commercial');

INSERT INTO `customers` 
(`first_name`, `last_name`, `mobile`, `email`, `customer_address_id`)
VALUES
('John', 'Doe', '0411222333', 'john@example.com', 1),
('Jane', 'Smith', '0435333555', 'jane@example.com', 2),
('David', 'Johnson', '0411555888', 'david@example.com', 3),
('Emily', 'Williams', '0416789456', 'emily@example.com', 4),
('Michael', 'Brown', '0455666777', 'michael@example.com', 5),
('Sarah', 'Davis', '0432654987', 'sarah@example.com', 6),
('Christopher', 'Miller', '0410456789', 'chris@example.com', 7),
('Jessica', 'Wilson', '0413666777', 'jess@example.com', 8),
('Matthew', 'Taylor', '0412121212', 'matt@example.com', 9),
('Lauren', 'Anderson', '0478787878', 'lauren@example.com', 10);

INSERT INTO `collections`
(`booking_date`, `collection_date`, `csr_customer_id`,`csr_address_id`, `cancelled`)
VALUES
('2023-06-01', '2023-06-15',1 ,1, 0),
('2023-06-02', '2023-06-16',2 ,2, 0),
('2023-06-03', '2023-06-17',3 ,3, 0),
('2023-06-04', '2023-06-18',4 ,4, 1),
('2023-06-05', '2023-06-19',5 ,5, 0),
('2023-06-06', '2023-06-20',6 ,6, 0),
('2023-06-07', '2023-06-21',7 ,7, 0),
('2023-06-08', '2023-06-22',8 ,8, 1),
('2023-06-09', '2023-06-23',9 ,9, 0),
('2023-06-10', '2023-06-24',10 ,10, 0);

INSERT INTO `items` 
(`item_collection_id`, `category`, `type`, `description`, `quantity`)
VALUES
(1, 'General Hard', 'Ceramics', 'Ceramic plates', 10),
(2, 'General Hard', 'Furniture', 'Wooden table', 1),
(3, 'General Hard', 'Cardboard and Paper', 'Cardboard boxes', 5),
(4, 'General Hard', 'Glass and Mirrors', 'Glass bottles', 20),
(5, 'General Hard', 'Timber', 'Wooden planks', 8),
(6, 'General Hard', 'Hard Plastic', 'Plastic containers', 15),
(7, 'Greens', 'Raw Wood', 'Tree trunk', 1),
(8, 'Greens', 'Branches', 'Tree branches', 20),
(9, 'Greens', 'Cuttings', 'Garden clippings', 10),
(10, 'Greens', 'Leaves', 'Fallen leaves', 100),
(5, 'Metal', 'Scrap Metal', 'Metal scraps', 50),
(5, 'Metal', 'Pieces and Equipment', 'Metal tools', 5),
(5, 'Metal', 'White Goods', 'Refrigerator', 1),
(5, 'Metal', 'Tins', 'Metal cans', 30),
(5, 'Electronic', 'Electrical', 'Power cables', 10),
(5, 'Electronic', 'Electronics', 'Computer monitors', 3),
(5, 'Electronic', 'Other Appliances', 'Microwave oven', 1);