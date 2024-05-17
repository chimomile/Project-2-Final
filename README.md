# Restaurant POS System using Java

Welcome to our Restaurant POS System project! This application is developed using Java and is designed to manage restaurant operations efficiently. The project includes functionalities such as user authentication, transaction management, database interactions, and administrative controls.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Files Description](#files-description)
- [Contributors](#contributors)
- [License](#license)

## Project Overview

This Restaurant POS System is created by undergraduate Technology Informatics students to provide a robust and scalable solution for managing a restaurant's point-of-sale operations. The application includes multiple Java classes, each handling specific functionalities to ensure a clean and maintainable codebase.

## Features

- User authentication (login and register)
- Transaction management and database interaction
- Menu management with CRUD operations
- Admin panel for employee and menu management
- Printable transaction receipts

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/chimomile/Project-2-Final.git

   ```

2. **Navigate to the project directory:**
   Navigate to the project directory
   cd Project-2-Final

3. **Compile the Java files:**
   javac -d bin src/\*.java

4. **Run the Application:**
   java -cp bin Dashboard

## Usage

- Login: Access the system using your credentials.
- Register: Create a new account if you don't have one.
- Transactions: Manage transactions and send them to the database.
- Admin Panel: Admin users can manage employee data and menu items, including adding, updating, and deleting records.

## Files-description

1. Dashboard.java: Contains the main context of the project, handling the primary user interface and navigation.
2. Login.java: Implements the logic for user authentication.
3. Register.java: Handles the user registration process.
4. TransactionDB.java: Manages transactions and communicates with the existing database.
5. DatabaseConnection.java: Manages database connections and includes methods for pushing and retrieving data.
6. Menu.java: Declares menu items and maintains their scalability and structure.
7. TextPrintable.java: Contains the logic for printing receipts and is integrated with the dashboard.
8. UpdateBtn.java: Provides CRUD operations for managing employee data in the admin panel.
9. UpdateMenu.java: Handles CRUD operations for menu management in the admin panel.

## Contributors

@KndaBimaa17
@chimomile
We are undergraduate Technology Informatics students passionate about developing practical software solutions.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
