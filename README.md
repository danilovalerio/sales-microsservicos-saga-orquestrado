# Order Service - Microsserviços Padrão Saga Orquestrado
## Em nossa arquitetura vamos ter 5 serviços, sendo eles Order, Orchestrator, Product Validation, Payment e o Inventory.

```mermaid

flowchart TB
  classDef done fill:#009900,stroke:#ffffff
  classDef todo fill:#990000,stroke:#ffffff
  classDef in_progress fill:#999900,stroke:#ffffff
  
  subgraph Fluxo Saga 
      Order[Order Service]
      OrderDB[(order-db)]
      
      Orchestrator[Orchestrator]
      
      ProductValidation[Product Validation]
      ProductValidationDB[(product-db)]
      
      Payment[Payment Service]
      PaymentDB[(payment-db)]
      
      Inventory[Inventory Service]
      InventoryDB[(inventory-db)]
  end


  Order --> Orchestrator
  Order -.- OrderDB
  Orchestrator --> ProductValidation
  Orchestrator --> Payment
  Orchestrator --> Inventory
  ProductValidation -.- ProductValidationDB
  Payment -.- PaymentDB
  Inventory -.- InventoryDB
  
  


```

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


