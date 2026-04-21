ALTER TABLE Users
ADD Role NVARCHAR(15) NOT NULL
    CONSTRAINT DF_Users_Role DEFAULT 'User',
    CONSTRAINT CK_User_Role CHECK (Role in ('User', 'Employee', 'Parent', 'Pupil'))