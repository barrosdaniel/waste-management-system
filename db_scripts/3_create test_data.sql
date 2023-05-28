USE `ewmsdb`;

INSERT INTO `addresses` 
(`line_1`, `line_2`, `state`, `postal_code`, `country`, `address_type`)
VALUES
('1 Main Street', 'Apt 4B', 'New South Wales', '2000', 'Australia', 'Residential'),
('100 Elm Avenue', NULL, 'Victoria', '3000', 'Australia', 'Commercial'),
('321 Oak Lane', 'Unit 2', 'Queensland', '4000', 'Australia', 'Residential'),
('123 Maple Court', NULL, 'Queensland', '4000', 'Australia', 'Commercial'),
('555 Pine Road', NULL, 'New South Wales', '2000', 'Australia', 'Residential'),
('777 Cedar Street', 'Suite 100', 'Victoria', '3000', 'Australia', 'Commercial'),
('555 Birch Avenue', NULL, 'Queensland', '4000', 'Australia', 'Residential'),
('97 Willow Lane', NULL, 'Victoria', '3000', 'Australia', 'Commercial'),
('32 Juniper Court', NULL, 'New South Wales', '2000', 'Australia', 'Residential'),
('15 Spruce Road', 'Suite 50', 'Queensland', '4000', 'Australia', 'Commercial');

INSERT INTO `customers` 
(`first_name`, `last_name`, `mobile`, `email`, `customer_adress_id`)
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
(`booking_date`, `collection_date`, `cancelled`)
VALUES
('2023-06-01', '2023-06-15', 0),
('2023-06-02', '2023-06-16', 0),
('2023-06-03', '2023-06-17', 0),
('2023-06-04', '2023-06-18', 1),
('2023-06-05', '2023-06-19', 0),
('2023-06-06', '2023-06-20', 0),
('2023-06-07', '2023-06-21', 0),
('2023-06-08', '2023-06-22', 1),
('2023-06-09', '2023-06-23', 0),
('2023-06-10', '2023-06-24', 0);

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
