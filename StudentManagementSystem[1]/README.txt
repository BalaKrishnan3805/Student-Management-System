Student Management System - Java + JDBC

Files:
- Student.java
- DBUtil.java (update DB credentials before running)
- StudentDAO.java
- Main.java
- create_table.sql

How to compile (example):
javac -cp .:mysql-connector-java-8.0.34.jar *.java
java -cp .:mysql-connector-java-8.0.34.jar Main

On Windows replace ':' with ';' in classpath.

NOTE: Put MySQL connector JAR in the project directory or add to your IDE's classpath.
