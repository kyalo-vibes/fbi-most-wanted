# 🚀 FBI Most Wanted - Containerized Web Application

## **📌 Overview**
This project is a full-stack web application that displays the **FBI Most Wanted List**, featuring:
- **Backend**: Spring Boot (Java 21) with PostgreSQL and Redis.
- **Frontend**: React (Vite) served via Nginx.
- **Database**: PostgreSQL for authentication and persistence.
- **Caching**: Redis for optimized API responses.
- **Containerized**: Managed using Docker & Docker Compose.

---

## **📂 Project Structure**
```bash
fbi-most-wanted/
│── backend/             # Spring Boot API
│   ├── src/             # Backend source code
│   ├── Dockerfile       # Backend container setup
│   ├── README.md        # Backend-specific documentation
│── frontend/            # React (Vite) Frontend
│   ├── src/             # Frontend source code
│   ├── Dockerfile       # Frontend container setup
│   ├── README.md        # Frontend-specific documentation
│── docker-compose.yml   # Defines the services
│── README.md            # Main documentation (this file)
```

## **🚀 How to Run the Application**
Ensure **Docker** and **Docker Compose** are installed, then run on root of the project:
```sh
docker-compose up --build -d
```
This will:
✅ Start **PostgreSQL** with `auth_db`.
✅ Start **Redis** for caching.
✅ Build and launch **Spring Boot backend**.
✅ Build and launch **React frontend**.

Once running, access the web app on:
- 📌 **Web Application*  → [http://localhost:5173](http://localhost:5173)

To check running containers:
```sh
docker ps
```
To stop the application:
```sh
docker-compose down
```

---

## **📜 Documentation Links**
- 📦 **Backend Documentation** → [backend/README.md](./backend/README.md)
- 🎨 **Frontend Documentation** → [frontend/README.md](./frontend/README.md)

---

## **🛠 Environment Variables**
Ensure the following **environment variables** are set in `application.properties` or `.env`:
```properties
# PostgreSQL Database
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/auth_db
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=secret

# Redis Cache
SPRING_REDIS_HOST=redis
SPRING_REDIS_PORT=6379
```
## **🛠 Environment Variables**
Ensure the following **environment variables** are set in `application.properties` or `.env`:
```properties
# PostgreSQL Database
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/auth_db
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=secret

# Redis Cache
SPRING_REDIS_HOST=redis
SPRING_REDIS_PORT=6379
```

---