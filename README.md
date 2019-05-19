Name : Kenneth Chadwick

Student Number : 20086513

Which level unit tests succeed completely :

  	Level 4

Which level unit tests succeed partially :

  	n/a

Self reflection - Grading Spectrum Level :

  	I think I have achieved an outstanding level based on the specific points in the grading spectrum, but I wouldn't give myself 100. I think 90 is probably fair. 

  	My data model includes student and premium members and relatively robust options for querying assessments. The API is fully-featured. The member menu includes progress sub-menu options with well-formated information returned. All unit tests passed.

  	I don't think I've implemented data input validation in the most efficient, or totally sufficient ways everywhere, notably with the chosenPackage method implementation.

A statement of how much of the application specification you implemented :

  	I attempted to implement everything in the spec, and I think I got everything working.

Any extra features you wish to bring to the assessors attention, i.e. extra functionality, Java syntax not covered in the lectures, non-standard Libraries used :

	I implemented delete functionality for members and assessments. I also added additional options to the trainer menu to make use of some of the GymAPI methods - listing members that are at their ideal weight and listing members based on BMI category.

	I used the if statement that Jonathan showed in one of his videos on abstract classes: ((booleanCondition) ? "String if true" : "String if false").

	I used a TreeSet instead of a SortedSet for the sortedAssessmentDates method in the Member class. This made implementing the sorting functionality easier.

	I used the DecimalFormat class to format the String returned from the listMemberDetailsImperialAndMetric method in the GymAPI class.

	Used git throughout the development process - Git repo: https://github.com/kento-mc/gym-app

Known bugs/problems :

	I think there are still some instances where the Scanner class bug pops up, and there may be some inconsistencies with how the StudentMember membership packages display in different places.

Any sources referred to during the development of the assignment (no need to reference lecture/lab materials) :
	
	https://alvinalexander.com/java/how-square-number-in-java-method-function:

		Used this article to figure out how to calculate the square of a value for use in the calculateBMI method in the GymUtility class.

	https://stackoverflow.com/questions/2538787/how-to-display-an-output-of-float-data-with-2-decimal-places-in-java:

		Used this article to figure out decimal formatting using the DecimalFormat class.

	https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html,
	https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html:

		Used the Java API to figure out some functionality with the TreeSet and HashMap classes.

	https://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value:

		Got an idea from one of the further down comments on this article for creating HashMaps with the keys and values reversed so I could return the date key base on an assessment.

