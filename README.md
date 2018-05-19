# Transactionstatistics
Calculate​ ​ realtime​ ​ statistic​ ​ from​ ​ the​ ​ last​ ​ 60​ ​ seconds

Reference:
1. http://www.baeldung.com/java-concurrent-skip-list-map
2. https://codereview.stackexchange.com/questions/173545/rest-api-for-realtime-statistics-of-last-60-seconds/173559#173559
3. https://netjs.blogspot.in/2016/03/concurrentskiplistmap-in-java.html
4. https://www.leveluplunch.com/java/examples/doublesummarystatistics-example/
5. https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/

# Why ConcurrentSkipListMap?
1. API​ ​ have​ ​ to​ ​ be​ ​ threadsafe​ ​ with​ ​ concurrent​ ​ requests
2. In​ ​ memory​ ​ solution​ ​ without​ ​ database
3. Endpoints​ ​ have​ ​ to​ ​ execute​ ​ in​ ​ constant​ ​ time​ ​ and​ ​ memory​

# To run the test
mvn test

# DB used
  ConcurrentSkipListMap
  
# Technology used
1. Spring-boot
2. Java-8
3. Spring-Mock-MVC


# For /transactions call

Request Payload

{ 
"amount":45.3,
"timestamp":1526755536459
}

# For /statistics call

Response

{
	"sum": 93.9,
	"avg": 31.3,
	"max": 45.3,
	"min": 24.3,
	"count": 3
}
