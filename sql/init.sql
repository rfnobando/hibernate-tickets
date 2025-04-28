CREATE DATABASE `hibernate_tickets`
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_ai_ci;

USE `hibernate_tickets`;

CREATE TABLE `users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `profile_pictures` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `file_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customers` (
  `user_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employees` (
  `user_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tickets` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `customer_id` bigint unsigned NOT NULL,
  `ticket_category_id` bigint unsigned NOT NULL,
  `status_id` bigint unsigned NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employees_tickets` (
  `employee_id` bigint unsigned NOT NULL,
  `ticket_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`employee_id`, `ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ticket_categories` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `statuses` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ticket_messages` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `body` text NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  `ticket_id` bigint unsigned NOT NULL,
  `created_at` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `attached_pictures` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `ticket_message_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `users`
ADD CONSTRAINT `unique_users_email` UNIQUE (`email`);

ALTER TABLE `profile_pictures`
ADD CONSTRAINT `unique_profile_pictures_file_name` UNIQUE (`file_name`),
ADD CONSTRAINT `unique_profile_pictures_user_id` UNIQUE (`user_id`),
ADD CONSTRAINT `fk_profile_pictures_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`); 

ALTER TABLE `customers`
ADD CONSTRAINT `unique_customers_user_id` UNIQUE (`user_id`),
ADD CONSTRAINT `fk_customers_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `employees`
ADD CONSTRAINT `unique_employees_user_id` UNIQUE (`user_id`),
ADD CONSTRAINT `fk_employees_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `tickets`
ADD KEY `idx_tickets_customer_id` (`customer_id`),
ADD KEY `idx_tickets_ticket_category_id` (`ticket_category_id`),
ADD KEY `idx_tickets_status_id` (`status_id`),
ADD CONSTRAINT `fk_tickets_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`user_id`),
ADD CONSTRAINT `fk_tickets_ticket_category_id` FOREIGN KEY (`ticket_category_id`) REFERENCES `ticket_categories` (`id`),
ADD CONSTRAINT `fk_tickets_status_id` FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`);

ALTER TABLE `employees_tickets`
ADD KEY `idx_employees_tickets_employee_id` (`employee_id`),
ADD KEY `idx_employees_tickets_ticket_id` (`ticket_id`),
ADD CONSTRAINT `fk_employees_tickets_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`user_id`),
ADD CONSTRAINT `fk_employees_tickets_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`);

ALTER TABLE `ticket_categories`
ADD CONSTRAINT `unique_ticket_categories_name` UNIQUE (`name`);

ALTER TABLE `statuses`
ADD CONSTRAINT `unique_statuses_name` UNIQUE (`name`);

ALTER TABLE `ticket_messages`
ADD KEY `idx_ticket_messages_user_id` (`user_id`),
ADD KEY `idx_ticket_messages_ticket_id` (`ticket_id`),
ADD CONSTRAINT `fk_ticket_messages_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
ADD CONSTRAINT `fk_ticket_messages_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`);

ALTER TABLE `attached_pictures`
ADD CONSTRAINT `unique_attached_pictures_file_name` UNIQUE (`file_name`),
ADD KEY `idx_attached_pictures_ticket_message_id` (`ticket_message_id`),
ADD CONSTRAINT `fk_attached_pictures_ticket_message_id` FOREIGN KEY (`ticket_message_id`) REFERENCES `ticket_messages` (`id`);
