# Restaurant Management System 🍽️
A Java assignment to create a restaurant management system in team.

A simple, console-based Point of Sale (POS) system built in Java. We built this to practice core Object-Oriented Programming (OOP) concepts like interfaces, abstract classes, and dynamic arrays across multiple packages.

## 🎯 Key OOP Concepts Demonstrated
During our presentation, we focused on implementing these core concepts:
* **Interfaces (`implements`):** The `Billable` interface enforces strict rules for how any order must calculate totals and print receipts.
* **Abstract Classes (`abstract`):** The `Person` base class defines the blueprint for any human entity in the system, preventing raw "Person" objects from being instantiated.
* **Inheritance (`extends`):** The `Customer` class inherits from `Person`, expanding it with order history and mobile tracking.
* **Encapsulation & Modularity:** Code is divided into strict domain packages (`catalog`, `people`, `billing`, `app`) to separate data models from business logic.

## 🏗️ System Architecture 
Our system is divided into four distinct modules:

```text
📦 src
 ┣ 📂 app
 ┃  ┗ 📜 main.java - (The execution loop and terminal UI router)
 ┣ 📂 billing
 ┃  ┣ 📜 Billable.java - (Interface contract)
 ┃  ┗ 📜 RestaurantOrder.java - (Calculates totals linking Menu and Customer)
 ┣ 📂 catalog
 ┃  ┣ 📜 Menu.java - (Manages the dynamic array of items)
 ┃  ┗ 📜 MenuItem.java - (Data container for food items)
 ┗ 📂 people
    ┣ 📜 Person.java - (Abstract base class)
    ┗ 📜 Customer.java - (Inherits from Person, manages order arrays)
```

## 🚀 How to Run (Terminal)

You will need the Java Development Kit (JDK) installed on your machine.

**1. Clone the repository**
```bash
git clone https://github.com/Divyansh-Choudhary/restaurant-management-program.git
cd restaurant-management-program
```

2. Compile the packages
Run this from the root folder. It compiles all the .java files and automatically builds the package structure in your directory.

```bash
javac -d . src/catalog/*.java src/people/*.java src/billing/*.java src/app/main.java
```

3. Start the application

```bash
java app.main
```


## 👥 The Team
Shoutout to the team that built this for Semester II OOP assignment:

* **[Divyansh Choudhary]** - System Architecture & Main UI Loop
* **[Kanchan G]** - Menu & Catalog Logic
* **[Aryaan Pathak]** - Customer Directory & History
* **[Dinu Arya & Divyansh]** - Billing Interfaces & Calculations
