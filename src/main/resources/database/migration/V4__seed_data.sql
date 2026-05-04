DELETE FROM Schedules;
DELETE FROM Subjects;
DELETE FROM Pupils;
DELETE FROM Classes;
DELETE FROM ClassTypes;
DELETE FROM EmployeesPositions;
DELETE FROM PositionsRequirements;
DELETE FROM PositionsResponsibilities;
DELETE FROM Passport;
DELETE FROM Employees;
DELETE FROM Parents;
DELETE FROM Users;
DELETE FROM Positions;
DELETE FROM Requirements;
DELETE FROM Responsibilities;
DBCC CHECKIDENT ('Users', RESEED, 0);
DBCC CHECKIDENT ('Employees', RESEED, 0);
DBCC CHECKIDENT ('Parents', RESEED, 0);
DBCC CHECKIDENT ('Pupils', RESEED, 0);
DBCC CHECKIDENT ('Positions', RESEED, 0);
DBCC CHECKIDENT ('Requirements', RESEED, 0);
DBCC CHECKIDENT ('Responsibilities', RESEED, 0);
DBCC CHECKIDENT ('ClassTypes', RESEED, 0);
DBCC CHECKIDENT ('Classes', RESEED, 0);
DBCC CHECKIDENT ('Subjects', RESEED, 0);
DBCC CHECKIDENT ('Schedules', RESEED, 0);
DBCC CHECKIDENT ('Passport', RESEED, 0);
DBCC CHECKIDENT ('EmployeesPositions', RESEED, 0);
DBCC CHECKIDENT ('PositionsRequirements', RESEED, 0);
DBCC CHECKIDENT ('PositionsResponsibilities', RESEED, 0);
INSERT INTO Users (Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address, Role) VALUES
                                                                                                          ('admin', '$2a$10$NkM3Y8sVqZVqZVqZVqZVqZuX9X9X9X9X9X9X9X9X9X9X9X9X9X9', 'Олег', 'Сидоренко', 'Васильович', '1985-06-15', 1, 'м. Київ, вул. Хрещатик, 10', 'Employee'),
                                                                                                          ('ivan.petrenko', '$2a$10$LmN4Z9tWrYWrYWrYWrYWrYvY8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8', 'Іван', 'Петренко', 'Миколайович', '1980-03-20', 1, 'м. Львів, вул. Шевченка, 25', 'Employee'),
                                                                                                          ('olena.kovalenko', '$2a$10$LmN4Z9tWrYWrYWrYWrYWrYvY8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8', 'Олена', 'Коваленко', 'Іванівна', '1988-11-10', 0, 'м. Одеса, вул. Дерибасівська, 5', 'Employee'),
                                                                                                          ('andriy.melnyk', '$2a$10$LmN4Z9tWrYWrYWrYWrYWrYvY8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8', 'Андрій', 'Мельник', 'Сергійович', '1992-07-22', 1, 'м. Харків, вул. Сумська, 15', 'Employee'),
                                                                                                          ('mariya.shevchenko', '$2a$10$LmN4Z9tWrYWrYWrYWrYWrYvY8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8Y8', 'Марія', 'Шевченко', 'Володимирівна', '1990-04-05', 0, 'м. Дніпро, пр. Дмитра Яворницького, 30', 'Employee'),
                                                                                                          ('petro.bondar', '$2a$10$KoL5A0uXrZXrZXrZXrZXrZwW7W7W7W7W7W7W7W7W7W7W7W7W7W7', 'Петро', 'Бондар', 'Іванович', '1982-09-18', 1, 'м. Київ, вул. Лісова, 7', 'Parent'),
                                                                                                          ('halyna.bondar', '$2a$10$KoL5A0uXrZXrZXrZXrZXrZwW7W7W7W7W7W7W7W7W7W7W7W7W7W7', 'Галина', 'Бондар', 'Петрівна', '1985-12-03', 0, 'м. Київ, вул. Лісова, 7', 'Parent'),
                                                                                                          ('mykhailo.tkachenko', '$2a$10$KoL5A0uXrZXrZXrZXrZXrZwW7W7W7W7W7W7W7W7W7W7W7W7W7W7', 'Михайло', 'Ткаченко', 'Андрійович', '1978-06-25', 1, 'м. Львів, вул. Зелена, 12', 'Parent'),
                                                                                                          ('oksana.tkachenko', '$2a$10$KoL5A0uXrZXrZXrZXrZXrZwW7W7W7W7W7W7W7W7W7W7W7W7W7W7', 'Оксана', 'Ткаченко', 'Михайлівна', '1980-02-14', 0, 'м. Львів, вул. Зелена, 12', 'Parent'),
                                                                                                          ('volodymyr.kravchenko', '$2a$10$KoL5A0uXrZXrZXrZXrZXrZwW7W7W7W7W7W7W7W7W7W7W7W7W7W7', 'Володимир', 'Кравченко', 'Олегович', '1975-11-30', 1, 'м. Одеса, вул. Пушкінська, 20', 'Parent'),
                                                                                                          ('nadiya.kravchenko', '$2a$10$KoL5A0uXrZXrZXrZXrZXrZwW7W7W7W7W7W7W7W7W7W7W7W7W7W7', 'Надія', 'Кравченко', 'Вікторівна', '1978-07-19', 0, 'м. Одеса, вул. Пушкінська, 20', 'Parent'),
                                                                                                          ('dmytro.bondar', '$2a$10$JmM6B1vYsZsZsZsZsZsZsZxX6X6X6X6X6X6X6X6X6X6X6X6X6X6', 'Дмитро', 'Бондар', 'Петрович', '2010-05-12', 1, 'м. Київ, вул. Лісова, 7', 'Pupil'),
                                                                                                          ('kateryna.tkachenko', '$2a$10$JmM6B1vYsZsZsZsZsZsZsZxX6X6X6X6X6X6X6X6X6X6X6X6X6X6', 'Катерина', 'Ткаченко', 'Михайлівна', '2011-08-23', 0, 'м. Львів, вул. Зелена, 12', 'Pupil'),
                                                                                                          ('oleh.kravchenko', '$2a$10$JmM6B1vYsZsZsZsZsZsZsZxX6X6X6X6X6X6X6X6X6X6X6X6X6X6', 'Олег', 'Кравченко', 'Володимирович', '2009-03-18', 1, 'м. Одеса, вул. Пушкінська, 20', 'Pupil'),
                                                                                                          ('anna.shevchenko', '$2a$10$JmM6B1vYsZsZsZsZsZsZsZxX6X6X6X6X6X6X6X6X6X6X6X6X6X6', 'Анна', 'Шевченко', 'Андріївна', '2012-11-07', 0, 'м. Харків, вул. Наукова, 5', 'Pupil'),
                                                                                                          ('tetiana.honchar', '$2a$10$HnN7C2wXtAtAtAtAtAtAtAyY5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5', 'Тетяна', 'Гончар', 'Василівна', '1995-02-28', 0, 'м. Запоріжжя, вул. Соборна, 45', 'USER'),
                                                                                                          ('serhii.lisovyi', '$2a$10$HnN7C2wXtAtAtAtAtAtAtAyY5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5', 'Сергій', 'Лісовий', 'Петрович', '1993-09-14', 1, 'м. Вінниця, вул. Київська, 8', 'USER');
INSERT INTO ClassTypes (Name, Description) VALUES
                                               ('Початкова школа', '1-4 класи, молодші школярі'),
                                               ('Середня школа', '5-9 класи, базові знання'),
                                               ('Старша школа', '10-11 класи, профільна освіта');
INSERT INTO Positions (Name, Salary) VALUES
                                         ('Директор', 35000.00),
                                         ('Заступник директора', 28000.00),
                                         ('Вчитель вищої категорії', 22000.00),
                                         ('Вчитель першої категорії', 18000.00),
                                         ('Вчитель', 15000.00),
                                         ('Молодший вчитель', 12000.00),
                                         ('Класний керівник (доплата)', 5000.00);
INSERT INTO Requirements (Name, Description) VALUES
                                                 ('Вища освіта', 'Диплом магістра за спеціальністю'),
                                                 ('Стаж роботи від 5 років', 'Досвід роботи в освіті'),
                                                 ('Знання англійської мови', 'Рівень не нижче B2'),
                                                 ('Сертифікат викладача', 'Підвищення кваліфікації раз на 3 роки'),
                                                 ('Знання ІКТ', 'Впевнений користувач ПК, Moodle, Google Classroom'),
                                                 ('Курси психології', 'Бажано курси дитячої психології');
INSERT INTO Responsibilities (Name, Description) VALUES
                                                     ('Проведення уроків', 'Згідно з навчальним планом'),
                                                     ('Перевірка домашніх завдань', 'Регулярна перевірка та оцінювання'),
                                                     ('Підготовка до уроків', 'Розробка конспектів та матеріалів'),
                                                     ('Класне керівництво', 'Організація виховної роботи'),
                                                     ('Зустрічі з батьками', 'Батьківські збори та індивідуальні консультації'),
                                                     ('Участь у педрадах', 'Педагогічні ради та наради'),
                                                     ('Підвищення кваліфікації', 'Курси, тренінги, семінари');
INSERT INTO Employees (UserId, PhoneNumber) VALUES
                                                ((SELECT Id FROM Users WHERE Login = 'admin'), '+380991234567'),
                                                ((SELECT Id FROM Users WHERE Login = 'ivan.petrenko'), '+380682345678'),
                                                ((SELECT Id FROM Users WHERE Login = 'olena.kovalenko'), '+380674567890'),
                                                ((SELECT Id FROM Users WHERE Login = 'andriy.melnyk'), '+380505678901'),
                                                ((SELECT Id FROM Users WHERE Login = 'mariya.shevchenko'), '+380987654321');
INSERT INTO Passport (Name, Data, EmployeeId) VALUES
                                                  ('Паспорт', 'Серія АА №123456, виданий Шевченківським РВ м. Києва, 2005-10-15', 1),
                                                  ('Паспорт', 'Серія ББ №234567, виданий Франківським РВ м. Львова, 1998-05-20', 2),
                                                  ('Паспорт', 'Серія ВВ №345678, виданий Приморським РВ м. Одеси, 2006-03-10', 3),
                                                  ('Паспорт', 'Серія ГГ №456789, виданий Салтівським РВ м. Харкова, 2010-08-25', 4),
                                                  ('Паспорт', 'Серія ДД №567890, виданий Амур-Нижньодніпровським РВ м. Дніпра, 2008-12-01', 5);
INSERT INTO Parents (UserId, Job, PhoneNumber) VALUES
                                                   ((SELECT Id FROM Users WHERE Login = 'petro.bondar'), 'Програміст, ТОВ "СофтСервіс"', '+380972345678'),
                                                   ((SELECT Id FROM Users WHERE Login = 'halyna.bondar'), 'Лікар, Клінічна лікарня №1', '+380681234567'),
                                                   ((SELECT Id FROM Users WHERE Login = 'mykhailo.tkachenko'), 'Підприємець', '+380931234567'),
                                                   ((SELECT Id FROM Users WHERE Login = 'oksana.tkachenko'), 'Вчитель, Львівська гімназія', '+380671234567'),
                                                   ((SELECT Id FROM Users WHERE Login = 'volodymyr.kravchenko'), 'Інженер, Одеський порт', '+380501234567'),
                                                   ((SELECT Id FROM Users WHERE Login = 'nadiya.kravchenko'), 'Бухгалтер', '+380631234567');
INSERT INTO Classes (CuratorId, ClassTypeId, Letter, StudyYear, CreatedAtYear) VALUES
                                                                                   ((SELECT Id FROM Employees WHERE UserId = (SELECT Id FROM Users WHERE Login = 'ivan.petrenko')), 1, 'А', 1, 2023),
                                                                                   ((SELECT Id FROM Employees WHERE UserId = (SELECT Id FROM Users WHERE Login = 'olena.kovalenko')), 1, 'Б', 1, 2023),
                                                                                   ((SELECT Id FROM Employees WHERE UserId = (SELECT Id FROM Users WHERE Login = 'andriy.melnyk')), 2, 'А', 5, 2019),
                                                                                   ((SELECT Id FROM Employees WHERE UserId = (SELECT Id FROM Users WHERE Login = 'mariya.shevchenko')), 2, 'Б', 5, 2019),
                                                                                   ((SELECT Id FROM Employees WHERE UserId = (SELECT Id FROM Users WHERE Login = 'ivan.petrenko')), 3, 'А', 10, 2014),
                                                                                   ((SELECT Id FROM Employees WHERE UserId = (SELECT Id FROM Users WHERE Login = 'olena.kovalenko')), 3, 'Б', 10, 2014);
INSERT INTO Pupils (UserId, FatherId, MotherId, ClassId, ExtraInformation) VALUES
                                                                               ((SELECT Id FROM Users WHERE Login = 'dmytro.bondar'),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'petro.bondar')),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'halyna.bondar')),
                                                                                (SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 5),
                                                                                'Відмінник, цікавиться математикою'),
                                                                               ((SELECT Id FROM Users WHERE Login = 'kateryna.tkachenko'),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'mykhailo.tkachenko')),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'oksana.tkachenko')),
                                                                                (SELECT Id FROM Classes WHERE Letter = 'Б' AND StudyYear = 5),
                                                                                'Займається музикою, фортепіано'),
                                                                               ((SELECT Id FROM Users WHERE Login = 'oleh.kravchenko'),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'volodymyr.kravchenko')),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'nadiya.kravchenko')),
                                                                                (SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 10),
                                                                                'Готується до ЗНО, планує вступати на IT'),
                                                                               ((SELECT Id FROM Users WHERE Login = 'anna.shevchenko'),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'petro.bondar')),
                                                                                (SELECT Id FROM Parents WHERE UserId = (SELECT Id FROM Users WHERE Login = 'halyna.bondar')),
                                                                                (SELECT Id FROM Classes WHERE Letter = 'Б' AND StudyYear = 10),
                                                                                'Переможниця олімпіад з англійської мови');
INSERT INTO PositionsRequirements (PositionId, RequirementId) VALUES
                                                                  (1, 1), (1, 2), (1, 3), (1, 5),
                                                                  (2, 1), (2, 2), (2, 3), (2, 5),
                                                                  (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6),
                                                                  (4, 1), (4, 2), (4, 3), (4, 5),
                                                                  (5, 1), (5, 3), (5, 5);
INSERT INTO PositionsResponsibilities (PositionId, ResponsibilityId) VALUES
                                                                         (1, 4), (1, 6), (1, 7),
                                                                         (2, 4), (2, 6), (2, 7),
                                                                         (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7),
                                                                         (4, 1), (4, 2), (4, 3), (4, 5), (4, 6), (4, 7),
                                                                         (5, 1), (5, 2), (5, 3), (5, 6);
INSERT INTO EmployeesPositions (EmployeeId, PositionId) VALUES
                                                            (1, 1),
                                                            (2, 2), (2, 3),
                                                            (3, 3), (3, 7),
                                                            (4, 4),
                                                            (5, 5);
INSERT INTO Subjects (Name, Description, TeacherId) VALUES
                                                        ('Математика', 'Алгебра та геометрія для середніх та старших класів', 2),
                                                        ('Українська мова', 'Мова та література', 3),
                                                        ('Англійська мова', 'ЗНО підготовка', 5),
                                                        ('Фізика', 'Закони фізики та практичні роботи', 4),
                                                        ('Інформатика', 'Програмування та комп''ютерна грамотність', 2),
                                                        ('Історія України', 'Історія рідного краю', 3),
                                                        ('Біологія', 'Природничі науки', 5),
                                                        ('Хімія', 'Хімічні елементи та реакції', 4);
INSERT INTO Schedules (ClassId, Date, DateOfWeek, SubjectId, StartTime, EndTime) VALUES
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 5), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Математика'), '09:00', '09:45'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 5), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Українська мова'), '09:55', '10:40'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 5), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Англійська мова'), '10:50', '11:35'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 5), DATEADD(DAY, 1, CAST(GETDATE() AS DATE)), 2, (SELECT Id FROM Subjects WHERE Name = 'Математика'), '09:00', '09:45'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 5), DATEADD(DAY, 1, CAST(GETDATE() AS DATE)), 2, (SELECT Id FROM Subjects WHERE Name = 'Інформатика'), '09:55', '10:40'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 5), DATEADD(DAY, 1, CAST(GETDATE() AS DATE)), 2, (SELECT Id FROM Subjects WHERE Name = 'Фізика'), '10:50', '11:35'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'Б' AND StudyYear = 5), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Українська мова'), '09:00', '09:45'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'Б' AND StudyYear = 5), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Математика'), '09:55', '10:40'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'Б' AND StudyYear = 5), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Англійська мова'), '10:50', '11:35'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 10), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Математика'), '10:00', '10:45'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 10), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Фізика'), '10:55', '11:40'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 10), CAST(GETDATE() AS DATE), 1, (SELECT Id FROM Subjects WHERE Name = 'Інформатика'), '11:50', '12:35'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 10), DATEADD(DAY, 1, CAST(GETDATE() AS DATE)), 2, (SELECT Id FROM Subjects WHERE Name = 'Англійська мова'), '10:00', '10:45'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 10), DATEADD(DAY, 1, CAST(GETDATE() AS DATE)), 2, (SELECT Id FROM Subjects WHERE Name = 'Історія України'), '10:55', '11:40'),
                                                                                     ((SELECT Id FROM Classes WHERE Letter = 'А' AND StudyYear = 10), DATEADD(DAY, 1, CAST(GETDATE() AS DATE)), 2, (SELECT Id FROM Subjects WHERE Name = 'Біологія'), '11:50', '12:35');