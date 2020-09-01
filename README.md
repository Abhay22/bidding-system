![Java CI with Gradle](https://github.com/ranjeet1523/bidding-system/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master&event=push)

# BIDDING SYSTEM

1. Springboot framework has been used to develop this project.
2. Language used Java for core functionality and Groovy is used for unit testing
3. Unit testing done using spock framework.
4. Unit testing covered with 100% code coverage for controller,service and biddingrule classes.
5. H2 embedded database used.
6. Liquibase has been used to manage schema creation and data population.
7. @Version is used to imlement Concurrency in distributed env
8. API is secured with Oauth2
9. gradle workflow enable for CI in github
10. Used lombok to generate POJO which reduce code.

##### Unit test coverage
<img src="" alt="drawing" width="1000" height="300"/> 


## Steps to run application
 * Download or clone the broject 
 * Traverse to `bidding-system`directory
 *  execute `./gradle clean build`
 * After build successful
      * run application using `java -jar -Dspring.profiles.active=prod build/libs/bidding-system-0.0.1-SNAPSHOT.jar 

 ##### API Details
 * Auction API: 
    * http://localhost:8080/bidding_system/v1/auction?status=RUNNING      [authentication disabled]
    
 * Place bid API 
    * http://localhost:8080/bidding_system/v1/place/RbXq9aArFC/bid        [authentication enabled]
    
 * How to fetch all auctions
    URL: GET http://localhost:8080/bidding_system/v1/auction?status=RUNNING
    
     <img src="https://github.com/ranjeet1523/bidding-system/blob/master/screenshot/Screen%20Shot%202020-09-01%20at%205.57.31%20PM.png" alt="drawing" width="1000" height="300"/> 
    
    
 * How to place a bid  
   1. * Generate a bearer token to call API
          * Post URL : http://localhost:8080/oauth/token
          * Authorization part 
             * username : bidclient
             * password : password
          * Body Part
             * username : user
             * password : user@123
             * grant_type : password
             * x-www-form-urlencoded
         
      * Bearer token
               
            ```{
                  "access_token": "9be85071-256c-4d3b-b00e-5dfa7c5b0b5e",
                  "token_type": "bearer",
                  "refresh_token": "923b1e2f-3ba5-49ff-a7de-fef5f80638bb",
                  "expires_in": 43179,
                  "scope": "read write"
                }```
<img src="https://github.com/ranjeet1523/bidding-system/blob/master/screenshot/Screen%20Shot%202020-09-01%20at%205.57.01%20PM.png" alt="drawing" width="1000" height="300"/> 
      <img src="https://github.com/ranjeet1523/bidding-system/blob/master/screenshot/Screen%20Shot%202020-09-01%20at%205.56.43%20PM.png" alt="drawing" width="1000" height="300"/>
            
   2. post the bid request
        * URL POST http://localhost:8080/bidding_system/v1/place/RbXq9aArFC/bid
        * Header part
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `9be85071-256c-4d3b-b00e-5dfa7c5b0b5e`
        
        * Body part
          ```
             {
               "bid_amount":"2500"
             }
                        
 <img src="https://github.com/ranjeet1523/bidding-system/blob/master/screenshot/Screen%20Shot%202020-09-01%20at%205.58.28%20PM.png" alt="drawing" width="1000" height="300"/> 
 <img src="https://github.com/ranjeet1523/bidding-system/blob/master/screenshot/Screen%20Shot%202020-09-01%20at%205.58.41%20PM.png" alt="drawing" width="1000" height="300"/>  
 
 




