insert into users(login, `password`, `role`)
values ('admin', '$2a$10$TTjkHUYZ7nyr1tgkAhzRbux/JI6h87HeYkkfntDrCzsne9iVURy8u', 0);
insert into users(login, `password`, `role`)
values ('user', '$2y$10$bXpV9RKwgAr4vBAQIg3qZ.W36bSm/yD5X2ganDiMi/VtX4NzK.uMG', 1);

insert into `sensor_types`(`name`) values ('Pressure');
insert into `sensor_types`(`name`) values ('Voltage');
insert into `sensor_types`(`name`) values ('Temperature');
insert into `sensor_types`(`name`) values ('Humidity');

insert into `sensor_metrics`(`value`) values ('bar');
insert into `sensor_metrics`(`value`) values ('voltage');
insert into `sensor_metrics`(`value`) values ('C');
insert into `sensor_metrics`(`value`) values ('%');

insert into `sensors`(`name`, `model`, `from`, `to`, location, description, sensor_type_id, sensor_metric_id)
values ('sensor_1', 'model_1', 0, 1, 'location1', 'description_1', 1, 1);
insert into `sensors`(`name`, `model`, `from`, `to`, location, description, sensor_type_id, sensor_metric_id)
values ('sensor_2', 'model_2', 0, 2, 'location2', 'description_2', 2, 2);
insert into `sensors`(`name`, `model`, `from`, `to`, location, description, sensor_type_id, sensor_metric_id)
values ('sensor_3', 'model_3', 0, 3, 'location3', 'description_3', 3, 3);
insert into `sensors`(`name`, `model`, `from`, `to`, location, description, sensor_type_id, sensor_metric_id)
values ('sensor_4', 'model_4', 0, 4, 'location4', 'description_4', 4, 4);
insert into `sensors`(`name`, `model`, `from`, `to`, location, description, sensor_type_id, sensor_metric_id)
values ('sensor_5', 'model_5', 0, 5, 'location5', 'description_5', 1, 1);
insert into `sensors`(`name`, `model`, `from`, `to`, location, description, sensor_type_id, sensor_metric_id)
values ('sensor_6', 'model_6', 0, 6, 'location6', 'description_6', 2, 2);
insert into `sensors`(`name`, `model`, `from`, `to`, location, description, sensor_type_id, sensor_metric_id)
values ('sensor_7', 'model_7', 0, 7, 'location7', 'description_7', 3, 3);