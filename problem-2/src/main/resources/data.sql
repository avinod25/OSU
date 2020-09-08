--default password: Welcome@01
INSERT INTO user_info (user_id,first_name,last_name,email,user_name,user_password,role,created_by,modified_by,created_date,modified_date)
		VALUES (100,'OSU','Admin','admin@gmail.com','osuadmin','$2y$12$VBYKCxm58hxGuivcUo/efOqC7MuJczj0CQzSVCtP8hErG6Qoh9Nva','ADMIN','osuadmin','osuadmin',NOW(),NOW());
INSERT INTO user_info (user_id,first_name,last_name,email,user_name,user_password,role,created_by,modified_by,created_date,modified_date)
		VALUES (101,'Test','User','test@gmail.com','osutest','$2y$12$VBYKCxm58hxGuivcUo/efOqC7MuJczj0CQzSVCtP8hErG6Qoh9Nva','USER','osuadmin','osuadmin',NOW(),NOW());
		
INSERT INTO lobsters (lobster_id,name,description,kind,created_by,modified_by,created_date,modified_date)
		VALUES (100,'Reef Lobster','Scientific name Enoplometopus','Clawed','osuadmin','osuadmin',NOW(),NOW());
INSERT INTO lobsters (lobster_id,name,description,kind,created_by,modified_by,created_date,modified_date)
		VALUES (101,'Slipper Lobster','Slipper lobsters are a family of about 90 species of achelate crustaceans, 
		in the Decapoda clade Reptantia, found in all warm oceans and seas','Spiny','osuadmin','osuadmin',NOW(),NOW());
INSERT INTO lobsters (lobster_id,name,description,kind,created_by,modified_by,created_date,modified_date)
		VALUES (102,'Rock Lobster','Spiny lobsters, also known as langustas','Spiny','osuadmin','osuadmin',NOW(),NOW());
INSERT INTO lobsters (lobster_id,name,description,kind,created_by,modified_by,created_date,modified_date)
		VALUES (103,'American Lobster','The American lobster is a species of lobster found on the Atlantic coast of North America, chiefly from Labrador to New Jersey','Clawed','osuadmin','osuadmin',NOW(),NOW());