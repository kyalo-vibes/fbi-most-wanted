# 🚀 FBI Most Wanted Backend - Spring Boot API

**Version:** 1.0.0  
**Author:** [Your Name]  
**Tech Stack:** Spring Boot 3.x, Redis, REST API, Maven, JUnit  

## 📌 Overview
This Spring Boot API fetches and caches data from the **FBI Most Wanted API**, supporting:
- **Pagination** for efficient data retrieval.
- **Search & Filtering** by `name`, `race`, `nationality`, and `sex`.
- **Redis Caching** for performance optimization.
- **Unit Testing** for stability.

---

## 🛠️ Setup Instructions

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/your-repo/fbi-most-wanted-backend.git
cd fbi-most-wanted-backend
```

### 2️⃣ Install Dependencies
```sh
mvn clean install
```

### 3️⃣ Configure Redis (Required)
**Option 1: Run Redis using Docker**
```sh
docker run --name redis -p 6379:6379 -d redis
```
**Option 2: Install & Start Redis Manually**
- **Mac/Linux:** `brew install redis && redis-server`
- **Windows:** Use [Redis for Windows](https://github.com/microsoftarchive/redis/releases)

### 4️⃣ Configure Application Properties
Modify `src/main/resources/application.properties` if needed.

```properties
server.port=8080

# Redis Configuration
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
```

### 5️⃣ Run the Application
```sh
mvn spring-boot:run
```
**Base URL:** `http://localhost:8080/api/v1.0.0/wanted`

---

## 📖 API Documentation

### **1️⃣ Fetch All Wanted Persons**
**Endpoint:** `GET /api/wanted`  
**Query Parameters:**

| Parameter     | Type   | Required | Default | Description            |
|--------------|--------|----------|---------|------------------------|
| `page`       | int    | No       | 1       | Pagination page number |
| `name`       | String | No       | N/A     | Search by name        |
| `race`       | String | No       | N/A     | Filter by race        |
| `nationality`| String | No       | N/A     | Filter by nationality |
| `sex`        | String | No       | N/A     | Filter by sex         |

#### Example Requests
Fetch page 1:
```
GET http://localhost:8080/api/v1.0.0/wanted?page=1
```
Search by name:
```
GET http://localhost:8080/api/v1.0.0/wanted?page=1&name=Livshits
```
Filter by nationality:
```
GET http://localhost:8080/api/v1.0.0/wanted?page=1&nationality=Russian
```

#### Example JSON Response
```json
[
  {
    "uid": "b953c05b852a4b719c0a152ef8a73ffd",
    "title": "BORIS YAKOVLEVICH LIVSHITS",
    "description": "Conspiracy to Defraud the United States...",
    "imageUrl": "https://www.fbi.gov/wanted/counterintelligence/livshits.jpg",
    "sex": "Male",
    "race": "White",
    "nationality": "Russian",
    "aliases": ["Boris Levitan", "David Wetzky"],
    "fieldOffices": ["New York"],
    "status": "na",
    "url": "https://www.fbi.gov/wanted/counterintelligence/boris-livshits"
  }
]
```

---

## 💾 Caching Strategy
**Why Redis?**
- **Speeds up API responses** by avoiding frequent external calls.
- **Stores previously requested pages and filters** for instant retrieval.

**Check Redis Cache Keys**
```sh
redis-cli KEYS "wantedList*"
```

**Example Cached Keys**
```
wantedList::page_1
wantedList::page_1_name_Livshits
wantedList::page_1_nationality_Russian
```

**Clear Cache**
```sh
redis-cli FLUSHALL
```

---


---
```
