DELETE FROM Pupils;
INSERT INTO Pupils (UserId, FatherId, MotherId, ClassId, ExtraInformation)
SELECT
    u_child.Id,
    f.Id AS FatherId,
    m.Id AS MotherId,
    c.Id AS ClassId,
    Data.ExtraInfo
FROM (
         VALUES
             -- Коваленки (1-А клас)
             ('student_kovalenko_andriy', 'kovalenko_av', 'kovalenko_na', N'1-А', N'Хороші здібності до математики'),
             ('student_kovalenko_olha', 'kovalenko_av', 'kovalenko_na', N'1-А', N'Відвідує гурток малювання'),
             ('student_kovalenko_maxym', 'kovalenko_av', 'kovalenko_na', N'1-А', N'Любить читати'),
             ('student_kovalenko_yaroslav', 'kovalenko_av', 'kovalenko_na', N'1-А', N'Займається плаванням'),

             -- Бондаренки (1-Б клас)
             ('student_bondarenko_dmytro', 'bondarenko_sm', 'bondarenko_tp', N'1-Б', N'Здібності до мов'),
             ('student_bondarenko_anna', 'bondarenko_sm', 'bondarenko_tp', N'1-Б', N'Відвідує хореографічний гурток'),
             ('student_bondarenko_yana', 'bondarenko_sm', 'bondarenko_tp', N'1-Б', N'Спокійна, старанна'),

             -- Шевчуки (2-А клас)
             ('student_shevchuk_mykola', 'shevchuk_ai', 'shevchuk_ov', N'2-А', N'Любить спорт'),
             ('student_shevchuk_iryna', 'shevchuk_ai', 'shevchuk_ov', N'2-А', N'Має здібності до музики'),
             ('student_shevchuk_viktor', 'shevchuk_ai', 'shevchuk_ov', N'2-А', N'Цікавиться комп''ютерами'),
             ('student_shevchuk_uliana', 'shevchuk_ai', 'shevchuk_ov', N'2-А', N'Відвідує гурток малювання'),

             -- Мельники (2-Б клас)
             ('student_melnyk_petro', 'melnyk_vo', 'melnyk_ie', N'2-Б', N'Здібності до природничих наук'),
             ('student_melnyk_oksana', 'melnyk_vo', 'melnyk_ie', N'2-Б', N'Відвідує театральний гурток'),
             ('student_melnyk_sofiya', 'melnyk_vo', 'melnyk_ie', N'2-Б', N'Старанна учениця'),
             ('student_melnyk_andriy', 'melnyk_vo', 'melnyk_ie', N'2-Б', N'Займається футболом'),

             -- Клименки (3-А клас)
             ('student_klymenko_oleh', 'klymenko_es', 'klymenko_lv', N'3-А', N'Лідерські якості'),
             ('student_klymenko_svіtlana', 'klymenko_es', 'klymenko_lv', N'3-А', N'Відвідує гурток рукоділля'),
             ('student_klymenko_dmytro', 'klymenko_es', 'klymenko_lv', N'3-А', N'Цікавиться історією'),
             ('student_klymenko_valeria', 'klymenko_es', 'klymenko_lv', N'3-А', N'Любить співати'),

             -- Савченки (3-Б клас)
             ('student_savchenko_andriy', 'savchenko_ma', 'savchenko_hd', N'3-Б', N'Здібності до технічних наук'),
             ('student_savchenko_maryna', 'savchenko_ma', 'savchenko_hd', N'3-Б', N'Відвідує художню школу'),
             ('student_savchenko_anna', 'savchenko_ma', 'savchenko_hd', N'3-Б', N'Любить тварин'),

             -- Лисенки (4-А клас)
             ('student_lysenko_ivan', 'lysenko_rp', 'lysenko_mo', N'4-А', N'Здібності до мов'),
             ('student_lysenko_natalia', 'lysenko_rp', 'lysenko_mo', N'4-А', N'Займається гімнастикою'),
             ('student_lysenko_petro', 'lysenko_rp', 'lysenko_mo', N'4-А', N'Любить читати енциклопедії'),

             -- Петренки (4-Б клас)
             ('student_petrenko_volodymyr', 'petrenko_vb', 'petrenko_vm', N'4-Б', N'Любить спорт'),
             ('student_petrenko_kateryna', 'petrenko_vb', 'petrenko_vm', N'4-Б', N'Відвідує музичну школу'),
             ('student_petrenko_alina', 'petrenko_vb', 'petrenko_vm', N'4-Б', N'Хороші здібності до малювання'),

             -- Гончаренки (5-А клас)
             ('student_honcharenko_serhiy', 'honcharenko_ov', 'honcharenko_io', N'5-А', N'Цікавиться програмуванням'),
             ('student_honcharenko_yulia', 'honcharenko_ov', 'honcharenko_io', N'5-А', N'Відвідує танцювальний гурток'),
             ('student_honcharenko_roman', 'honcharenko_ov', 'honcharenko_io', N'5-А', N'Любить спорт'),

             -- Романенки (5-Б клас)
             ('student_romanenko_vitaliy', 'romanenko_vp', 'romanenko_lm', N'5-Б', N'Здібності до точних наук'),
             ('student_romanenko_olena', 'romanenko_vp', 'romanenko_lm', N'5-Б', N'Відвідує літературний гурток'),
             ('student_romanenko_viktoria', 'romanenko_vp', 'romanenko_lm', N'5-Б', N'Хороші організаторські здібності'),

             -- Ткальченки (6-А клас)
             ('student_tkalchenko_maksym', 'tkalchenko_sv', 'tkalchenko_ov', N'6-А', N'Любить комп''ютерні ігри та програмування'),
             ('student_tkalchenko_angelina', 'tkalchenko_sv', 'tkalchenko_ov', N'6-А', N'Відвідує вокальний гурток'),
             ('student_tkalchenko_ivan', 'tkalchenko_sv', 'tkalchenko_ov', N'6-А', N'Займається боротьбою'),

             -- Панченки (6-Б клас)
             ('student_panchenko_bohdan', 'panchenko_op', 'panchenko_vs', N'6-Б', N'Цікавиться історією'),
             ('student_panchenko_sofia', 'panchenko_op', 'panchenko_vs', N'6-Б', N'Відвідує гурток біології'),
             ('student_panchenko_oleksandr', 'panchenko_op', 'panchenko_vs', N'6-Б', N'Любить конструювати'),

             -- Кушніри (7-А клас)
             ('student_kushnir_artem', 'kushnir_vm', 'kushnir_ir', N'7-А', N'Здібності до математики'),
             ('student_kushnir_diana', 'kushnir_vm', 'kushnir_ir', N'7-А', N'Відвідує гурток журналістики'),
             ('student_kushnir_tetiana', 'kushnir_vm', 'kushnir_ir', N'7-А', N'Любить вивчати мови'),

             -- Лукашенки (7-Б клас)
             ('student_lukashenko_vladyslav', 'lukashenko_ai', 'lukashenko_om', N'7-Б', N'Цікавиться хімією'),
             ('student_lukashenko_veronika', 'lukashenko_ai', 'lukashenko_om', N'7-Б', N'Відвідує школу мистецтв'),
             ('student_lukashenko_oleg', 'lukashenko_ai', 'lukashenko_om', N'7-Б', N'Займається робототехнікою'),

             -- Бондарі (8-А клас)
             ('student_bondar_danylo', 'bondar_yv', 'bondar_lp', N'8-А', N'Лідер класу'),
             ('student_bondar_marharyta', 'bondar_yv', 'bondar_lp', N'8-А', N'Відвідує театральний гурток'),
             ('student_bondar_nina', 'bondar_yv', 'bondar_lp', N'8-А', N'Хороші здібності до літератури')
     ) AS Data(ChildLogin, FatherLogin, MotherLogin, ClassName, ExtraInfo)
         INNER JOIN Users u_child ON u_child.Login = Data.ChildLogin
         INNER JOIN Parents f ON f.UserId = (SELECT Id FROM Users WHERE Login = Data.FatherLogin)
         INNER JOIN Parents m ON m.UserId = (SELECT Id FROM Users WHERE Login = Data.MotherLogin)
         INNER JOIN Classes c ON CONCAT(c.StudyYear, '-', c.Letter) = Data.ClassName
WHERE u_child.Role = 'PUPIL';