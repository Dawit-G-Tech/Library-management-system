# Library Management System (Java GUI)

## Overview

This is a **Library Management System** built entirely using Java with a graphical user interface (GUI). The system allows users to manage books, members, and transactions (issue/return) efficiently. It is suitable for small to medium-sized libraries and educational institutions.

---

## Features

- **Book Management**
  - Add, update, delete, and search books
  - View all available books
- **Member Management**
  - Register new members
  - Update or delete member information
  - Search for members
- **Issue & Return**
  - Issue books to members
  - Return books and update availability
  - View issued/returned book history
- **User Interface**
  - Intuitive Java Swing GUI
  - Menu-driven navigation
  - Dialogs for confirmations and alerts
- **Data Persistence**
  - MYSQL
- **Validation & Error Handling**
  - Input validation for all forms
  - User-friendly error messages

---

## Technologies Used

- **Java SE** (JDK 8 or above)
- **Java Swing** (for GUI)
- **MYSQL** (for database)
- **File I/O / Serialization** 
- **Object-Oriented Programming Principles**

---

## Getting Started

### Prerequisites

- Java JDK 8 or above installed
- (Optional) Any Java IDE (e.g., Eclipse, IntelliJ IDEA, NetBeans, VS Code)

### How to Run

1. **Clone or Download the Repository**
   - Download the ZIP and extract, or use:
     ```
     git clone https://github.com/Dawit-G-Tech/Library-management-system.git
     ```

2. **Compile the Source Code**
   - Open a terminal/command prompt in the project directory.
   - Compile all `.java` files:
     ```
     javac src\*.java
     ```

3. **Run the Application**
   - Run the main class (`Home.java`):
     ```
     java -cp src Home
     ```

---

## Project Structure

```
lManagment/
│
├── icons
|
├── src/
    |──lManagment/
       ├── Book.java
       ├── Connect.java
       ├── Defaulters.java
       ├── Home.java
       ├── Login.java
       ├── Register.java
       ├── User.java
       └── ... (other classes)

│
├── README.md
└── ...
```

---

## Screenshots


<img width="600" height="500" alt="Pages" src="https://github.com/user-attachments/assets/a2146a1f-75cc-4a07-b26b-cbcb475c5034" />

<img width="368" height="328" alt="Pages" src="https://github.com/user-attachments/assets/537231a9-b9a3-42f6-89e1-1d19f5586c78" />



---

## Usage

1. **Launch the application.**
2. **Navigate** using the menu to manage books, members, or transactions.
3. **Add/Edit/Delete** records as needed.
4. **Issue or Return** books using the respective forms.
5. **Search** for books or members using the search functionality.

---

## Customization

- To change data storage (e.g., switch from file to database), modify the data access classes.
- To update the look and feel, customize the Swing components in the GUI classes.

---

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## License

This project is licensed under the MIT License.

---

## Author

- [Dawit-G-Tech]

---
