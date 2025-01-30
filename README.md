# DD2480-DECIDE

## Overview

The DECIDE program evaluates radar tracking data to determine if an interceptor missile should be launched or not. Detailed requirements and functionality are described in the specification [here](./decide.pdf).

The key component of this program is the DECIDE() function, which produces a launch decision: either "YES" to launch the interceptor or "NO" to not launch.

## Project Structure

```
.gitignore
pom.xml
README.md
src/
    main/
        java/
            decideProgram/
                Decide.java
                LIC.java
                Main.java
                Parameters.java
    test/
        java/
            decideTest/
                DECIDETests.java
                LICTests.java

```

## Key Components

### Classes

- **Decide.java**: Contains the main decision function and other calculation methods
- **LIC.java**: Implements the 15 conditions.
- **Parameters.java**: Holds the parameters required for the conditions.
- **Main.java**: Entry point for the application.

### Tests

- **LICTests.java**: Contains unit tests for the conditions implemented in LIC.java
- **DECIDETests.java**: Contains unit tests for the decision function and the calculations made implemented in Decide.java

## Running the program

First download Maven: you can do it [here](https://maven.apache.org/download.cgi)

1. **Build the Project**


First, build the project using the following command:

```sh
mvn clean install
```

2. **Run the Program**

To run the program, use the following command:

```sh
mvn exec:java
```
This will run from Main.java where the main method is, you can change the input here.

3. **Run Tests**

To run the tests, use the following command:

```sh
mvn test
```

This will execute all the unit tests.





## Contributions
- **Felicia Murkes**
    - Setup Repo
    - Setup directory structure 
    - Setup class files
    - Setup junit tests
    - Setup Maven
    - Wrote pom.xml file
    - Wrote condition 0, 7 and 12
        - Wrote tests for each
    - Wrote tests for computePUM()x
    - Wrote Decide function + 1 test for the program


- **Melissa Saber**
    - Wrote conditions 2, 4 and 9
        - Wrote tests for each
    - Wrote a helper function for condition 4
    - Wrote 2 tests for the program


- **Bingjie Zhao**
    - Wrote condition 3, 10 and 14
        - Wrote tests for each
    - Wrote determinelaunch() + test
    - calculateFUV() + test
    - Added tests for functions in Decide.java
    - Wrote javadoc for Decide.java


- **Eyüp Ahmet Başaran**
    - Wrote conditions 1, 8 and 13
        - Wrote tests for each
    - Wrote a helper function minimalEnclosingCircle()
    - Wrote computeCMV()
    - Wrote computePUM()

- **Ismail Mohammed**
    - Wrote condition 5, 6 and 11
        - Wrote tests for each
    - Wrote a helper function perpendicularDistance()
    - Wrote test cases for computeCMV()
    - Wrote javadoc for LIC functions


## Our workflow in Github

For the LIC implementation we used test-driven development. Meaning that the person who was going toimplement the condition, had to first create tests for it, at least one that would return true and one false. This made sure that the person had a full understanding of the condition but also made sure that tests werent created with the implementation in mind.

For most of the other implementations one group member wrote the tests after the implementation was made by another group member. This made sure that all of us got an understanding of the implementations.

We used some prefixes for the commits so that we easily could see what each commit did
- feat: a new implementation was made
- test: testcase was written for a function or for the program
- refactor: The code was changed in some way without implementing something new
- fix: a bug was fixed
- doc: documentation was added

These were the steps we took once an issue was created and a group member was assigned to it

1. A branch was created and named after the issue
3. All the commits needed were committed
    - All commits has a prefix
    - All commits reference the issue by adding (#xx) at the end, xx being the issue number
4. Once the issue has been fixed a pull request was made to merge it into main
5. Any merge conflicts were handled
6. Lastly it was merged into main


## Our way-of-working

We started this project with the analysis of the problem and familiarization with our working environment. Then, based on our skills, we set the principles by deciding on the tools to work with: Java, JUnit, and Maven. Further, we came up with the project skeleton; the project's structure was outlined and the main practices were determined. Felicia led this phase by implementing a well-organized project structure and guiding our way of working, which eventually allowed us to make the transition into the "In Use" phase. Here, we followed an issue-based task approach, where we created an issue for each task, and made each commit related to an issue. After getting comfortable with this workflow we progressed to "In Place" phase where everyone was clearly using the agreed-upon practices, implementing their tasks with no conflict. We couldn't achieve the stage of "Working Well" because refinement and guidance on practices are still necessary, but this project really helped in creating a very strong balance in the team. Therefore, with such a solid foundation, we do think that this would be possible for upcoming projects.

