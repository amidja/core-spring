insert into SYSTEM_USER(USER_ID, USER_NAME, PASSWORD, FIRST_NM, LAST_NM, USING_2FA, OTP_CODE) 
	values ( 001, 'admin', 'admin', 'Jasmin', 'Amidzic', true, 'A002');
insert into SYSTEM_USER(USER_ID, USER_NAME, PASSWORD, FIRST_NM, LAST_NM, USING_2FA, OTP_CODE) 
	values ( 002, 'amidja', 'amidja', 'Jasmin', 'Amidzic', false, 'A001');

insert into SYSTEM_ROLE(ROLE_ID, ROLE_DESC, ROLE_VAL) values ( 1, 'Admin User Role', 'ROLE_ADMIN');
insert into SYSTEM_ROLE(ROLE_ID, ROLE_DESC, ROLE_VAL) values ( 2, 'Default User Role', 'ROLE_USER');
insert into SYSTEM_ROLE(ROLE_ID, ROLE_DESC, ROLE_VAL) values ( 3, 'Default User Role', 'ROLE_GUEST');

insert into USER_ROLES(USER_ID_FK, ROLE_ID_FK) values ( 1, 1);
insert into USER_ROLES(USER_ID_FK, ROLE_ID_FK) values ( 1, 2);

insert into USER_ROLES(USER_ID_FK, ROLE_ID_FK) values ( 2, 2);
insert into USER_ROLES(USER_ID_FK, ROLE_ID_FK) values ( 2, 3);