# Project

React FE + Java BE for keeping track of the Portuguese athletes competing in each day (Tokyo 2021). The webpage displays all the Portuguese events from the current day till the end.

## Description

* The application imports data from scrapped .csv files from a Portuguese newspaper supporting the events with *Datawrapper* capabilities. They are imported through the resources folder /data.
* The backend supports adding result from each event, sadly, the frontend doesn't. I got demotivated when the newspaper completely changed the data format mid-developing. Although I added a new parser for it, the olympic games were coming to an end so the motivation ran off.

## Technicalities

* Backend was developed with Spring MVC + Spring Data. H2 in memory database as it didn't reach PROD. No authentication/authorization provided, just good vibes!
* Frontend was developed with REACT (first time working with the library). Simple webpage with one list and a header.

## Webpage

* Since I didn't get to deploy the app:

![Webpage](images/webpage.jpg)

Similar tables are displayed below, depending on the day.
