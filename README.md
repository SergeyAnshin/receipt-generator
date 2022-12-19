# Project name

Receipt Generator

# Description

Console application that allows you to generate a store receipt.

##  Stack

* Java
* Gradle

# Application launch

## Requirements

* JDK 17
* Gradle 7.5

## Specific steps

Clone the repository.

```bash
git clone https://github.com/SergeyAnshin/receipt-generator.git

git clone git@github.com:SergeyAnshin/receipt-generator.git
```

For the console application to work, you must pass arguments. 
Arguments can be passed through the console and file.
The type of argument passing is defined in the application.properties file in resources.

```bash
# Arguments from console
argument.type=console

# Arguments from file
argument.type=file
```

To run the app via gradle you can use the following commands:

```bash
# Arguments from console
# Argument format: 1-3 2-6 card-55
./gradlew run --args='<productId-quantity> <productId-quantity> <card-discountCardId>'

# Arguments from file
# Argument format: file.txt
./gradlew run --args='<fileName>'
```
