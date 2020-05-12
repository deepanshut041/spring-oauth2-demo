# Spring Boot OAuth2 Social Login

This project on how to implement OAuth2 with JWT tokens using Spring Boot 2 and Angular. Both Kotlin and Java version are available with NoSQL and SQL Database respectively. 

## Development Environment

### Setting up the Backend Server

- Configure database(backend-java)
    ```yaml
    ```
- Configure database(backend-kotlin)
    ```yaml
    ```
- Specify OAuth2 Provider
    ```yaml
    security:
    oauth2:
        client:
        registration:
            google:
            clientId: <GOOGLE_CLIENT_ID>
            clientSecret: <GOOGLE_CLIENT_SECRET>
            redirectUriTemplate: "{baseUrl}/oauth2/callback/{registrationId}"
            scope:
                - email
                - profile
            facebook:
            clientId: <FACEBOOK_CLIENT_ID>
            clientSecret: <FACEBOOK_CLIENT_SECRET>
            redirectUriTemplate: "{baseUrl}/oauth2/callback/{registrationId}"
            scope:
                - email
                - public_profile
            github:
            clientId: <GITHUB_CLIENT_ID>
            clientSecret: <GITHUB_CLIENT_SECRET>
            redirectUriTemplate: "{baseUrl}/oauth2/callback/{registrationId}"
            scope:
                - user:email
                - read:user
    ```

### Setting up the Angular Server (frontend)

```bash
cd frontend
npm i
ng serve
```

Backend server start at http://localhost:8080 and frontend server start at http://localhost:4200.