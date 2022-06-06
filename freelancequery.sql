CREATE DATABASE FreelancePlatformBaoHLG
USE FreelancePlatformBaoHLG

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
    UserID int NOT NULL,
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
    UserID int NOT NULL ,
	CompanyID int ,
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
	FieldID int ,
);

--Table for Skill (Freelancer N-N Tech)
CREATE TABLE Skill (
    ID int IDENTITY(1,1) PRIMARY KEY,
	FreelancerID int ,
	TechID int ,
);

--Table for Project (job post)
CREATE TABLE Project (
    ID int IDENTITY(1,1) PRIMARY KEY,
	CustomerID int ,
	Title nvarchar(64) NOT NULL,
	Description nvarchar(128) NOT NULL,
	Duration nvarchar(64) NOT NULL,
	Scope nvarchar(64) NOT NULL,
	HrsPerWeek int NOT NULL,
	MaximumBudget int NOT NULL,
	FieldID int NOT NULL ,
	Status int NOT NULL,
);

--Table for Tag (Tech N-N Project)
CREATE TABLE Tag (
    ID int IDENTITY(1,1) PRIMARY KEY,
	FreelancerID int ,
	TechID int ,
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
	FreelancerID int ,
	ProjectID int ,
	Date DateTime NOT NULL,
	Bid int NOT NULL,
	ApplyStatusID int ,
);

--Table for Report
CREATE TABLE Report (
    ID int IDENTITY(1,1) PRIMARY KEY,
	FreelancerID int ,
	ApplyID int ,
	CustomerID int ,
	Title nvarchar(64) NOT NULL,
	Date DateTime NOT NULL,
	ReportItem nvarchar(128) NOT NULL,
	Status int NOT NULL,
);

--Table for Payment
CREATE TABLE Payment (
    ID int IDENTITY(1,1) PRIMARY KEY,
	ApplyID int ,
	Date DateTime NOT NULL,
	PaymentMethod nvarchar(64) NOT NULL,
);

--Table for Transaction
CREATE TABLE Transactions (
    ID int IDENTITY(1,1) PRIMARY KEY,
	PaymentID int ,
	FreelancerID int ,
	Amount int NOT NULL,
);

--Foreign keys
ALTER TABLE FreelancerAccount ADD FOREIGN KEY(UserID) REFERENCES UserAccount(ID)
ALTER TABLE CustomerAccount ADD FOREIGN KEY(UserID) REFERENCES UserAccount(ID)
ALTER TABLE CustomerAccount ADD FOREIGN KEY(CompanyID) REFERENCES Company(ID)
ALTER TABLE Tech ADD FOREIGN KEY(FieldID) REFERENCES Field(ID)
ALTER TABLE Skill ADD FOREIGN KEY(FreelancerID) REFERENCES FreelancerAccount(ID)
ALTER TABLE Skill ADD FOREIGN KEY(TechID) REFERENCES Tech(ID)
ALTER TABLE Project ADD FOREIGN KEY(CustomerID) REFERENCES CustomerAccount(ID)
ALTER TABLE Project ADD FOREIGN KEY(FieldID) REFERENCES Field(ID)
ALTER TABLE Tag ADD FOREIGN KEY(FreelancerID) REFERENCES FreelancerAccount(ID)
ALTER TABLE Tag ADD FOREIGN KEY(TechID) REFERENCES Tech(ID)
ALTER TABLE Apply ADD FOREIGN KEY(FreelancerID) REFERENCES FreelancerAccount(ID)
ALTER TABLE Apply ADD FOREIGN KEY(ProjectID) REFERENCES Project(ID)
ALTER TABLE Apply ADD FOREIGN KEY(ApplyStatusID) REFERENCES ApplyStatus(ID)
ALTER TABLE Report ADD FOREIGN KEY(FreelancerID) REFERENCES FreelancerAccount(ID)
ALTER TABLE Report ADD FOREIGN KEY(ApplyID) REFERENCES Apply(ID)
ALTER TABLE Report ADD FOREIGN KEY(CustomerID) REFERENCES CustomerAccount(ID)
ALTER TABLE Payment ADD FOREIGN KEY(ApplyID) REFERENCES Apply(ID)
ALTER TABLE Transactions ADD FOREIGN KEY(PaymentID) REFERENCES Payment(ID)
ALTER TABLE Transactions ADD FOREIGN KEY(FreelancerID) REFERENCES FreelancerAccount(ID)

DROP DATABASE FreelancePlatformBaoHLG