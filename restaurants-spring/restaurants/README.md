# Restaurant Management API

A REST API for managing restaurants and their menu items built with Spring Boot.

## How to Run

```bash
cd restaurants
./mvnw spring-boot:run
```

Server starts at `http://localhost:8080`

## APIs

### Restaurant

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/restaurants` | Create restaurant |
| GET | `/api/v1/restaurants/{id}` | Get restaurant by ID |
| PATCH | `/api/v1/restaurants/{id}/status` | Open/Close restaurant |

### Menu Items

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/restaurants/{id}/menu-items` | Add menu item |
| GET | `/api/v1/restaurants/{id}/menu-items` | Get menu items (supports filtering & sorting) |
| PATCH | `/api/v1/menu-items/{id}/status` | Update item status |
| DELETE | `/api/v1/menu-items/{id}` | Delete menu item |

### Query Parameters for GET Menu Items

| Parameter | Example | Description |
|-----------|---------|-------------|
| category | `?category=VEG` | Filter by category (VEG, NON_VEG, DESSERT, DRINK) |
| status | `?status=AVAILABLE` | Filter by status (AVAILABLE, OUT_OF_STOCK) |
| sort | `?sort=price,desc` | Sort by field |
| page | `?page=0` | Page number |
| size | `?size=10` | Page size |

## Sample Requests

**Create Restaurant**
```bash
curl -X POST http://localhost:8080/api/v1/restaurants \
  -H "Content-Type: application/json" \
  -d '{"name": "Pizza Palace", "location": "New York"}'
```

**Add Menu Item**
```bash
curl -X POST http://localhost:8080/api/v1/restaurants/1/menu-items \
  -H "Content-Type: application/json" \
  -d '{"name": "Margherita Pizza", "price": 12.99, "category": "VEG"}'
```

**Close Restaurant**
```bash
curl -X PATCH http://localhost:8080/api/v1/restaurants/1/status \
  -H "Content-Type: application/json" \
  -d '{"open": false}'
```

## Assumptions

1. **In-memory storage** - Data is stored in ConcurrentHashMap; lost on restart
2. **Unique restaurant names** - No two restaurants can have the same name
3. **Menu items belong to one restaurant** - Cannot be shared across restaurants
4. **Closed restaurant restriction** - Cannot add menu items to a closed restaurant
5. **Soft delete** - Menu items are marked as deleted, not removed from storage
6. **Categories are fixed** - VEG, NON_VEG, DESSERT, DRINK
7. **Default status** - New menu items are AVAILABLE by default
8. **New restaurants open by default** - isOpen = true on creation

