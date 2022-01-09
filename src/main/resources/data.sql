insert into users values (0, 'myusername', '$2a$10$u7FFGoYdLQghzodp8/ZC/uhgC2FwsCU404ZiikBijy3aUKA0azeAG', 'manolismavrok@gmail.com', 1);
insert into users values (0, 'yourusername', '$2a$10$bp8hHYu6M/thAdc2wtIiBeXcmZvMPv1D9gG2wxHX7806gFVHh8KDy', 'asimeniah@oaed.com', 1);
insert into users values (0, 'hisusername', '$2a$10$uj2/eisi0h08caVqNvXmDOSLiQMWt14s3iqq/MF4a7iPJp4XR1T0m', 'john@oasa.com', 1);
insert into users values (0, 'admin', '$2a$10$X2.WTmLxvw0vN7SiAL7AWerQLUUEBPz6MJ5//daPUPSg8PJnaTsT.', 'group20@gmail.com', 1);


insert into authorities values ('myusername', 'ROLE_CIVILIAN');
insert into authorities values ('yourusername', 'ROLE_OAED');
insert into authorities values ('hisusername', 'ROLE_OASA');
insert into authorities values ('admin', 'ROLE_ADMIN');