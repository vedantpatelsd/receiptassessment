Assessment

To run locally:

Java 8 or above version installed
Maven 3.9.1 or above version installed


Run:

`mvn clean install`

`java -jar target\receiptassignment-0.0.1-SNAPSHOT.jar`


OR


To run via Docker:

`docker build --build-arg JAR_FILE=target/*.jar -t myorg/myapp .`

`docker run -p 8080:8080 myorg/myapp`




cURL:



`
curl --location 'http://localhost:8080/receipts/process' \
--header 'Content-Type: application/json' \
--data '{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}'
`


`curl --location 'http://localhost:8080/receipts/6fa8190e-047f-46d8-892a-a338d9672d7f/points'`
