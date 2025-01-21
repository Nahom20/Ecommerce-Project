eCommerce Backend Application

This eCommerce backend application is built using a microservices architecture and designed for scalability, resilience, and security. It integrates key technologies such as Spring Boot, Eureka Server, and API Gateway to provide a modular, easily extendable system for handling inventory, orders, and product management. Key features include:

Inventory Service: Manages stock levels, product availability, and real-time inventory updates.
Order Service: Processes and tracks customer orders, ensuring accurate and timely delivery.
Product Service: Handles product listings, descriptions, and categorization for a seamless shopping experience.
Eureka Server: Acts as a service registry, enabling dynamic service discovery within the microservices architecture.
API Gateway: Routes requests from the client-side, ensuring centralized access control and load balancing across microservices.
Resilience4j & Circuit Breaker Pattern: Implements fault tolerance and ensures high availability, even in case of service failure, improving system reliability.
OAuth2 & Keycloak: Provides secure and standardized user authentication and authorization with token-based security.
Databases: Uses SQL (for structured data) and MongoDB (for flexible and scalable data storage), providing optimal storage solutions for different application needs.
WebClient: Allows seamless communication between microservices using RESTful APIs, enabling efficient inter-service data exchange.
Deployed via Docker, this application is containerized for consistent, cross-platform deployment, ensuring smooth scaling and efficient resource management. The architecture ensures high performance and fault tolerance, offering a robust solution for building modern, secure, and scalable eCommerce platforms.
