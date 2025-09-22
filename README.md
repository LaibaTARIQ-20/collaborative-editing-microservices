# Collaborative Editing System - Microservices Architecture

A distributed microservices-based collaborative editing platform built with Spring Boot, similar to Google Docs, enabling multiple users to edit documents simultaneously with real-time version tracking.

## System Architecture

This system implements a complete microservices architecture with API Gateway (Port 8084), Service Discovery (Port 8761), Database Integration using MySQL with JPA/Hibernate, and Comprehensive Testing with JUnit and Mockito.

## Microservices

### 1. User Management Service (Port 9091)
Handles user authentication, registration, and profile management.

**Endpoints:**
- POST /api/auth/login - User authentication
- POST /api/user/register - User registration
- GET /api/user/profile/{id} - User profile management
- GET /api/user/list - List all users

### 2. Document Editing Service (Port 9191)
Manages document creation, collaborative editing, and real-time change tracking.

**Endpoints:**
- POST /document/create - Create new document
- POST /document/open-file - Open document for editing
- POST /document/revert-version - Revert document changes
- GET /document/documents - List all documents

### 3. Version Control Service (Port 8082)
Maintains version history, enables reverting to previous versions, and tracks user contributions.

**Endpoints:**
- POST /version/save - Save document version
- GET /version/document/{id}/history - Get version history
- POST /version/document/{id}/revert - Revert to previous version
- GET /version/user/{id}/contributions - Track user contributions

## Prerequisites

- Java 17+
- MySQL 8.0+
- Maven 3.6+

## Running the System

Getting Started
Start the Services in Order

Eureka Discovery Service

cd eureka_server
mvn spring-boot:run


User Service

cd user_microservice_module
mvn spring-boot:run


Document Service

cd document_microservice
mvn spring-boot:run


Version Control Service

cd tracking
mvn spring-boot:run


API Gateway

cd api_gateway_microservice
mvn spring-boot:run

🌐 Access Points

Eureka Dashboard: http://localhost:8761

API Gateway: http://localhost:8084

Demo Interface: http://localhost:8084/api-demo.html

🧪 Testing

Run tests for each microservice:

User Service

cd user_microservice_module
mvn test


Document Service

cd document_microservice
mvn test


Version Control Service

cd tracking
mvn test

🛠️ Technology Stack

Framework: Spring Boot 3.3.4

Service Discovery: Netflix Eureka

API Gateway: Spring Cloud Gateway

Database: MySQL with JPA/Hibernate

Testing: JUnit 5, Mockito

Build Tool: Maven

🔀 API Gateway Routes

/api/** → User Management Service

/document/** → Document Editing Service

/document/** → Version Control Service (for version endpoints)

✨ Features

Real-time document editing

Version history tracking

User contribution monitoring

Document change reversion

Multi-user collaboration support

Microservices architecture patterns

Service discovery and load balancing

Database integration with Spring Data JPA

RESTful API design

Automated testing strategies

📊 System Architecture
graph TD
    A[Client Browser] -->|HTTP Request| B[API Gateway]
    B --> C[Eureka Server]
    B --> D[User Service]
    B --> E[Document Service]
    B --> F[Version Control Service]
    D -->|JPA/Hibernate| G[(MySQL Database)]
    E -->|JPA/Hibernate| G
    F -->|JPA/Hibernate| G

🔄 Service Flow
sequenceDiagram
    participant Client
    participant Gateway
    participant Eureka
    participant UserService
    participant DocService
    participant VersionService
    participant Database

    Client->>Gateway: Request (e.g., /document/save)
    Gateway->>Eureka: Service Lookup
    Eureka-->>Gateway: Service Instance Info
    Gateway->>DocService: Forward Request
    DocService->>Database: Save Document
    DocService-->>Gateway: Response
    Gateway-->>Client: Response Delivered
RESTful API design
Automated testing strategies
