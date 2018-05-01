# Mini-User

The application contains a page, where users can login/registrate. Only the administrator is able to see the user list and delete users. The admin user is saved to the database on server ignition.

## Install Guide

1. Navigate to */backend/src/main/resources* folder
2. **Copy** the *template.application.properties* to the same folder and **rename** it to *application.properties*
3. Replace *DATABASE*, *USERNAME* and *PASSWORD* (the dialect is set to PostgreSQL, maybe you have to change that as well)
4. In **terminal**:
* navigate to */backend* 
* *mvn clean*
* *mvn clean install*
* *cd target/*
* *java -jar mini-user-0.0.1-SNAPSHOT.jar*
5. Open a **new teminal**:
* navigate to */frontend*
* *npm start*
6. The application runs at [http://localhost:4200](http://localhost:4200)

## First Steps

1. To **log in** as an *administrator* - **email**: admin@admin.hu, **password**: admin123
2. You can delete users or sort, paginate and filter their data.
2. Enjoy!
