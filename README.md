# Welcome to the Secure ERP project repository!

Secure ERP is an enterprise resource planning software where you can organise and view customers, Sales and HR people.

![main-menu](https://github.com/user-attachments/assets/9062cc57-999a-43e8-ae2a-7496e8ff26e4)

## Table of Contents
- [Overview](#overview)
- [Technologies](#technologies)
- [Getting Started](#getting-Started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Features](#features)
  - [App Functionalities](#app-functionalities)
  - [Dockerization](#dockerization)
- [Usage](#usage)

## Overview
Secure ERP is an enterprise resource planning software. The project is written in vanilla Java and the user interface runs in the terminal. Users can view customers, salespeople and human resource colleagues in a dashboard. They can add, update, delete colleagues. Various other functions are also available, depending on which colleagues are being modified. For example: average age calculation, sum of transactions within a date interval, list of subscribed colleagues' emails.

## Technologies
### Backend:
- [![java][java]][java-url]
- [![maven][maven]][maven-url]

### Containerization:
- [![docker][docker]][docker-url]

## Getting Started

### Prerequisites

#### To Run the Project with Docker:
  - [![docker][docker]][docker-url]

#### To Run Backend and Frontend Separately:
  - [![java][java]][java-url]
  - [![maven][maven]][maven-url]


### Installation

#### Running the Project with Docker
1. Clone the repository from GitHub into your desired folder:
   ```bash
    git clone git@github.com:CodecoolGlobal/secure-erp-java-Geonauta11.git

    # navigate into the project directory
    cd <foldername>
   ```


#### Running the application

   - Run the applicationy:
     ```bash
      javac <project-folder-name>/src/main/java/com/codecool/secureerp/App
     ```
## Features
  ### App Functionalities
  - Manage customers, salespeopel and HR people.
  - Features include:
    - List them in table view in the terminal.
    - Full CRUD operations.
    - Specific functions:
        - average age calculation
        - sum of transactions within a date interval
        - list of subscribed colleagues' emails
        - etc.

  ### Dockerization
  - The application is fully containerized using Docker.

## Usage
Using Secure ERP:
  - Main Menu:
    - Detail: Display main menu.
    ![main-menu](https://github.com/user-attachments/assets/3c340576-a1d8-4969-81cb-e55b8a184627)

  - CRM:
    - Detail: Display CRM options.
    ![crm](https://github.com/user-attachments/assets/ca1fbde1-5fbe-4c01-b3ae-3f218a82d5c0)

  - CRM Table:
    - Detail: Display customers in a table.
    ![list-customers](https://github.com/user-attachments/assets/3f5e282a-6f68-4a79-a66a-978f5b5dd6ee)

  - Sales:
    - Detail: Display Sales options.
    ![sales-operations](https://github.com/user-attachments/assets/eceac4df-8084-4129-889b-920a164bbaec)

  - Sales Table:
    - Detail: Display transactions in a table.
    ![list-transactions](https://github.com/user-attachments/assets/b5c3d998-a499-41bd-93cd-e323110b28e4)

  - Human Resource:
    - Detail: Display HR options.
    ![hr](https://github.com/user-attachments/assets/f584a21f-529c-4527-98bd-9d4bfa72eba2)

  - Human Resource Table:
    - Detail: Display HR employees in a table.
    ![hr-employees](https://github.com/user-attachments/assets/2b222444-beee-42a3-885a-b5427396de73)

[docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
[docker-url]: https://docs.docker.com/engine/install/

[java]: https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&labelColor=ED8B00&logo=java&color=808080[Java
[java-url]: https://www.java.com/en/download/

[maven]: https://img.shields.io/badge/Maven-4%2B-ED8B00?style=for-the-badge&labelColor=ED8B00&logo=maven&color=808080[Maven
[maven-url]: https://maven.apache.org/
