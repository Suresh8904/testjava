# Currency Converter Application (Java)

This is a simple Java console application that converts an amount from one currency to another using live exchange rates provided by the ExchangeRate-API. The user can input the amount, select the currencies, and the application will provide the converted amount based on the latest exchange rates.

## Features

- Converts currency using live exchange rates from the ExchangeRate-API.
- Supports multiple currencies like USD, EUR, GBP, JPY, INR, etc.
- Easy to use command-line interface.

## Requirements

- Java 8 or higher
- Internet connection for fetching live exchange rates
- IntelliJ IDEA (or any other Java IDE)

## Getting Started

### 1. Clone the repository or download the source code:

    ```bash
      git clone https://github.com/your-repository/currency-converter.git

# Download the JSON library:
- Download the latest json.jar file from the following link: JSON Library - Maven Central
   - Direct Download: json-20230618.jar

# Setting up the Project in IntelliJ:
  ## Open the project in IntelliJ:
  - Open IntelliJ IDEA and choose Open from the Welcome Screen.
  - Select the folder where the project is located.

  ## Add the json.jar Library to the Project:

   - Go to File > Project Structure (or press Ctrl + Alt + Shift + S).
   - In the Project Structure window, select Modules on the left sidebar.
   - Click on the Dependencies tab.
   - Click the + button and choose JARs or directories.
   - Browse to the location where you downloaded json-20230618.jar and select it.
   - Click OK.

  ## Rebuild the project:
  - Right-click the project in the Project panel and choose Rebuild Project.

# Running the Application:
  - After you've successfully set up the project, navigate to the CurrencyConverterAPI.java file.
  - Right-click on the file and select Run 'CurrencyConverterAPI.main()'.
  - Follow the on-screen prompts to enter the amount and currency codes for conversion.

# Running via Command Line:
  - Open a terminal in the project directory.
  - Compile the Java file with the following command (ensure the json.jar file is in the same directory or provide the correct path):
  - ```bash
    javac -cp .:json-20230618.jar CurrencyConverterAPI.java

  - Run the program:
  - ```bash
    java -cp .:json-20230618.jar CurrencyConverterAPI

  - Example Usage
      - Welcome to the Currency Converter!
      - Enter the amount to convert: 100
      - Currencies available: [USD, EUR, GBP, JPY, INR, ...]
      - From (currency code): USD
      - To (currency code): EUR
      - 100.0 USD is equivalent to 85.0 EUR.

# License

This project is licensed under the MIT License - see the LICENSE file for details.
