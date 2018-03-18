# mybook
Project for team programming
# Development tips
## First run
### Database tables creation
After pulling from development branch set up your **application.properties** file:  
`spring.jpa.hibernate.ddl-auto=create`  
`spring.datasource.url=jdbc:mysql://localhost:3306/`  
`spring.datasource.username=`  
`spring.datasource.password=`

Create empty database in MySQL (phpmyadmin while using xampp for initial testing purposes) and put your database name in line 2. Also, put your database creditentials in lines 3 and 4 (default for xampp is probably root without password).  
After the initial run, check if database tables were successfully created. If so, change **create** to **none** in line 1.
***
## Permission denied when pushing using intellij after setting different creditentials
Delete github entry from (path in polish):  
**Panel sterowania\Konta użytkowników\Menedżer poświadczeń -> Poświadczenia systemu Windows -> Poświadczenia rodzajowe**  
[stackoverflow solution](https://stackoverflow.com/questions/47465644/github-remote-permission-denied)
***
## Newbie tips
* Remember to change your email and name in git (local, not github!)
* Initialy, clone development branch when setting up project on your computer (I recommend using 'git clone' in git bash console)
* Read and follow **First run** instructions!
* Always work on your branch! (for example, create your own feature branch named 'feature-search-view')
* Commit your work often!
* Before merging your branch into development, make sure that your changes don't break anything! Idealy, create pull request, or at least consult it with someone!