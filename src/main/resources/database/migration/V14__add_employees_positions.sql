DELETE FROM EmployeesPositions;
INSERT INTO EmployeesPositions (EmployeeId, PositionId)
SELECT e.Id, p.Id
FROM (
         VALUES
             ('ivan', N'Вчитель інформатики'),
             ('ivan', N'Класний керівник'),
             ('olena', N'Вчитель української мови та літератури'),
             ('olena', N'Класний керівник'),
             ('andriy', N'Вчитель математики'),
             ('maryna', N'Вчитель англійської мови'),
             ('dmytro', N'Вчитель фізики'),
             ('oksana', N'Вчитель хімії'),
             ('petro', N'Вчитель біології'),
             ('taras', N'Вчитель історії'),
             ('iryna', N'Вчитель географії'),
             ('mykola', N'Вчитель фізичної культури'),
             ('yuliia', N'Вчитель музики'),
             ('vitaliy', N'Вчитель початкових класів'),
             ('svitlana', N'Практичний психолог'),
             ('oleh', N'Соціальний педагог'),
             ('kateryna', N'Бібліотекар'),
             ('volodymyr', N'Заступник директора з навчальної роботи'),
             ('nadiia', N'Медична сестра')
     ) AS Data(Login, PositionName)
         INNER JOIN Users u ON u.Login = Data.Login
         INNER JOIN Employees e ON e.UserId = u.Id
         INNER JOIN Positions p ON p.Name = Data.PositionName
WHERE u.Role = 'EMPLOYEE';