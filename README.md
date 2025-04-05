# 📱 Android Task Manager App

A simple Android task management app using **Jetpack Compose**, **Room DB**, and **Firebase**.  
You can add, edit, complete, and delete tasks. It also tracks events using **Firebase Analytics**,  
reports crashes using **Firebase Crashlytics**, and monitors performance using **Firebase Performance Monitoring**.

---

## 🚀 Setup Instructions

### 1. Clone the project

```bash
git clone https://github.com/shashank9759/android_assignment_shashank.git
cd android_assignment_shashank
```
## 🚀 Getting Started

### 2. Open in Android Studio
- Open the project in **Android Studio**.
- Let **Gradle sync**.

### 3. Add Firebase
1. Go to the [Firebase Console](https://console.firebase.google.com).
2. Create a new project.
3. Add an Android app with the package name:
4. Download `google-services.json` and place it in the `app/` folder.
5. Ensure your `build.gradle` (project-level and app-level) include the required Firebase plugins.

### 4. Run the App
- Use a **real or virtual device**.
- Click ▶️ in Android Studio, or run:

```bash
./gradlew installDebug
```
-To see the firebase analytics and key events, navigate to the `AppData\Local\Android\Sdk\platform-tools` and  use the below commands:
```bash
 .\adb.exe shell setprop debug.firebase.analytics.app com.shashank.android_assignment_shashank
```
## 📦 Third-Party Libraries Used

| Library              | Purpose                          |
|----------------------|----------------------------------|
| Jetpack Compose      | Modern UI framework              |
| Room Database        | Local DB for tasks               |
| Hilt                 | Dependency injection             |
| Firebase Analytics   | Track task-related events        |
| Firebase Crashlytics | Crash monitoring                 |
| Firebase Performance | App/network performance tracking |



## 🧠 Explanations of Design Decisions

### 🏗️ MVVM Architecture

I used **MVVM (Model-View-ViewModel)** for a clear separation of concerns:

- **Model**: Contains data classes and Room database for persistence.
- **ViewModel**: Manages UI-related data and handles logic like task add/update/delete.
- **View (UI)**: Built using **Jetpack Compose**, observes ViewModel's state and displays it reactively.

This architecture makes the code:
- ✅ Modular
- ✅ Testable
- ✅ Easier to maintain

---

### 🔌 Dependency Injection with Hilt

I used **Hilt** to inject dependencies like the repository and database. This helps in:

- ✨ Reducing boilerplate code for dependency management.
- 🧪 Promoting testability by allowing mocking of dependencies.
- 🧼 Keeping the codebase cleaner and scalable.

### Screenshot of the events on Firebase analytics console:
![{A4D05C4C-3E32-493B-A788-191E9D3435AE}](https://github.com/user-attachments/assets/19fd1402-a4fd-4d9f-8547-718028dfd758)

###  Screenshots of the crashes on the Firebase console.
![{D734AFAA-5F01-4E25-A9AC-3142BCC383C3}](https://github.com/user-attachments/assets/1f6e164f-5204-4897-9689-368361e7645f)

## App Crash to test Firebase Crashlytics
![{E2A59F0F-ACAC-4CE1-BBDA-52CD468CE55F}](https://github.com/user-attachments/assets/78ac8e76-4bf6-4610-a01d-da5793b21e0b)

# DB Error Crash (Null Pointer Exception)
![{3D1A21DE-6A0A-4450-A46F-2357C9561E38}](https://github.com/user-attachments/assets/0019ed18-41ed-4f2d-857c-9d26d6b7ef6c)

## Mobile screen recording of the crash
https://github.com/user-attachments/assets/6fb5c565-5889-4911-9b2a-620da5a8e601


### Screenshots of the network performance.
![{21301D92-9325-4218-894E-4775B0327F13}](https://github.com/user-attachments/assets/f56a7656-9013-46cb-bd09-44db16622a59)
