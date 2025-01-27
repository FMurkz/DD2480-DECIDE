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


- **Eyüp Ahmet Başaran**
    - Wrote conditions 1, 8 and 13
        - Wrote tests for each
    - Wrote a helper function minimalEnclosingCircle()
    - Wrote computeCMV()
    - Wrote computePUM()

- **Ismail Mohammed**
    - Wrote condition 5, 6 and 11
        - Wrote tests for each
    - Wrote test cases for computeCMV()






