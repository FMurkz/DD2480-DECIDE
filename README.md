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




