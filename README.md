# Readers' Den - Android Book Selling App

**Readers' Den** is an Android application designed for book lovers who wish to buy books with ease. The app offers users a seamless browsing and purchasing experience, while providing administrators with a powerful backend to manage the book inventory, customer orders, and user interactions.

## Key Features

### User Features:
- **Browse Books**: Explore a wide range of books across multiple categories.
- **Detailed Book Information**: View book details including price, description, and image.
- **Add to Cart**: Add books to the shopping cart for easy checkout.
- **Secure Login**: Sign up and log in securely using **Firebase Authentication**.
- **Manage Profile**: Update user details and view order history.
- **Order Management**: Place orders and track their status.

### Admin Features:
- **Book Management**: Add, update, or delete books from the inventory.
- **Customer Management**: View and manage customer orders.
- **Admin Authentication**: Admins can securely log in to the system via Firebase Authentication.

## Technologies Used

- **Android Studio**: IDE used for developing the Android app and building the user interface.
- **Java**: Programming language used to develop the application.
- **Firebase Authentication**: Handles user registration, login, and authentication securely.
- **Firebase Firestore**: Real-time NoSQL database for storing and managing books, user profiles, and orders.
- **Firebase Cloud Messaging (FCM)**: Push notifications to notify users about order status and updates.
- **RecyclerView**: Used to display a scrollable list of books and categories in the app.
- **Firebase Realtime Database**: Stores and syncs cart data and customer order history in real-time.

## Installation Guide

To get started with the Readers' Den Android app, follow these steps:

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/Readers-Den.git
```
## 2. Set Up Firebase

To integrate Firebase into the project, follow these steps:

1. Go to Firebase Console.
2. Create a new Firebase project (or use an existing one).
3. Add your Android app to the Firebase project by registering your app’s package name.
4. Download the `google-services.json` file and place it in the `app/` directory of the project.
5. Enable Firebase Authentication, Firestore, and Firebase Cloud Messaging in the Firebase console.

## 3. Sync the Project

1. Open the project in Android Studio.
2. Let Android Studio sync with Firebase to set up the required dependencies.
3. Build and run the app on an emulator or a physical device.

## App Usage

### For Customers:
1. **Sign Up / Log In**: Open the app and use Google Authentication to sign in or create a new account.
2. **Browse Books**: View different book categories and explore available books.
3. **Add to Cart**: Select books to add to the shopping cart.
4. **Checkout**: Review your cart and proceed with payment to complete your purchase.

### For Admins:
1. **Admin Login**: Admins can log in using a separate admin panel with specific privileges.
2. **Manage Books**: Admins can add new books, update existing listings, and remove books.
3. **View Orders**: Admins can view all customer orders and update their statuses (e.g., shipped, completed).

## Running the App

1. Open the project in Android Studio.
2. Make sure your Android device or emulator is set up.
3. Run the app directly from Android Studio by clicking the **Run** button.

## Important Files and Structure

- **Authentication**: Handles user login and signup using Firebase Authentication.
- **Admin Panel**: Allows the admin to add, edit, and delete book listings and manage orders.
- **Cart & Payment**: The functionality for customers to manage their shopping cart and proceed with payment.
