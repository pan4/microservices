#! /bin/sh
echo Welcome to database initialization script. This wizard will help you setup required schemes and users.
echo This is only required if you want to run services locally and see how it works before doing containerization task.
echo For containerization task you will need postgres images with preconfigured scheme and user for each service.

read -p "Continue with setup (y/n)? " -i "y" -e answer
case ${answer:0:1} in
    y|Y )

read -p "Please type postgres instance host : " -i "localhost" -e PGHOST
read -p "Please type postgres instance port : " -i "5432" -e PGPORT
read -p "Please type postgres username : " -i "postgres" -e PGUSER
read -p "Please type postgres password : " -i "password" -e PGPASSWORD

export PGHOST=${PGHOST-localhost}
export PGPORT=${PGPORT-5432}
export PGUSER=${PGUSER-postgres}
export PGPASSWORD=${PGPASSWORD-password}

RUN_SQL="psql -X --set ON_ERROR_STOP=on --set AUTOCOMMIT=on"

echo business_area_service DB setup: creating database and user...
$RUN_SQL <<SQL
    DROP DATABASE IF EXISTS business1;
    DROP USER IF EXISTS business1;
    CREATE DATABASE business1;
    CREATE USER business1 WITH ENCRYPTED PASSWORD 'business1';
    GRANT ALL PRIVILEGES ON DATABASE business1 TO business1;
SQL
echo business_area_service DB setup: user 'business1', password 'business1', database 'business1' has been created

echo client_service DB setup: creating database and user...
$RUN_SQL <<SQL
    DROP DATABASE IF EXISTS client1;
    DROP USER IF EXISTS client1;
    CREATE DATABASE client1;
    CREATE USER client1 WITH ENCRYPTED PASSWORD 'client1';
    GRANT ALL PRIVILEGES ON DATABASE client1 TO client1;
SQL
echo client_service DB setup: user 'client1', password 'client1', database 'client1' has been created

echo product_service DB setup: creating database and user...
$RUN_SQL <<SQL
    DROP DATABASE IF EXISTS product1;
    DROP USER IF EXISTS product1;
    CREATE DATABASE product1;
    CREATE USER product1 WITH ENCRYPTED PASSWORD 'product1';
    GRANT ALL PRIVILEGES ON DATABASE product1 TO product1;
SQL
echo client_service DB setup: user 'product1', password 'product1', database 'product1' has been created

echo research_service DB setup: creating database and user...
$RUN_SQL <<SQL
    DROP DATABASE IF EXISTS research1;
    DROP USER IF EXISTS research1;
    CREATE DATABASE research1;
    CREATE USER research1 WITH ENCRYPTED PASSWORD 'research1';
    GRANT ALL PRIVILEGES ON DATABASE research1 TO research1;
SQL
echo research_service DB setup: user 'research1', password 'research1', database 'research1' has been created

echo resources_service DB setup: creating database and user...
$RUN_SQL <<SQL
    DROP DATABASE IF EXISTS resources1;
    DROP USER IF EXISTS resources1;
    CREATE DATABASE resources1;
    CREATE USER resources1 WITH ENCRYPTED PASSWORD 'resources1';
    GRANT ALL PRIVILEGES ON DATABASE resources1 TO resources1;
SQL
echo resources_service DB setup: user 'resources1', password 'resources1', database 'resources1' has been created



    ;;
esac

read -n 1 -s -r -p "Press any key to exit..."