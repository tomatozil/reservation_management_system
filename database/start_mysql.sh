#!/bin/sh
set -e

# Function to execute MySQL commands
mysql_command() {
    mysql -u root -p"${MYSQL_ROOT_PASSWORD}" -e "$1"
}

mysql_command "CREATE DATABASE IF NOT EXISTS \`$MYSQL_DATABASE\`;"
mysql_command "ALTER USER 'root'@'localhost' IDENTIFIED BY '$MYSQL_ROOT_PASSWORD';"
mysql_command "GRANT ALL ON *.* TO 'root'@'localhost';"
mysql_command "CREATE USER IF NOT EXISTS '$MYSQL_USER'@'%' IDENTIFIED BY '$MYSQL_USER_PASSWORD';"
mysql_command "GRANT ALL ON \`$MYSQL_DATABASE\`.* TO '$MYSQL_USER'@'%';"
mysql_command "FLUSH PRIVILEGES;"
