DELETE FROM Parents;
INSERT INTO Parents (UserId, Job, PhoneNumber)
SELECT u.Id, Data.Job, Data.PhoneNumber
FROM (
         VALUES
             ('kovalenko_av', N'Програміст', '+386712345679'),
             ('kovalenko_na', N'Бухгалтер', '+386709876544'),
             ('bondarenko_sm', N'Водій', '+386700112244'),
             ('bondarenko_tp', N'Продавець', '+386700445567'),
             ('shevchuk_ai', N'Будівельник', '+386700778890'),
             ('shevchuk_ov', N'Медсестра', '+386700990012'),
             ('melnyk_vo', N'Інженер', '+386701112234'),
             ('melnyk_ie', N'Вчителька', '+386701445567'),
             ('klymenko_es', N'Економіст', '+386701778891'),
             ('klymenko_lv', N'Домогосподарка', '+386702001123'),
             ('savchenko_ma', N'Військовослужбовець', '+386702334456'),
             ('savchenko_hd', N'Перукар', '+386702667789'),
             ('lysenko_rp', N'Менеджер', '+386702990012'),
             ('lysenko_mo', N'Фармацевт', '+386703223345'),
             ('petrenko_vb', N'Фермер', '+386703556678'),
             ('petrenko_vm', N'Швачка', '+386703889901'),
             ('honcharenko_ov', N'Юрист', '+386704001123'),
             ('honcharenko_io', N'Касир', '+386704112234'),
             ('romanenko_vp', N'Електрик', '+386704223345'),
             ('romanenko_lm', N'Вихователька', '+386704334456'),
             ('tkalchenko_sv', N'Сантехнік', '+386704445567'),
             ('tkalchenko_ov', N'Кухарка', '+386704556678'),
             ('panchenko_op', N'Підприємець', '+386704667789'),
             ('panchenko_vs', N'Дизайнерка', '+386704778890'),
             ('kushnir_vm', N'Лікар', '+386704889901'),
             ('kushnir_ir', N'АптекаРка', '+386704990012'),
             ('lukashenko_ai', N'Архітектор', '+386705001123'),
             ('lukashenko_om', N'Журналістка', '+386705112234'),
             ('bondar_yv', N'Слюсар', '+386705223345'),
             ('bondar_lp', N'Пенсіонерка', '+386705334456')
     ) AS Data(Login, Job, PhoneNumber)
         INNER JOIN Users u ON u.Login = Data.Login
WHERE u.Role = 'PARENT';