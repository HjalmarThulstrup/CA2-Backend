
INSERT INTO ca2Test.Hobby (description, name) VALUES ("populationTest", "popTest");
INSERT INTO ca2Test.Hobby (description, name) VALUES ("getTESTDESC", "GetTest");

INSERT INTO ca2Test.CityInfo (zipCode, City) VALUES (1000, "Testby");
INSERT INTO ca2Test.Address (additionalInfo, street, fkCity) VALUES ("Det en addresse", "Gadetest", 1000);

INSERT INTO ca2Test.Person (email, firstName, lastName, fkAddressId) VALUES ("email@email.com", "test", "Testings", 1);
INSERT INTO ca2Test.Person (email, firstName, lastName, fkAddressId) VALUES ("cmail@email.com", "Bob", "Jensen", 1);
INSERT INTO ca2Test.Person (email, firstName, lastName, fkAddressId) VALUES ("gmail@email.com", "jannet", "svendsen", 1);

INSERT INTO ca2Test.PersonHobby (personId, hobbyId) VALUES (1, 2);
INSERT INTO ca2Test.PersonHobby (personId, hobbyId) VALUES (2, 2);
INSERT INTO ca2Test.PersonHobby (personId, hobbyId) VALUES (3, 2);

INSERT INTO ca2Test.Phone (description, `number`, fkPersonId) VALUES ("TestNummer", 12345678, 1);
