# Progetto Basi di Dati

Project for the Basi di Dati course with Professor Pellegrini at the University of Rome Tor Vergata.

## Project Description

The aim of this project is to develop an application for personal trainers in a gym to manage their clients' workout plans. Each gym user is assigned a personal trainer who can create personalized workout schedules, selecting exercises and equipment from a predefined set provided by the gym owner. For each exercise, the trainer specifies the number of sets and repetitions.

When a new workout plan is created, the previous one is archived. Users can still view archived plans, but they can only interact with the current one. Users can access the application to see which exercises they need to perform during a workout session. The system displays the current exercise and set, allowing users to mark an exercise as completed or skip it and move to the next one.

Personal trainers can generate reports showing the number of workouts completed by their clients within a specified time frame, the percentage of workout plans completed in each session, and the duration of each workout.

## Database Description

The database is designed to support the following functionalities:

- **User and Trainer Management**: Each gym user is associated with a personal trainer.
- **Workout Plan Management**: Trainers can create and update workout plans, specifying exercises, sets, and repetitions.
- **Exercise Tracking**: Users can mark exercises as completed or skip them.
- **Reporting**: Trainers can generate reports on their clients' workout activities.

## Project Focus

The focus of the project was on constructing the database through various stages, including:

1. **Requirements Analysis**: Understanding and documenting the requirements for the application.
2. **Conceptual Design**: Creating a high-level model of the database using Entity-Relationship diagrams.
3. **Logical Design**: Translating the conceptual model into a logical schema using relational models.
4. **Physical Design**: Implementing the logical schema into a physical database using SQL.

The database is utilized through an application developed in Java with a Command Line Interface (CLI). The application allows personal trainers and gym users to interact with the database to manage workout plans and track progress.

## Repository Structure

- **`Documentazione`**:
    - **`Relazione_Azzarito.pdf`**: Contains all the documentation related to the database creation process, covering the various phases of requirements analysis, conceptual, logical, and physical design.
    -  **`Relazione_Azzarito.pdf`**: Contains the script to build the database schema and populate it.
    -  **`schemaWorkbench.mwb`**: This MySQL Workbench file is used to visualize and potentially modify the database more effectively.
- **`PersonalTrainerDigitale`**: Contains the code for the Java application.

For more details, please refer to the file `Documentazione/Relazione_Azzarito.pdf`.

