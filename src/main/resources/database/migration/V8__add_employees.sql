INSERT INTO Employees (UserId, PhoneNumber)
SELECT Id, PhoneNumber
FROM (
         VALUES
             ('ivan', '+386712345678'),
             ('olena', '+386709876543'),
             ('andriy', '+386700112233'),
             ('maryna', '+386700445566'),
             ('dmytro', '+386700778899'),
             ('oksana', '+386700990011'),
             ('petro', '+386701112233'),
             ('taras', '+386701445566'),
             ('iryna', '+386701778899'),
             ('mykola', '+386702001122'),
             ('yuliia', '+386702334455'),
             ('vitaliy', '+386702667788'),
             ('svitlana', '+386702990011'),
             ('oleh', '+386703223344'),
             ('kateryna', '+386703556677'),
             ('volodymyr', '+386703889900'),
             ('nadiia', '+386704001122')
     ) AS Data(Login, PhoneNumber)
         INNER JOIN Users ON Users.Login = Data.Login
WHERE Users.Role = 'EMPLOYEE';