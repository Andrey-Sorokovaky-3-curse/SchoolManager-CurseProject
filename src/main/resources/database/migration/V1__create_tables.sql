CREATE TABLE Users (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Login NVARCHAR(50) NOT NULL,
    Password NVARCHAR(300) NOT NULL,
    FirstName NVARCHAR(50) NOT NULL,
    LastName NVARCHAR(50) NOT NULL,
    MiddleName NVARCHAR(50) NOT NULL,
    Birthday DATE NOT NULL,
    Gender BIT NOT NULL,
    Address NVARCHAR(200) NOT NULL,

    CONSTRAINT UQ_User_Login UNIQUE (Login)
);

CREATE TABLE Parents (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    UserId INT NOT NULL FOREIGN KEY REFERENCES Users(Id),
    Job NVARCHAR(1000),
    PhoneNumber NVARCHAR(20) NOT NULL,
    CONSTRAINT CK_Parent_PhoneNumber_UA CHECK (PhoneNumber LIKE '+38[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    CONSTRAINT UQ_Parent_PhoneNumber UNIQUE (PhoneNumber)
);

CREATE TABLE Positions (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL,
);

CREATE TABLE Employees (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    UserId INT NOT NULL FOREIGN KEY REFERENCES Users(Id),
    PhoneNumber NVARCHAR(20) NOT NULL,
    CONSTRAINT CK_Employee_PhoneNumber_UA CHECK (PhoneNumber LIKE '+38[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    CONSTRAINT UQ_Employee_PhoneNumber UNIQUE (PhoneNumber)
);

CREATE TABLE Passport (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Name NVARCHAR(30) NOT NULL,
    Data NVARCHAR(1000) NOT NULL,
    EmployeeId INT NOT NULL FOREIGN KEY REFERENCES Employees(Id)
);

CREATE TABLE Responsibilities (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(1000) DEFAULT ''
);

CREATE TABLE Requirements (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(1000) DEFAULT ''
);

CREATE TABLE PositionsResponsibilities (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    PositionId INT NOT NULL FOREIGN KEY REFERENCES Positions(Id) ON DELETE CASCADE ,
    ResponsibilityId INT NOT NULL FOREIGN KEY REFERENCES Responsibilities(Id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX UIX_PositionsResponsibilities_Pair ON PositionsResponsibilities(PositionId, ResponsibilityId);

CREATE TABLE PositionsRequirements (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    PositionId INT NOT NULL FOREIGN KEY REFERENCES Positions(Id),
    RequirementId INT NOT NULL FOREIGN KEY REFERENCES Requirements(Id)
);

CREATE UNIQUE INDEX UIX_PositionsRequirements_Pair ON PositionsRequirements(PositionId, RequirementId);

CREATE TABLE EmployeesPositions (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    EmployeeId INT NOT NULL FOREIGN KEY REFERENCES Employees(Id),
    PositionId INT NOT NULL FOREIGN KEY REFERENCES Positions(Id)
);

CREATE UNIQUE INDEX UIX_PositionsEmployees_Pair ON EmployeesPositions(PositionId, EmployeeId);

CREATE TABLE ClassTypes (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(1000) DEFAULT ''
);

CREATE TABLE Classes (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    CuratorId INT NOT NULL FOREIGN KEY REFERENCES Employees(Id),
    ClassTypeId INT NOT NULL FOREIGN KEY REFERENCES ClassTypes(Id),
    Letter CHAR NOT NULL,
    StudyYear INT NOT NULL,
    CreatedAtYear INT NOT NULL,

    CONSTRAINT CK_Class_CreatedAtYear CHECK (CreatedAtYear BETWEEN 0 AND YEAR(GETDATE())),
    CONSTRAINT CK_Class_StudyYear CHECK (StudyYear BETWEEN 1 AND 12)
);

CREATE TABLE Pupils (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    UserId INT NOT NULL FOREIGN KEY REFERENCES Users(Id),
    FatherId INT NOT NULL FOREIGN KEY REFERENCES Parents(Id),
    MotherId INT NOT NULL FOREIGN KEY REFERENCES Parents(Id),
    ClassId INT NOT NULL FOREIGN KEY REFERENCES Classes(Id),
    ExtraInformation NVARCHAR(1000) DEFAULT '',
    CONSTRAINT CK_Pupil_FatherNotMother CHECK (FatherId != MotherId)
);

CREATE TABLE Subjects (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Name NVARCHAR(50),
    Description NVARCHAR(500) DEFAULT '',
    TeacherId INT NOT NULL FOREIGN KEY REFERENCES Employees(Id),
);

CREATE TABLE Schedules (
    Id INT IDENTITY(1, 1) PRIMARY KEY,
    Date DATE NOT NULL,
    DateOfWeek INT NOT NULL,
    SubjectId INT NOT NULL FOREIGN KEY REFERENCES Subjects(Id),
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    CONSTRAINT CK_Schedules_Time CHECK (StartTime < EndTime),
    ClassId INT NOT NULL FOREIGN KEY REFERENCES Classes(Id)
);