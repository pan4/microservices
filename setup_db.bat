@echo off //TODO NOT FINISHED
echo Welcome to database initialization script. This wizard will help you setup required schemes and users.
echo This is only required if you want to run services locally and see how it works before doing containerization task.
echo For containerization task you will need postgres images with preconfigured scheme and user for each service.

SET /P y CONTINUESETUP="Continue with setup (y/n)? "
IF /I "%CONTINUESETUP%" NEQ "y" GOTO END

SET /P PGHOST="Please type postgres instance host (default=localhost): " || SET "PGHOST=localhost"
SET /P PGPORT="Please type postgres instance port (default=5432): " || SET "PGPORT=5432"
SET /P PGUSER="Please type postgres username (default=postgres): " || SET "PGUSER=postgres"
SET /P PGPASSWORD="Please type postgres password (default=password): " || SET "PGPASSWORD=password"


echo business_area_service DB setup: creating database and user...
psql -X --set ON_ERROR_STOP=on --set AUTOCOMMIT=on -c "\n
    DROP DATABASE IF EXISTS business1;\n
    DROP USER IF EXISTS business1;\n
    CREATE DATABASE business1;\n
    CREATE USER business1 WITH ENCRYPTED PASSWORD 'business1';\n
    GRANT ALL PRIVILEGES ON DATABASE business1 TO business1;\n
"
echo business_area_service DB setup: user 'business1', password 'business1', database 'business1' has been created

echo client_service DB setup: creating database and user...
psql -X --set ON_ERROR_STOP=on --set AUTOCOMMIT=on -c "
    DROP DATABASE IF EXISTS client1;
    DROP USER IF EXISTS client1;
    CREATE DATABASE client1;
    CREATE USER client1 WITH ENCRYPTED PASSWORD 'client1';
    GRANT ALL PRIVILEGES ON DATABASE client1 TO client1;
"
echo client_service DB setup: user 'client1', password 'client1', database 'client1' has been created

echo product_service DB setup: creating database and user...
psql -X --set ON_ERROR_STOP=on --set AUTOCOMMIT=on -c "
    DROP DATABASE IF EXISTS product1;
    DROP USER IF EXISTS product1;
    CREATE DATABASE product1;
    CREATE USER product1 WITH ENCRYPTED PASSWORD 'product1';
    GRANT ALL PRIVILEGES ON DATABASE product1 TO product1;
"
echo client_service DB setup: user 'product1', password 'product1', database 'product1' has been created

echo research_service DB setup: creating database and user...
psql -X --set ON_ERROR_STOP=on --set AUTOCOMMIT=on -c "
    DROP DATABASE IF EXISTS research1;
    DROP USER IF EXISTS research1;
    CREATE DATABASE research1;
    CREATE USER research1 WITH ENCRYPTED PASSWORD 'research1';
    GRANT ALL PRIVILEGES ON DATABASE research1 TO research1;
"
echo research_service DB setup: user 'research1', password 'research1', database 'research1' has been created

echo resources_service DB setup: creating database and user...
psql -X --set ON_ERROR_STOP=on --set AUTOCOMMIT=on -c "
    DROP DATABASE IF EXISTS resources1;
    DROP USER IF EXISTS resources1;
    CREATE DATABASE resources1;
    CREATE USER resources1 WITH ENCRYPTED PASSWORD 'resources1';
    GRANT ALL PRIVILEGES ON DATABASE resources1 TO resources1;
"
echo resources_service DB setup: user 'resources1', password 'resources1', database 'resources1' has been created


:END

pause