# Readme

This project is using H2 database.

**Get User Details**
----

* **URL :** `/users`

* **Method :** `GET`
  
* **Request Parameters :** `userId (required)`
  
* **Sample Response :**

  ```
  {
    "userName": "User A",
    "groupName": "Group A",
    "modules": [
      {
        "moduleName": "Promo",
        "moduleOrder": 1
      }
    ]
  }
  ```

**Get All Modules**
----

* **URL :** `/modules`

* **Method :** `GET`
  
* **Request Parameters :** `(none)`
  
* **Sample Response :**

  ```
  [
    {
      "moduleId": 1,
      "moduleName": "Category"
    },
    {
      "moduleId": 2,
      "moduleName": "History"
    }
  ]
  ```

**Get All Groups**
----

* **URL :** `/groups`

* **Method :** `GET`
  
* **Request Parameters :** `(none)`
  
* **Sample Response :**

  ```
  [
    {
      "groupName": "Group A",
      "modules": [
        {
          "moduleName": "Promo",
          "moduleOrder": 1
        },
        {
          "moduleName": "FlashSale",
          "moduleOrder": 2
        }
      ]
    }
  ]
  ```

**Change Module Order in a Group**
----

* **URL :** `/groups`

* **Method :** `GET`
  
* **Request Parameters :**

  ```
    groupId (required)
    moduleId (required)
    operation (required) - up/down    
  ```
  
* **Sample Response :**

  ```
  [
    {
      "groupName": "Group A",
      "modules": [
        {
          "moduleName": "FlashSale",
          "moduleOrder": 1
        },
        {
          "moduleName": "Promo",
          "moduleOrder": 2
        }
      ]
    }
  ]
  ```
