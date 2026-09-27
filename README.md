# restaurants-app

Practice repo for restaurant/menu REST APIs in Java. Three separate sub-projects, each a different iteration/approach.

## Structure

### `/` (root) — plain Servlet API
Raw Java Servlet + Jetty, no Spring. `GET /api/menu` returns in-memory menu list as JSON (Gson).
Run: `mvn jetty:run` (serves on port 8080).

### `menu-api/` — Spring Boot menu API
Spring Boot version of the same idea. `GET /api/menu` via `MenuController`, in-memory `MenuItem` list.
Run: `cd menu-api && ./mvnw spring-boot:run`.

### `restaurants-spring/restaurants/` — Spring Boot restaurants + menu items
Fuller app: `Restaurant` and `MenuItem` entities, repositories, services, DTOs, exception handling.

Endpoints:
- `POST /api/v1/restaurants` — create restaurant
- `GET /api/v1/restaurants/{id}` — get restaurant
- `PATCH /api/v1/restaurants/{id}/status` — open/close restaurant
- `POST /api/v1/restaurants/{restaurantId}/menu-items` — add menu item
- `GET /api/v1/restaurants/{restaurantId}/menu-items` — list menu items (filter by category/status, sort, paginate)
- `PATCH /api/v1/menu-items/{menuItemId}/status` — update menu item status
- `DELETE /api/v1/menu-items/{menuItemId}` — delete menu item

Run: `cd restaurants-spring/restaurants && ./mvnw spring-boot:run`.

## Requirements
Java 17, Maven (wrapper included per sub-project).