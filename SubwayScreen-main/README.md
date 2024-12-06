# SubwayScreen

# Please read the following information before compiling

How To Setup The Database:
1. Download the advertisement.sql file provided in the src directory of the application
2. Make sure MySQL is installed and configured on your laptop
4. Navigate to your MySQL server's bin directory using command prompt
5. Run the command "mysql -u root -p" and enter the password you set when configuring MySQL as prompted
6. Enter the command "CREATE DATABASE advertisement;"
7. Enter the command "CREATE USER 'Mahdi'@'3306' IDENTIFIED BY 'ensf380';"
8. Enter the command "GRANT ALL PRIVILEGES ON advertisement.* TO 'Mahdi'@'3306';"
9. Enter the command "EXIT;"
10. Run the command
    "mysql -u Mahdi -p advertisement < c:/path/to/advertisement.sql" and enter the password 'ensf380' when asked (NOTE: make sure to replace c:/path/to/advertisement.sql with the actual path to the location where you have saved the advertisement.sql file)

How To Run The Program:
1. Check if all JAR files in the image below are present
 <img width="1097" alt="Screenshot 2024-08-04 at 10 14 18 PM" src="https://github.com/user-attachments/assets/dc30f138-d9ea-4714-bfc3-799bd5be4bdc">

 If you do not see the JAR files please do the following:
Click classpath and then Add JARs
<img width="1090" alt="Screenshot 2024-08-04 at 10 15 51 PM" src="https://github.com/user-attachments/assets/35ed478e-33f0-44f8-95d3-b2d2a11bbbb4">

Navigate to the libs folder in Project and add all the necessary JAR files. Then apply and close. 
<img width="904" alt="Screenshot 2024-08-04 at 10 19 05 PM" src="https://github.com/user-attachments/assets/306e5e88-873e-4f66-a506-c1843e1887b8">


2. Next, go to run configurations
<img width="719" alt="Screenshot 2024-08-04 at 10 20 40 PM" src="https://github.com/user-attachments/assets/38103b33-14cb-4deb-a3ab-d5fa3374168b">

3. Please enter a command line argument. Select a city name (like toronto) and a train number between 1 to 12. Select apply and then run.
   <img width="973" alt="Screenshot 2024-08-04 at 10 22 32 PM" src="https://github.com/user-attachments/assets/4d356637-e606-4796-911b-aacd4ab1f6da">

The program should run, please give it a few seconds to load. 
Also note that the test files are located in the src/test/java/ca/ucalgary/edu/ensf380 folder and can be run from there

ALSO, to view WeatherParser.java, please go into WeatherProject > src > ca.ucalgary.ensf380 
