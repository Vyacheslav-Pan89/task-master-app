# Task Master App

Task Master App is a task management application currently in development, built with Java 17, Spring Boot, Spring Data JPA, Thymeleaf, and H2 Database.

## Features

- User Authentication with Registration Validation: Securely register new users with validation checks for username uniqueness, strong password requirements, and email verification via activation link.
- Email Activation: Users must activate their account via email confirmation to ensure account authenticity and security.
- Task Management (Currently in Development): Basic functionality to manage tasks including creation, viewing, updating, and deleting tasks is under active development.
- Logout (Currently in Development): Implementing a secure logout feature to end user sessions is currently in progress.

## Installation

1. **Clone the repository:**

git clone https://github.com/Vyacheslav-Pan89/task-master-app.git
cd task-master-app

2. **Run the application:**

mvn spring-boot

3. **Access the application:**

Open a web browser and navigate to `http://localhost:8080`

## Usage

1. **Register:**
- Sign up with a new username, password, and valid email address.
- Receive an email with an activation link to verify your account.
- Ensure your registration details meet the validation criteria to successfully register.

2. **Login:**
- Use your registered credentials to log into the application after completing the activation process.

3. **Managing Tasks:**
- (Currently in Development) - Work in progress to allow users to perform CRUD operations on tasks.
- Watch for updates as we add more functionality to improve task management capabilities.

4. **Logout:**
- (Currently in Development) - Implementing a secure logout feature to end user sessions gracefully and securely.

## Future Plans

- Implement User Roles: Introduce roles such as admin and user to control access levels and permissions within the application.
- Enhance Security with Spring Security: Strengthen application security with Spring Security to manage authentication, authorization, and secure endpoints.
- Migrate to PostgreSQL: Transition from H2 Database to PostgreSQL for improved scalability and performance.
- Task Categorization and Filtering: Enable users to categorize tasks and apply filters to better organize and manage their tasks.
- User Profiles: Introduce customizable user profiles to enhance personalization and user experience.
- UI/UX Enhancements: Continuously improve the user interface and experience to ensure smooth and intuitive interaction.

## Development Status

Task Master App is currently in active development, focusing on enhancing existing features and implementing planned functionalities. Your feedback and contributions are highly appreciated to make this application even better!

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.


