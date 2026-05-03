ALTER TABLE Employees DROP CONSTRAINT CK_Employee_PhoneNumber_UA;
ALTER TABLE Parents DROP CONSTRAINT CK_Parent_PhoneNumber_UA;

ALTER TABLE Employees
    ADD CONSTRAINT CK_Employee_PhoneNumber_UA CHECK (
        PhoneNumber LIKE '+38[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'
        );

ALTER TABLE Parents
    ADD CONSTRAINT CK_Parent_PhoneNumber_UA CHECK (
        PhoneNumber LIKE '+38[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'
        );