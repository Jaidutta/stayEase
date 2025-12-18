# 🏨 StayEase: Booking & Hotel Management API

StayEase is a high-performance, backend-only REST API built to handle the complexities of modern hospitality management. From dynamic pricing engines to seamless Stripe integrations, the system is designed for scalability and real-world reliability.

### Features
StayEase enables users to:

- Search and book hotels
- Manage bookings and guests
- Make secure payments via Stripe
- Enjoy dynamic pricing based on real-time strategies

### Built with
- Spring Boot (RESTful APIs)
- JWT Authentication
- Role-based Access
- Stripe Integration
- Scheduled Background Tasks

### 📦 Modules
- Authentication Module
- Hotel Management Module
- Room and Inventory Module
- Booking Module
- Guest Management Module
- Pricing Module
- Stripe Webhook Listener
- Scheduled Task Executor

### 👤 User Roles

#### GUEST
- Sign up/login
- Search and book hotels
- Manage bookings and guests

#### HOTEL_MANAGER
- Create and manage hotels/rooms
- View bookings and generate reports
- Activate/deactivate hotel listings

### 🔐 Authentication & Authorization
- JWT-based security with refresh tokens
- Secure cookie storage for refresh tokens

#### Endpoints
| Method | Endpoint              | Description                  |
|--------|-----------------------|------------------------------|
| `POST` | `/auth/signup`        | Register new guest           |
| `POST` | `/auth/login`         | Login and receive tokens     |
| `POST` | `/auth/refresh`       | Refresh access token         |

### 🧳 Hotel Browsing & Booking (GUEST)

| Method | Endpoint                              | Description                          |
|--------|---------------------------------------|--------------------------------------|
| `GET`  | `/hotels/searchAll`                   | Paginated hotel list                 |
| `GET`  | `/hotels/search`                      | Filtered search                      |
| `GET`  | `/hotels/info/{hotelId}`              | Hotel details                        |
| `POST` | `/bookings/init`                      | Initialize booking                   |
| `POST` | `/bookings/{bookingId}/addGuests`     | Add guests                           |
| `POST` | `/bookings/{bookingId}/payments`      | Start payment                        |
| `POST` | `/bookings/{bookingId}/cancel`        | Cancel booking                       |
| `GET`  | `/bookings/{bookingId}/status`        | Booking status                       |

### 🛠️ Admin Functionality (HOTEL_MANAGER)

| Method | Endpoint                                 | Description                          |
|--------|------------------------------------------|--------------------------------------|
| `POST` | `/admin/hotels`                          | Create hotel                         |
| `PUT`  | `/admin/hotels/{id}`                     | Update hotel                         |
| `PATCH`| `/admin/hotels/activate/{id}`            | Activate/deactivate                  |
| `GET`  | `/admin/hotels/{id}/bookings`            | View bookings                        |
| `GET`  | `/admin/hotels/{id}/reports`             | Generate reports                     |

### 🏨 Room & Inventory Management

| Method | Endpoint                                      | Description                          |
|--------|-----------------------------------------------|--------------------------------------|
| `POST` | `/admin/hotels/{hotelId}/rooms`               | Add room                             |
| `PUT`  | `/admin/hotels/{hotelId}/rooms/{roomId}`      | Update room                          |
| `PATCH`| `/admin/inventory/rooms/{roomId}`             | Update inventory                     |
| `GET`  | `/admin/inventory/rooms/{roomId}`             | View inventory                       |

### 💳 Payment Flow (Stripe Integration)

1. Initiate payment – `POST /bookings/{id}/payments`
2. Stripe Checkout session created
3. Stripe sends event → `POST /webhook/payment`
4. Booking status updated to **CONFIRMED**
5. Refunds handled via Stripe on cancellations

### 👥 Guest Management

| Method   | Endpoint                  | Description       |
|----------|---------------------------|-------------------|
| `GET`    | `/users/guests`           | List guests       |
| `POST`   | `/users/guests`           | Add guest         |
| `PUT`    | `/users/guests/{id}`      | Update guest      |
| `DELETE` | `/users/guests/{id}`      | Delete guest      |

### 📈 Dynamic Pricing
Implemented using the **Decorator Pattern** with multiple strategies:

- **BasePricingStrategy** – Default base price
- **SurgePricingStrategy** – Increases during high demand
- **OccupancyPricingStrategy** – Adjusts based on availability
- **UrgencyPricingStrategy** – Increases near check-in date
- **HolidayPricingStrategy** – Premium pricing on holidays

```java
PricingStrategy strategy = new BasePricingStrategy();
strategy = new SurgePricingStrategy(strategy);
strategy = new OccupancyPricingStrategy(strategy);
strategy = new UrgencyPricingStrategy(strategy);
strategy = new HolidayPricingStrategy(strategy);

BigDecimal finalPrice = strategy.calculatePrice(inventory);
```
## ⚙️ Scheduled Background Tasks
To maintain system integrity and profitability, automated jobs run at set intervals:


TaskScheduleDescriptionBooking CleanupEvery 15 minutesExpire unpaid bookings to release inventoryPrice RefreshEvery hourRecalculate dynamic pricing across all rooms
### Expire unpaid bookings every 15 minutes
```Java
@Scheduled(cron = "0 */15 * * * *")
public void expireBooking() { 
    /* Expire unpaid sessions */ 
}
```
### Update room pricing hourly
```Java
@Scheduled(cron = "0 0 * * * *")
public void updatePrices() { 
    /* Refresh pricing engine */ 
}
```
## 🏗 Project Structure
```http
textcom.stayease
├── auth        – Authentication & JWT/Token management
├── hotel       – Hotel entity lifecycle management
├── room        – Room & inventory data (optimized with JPA)
├── booking     – Complex booking flows & state machine
├── pricing     – Decorator Pattern implementations
├── payment     – Stripe integration & Webhook listeners
└── scheduler   – Background tasks & cron jobs
```
