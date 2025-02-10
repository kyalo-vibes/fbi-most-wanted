# 🚀 FBI Most Wanted Backend - Spring Boot API

**Version:** 1.0.0  
**Author:** [Your Name]  
**Tech Stack:** Spring Boot 3.x, Redis, REST API, Maven, JUnit, Mockito, WireMock  

## 📌 Overview
This backend API **fetches, processes, caches, and serves** FBI Most Wanted data, enabling **pagination, search, filtering, and high-performance caching**.

### ✨ Features:
- **🔍 Search & Filtering** → Query by `name`, `race`, `nationality`, and `sex`
- **📄 Pagination** → Efficient data retrieval using backend pagination
- **⚡ Performance Optimized** → Uses **Redis Caching** for faster API responses
- **✅ Unit & Integration Tests** → Ensures system stability
- **🔧 Structured API Design** → Follows RESTful best practices

---

## 🛠️ **Setup Instructions**

### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/your-repo/fbi-most-wanted-backend.git
cd fbi-most-wanted-backend
```

### 2️⃣ **Install Dependencies**
```sh
mvn clean install
```

### 3️⃣ **Configure & Start Redis**
#### **Option 1: Run Redis in Docker** (Recommended)
```sh
docker run --name redis -p 6379:6379 -d redis
```
#### **Option 2: Install & Start Redis Locally**
- **Mac/Linux:**
  ```sh
  brew install redis && redis-server
  ```
- **Windows:** Install [Redis for Windows](https://github.com/microsoftarchive/redis/releases) and run:
  ```sh
  redis-server
  ```

### 4️⃣ **Configure Application Properties**
Modify `src/main/resources/application.properties` if needed.

```properties
server.port=8080

# Redis Configuration
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.time-to-live=60000  # Cache expiration in milliseconds

# Logging
logging.level.org.springframework=INFO
```

### 5️⃣ **Run the Application**
```sh
mvn spring-boot:run
```
**Base URL:** `http://localhost:8080/api/v1.0.0/wanted`

---

## 📖 **API Documentation**

### **1️⃣ Fetch All Wanted Persons**
**Endpoint:** `GET /api/v1.0.0/wanted`  
**Query Parameters:**

| Parameter     | Type   | Required | Default | Description            |
|--------------|--------|----------|---------|------------------------|
| `page`       | int    | No       | 1       | Pagination page number |
| `name`       | String | No       | N/A     | Search by name        |
| `race`       | String | No       | N/A     | Filter by race        |
| `nationality`| String | No       | N/A     | Filter by nationality |
| `sex`        | String | No       | N/A     | Filter by sex         |

#### **Example Requests**
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

#### **Example JSON Response**
```json
{
  "currentPage": 1,
  "totalPages": 10,
  "totalResults": 100,
  "data": [
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
}
```

---

## 💾 **Caching Strategy**
### **Why Redis?**
- **🚀 Faster API responses** by reducing external API calls.
- **🔄 Stores previous requests** for instant retrieval.

### **Checking Redis Cache**
```sh
redis-cli KEYS "wantedList*"
```
Example Output:
```
wantedList::page_1
wantedList::page_1_name_Livshits
wantedList::page_1_nationality_Russian
```

### **Clearing Redis Cache**
```sh
redis-cli FLUSHALL
```

---

## 🧪 **Testing Strategy**
The project includes **unit tests, integration tests, and Redis cache tests**.

### **1️⃣ Run All Tests**
```sh
mvn test
```

### **2️⃣ Test Coverage**
| **Test Type**         | **Tools Used**       | **Purpose**                                 |
|-----------------------|---------------------|---------------------------------------------|
| **Unit Tests**        | JUnit 5, Mockito    | Test individual methods in `FbiService`    |
| **Integration Tests** | MockMvc, WireMock   | Verify API response & caching logic        |
| **Redis Cache Tests** | Embedded Redis      | Validate caching & retrieval performance   |

---

## 🎯 **Project Structure**
```
backend/
│── src/
│   ├── main/
│   │   ├── java/com/example/fbiwanted/  # Base package
│   │   │   ├── controller/             # API controllers
│   │   │   ├── service/                # Business logic
│   │   │   ├── repository/             # Database interactions
│   │   │   ├── model/                  # Data models (DTOs & Entities)
│   │   │   ├── config/                 # Configuration files (CORS, Security, Redis)
│   │   │   ├── exception/              # Custom exception handling
│   │   │   ├── util/                   # Utility/helper classes
│   │   │   ├── security/               # JWT Auth (if implemented)
│   │   │   ├── FbiMostWantedApplication.java  # Main Spring Boot class
│   ├── test/                           # Unit & Integration Tests
│   │   ├── java/com/example/fbiwanted/
│   │   │   ├── controller/              # API tests
│   │   │   ├── service/                 # Service layer tests
│   │   │   ├── config/                  # Redis tests
│── config/
│   ├── application.properties          # Spring Boot properties
│── scripts/
│   ├── docker-compose.yml              # Containerization setup (if implemented)
│── pom.xml                              # Maven dependencies
│── README.md                            # Backend documentation
```