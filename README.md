
# Hotels lookup/sorting service
----

### Summary
This application is a rest service that provides a sorted list of hotels available with respect to a specific location. The sorting can be done (either ascending/descending) on Price of hotels rooms, Name of hotel, Distance from location and average price. 
### Technologies/tool used
- Java 1.8
- Maven
- SpringBoot (spring-web)

### Instructions to get this app up and running
##### Running app on local machine
Clone this git repo into your local machine. Either use a git tool or you can use command line options.

```sh
git clone -b master https://github.com/neerajsu/hotels-info-app
```

There is also a library jar that must be imported into your local maven repository. You can generate the jar by exporting the project from [Sorting Library repo](https://github.com/neerajsu/SortingLibrary). For more information about this, please visit the GitHub link.

If you wish to skip that step, you can also directly download the generated jar from project [here](https://github.com/neerajsu/FileRepository/blob/master/sorting-library.jar).

##### Importing sorting-library jar into your maven repository

For this you will need maven installed on your machine. Check to see if maven is installed on your machine by typing the following in command prompt or terminal on linux machine.

```sh
mvn -version
```

This should output your Maven version and other related information. If it shows up that you do not have maven, you can install maven from [here](https://maven.apache.org/download.cgi)

Once you have maven up and running on your command prompt or terminal. Execute the following commmand to import that jar into your local maven repository

```sh
mvn install:install-file -Dfile="<insert path to file here including .jar extension>" -DgroupId="com.expedia.assessment" -DartifactId="sorting-library" -Dversion="1.0.0" -Dpackaging="jar"
```

When done, run the downloaded hotel-info-app spring-boot project either by

- Using command prompt execute command:  mvn spring-boot:run
- Or import the project into your workspace in your favorite IDE and run the java application. 

 
Ensure that the project is running sucessfully without any errors. If you see any errors regarding SortingLibrary, the step during the import process of sorting-library mentioned before wasn't properly done. 

Once the application is up and running you can test it by sending a GET request using Postman or any other rest client of your choice.

Following is a a sample requests and its explanation

	1. http://localhost:8080/location/1/hotels?sort_by=RATING&is_ascending=true 
    	2. This will get all hotels near location with location_id = 1, sorted by RATING field, in ascending order
	3. location_id is an integer
	4. sort_by request parameter can be either RATING, DISTANCE, NAME or PRICE. is_ascending can be true or false.
	If true, the hotels are sorted in ascending order based on the sort_by paramter.
	If false, hotels result is sorted in descending  order.
