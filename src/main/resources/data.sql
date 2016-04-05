INSERT INTO authority VALUES('ROLE_ADMIN');
INSERT INTO authority VALUES('ROLE_USER');

INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG',NULL,'System',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'User',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(5,'member1','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'Member 1',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(6,'member2','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'Member 2',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(7,'member3','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'Member 3',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(8,'member4','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'Member 4',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(9,'member5','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'Member 5',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(10,'member6','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'Member 6',NULL,true,'en','system', '2015-01-01');
INSERT INTO users(id, username, password, first_name, last_name, email, activated, lang_key, created_by, created_date) VALUES(11,'member7','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K',NULL,'Member 7',NULL,true,'en','system', '2015-01-01');

INSERT INTO users_authorities VALUES(1, 'ROLE_ADMIN');
INSERT INTO users_authorities VALUES(1, 'ROLE_USER');
INSERT INTO users_authorities VALUES(3, 'ROLE_ADMIN');
INSERT INTO users_authorities VALUES(3, 'ROLE_USER');
INSERT INTO users_authorities VALUES(4, 'ROLE_USER');
INSERT INTO users_authorities VALUES(5, 'ROLE_USER');
INSERT INTO users_authorities VALUES(6, 'ROLE_USER');
INSERT INTO users_authorities VALUES(11, 'ROLE_USER');

INSERT INTO client (id, uuid, name, created_by, created_date) VALUES (1, '0000-0000-0000-001', 'Jones', 'System', '2015-01-01');
INSERT INTO client (id, uuid, name, created_by, created_date) VALUES (2, '0000-0000-0000-002', 'Smith', 'System', '2015-01-01');
INSERT INTO client (id, uuid, name, created_by, created_date) VALUES (3, '0000-0000-0000-003', 'Dube', 'System', '2015-01-01');
INSERT INTO client (id, uuid, name, created_by, created_date) VALUES (4, '0000-0000-0000-004', 'Ndex', 'System', '2015-01-01');

INSERT INTO event (id, uuid, event_name, venue, event_date, amount, total_expenditure, tax_charge, status, created_by, created_date) VALUES (1, '0000-0000-0000-001', 'Durban July', 'Durban', '2015-07-25', '50000', '15000', '7000', 'Completed', 'System', '2015-01-01');
INSERT INTO event (id, uuid, event_name, venue, event_date, amount, total_expenditure, tax_charge, status, created_by, created_date) VALUES (2, '0000-0000-0000-002', 'Rand Show', 'Narsric', '2015-04-27', '30000', '5000', '4200', 'Upcoming', 'System', '2015-01-01');
INSERT INTO event (id, uuid, event_name, venue, event_date, amount, total_expenditure, tax_charge, status, created_by, created_date) VALUES (3, '0000-0000-0000-003', 'Event 4', 'Jazz Festival', '2015-09-01', '45000', '17000', '6300', 'Upcoming', 'System', '2015-01-01');

