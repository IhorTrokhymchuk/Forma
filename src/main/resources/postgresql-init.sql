insert into role_types (id, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae0', 'USER');
insert into role_types (id, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae1', 'ADMIN');

insert into training_levels(id, display_name, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae3', 'Beginner', 'BEGINNER');
insert into training_levels(id, display_name, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae4', 'Intermediate', 'INTERMEDIATE');
insert into training_levels(id, display_name, name) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae5', 'Advanced', 'ADVANCED');
--
-- -- insert into users (id, email, ) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae2', 'Y0G6Y@example.com', '$2a$10$3u9v3y9dC1j8uKfCzqB6aeq8x3p5V3mK5Iu3n8vS1r3h5EYK0Yi', 'admin');
--
-- insert into users(id, email, first_name, last_name, ga_secret, password ) values ('b0f54a64-131f-4c5c-ad52-87d57efcab0c', 'i.trokhymchuk@1plus1.tv', 'ihor', 'trokhymchuk', 'QHH9I19fWQ8Bsgm7yvtu+VnoFTmYO+4B+qIArDpQLpa6TnE3hW8/aAG/F5xp7Wjq', '$2a$10$Mk.apnQoemwMH8VYKFBphesUSncrROcMc9e1wHgTfxVoXGfXT4dYK');
-- insert into users_role_types(role_type_id, user_id) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae0', 'b0f54a64-131f-4c5c-ad52-87d57efcab0c');
-- insert into users_role_types(role_type_id, user_id) values ('d6ccfc2b-e1cc-4ee9-9f76-794cce1c6ae1', 'b0f54a64-131f-4c5c-ad52-87d57efcab0c');
--

INSERT INTO role_types (id, name) VALUES
                                    (gen_random_uuid(), 'Грудні'),
                                    (gen_random_uuid(), 'Широка спина'),
                                      (gen_random_uuid(), 'Плечі'),
                                      (gen_random_uuid(), 'Біцепс'),
                                      (gen_random_uuid(), 'Трицепс'),
                                      (gen_random_uuid(), 'Прес'),
                                      (gen_random_uuid(), 'Сідниці'),
  (gen_random_uuid(), 'Задня поверхня стегна'),
  (gen_random_uuid(), 'Ікри');