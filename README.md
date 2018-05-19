# transactionstatistics
Calculate​ ​ realtime​ ​ statistic​ ​ from​ ​ the​ ​ last​ ​ 60​ ​ seconds

Reference:
http://www.baeldung.com/java-concurrent-skip-list-map
https://codereview.stackexchange.com/questions/173545/rest-api-for-realtime-statistics-of-last-60-seconds/173559#173559
https://netjs.blogspot.in/2016/03/concurrentskiplistmap-in-java.html
https://www.leveluplunch.com/java/examples/doublesummarystatistics-example/
https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/

	Why ConcurrentSkipListMap?

		1. API​ ​ have​ ​ to​ ​ be​ ​ threadsafe​ ​ with​ ​ concurrent​ ​ requests
		2. In​ ​ memory​ ​ solution​ ​ without​ ​ database
		3. Endpoints​ ​ have​ ​ to​ ​ execute​ ​ in​ ​ constant​ ​ time​ ​ and​ ​ memory​
