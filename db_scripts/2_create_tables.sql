USE `ewmsdb`;

CREATE TABLE `ewmsdb`.`addresses` (
  `address_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `street_address` VARCHAR(64) NOT NULL,
  `suburb` VARCHAR(64) NULL,
  `state` VARCHAR(32) NOT NULL,
  `postal_code` VARCHAR(32) NOT NULL,
  `country` VARCHAR(32) NOT NULL,
  `address_type` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC) VISIBLE);

CREATE TABLE `ewmsdb`.`customers` (
  `customer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(64) NOT NULL,
  `last_name` VARCHAR(64) NOT NULL,
  `mobile` VARCHAR(64) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `customer_adress_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `customer_id_UNIQUE` (`customer_id` ASC) VISIBLE,
  INDEX `FK_address_id_idx` (`customer_adress_id` ASC) VISIBLE,
  CONSTRAINT `FK_address_id`
    FOREIGN KEY (`customer_adress_id`)
    REFERENCES `ewmsdb`.`addresses` (`address_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE `ewmsdb`.`collections` (
  `collection_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `booking_date` DATE NOT NULL,
  `collection_date` DATE NOT NULL,
  `cancelled` TINYINT NOT NULL,
  PRIMARY KEY (`collection_id`),
  UNIQUE INDEX `collection_id_UNIQUE` (`collection_id` ASC) VISIBLE);

CREATE TABLE `ewmsdb`.`items` (
  `item_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_collection_id` INT UNSIGNED NOT NULL,
  `category` VARCHAR(32) NOT NULL,
  `type` VARCHAR(32) NOT NULL,
  `description` VARCHAR(64) NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE INDEX `item_id_UNIQUE` (`item_id` ASC) VISIBLE,
  INDEX `FK_collection_id_idx` (`item_collection_id` ASC) VISIBLE,
  CONSTRAINT `FK_collection_id`
    FOREIGN KEY (`item_collection_id`)
    REFERENCES `ewmsdb`.`collections` (`collection_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);