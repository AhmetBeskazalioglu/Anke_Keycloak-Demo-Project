

# Keycloak Demo Project with Spring Boot

This project demonstrates how to integrate Keycloak with a Spring Boot application for authentication and authorization using JWT tokens. It includes a simple setup for JWT decoding and role-based access control.

## Features
- **JWT Authentication** using Keycloak
- **Role-based Access Control (RBAC)** with custom JWT converters
- **Stateless Authentication** (using Spring Security's stateless session management)

## Prerequisites

Before you start, make sure you have the following installed:

- **JDK 11** or later
- **Maven 3.6+**
- **Docker and Docker Compose**
- **Keycloak** (via Docker)

## Setup Instructions

### Step 1: Clone the Repository

```bash
git clone https://github.com/AhmetBeskazalioglu/Anke_Keycloak-Demo-Project.git
cd keycloak-demo-project
```

### Step 2: Configure Keycloak

1. Run Keycloak and PostgreSQL using Docker Compose. The necessary configuration is already provided in the `docker-compose.yml` file. You can start it with the following command:

   ```bash
   docker-compose up
   ```

2. Access the Keycloak admin console at `http://localhost:8080` with the default credentials:
   - **Username**: `admin`
   - **Password**: `admin`

3. In the admin console:
   - Create a new **Realm**.
   - Add a new **Client**. Use `Spring Boot` as the client name, set **Access Type** to `confidential`, and configure redirect URIs.
   - Add roles under the **Roles** tab and assign them to your users.

### Step 3: Configure Application Properties

Update your `application.yml` or `application.properties` file with the following configurations:

```yaml
jwt:
  auth:
    converter:
      resource-id: <your-client-id>
      principle-attribute: preferred_username
```

Make sure that `resource-id` matches the client ID created in Keycloak.

### Step 4: Run the Application

You can now run the Spring Boot application locally using Maven:

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`. You can access secured endpoints once you've authenticated through Keycloak.

### Step 5: Access Endpoints

- Once logged in, Keycloak will provide a JWT token.
- You can use this token to access the protected endpoints in the Spring Boot application.

## Keycloak Configuration

This project uses Keycloak for both authentication and authorization. The project is configured to use Keycloak's **OAuth2.0** and **OpenID Connect (OIDC)** protocols to validate JWT tokens.

To adjust Keycloak settings, edit the `JwtAuthConverter` and `KeyCloakConfiguration` classes to customize the JWT claim extraction process.

## Docker Compose Configuration

The `docker-compose.yml` file sets up both a PostgreSQL database and Keycloak server.

```yaml
version: '3.8'

services:
  postgres:
    container_name: kc_db
    image: postgres:16
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: keycloak
      POSTGRES_PASSWORD: 123456
    volumes:
      - kc-db:/var/lib/postgresql/data

  keycloak:
    container_name: kc_current
    image: quay.io/keycloak/keycloak:23.0.6
    command: start-dev
    environment:
      DB_VENDOR: postgres
      DB_ADDR: postgres
      DB_PORT: "5432"
      DB_DATABASE: keycloak
      DB_USER: keycloak
      DB_PASSWORD: 123456
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
    ports:
      - "8080:8080"
    depends_on:
      - postgres

volumes:
  kc-db:
```

## Contributing

Feel free to contribute to the project by submitting pull requests or reporting issues.

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, feel free to contact the repository maintainer:

- **GitHub**: https://github.com/AhmetBeskazalioglu
- **Email**: ahmetbeskazalioglu@gmail.com
