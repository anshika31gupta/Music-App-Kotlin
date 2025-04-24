
# 🎵 Music App using Deezer API

This is a simple Music App built with **Kotlin** for Android that uses the **Deezer Music API** (via RapidAPI) to search and display songs using a query. The app fetches music data from the internet and shows it in a clean list using **RecyclerView**.

---

## ✨ Features

- 📄 Display artist name, song title, and album cover
- 🔄 Fetch real-time data from Deezer API using Retrofit
- 🖼️ Load album images with Picasso
- ⚡ Smooth and fast UI using RecyclerView

---

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: XML Layouts + RecyclerView
- **Networking**: Retrofit
- **Image Loading**: Picasso
- **API**: Deezer API (via RapidAPI)

---

## 📡 API Used

Data is fetched from the [Deezer API](https://rapidapi.com/deezerdevs/api/deezer-1) using RapidAPI.

### ✅ Required Headers

```kotlin
@Headers(
    "x-rapidapi-key: fa1e44d6b6msh4c269a7e4e3d775p135463jsn45dde894a147",
    "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com"
)
