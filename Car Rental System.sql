Create database CarRentalSystem;

Use CarRentalSystem;

/* 
drop table Vehicle;
drop table Customer;
drop table Lease;
drop table Payment;
*/

Create table Vehicle(
	vehicleID int primary key auto_increment,
    make varchar(255) not null,
    model varchar(255) not null,
    year int not null,
    dailyRate double not null,
    status enum('available', 'notAvailable') not null,
    passengerCapacity int not null,
    engineCapacity double not null
);

create table Customer(
	customerID int primary key auto_increment,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    email varchar(255) not null unique,
    phoneNumber varchar(15) not null
) auto_increment = 101;

create table Lease(
	leaseID int primary key auto_increment,
    vehicleID int not null,
    customerID int ,
    startDate date not null,
    endDate date not null,
    type enum('Daily', 'Monthly') not null,
    constraint vid_fk 
    foreign key(vehicleID) references Vehicle(vehicleID) on delete cascade on update cascade,
    constraint cid_fk 
    foreign key(customerID) references Customer(customerID) on delete set null on update cascade
) auto_increment = 1001;

create table Payment(
	paymentID int primary key auto_increment,
    leaseID int not null,
    paymentDate date not null,
    amount double not null,
    constraint lid_fk 
    foreign key(leaseID) references Lease(leaseID) on delete cascade on update cascade
) auto_increment = 10001;


insert into Vehicle (make, model, year, dailyRate, status, passengerCapacity, engineCapacity)
values
('Tesla', 'Model 3', 2023, 150.0, 'available', 5, 0.0),
('BMW', 'X5', 2021, 130.0, 'available', 5, 3.0),
('Mercedes-Benz', 'C-Class', 2022, 140.0, 'notAvailable', 5, 2.0),
('Audi', 'A6', 2022, 135.0, 'available', 5, 2.0),
('Porsche', '911', 2023, 300.0, 'available', 2, 3.0),
('Lexus', 'RX', 2021, 125.0, 'available', 5, 3.5);


insert into Customer (firstName, lastName, email, phoneNumber)
values
('John', 'Doe', 'john.doe@gmail.com', '555-123-4567'),
('Emma', 'Stone', 'emma.stone@yahoo.com', '555-987-6543'),
('Liam', 'Smith', 'liam.smith@hotmail.com', '555-222-3333'),
('Olivia', 'Brown', 'olivia.brown@outlook.com', '555-444-5555'),
('Noah', 'Davis', 'noah.davis@gmail.com', '555-666-7777'),
('Ava', 'Johnson', 'ava.johnson@yahoo.com', '555-888-9999');


insert into Lease (vehicleID, customerID, startDate, endDate, type)
values
(1, 101, '2025-06-01', '2025-06-10', 'Daily'),
(2, 102, '2025-05-20', '2025-06-20', 'Monthly'),
(3, 103, '2025-06-15', '2025-07-15', 'Monthly'),
(5, 101, '2025-07-01', '2025-07-15', 'Daily'),
(2, 104, '2025-08-01', '2025-08-10', 'Daily');


insert into Payment (leaseID, paymentDate, amount)
values
(1001, '2025-06-01', 1350.0),
(1002, '2025-05-20', 3900.0),
(1003, '2025-06-15', 4200.0),
(1004, '2025-07-01', 4200.0),
(1005, '2025-08-01', 1170.0);


select * from Vehicle;

select * from Customer;

select * from Lease;

select * from Payment;
