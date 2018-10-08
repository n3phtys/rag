
# R.A.G. (Random Asset Generator)

This kotlin-based CLI tool takes a json in a custom Template syntax and uses random generators to multiply the given json. It can deal with simple JSON arrays or even nested structures.

## JSON Template Syntax
WIP


## How to run tests
`./gradlew test`

## How to develop
`./gradlew installDist && ./build/install/rag/bin/rag` compiles and runs the CLI tool in one line, and can be run with parameters for simple access


## How to distribute

`./gradlew distZip` will create a zip with shell and batch scripts to start the application from command line. The zip lies at `./build/distributions`