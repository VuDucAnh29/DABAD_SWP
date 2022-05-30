CREATE DATABASE FreelancePlatform
USE FreelancePlatform

--Table for User Account entity
CREATE TABLE UserAccount (
    ID int IDENTITY(1,1) PRIMARY KEY,
    Email varchar(128) NOT NULL UNIQUE,
    Password varchar(32) NOT NULL,
    FullName nvarchar(128) NOT NULL,
	Avatar varchar(128),
	RoleID int NOT NULL,
	CreateDate DateTime NOT NULL,
	Balance float NOT NULL,
	Status int NOT NULL,
);

--Table for Freelancer Account
CREATE TABLE FreelancerAccount (
    ID int IDENTITY(1,1) PRIMARY KEY,
    UserID int NOT NULL FOREIGN KEY REFERENCES UserAccount(ID),
	Overview nvarchar(128),
);

--Table for Company (dict)
CREATE TABLE Company (
    ID int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(64) NOT NULL,
	Location nvarchar(64) NOT NULL,
);

--Table for Customer Account
CREATE TABLE CustomerAccount (
    ID int IDENTITY(1,1) PRIMARY KEY,
    UserID int NOT NULL FOREIGN KEY REFERENCES UserAccount(ID),
	CompanyID int FOREIGN KEY REFERENCES Company(ID),
);

--Table for field
CREATE TABLE Field (
    ID int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(64) NOT NULL,
	Description nvarchar(128) NOT NULL,
);

--Table for Tech
CREATE TABLE Tech (
    ID int IDENTITY(1,1) PRIMARY KEY,
    Name nvarchar(64) NOT NULL,
	Description nvarchar(128) NOT NULL,
	FieldID int FOREIGN KEY REFERENCES Field(ID),
);

--Table for Skill (Freelancer N-N Tech)
CREATE TABLE Skill (
    ID int IDENTITY(1,1) PRIMARY KEY,
	FreelancerID int FOREIGN KEY REFERENCES FreelancerAccount(ID),
	TechID int FOREIGN KEY REFERENCES Tech(ID),
);

--Table for Project (job post)
CREATE TABLE Project (
    ID int IDENTITY(1,1) PRIMARY KEY,
	CustomerID int FOREIGN KEY REFERENCES CustomerAccount(ID),
	Title nvarchar(64) NOT NULL,
	Description nvarchar(128) NOT NULL,
	Duration nvarchar(64) NOT NULL,
	Scope nvarchar(64) NOT NULL,
	HrsPerWeek int NOT NULL,
	MaximumBudget int NOT NULL,
	FieldID int NOT NULL FOREIGN KEY REFERENCES Field(ID),
	Status int NOT NULL,
);

--Table for Tag (Tech N-N Project)
CREATE TABLE Tag (
    ID int IDENTITY(1,1) PRIMARY KEY,
	FreelancerID int FOREIGN KEY REFERENCES FreelancerAccount(ID),
	TechID int FOREIGN KEY REFERENCES Tech(ID),
);

--Table for apply status (dict)
CREATE TABLE ApplyStatus (
    ID int IDENTITY(1,1) PRIMARY KEY,
	Name nvarchar(64) NOT NULL,
	Description nvarchar(128) NOT NULL,
);

--Table for Apply (proposal for project)
CREATE TABLE Apply (
    ID int IDENTITY(1,1) PRIMARY KEY,
	FreelancerID int FOREIGN KEY REFERENCES FreelancerAccount(ID),
	ProjectID int FOREIGN KEY REFERENCES Project(ID),
	Date DateTime NOT NULL,
	Bid int NOT NULL,
	ApplyStatusID int FOREIGN KEY REFERENCES ApplyStatus(ID),
);

--Table for Report
CREATE TABLE Report (
    ID int IDENTITY(1,1) PRIMARY KEY,
	FreelancerID int FOREIGN KEY REFERENCES FreelancerAccount(ID),
	ApplyID int FOREIGN KEY REFERENCES Apply(ID),
	CustomerID int FOREIGN KEY REFERENCES CustomerAccount(ID),
	Title nvarchar(64) NOT NULL,
	Date DateTime NOT NULL,
	ReportItem nvarchar(128) NOT NULL,
	Status int NOT NULL,
);

--Table for Payment
CREATE TABLE Payment (
    ID int IDENTITY(1,1) PRIMARY KEY,
	ApplyID int FOREIGN KEY REFERENCES Apply(ID),
	Date DateTime NOT NULL,
	PaymentMethod nvarchar(64) NOT NULL,
);

--Table for Transaction
CREATE TABLE Transactions (
    ID int IDENTITY(1,1) PRIMARY KEY,
	PaymentID int FOREIGN KEY REFERENCES Payment(ID),
	FreelancerID int FOREIGN KEY REFERENCES FreelancerAccount(ID),
	Amount int NOT NULL,
);

DROP DATABASE FreelancePlatform