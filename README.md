# Employee Leave Management System (ELMS)

A robust **Leave Management System** built with **Spring Boot**, **Spring Security**, and **JPA/Hibernate**. This system enables employees to apply for leaves and managers to approve, reject, or cancel leave requests. Role-based access ensures secure operations.

---

## Features

* Apply, cancel, approve, and reject leave requests
* Role-based authentication (`EMPLOYEE` / `MANAGER`)
* Automatic tracking of `createdDate` and `updatedDate`
* RESTful API with JSON responses

---

## Technology Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA / Hibernate
* PostgreSQL / H2
* Lombok

---

## Setup

1. Clone the repository:

```bash
git clone https://github.com/karansahani78/Employee-Leave-Management-System-ELMS-.git
```

2. Create `.env` in the project root:

```env
USERNAME=postgres
PASSWORD=your_password
```

3. Configure `application.properties` or `application.yml` to read from environment variables.

4. Run the application:

```bash
mvn spring-boot:run
```

---

## API Endpoints

All endpoints require **Basic Authentication**.

### Apply Leave (EMPLOYEE)

**Request**

```json
POST /api/v1/requests/apply
{
  "employeeId": 3,
  "startDate": "2025-08-20T09:00:00",
  "endDate": "2025-08-22T18:00:00",
  "reason": "Family trip"
}
```

**Response**

```json
{
  "id": 1,
  "employeeId": 3,
  "startDate": "2025-08-20T09:00:00",
  "endDate": "2025-08-22T18:00:00",
  "status": "PENDING",
  "reason": "Family trip",
  "actionBy": "EMPLOYEE",
  "remarks": null,
  "createdDate": "2025-08-15T11:10:00",
  "updatedDate": "2025-08-15T11:10:00"
}
```

---

### Approve Leave (MANAGER)

**Request**

```json
PUT /api/v1/requests/approve/1
{
  "actionBy": "MANAGER",
  "reason": "Approved for vacation"
}
```

**Response**

```json
{
  "id": 1,
  "employeeId": 3,
  "status": "APPROVED",
  "remarks": "Approved for vacation",
  "updatedDate": "2025-08-15T12:00:00"
}
```

---

### Reject Leave (MANAGER)

**Request**

```json
PUT /api/v1/requests/reject/2
{
  "actionBy": "MANAGER",
  "reason": "Workload too high"
}
```

**Response**

```json
{
  "id": 2,
  "employeeId": 3,
  "status": "REJECTED",
  "remarks": "Workload too high",
  "updatedDate": "2025-08-15T12:05:00"
}
```

---

### Cancel Leave (EMPLOYEE)

**Request**

```json
PUT /api/v1/requests/cancel/3
{
  "actionBy": "EMPLOYEE",
  "reason": "Plans changed"
}
```

**Response**

```json
{
  "id": 3,
  "employeeId": 3,
  "status": "CANCELLED",
  "remarks": "Plans changed",
  "updatedDate": "2025-08-15T12:10:00"
}
```

---

### Get All Leave Requests

**Request**

```http
GET /api/v1/requests
```

**Response**

```json
[
  {
    "id": 1,
    "employeeId": 3,
    "status": "APPROVED",
    "reason": "Family trip",
    "actionBy": "MANAGER",
    "remarks": "Approved for vacation",
    "createdDate": "2025-08-15T11:10:00",
    "updatedDate": "2025-08-15T12:00:00"
  }
]
```

---

### Get Leave Request By ID

**Request**

```http
GET /api/v1/requests/1
```

**Response**

```json
{
  "id": 1,
  "employeeId": 3,
  "status": "APPROVED",
  "reason": "Family trip",
  "actionBy": "MANAGER",
  "remarks": "Approved for vacation",
  "createdDate": "2025-08-15T11:10:00",
  "updatedDate": "2025-08-15T12:00:00"
}
```

---

### Delete Leave Request

**Request**

```http
DELETE /api/v1/requests/1
```

**Response**

```http
204 No Content
```

---

## Notes

* **Date format:** Use ISO 8601 for `startDate` and `endDate` (`YYYY-MM-DDTHH:MM:SS`).
* `.env` contains sensitive DB credentials. Make sure it is added to `.gitignore`.
* Roles:

  * `EMPLOYEE` → apply & cancel leave
  * `MANAGER` → approve & reject leave
* Spring Security uses **HTTP Basic Authentication** for simplicity.

---

## Author

**Karan Sahani**
[GitHub Profile](https://github.com/karansahani78)

