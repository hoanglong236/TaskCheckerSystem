CREATE DATABASE TaskCheckerSystem DEFAULT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_UNICODE_CI;

USE TaskCheckerSystem;

CREATE TABLE `task` (
	`id` VARCHAR(15),
    `parent_id` VARCHAR(15),
    `title` VARCHAR(256) NOT NULL,
    `important` BOOLEAN NOT NULL DEFAULT FALSE,
    `start_datetime` TIMESTAMP,
    `finish_datetime` TIMESTAMP,
    `submit_datetime` TIMESTAMP, 
    `completed` BOOLEAN NOT NULL DEFAULT FALSE,
    `note` VARCHAR(256),
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
	CONSTRAINT fk_task FOREIGN KEY (`parent_id`) REFERENCES `task`(`id`),
	CONSTRAINT check_finish_datetime CHECK (`finish_datetime` >= `start_datetime`),
	CONSTRAINT check_submit_datetime CHECK (`submit_datetime` >= `start_datetime`)
);

CREATE TABLE `gen_master` (
	`data_id` VARCHAR(5),
    `data_cd` VARCHAR(5),
    `data_name` VARCHAR(50) NOT NULL,
    `data_value` VARCHAR(10) NOT NULL,
    `note` VARCHAR(256) NOT NULL,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`data_id`, `data_cd`)
);