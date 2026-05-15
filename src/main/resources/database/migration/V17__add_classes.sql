INSERT INTO Classes (CuratorId, ClassTypeId, Letter, StudyYear, CreatedAtYear)
SELECT e.Id, ct.Id, Data.Letter, Data.StudyYear, Data.CreatedAtYear
FROM (
         VALUES
             ('olena', N'Початкова школа', N'А', 1, 2024),
             ('vitaliy', N'Початкова школа', N'Б', 1, 2024),
             ('petro', N'Початкова школа', N'А', 2, 2023),
             ('olena', N'Початкова школа', N'Б', 2, 2023),
             ('vitaliy', N'Початкова школа', N'А', 3, 2022),
             ('petro', N'Початкова школа', N'Б', 3, 2022),
             ('olena', N'Початкова школа', N'А', 4, 2021),
             ('vitaliy', N'Початкова школа', N'Б', 4, 2021),
             ('taras', N'Середня школа', N'А', 5, 2020),
             ('iryna', N'Середня школа', N'Б', 5, 2020),
             ('andriy', N'Середня школа', N'А', 6, 2019),
             ('svitlana', N'Середня школа', N'Б', 6, 2019),
             ('dmytro', N'Середня школа', N'А', 7, 2018),
             ('taras', N'Середня школа', N'Б', 7, 2018),
             ('mykola', N'Середня школа', N'А', 8, 2017),
             ('yuliia', N'Середня школа', N'Б', 8, 2017),
             ('andriy', N'Середня школа', N'А', 9, 2016),
             ('dmytro', N'Середня школа', N'Б', 9, 2016),
             ('volodymyr', N'Старша школа', N'А', 10, 2015),
             ('kateryna', N'Старша школа', N'Б', 10, 2015),
             ('volodymyr', N'Старша школа', N'А', 11, 2014),
             ('nadiia', N'Старша школа', N'Б', 11, 2014)
     ) AS Data(CuratorLogin, ClassTypeName, Letter, StudyYear, CreatedAtYear)
         INNER JOIN Users u ON u.Login = Data.CuratorLogin
         INNER JOIN Employees e ON e.UserId = u.Id
         INNER JOIN ClassTypes ct ON ct.Name = Data.ClassTypeName
WHERE u.Role = 'EMPLOYEE';