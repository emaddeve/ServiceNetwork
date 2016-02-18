#!/bin/bash
 
EXPECTED_ARGS=1
E_BADARGS=65
MYSQL=`which mysql`
 
Q1="CREATE DATABASE IF NOT EXISTS ServiceNetworkTest;"
Q2="use ServiceNetworkTest;"
Q3="create table persons (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(100) NOT NULL,firstName VARCHAR(100) NOT NULL
	,email VARCHAR(100) NOT NULL,password text NOT NULL,role INT DEFAULT 0,PRIMARY KEY(id));"
Q4="create table services (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(200) NOT NULL,DES text NOT NULL,type int(11) NOT NULL,pid INT(11) NOT NULL,
	beginDate datetime,endDate datetime,sid INT,PRIMARY KEY (id),FOREIGN KEY(pid) REFERENCES persons(id));"
Q5="INSERT INTO persons (name,firstName,email,password,role) VALUES('root','root','root@gmail.com','root',1);"
Q6="INSERT INTO persons (name,firstName,email,password) VALUES('user1','user1','user1@gmail.com','user1');"
Q7="INSERT INTO persons (name,firstName,email,password) VALUES('user2','user2','user2@gmail.com','user2');"
Q8="INSERT INTO persons (name,firstName,email,password) VALUES('user3','user3','user3@gmail.com','user1');"


Q9="INSERT INTO services (name,DES,type,pid,beginDate,endDate) VALUES('french courses','i need some french courses',1,2,'2016-02-04 00:00:00','2017-02-04 00:00:00');"
Q10="INSERT INTO services (name,DES,type,pid,beginDate,endDate) VALUES('english courses','i can teach english',0,2,'2016-02-04 00:00:00','2017-02-04 00:00:00');"

Q11="INSERT INTO services (name,DES,type,pid,beginDate,endDate) VALUES('taxi drive','i can pick you up from anywhere',0,3,'2016-02-04 00:00:00','2017-02-04 00:00:00');"
Q12="INSERT INTO services (name,DES,type,pid,beginDate,endDate) VALUES('english courses','i need some english courses',1,3,'2016-02-04 00:00:00','2017-02-04 00:00:00');"

Q13="INSERT INTO services (name,DES,type,pid,beginDate,endDate) VALUES('taxi drive','i can pick you up from anywhere',1,4,'2016-02-04 00:00:00','2017-02-04 00:00:00');"
Q14="INSERT INTO services (name,DES,type,pid,beginDate,endDate) VALUES('french courses','i can teach french',0,4,'2016-02-04 00:00:00','2017-02-04 00:00:00');"


SQL="${Q1}${Q2}${Q3}${Q4}${Q5}${Q6}${Q7}${Q8}${Q9}${Q10}${Q11}${Q12}${Q13}${Q14}"
 
if [ $# -ne $EXPECTED_ARGS ]
then
  echo "you have entered wrong arguments examples of using this script ex: bash mysqldb.sh (username of the DB)";
  exit $E_BADARGS
fi
 
$MYSQL -u $1 -p -e "$SQL"	


