INSERT INTO task (id, title, category, description)
VALUES  (1, 'Finish Spring Boot Tutorial', 'Work',
 'Complete the remaining sections of the Spring Boot guide.'),
        (2, 'Buy Groceries', 'Shopping',
 'Pick up essentials like milk, bread, and eggs.'),
        (3, 'Read Java Concurrency in Practice', 'Personal Development',
 'Read the first two chapters of the book.'),
        (4, 'Clean Workspace', 'Household',
 'Organize desk and clean clutter from workspace.'),
        (5, 'Team Meeting Preparation', 'Work',
 'Prepare agenda for the upcoming team meeting.'),
        (6, 'Yoga Practice', 'Fitness',
 'Complete a 30-minute morning yoga routine.'),
        (7, 'Plan Weekend Trip', 'Personal',
 'Research locations and activities for a weekend getaway.'),
        (8, 'Update LinkedIn Profile', 'Work',
 'Add recent projects and update professional summary.'),
        (9, 'Review Java Collections Framework', 'Study',
 'Revise the main Java collections, focusing on maps and sets.'),
        (10, 'Declutter Email Inbox', 'Personal',
 'Organize and delete unnecessary emails from inbox.');


INSERT INTO user_data (id, user_name, hashed_password, email, full_name, status)
VALUES (1, 'j_doe', '$2a$12$nwwPEsqsHXdAo7X8lvaKSeIdIbs75IFjsKJYdCcYT6xzn.Za60Kh6', 'jDoe@mail.com', 'John Doe', 'ACTIVATED'),
       (2, 'admin', '$2a$12$nwwPEsqsHXdAo7X8lvaKSeIdIbs75IFjsKJYdCcYT6xzn.Za60Kh6', 'admin@mail.com', 'ADMIN ADMIN', 'ACTIVATED');

INSERT INTO user_roles (user_id, role)
VALUES (1, 'USER'),
       (2, 'USER'),
       (2, 'ADMIN');

INSERT INTO activation_token (token_id, token, user_id)
VALUES (1, '13cff00b-35b1-4240-81b4-9d9e590cfe3a', 1),
       (2, 'e0901c93-caa6-4486-8ffa-a56809820bae', 2);
