# Valentine’s Garage Management App

A mobile garage management application built using Kotlin, Jetpack Compose, and Room Database for Android.

## Overview

Valentine’s Garage is a garage management system designed to help mechanics and administrators manage vehicle repairs, repair tasks, and employee progress inside a garage environment.

The application supports:

* User authentication
* Role-based access
* Truck check-in management
* Repair task tracking
* Admin reporting
* Repair progress monitoring

The app was developed as an educational software development project.

---

# Features

## Authentication System

* User signup
* User login
* Role selection (Admin or Mechanic)

## Mechanic Features

* Check in new vehicles
* View trucks in the garage
* Add repair tasks
* Update task completion
* Track assigned work

## Admin Features

* View all trucks
* Monitor repair progress
* Track completed and pending tasks
* View repair notes
* Monitor garage activity

---

# Technologies Used

* Kotlin
* Jetpack Compose
* Room Database
* Android Studio
* MVVM Architecture
* Coroutines & Flow

---

# Database Structure

The application uses Room Database with the following entities:

## User

Stores:

* Full name
* Email
* Password
* User role

## Truck

Stores:

* Truck name
* Owner name
* Kilometers
* Vehicle condition
* Repair issue
* Check-in date

## Task

Stores:

* Task description
* Completion status
* Repair notes
* Truck relationship

---

# Application Architecture

The project follows the MVVM (Model-View-ViewModel) architecture:

* Entities
* DAOs
* Repositories
* ViewModels
* Compose UI Screens

This separation improves:

* Modularity
* Maintainability
* Scalability
* Code organization

---

# Admin Monitoring System

Admins are able to:

* View all garage trucks
* Monitor repair progress
* View task completion statistics
* Read mechanic repair notes
* Track garage operations

---

# Future Improvements

Possible future upgrades include:

* Firebase Authentication
* Cloud database synchronization
* Image uploads
* Push notifications
* Employee assignment system
* Search and filtering
* Analytics dashboard

---

# Developers

This application was developed by:

* Moses Filippus
* Nabot Albert
* Kiami Quinga
* Jason Tuhafeni

---

# Project Purpose

This project was created for educational purposes to demonstrate:

* Android application development
* Database integration
* CRUD operations
* Role-based systems
* Mobile UI design
* Software architecture principles

---
