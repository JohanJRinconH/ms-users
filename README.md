# Servicio de Gestión de Usuarios
    
    Este proyecto es un Servicio de Gestión de Usuarios construido con Java y Spring Boot. Proporciona APIs RESTful para el registro y gestión de usuarios. El servicio incluye características como encriptación de contraseñas usando bcrypt, generación de tokens JWT y almacenamiento de datos de usuarios en una base de datos.
    
## Características
    
    - Registro de usuarios con contraseñas encriptadas
    - Generación de tokens JWT para autenticación
    - Almacenamiento de datos de usuarios en una base de datos
    - Endpoints API RESTful
    
## Tecnologías Utilizadas
    
    - Java
    - Spring Boot
    - Maven
    - Spring Security
    - JWT
    
## Comenzando
    
### Prerrequisitos
    
    - Java 11 o superior
    - Maven
    - Una instancia en ejecución de una base de datos (por ejemplo, MySQL, PostgreSQL)
    
### Instalación
    
    1. Clona el repositorio:
       ```sh
       git clone https://github.com/JohanJRinconH/ms-users.git
       cd ms-users

### Actualiza la configuración de la base de datos en bbdd.properties:

    spring.datasource.driver-class-name=org.h2.Driver
    spring.datasource.url=jdbc:h2:mem:db
    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}

### Construye el proyecto:

    mvn clean install

### Ejecuta la aplicación:

    mvn spring-boot:run

### Endpoints API
#### Registrar Usuario

    URL: /usuarios/registrar
    Método: POST
    Content-Type: application/json
    Cuerpo de la Solicitud:
    {
        "name": "John Doe",
        "email": "mail@web.com",
        "password": "12345678s/A",
        "phones": [{
            "number": "12341234",
            "citycode": "9",
            "countrycode": "56"
        }]
    }

#### Respuesta:
    Código de Estado: 201 Created

    {
        "id": "fde29e03-5d23-4f74-82f5-2087cb644b0a",
        "nombre": "JOHN DOE",
        "created": "2023-10-01T12:00:00Z",
        "modified": "2023-10-01T12:00:00Z",
        "lastLogin": "2023-10-01T12:00:00Z",
        "token": "jwt_token",
        "isActive": true
    }

### Uso de .collections
Al levantar el proyecto, puede usar el archivo .collections/POST registrar-usuarios.http que ya tiene datos de prueba para probar el endpoint de registro de usuarios. Simplemente abra el archivo en IntelliJ IDEA y haga clic en el icono "Run" junto a la solicitud POST para enviar la solicitud al servidor.

### Uso de API en Postman
Como opcion se puedes importar el siguiente cURL en Postman.

    curl --location --request POST 'localhost:9090/usuarios/registrar' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "John Doe",
        "email": "mail@web.com",
        "password": "12345678s/A",
        "phones": [
            {
                "number": "12341236",
                "citycode": "9",
                "countrycode": "56"
            }
        ]
    }'

### Uso de Swagger
Swagger está habilitado para la documentación de la API. Se puede acceder a la interfaz de usuario de Swagger o al 
JSON en:

    JSON:   http://localhost:8080/v3/api-docs
    UI:     http://localhost:8080/swagger-ui.html

### Manejo de secretos

Para manejar los secretos, se debe configurar las variable de entorno o pasar via properties, en ambiente 
productivos se podrian manejar via algun recurso vault.

    JWT_SECRET=tBOVh+GMHxhLqBpbmfOjbUdq1wAtsHhJbvObGNUFHS71YRvPNnDnxjGH5uheeR0pMZJQvXZyPNuvHGxChcOZKg==
    DB_USERNAME=sa
    DB_PASSWORD=