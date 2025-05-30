insert into role_types (id, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae0', 'USER');
insert into role_types (id, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae1', 'ADMIN');

insert into training_levels(id, display_name, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae3', 'Beginner', 'BEGINNER');
insert into training_levels(id, display_name, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae4', 'Intermediate', 'INTERMEDIATE');
insert into training_levels(id, display_name, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae5', 'Advanced', 'ADVANCED');

INSERT INTO public.users (age, last_login, weight, id, training_level_id, email, first_name, gender, last_name, password) VALUES (30, '2025-05-28 17:17:59.464040 +00:00', 85, '626fc8ef-4b13-4b2d-80a7-55b41f971939', 'd6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae3', 'root1@root.root', 'John', 'MALE', 'Doe', '$2a$10$WKljzEyVPub/5Q6vHp0RA.xV0SXPDF5j3yJvTMNWzSOdE7AFFCxBy');
insert into users_role_types(role_type_id, user_id) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae0', '626fc8ef-4b13-4b2d-80a7-55b41f971939');
insert into users_role_types(role_type_id, user_id) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae1', '626fc8ef-4b13-4b2d-80a7-55b41f971939');

INSERT INTO public.muscle_groups (id, name) VALUES ('8b5937c2-313d-4348-ac29-ad5bfda59bcb', 'Грудні');
INSERT INTO public.muscle_groups (id, name) VALUES ('0a480c10-cda8-4735-84b6-de0b0045192c', 'Широка спина');
INSERT INTO public.muscle_groups (id, name) VALUES ('d606339c-54cc-4a52-a56a-54a7e98590eb', 'Плечі');
INSERT INTO public.muscle_groups (id, name) VALUES ('ae59d818-9493-45b9-8d7b-7918977f6103', 'Біцепс');
INSERT INTO public.muscle_groups (id, name) VALUES ('7d4ed795-8ecb-4848-b06b-695fd914848f', 'Трицепс');
INSERT INTO public.muscle_groups (id, name) VALUES ('869e8ae5-c9b9-452a-8d40-8a9f12b8895a', 'Прес');
INSERT INTO public.muscle_groups (id, name) VALUES ('61226686-f185-4d76-a24e-b977e7337b2b', 'Сідниці');
INSERT INTO public.muscle_groups (id, name) VALUES ('dbb5b0b2-021d-4b64-a015-75b29773fc89', 'Задня поверхня стегна');
INSERT INTO public.muscle_groups (id, name) VALUES ('cb2ff15d-8fc1-4bfb-ab5a-1c6b58391241', 'Ікри');

INSERT INTO public.base_sets (kg, position, reps, id) VALUES (10, 1, 15, 'ee3f008e-7b92-41fc-a20d-324b822fe001');
INSERT INTO public.base_sets (kg, position, reps, id) VALUES (15, 2, 12, '63ef050f-66a9-4649-add1-fe8ef27cf107');
INSERT INTO public.base_sets (kg, position, reps, id) VALUES (20, 3, 10, '27eb9c6b-5fdf-4b28-95c4-79ade5e0a94e');
INSERT INTO public.base_sets (kg, position, reps, id) VALUES (30, 4, 18, '61cafa3c-2378-4074-8672-1c844f701f19');

INSERT INTO public.base_exercises (id, muscle_group_id, description, main_image, mini_image, name, video_url) VALUES ('f3f0359f-b942-45d0-bb90-d091c607b4e4', 'ae59d818-9493-45b9-8d7b-7918977f6103', 'description1', null, null, 'На біцепс', 'https://www.youtube.com/watch?v=MWNmDQHGgkc&ab_channel=%D0%A3%D0%A2-2');
INSERT INTO public.base_exercises (id, muscle_group_id, description, main_image, mini_image, name, video_url) VALUES ('da8d22a0-d9a2-4a52-928e-a931ac12251b', '8b5937c2-313d-4348-ac29-ad5bfda59bcb', 'description1', null, null, 'На груди', 'https://www.youtube.com/watch?v=MWNmDQHGgkc&ab_channel=%D0%A3%D0%A2-2');
INSERT INTO public.base_exercises (id, muscle_group_id, description, main_image, mini_image, name, video_url) VALUES ('1ff2faa5-0c74-4fd8-b46c-29a8f856c866', '0a480c10-cda8-4735-84b6-de0b0045192c', 'description1', null, null, 'На спину', 'https://www.youtube.com/watch?v=MWNmDQHGgkc&ab_channel=%D0%A3%D0%A2-2');

INSERT INTO public.base_ex_to_position (position, base_ex_id, id) VALUES (1, 'f3f0359f-b942-45d0-bb90-d091c607b4e4', 'c16b49b8-a9ab-4532-985c-736aea711387');
INSERT INTO public.base_ex_to_position (position, base_ex_id, id) VALUES (2, 'da8d22a0-d9a2-4a52-928e-a931ac12251b', '1a5fdf39-ee99-47d3-b67f-70f4fdcedd86');
INSERT INTO public.base_ex_to_position (position, base_ex_id, id) VALUES (3, '1ff2faa5-0c74-4fd8-b46c-29a8f856c866', 'f8873fe1-9419-403b-a040-5193435ca6e6');

INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('c16b49b8-a9ab-4532-985c-736aea711387', 'ee3f008e-7b92-41fc-a20d-324b822fe001');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('c16b49b8-a9ab-4532-985c-736aea711387', '63ef050f-66a9-4649-add1-fe8ef27cf107');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('c16b49b8-a9ab-4532-985c-736aea711387', '27eb9c6b-5fdf-4b28-95c4-79ade5e0a94e');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('c16b49b8-a9ab-4532-985c-736aea711387', '61cafa3c-2378-4074-8672-1c844f701f19');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('1a5fdf39-ee99-47d3-b67f-70f4fdcedd86', 'ee3f008e-7b92-41fc-a20d-324b822fe001');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('1a5fdf39-ee99-47d3-b67f-70f4fdcedd86', '63ef050f-66a9-4649-add1-fe8ef27cf107');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('1a5fdf39-ee99-47d3-b67f-70f4fdcedd86', '27eb9c6b-5fdf-4b28-95c4-79ade5e0a94e');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('1a5fdf39-ee99-47d3-b67f-70f4fdcedd86', '61cafa3c-2378-4074-8672-1c844f701f19');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('f8873fe1-9419-403b-a040-5193435ca6e6', 'ee3f008e-7b92-41fc-a20d-324b822fe001');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('f8873fe1-9419-403b-a040-5193435ca6e6', '63ef050f-66a9-4649-add1-fe8ef27cf107');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('f8873fe1-9419-403b-a040-5193435ca6e6', '27eb9c6b-5fdf-4b28-95c4-79ade5e0a94e');
INSERT INTO public.base_ex_to_position_base_sets (base_ex_to_position_id, base_set_id) VALUES ('f8873fe1-9419-403b-a040-5193435ca6e6', '61cafa3c-2378-4074-8672-1c844f701f19');

INSERT INTO public.base_trainings (id, training_level_id, description, name) VALUES ('32c223ea-0918-42a3-af3e-d28c55c2dc4f', 'd6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae3', 'description', 'Сет з 3 вправ');

INSERT INTO public.base_training_base_ex_to_position (base_ex_to_position, base_training_id) VALUES ('c16b49b8-a9ab-4532-985c-736aea711387', '32c223ea-0918-42a3-af3e-d28c55c2dc4f');
INSERT INTO public.base_training_base_ex_to_position (base_ex_to_position, base_training_id) VALUES ('1a5fdf39-ee99-47d3-b67f-70f4fdcedd86', '32c223ea-0918-42a3-af3e-d28c55c2dc4f');
INSERT INTO public.base_training_base_ex_to_position (base_ex_to_position, base_training_id) VALUES ('f8873fe1-9419-403b-a040-5193435ca6e6', '32c223ea-0918-42a3-af3e-d28c55c2dc4f');

