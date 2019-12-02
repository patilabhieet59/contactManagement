# contactManagement
Contact Management project

## Software Stack:
  - Java 8
  - Spring boot 2.2.1
  - Spring data JPA
  - H2 database
  - Maven

## API:
  1)List Contacts: GET request to pull all contacts
   ```
    http://<host>:8080/getContacts
  ```
  2)Add a contact: POST request to add contact
  Sample json input:{"id":1,"firstName":"Abhijeet","lastName":"Patil","email":"abc@gmail.com","phoneNumber":"12345","active":true}
  ```
    http://<host>:8080/addContact
  ```  
  3)Edit contact: PUT request to edit existing contact
  ```
    http://<host>:8080/updateContact
  ```
  4)Delete contact:DELETE request to delete/inactivate contact
  ```
    http://<host>:8080/deleteContact/{contactid}
  ```
    
## Deployment :
   Docker image is created on docker hub. You can pull image and run in docker container. You can find dockerfile in codebase.
   
   1. To clone the repo use below command:
   ```
    git clone https://patilabhijeet59@github.com/patilabhieet59/contactManagement.git
   ```
   2. To pull docker image:
   ```
    docker pull patilabhijeet59/contact-management
   ```
   3. To run docker image:
   ```
    docker run -p 8080:8080 -t contact-management
   ```
