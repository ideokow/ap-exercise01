Heres a documention I accumulated for the app:
## Model classes:

##### classes about properties
---
#### `House` (`abstract class`)
* **Fields:**
    * `String uniqueID`
    * `String ownerID`
    * `String tenantID`
    * `double area`
    * `Neighborhood neighborhood`
    * `int bedrooms`
    * `int bathrooms`
    * `PropertyStatus status`
* **Methods:**
    * `abstract double calculateSalePrice()`
    * `double calculateRentPrice()`
#### `Apartment` (`extends House`)
* **Fields:**
    * `int floors`
* **Methods:**
    * `@Override double calculateSalePrice()`
#### `Villa` (`extends House`)
* **Fields:**
    * `double yardArea`
    * `int floors` (Total floors of the building)
* **Methods:**
    * `@Override double calculateSalePrice()`
#### `Penthouse` (`extends House`)
* **Fields:**
    * `double terraceArea`
* **Methods:**
    * `@Override double calculateSalePrice()`

##### user, contract and institution classes
---
### `User`
* **Fields:**
    * `String uniqueID`
    * `String username`
    * `String passwordHash`
    * `double balance`
    * `List<String> ownedPropertyIDs`
    * `List<String> rentedPropertyIDs`
    * `List<String> contractIDs`
* **Methods:**
    * `boolean increaseBalance(double amount)`
    * `boolean decreaseBalance(double amount)`
### `Institution` (Singleton Class)
* **Fields:**
    * `static final Institution INSTANCE`
    * `String uniqueID = "AGENCY_000"`
    * `List<String> availablePropertyIDs`
    * `static Institution getInstance()`
    * `void acquireProperty(String propertyID)`
### `Contract`
* **Fields:**
    * `String uniqueID`
    * `String propertyID`
    * `String partyOneID` (Owner/Landlord)
    * `String partyTwoID` (Buyer/Tenant)
    * `ContractType type`
    * `double finalPrice` (The agreed-upon price at the time of signing)
    * `boolean isCancelled` (Default: false)
* **Methods:**
    * `double calculateCancellationPenalty()`