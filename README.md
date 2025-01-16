# Ecommerce Web Application Backend Implementation

This repository contains the backend implementation for an **E-commerce Web Application** built using a microservices architecture. Each microservice is designed to handle specific business functionality, ensuring scalability, maintainability, and flexibility. The implementation includes Swagger OpenAPI documentation for each service to facilitate easy integration and testing.

---

## Microservices Overview

### **1. EcommCart**
- **Purpose**: Manages the shopping cart functionality for users.
- **Responsibilities**:
  - Add, update, and remove items in the shopping cart.
  - Maintain the state of the cart for authenticated users.
  - Calculate cart totals, including discounts and taxes.
  - Integrates with `EcommProduct` for product details and `EcommUser` for user-specific cart data.
- **Swagger OpenAPI**: Provides API documentation for all endpoints, enabling easier integration with front-end or third-party services.

---

### **2. EcommDiscoveryServer**
- **Purpose**: Acts as the service registry and discovery server.
- **Responsibilities**:
  - Enables dynamic service registration and discovery for all microservices.
  - Ensures seamless communication between microservices in the distributed architecture.
  - Uses Netflix Eureka or equivalent for service discovery.
  - Improves system fault tolerance and resilience by automatically rerouting requests in case of service failures.

---

### **3. EcommOrder**
- **Purpose**: Manages order creation, processing, and tracking.
- **Responsibilities**:
  - Handles the checkout process, including payment processing and order confirmation.
  - Stores and retrieves order details, including order history for users.
  - Communicates with `EcommCart` for cart finalization and `EcommProduct` for inventory validation.
  - Supports integration with external payment gateways.
- **Swagger OpenAPI**: Documents APIs for order placement, status updates, and retrieval.

---

### **4. EcommProduct**
- **Purpose**: Manages product catalog and inventory.
- **Responsibilities**:
  - Handles CRUD operations for product details (e.g., name, description, price, stock availability).
  - Provides APIs to search, filter, and sort products.
  - Manages inventory updates based on order processing from `EcommOrder`.
  - Ensures real-time stock updates to avoid over-ordering.
- **Swagger OpenAPI**: Provides endpoints for product management and catalog retrieval.

---

### **5. EcommUser**
- **Purpose**: Manages user authentication, authorization, and profiles.
- **Responsibilities**:
  - Handles user registration, login, and profile management.
  - Manages roles and permissions for users (e.g., admin vs. customer).
  - Provides secure APIs using JWT-based authentication.
  - Stores user preferences and integrates with `EcommCart` and `EcommOrder` for personalized experiences.
- **Swagger OpenAPI**: Documents endpoints for user management and authentication flows.

---

### **6. API Gateway**
- **Purpose**: Serves as the single entry point for all client requests.
- **Responsibilities**:
  - Routes requests to appropriate microservices (e.g., `EcommCart`, `EcommOrder`).
  - Enforces security protocols like authentication and authorization using JWT.
  - Implements rate limiting and request logging for better monitoring.
  - Provides load balancing to optimize service performance and scalability.
- **Swagger OpenAPI**: Offers a consolidated API interface for the entire system.

---

## How to Run the Project

1. **Set Up the Environment**:
   - Ensure you have Java, Spring Boot, and a compatible database (e.g., MySQL) installed.
   - Clone the repository and navigate to each microservice directory.

2. **Start the Microservices**:
   - Begin by running the `EcommDiscoveryServer` for service registration.
   - Start each microservice (`EcommCart`, `EcommOrder`, etc.) sequentially.
   - Finally, run the `api-gateway` service.

3. **Access Swagger Documentation**:
   - Visit the `/swagger-ui.html` endpoint for each service to explore the API documentation.

4. **Integration with Frontend**:
   - Use the APIs exposed via the `api-gateway` to connect with the front-end application.

---

## Future Enhancements
- Add monitoring using tools like Prometheus and Grafana.
- Implement advanced caching mechanisms for improved performance.
- Enhance security with role-based access control (RBAC) and OAuth2.0.

---

Feel free to contribute or suggest improvements by creating a pull request or opening an issue!
