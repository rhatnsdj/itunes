# 🎵 iTunes Music App

An Android app that fetches music data from the **iTunes API**, displays track lists, allows users to filter tracks by type, and manage favorites using **Room Database**.  

## 📌 Features
- Fetches track data from **iTunes API** using `Retrofit`
- Displays **track list with album, artist, and artwork**
- Allows filtering by **song, album, or music video**
- **Favorites management** using `Room Database`
- **Bottom Navigation** to switch between the track list and favorites
- Uses **MVVM Architecture** for better separation of concerns
- Implements **Data Binding** for efficient UI updates

## 🚀 Tech Stack
- **Language:** Kotlin
- **Framework:** Android (Jetpack Components)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Libraries Used:**
  - [Retrofit](https://square.github.io/retrofit/) - API requests
  - [Glide](https://github.com/bumptech/glide) - Image loading
  - [Room Database](https://developer.android.com/training/data-storage/room) - Local storage
  - Data Binding - UI optimization

## Project Structure
📂 app/src/main/
├── 📂 data
│   ├── model/        # Data Models (Track, FavoriteTrack)
│   ├── api/          # Retrofit API service
│   ├── db/           # Room Database setup
│
├── 📂 ui
│   ├── adapter/      # RecyclerView Adapters
│   ├── viewmodel/    # MVVM ViewModels
│   ├── fragment/     # TrackList & Favorite Fragment
│
├── 📂 res
│   ├── layout/       # XML layouts
│   ├── drawable/     # Icons & images
│   ├── values/       # Strings & themes
│
└── MainActivity.kt   # Handles Bottom Navigation

