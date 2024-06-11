# Order Service - Microsserviços Padrão Saga Orquestrado
## Em nossa arquitetura vamos ter 5 serviços, sendo eles Order, Orchestrator, Product Validation, Payment e o Inventory.



## Tecnologias
* **Java 17**
* **Spring Boot 3**
* **Apache Kafka**
* **API REST**
* **PostgreSQL**
* **MongoDB**
* **Docker**
* **docker-compose**
* **Redpanda Console**

```mermaid

flowchart TB
  classDef done fill:#009900,stroke:#ffffff
  classDef todo fill:#990000,stroke:#ffffff
  classDef in_progress fill:#999900,stroke:#ffffff


  Order --> OrderDB
  Order -->|Inicia Saga| Orchestrator
  Orchestrator -->|Finaliza Saga| Order
  Orchestrator --> ProductValidation
  Orchestrator --> ProductValidation
  ProductValidation -->  Orchestrator
  Orchestrator --> Payment
  Orchestrator --> Inventory
  ProductValidation --> ProductValidationDB((product-db))
  Payment --> PaymentDB((payment-db))
  Inventory --> InventoryDB((inventory-db))
  OrderDB((order-db))
  


```
