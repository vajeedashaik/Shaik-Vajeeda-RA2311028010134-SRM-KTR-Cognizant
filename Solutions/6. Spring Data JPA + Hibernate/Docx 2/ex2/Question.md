Hands on 2
Write queries on stock table using Query Methods With one year stock data of Facebook, Google and Netflix, we need to implement Spring Data JPA Query Methods for the following scenarios:Sample DataSample data for implementing this hands on is provided to you in the platform
Setup stock data
Create a new table for storing stock details.
CREATE TABLE IF NOT EXISTS `ormlearn`.`stock` (
  `st_id` INT NOT NULL AUTO_INCREMENT,
  `st_code` varchar(10), 
  `st_date` date,
  `st_open` numeric(10,2),
  `st_close` numeric(10,2), 
  `st_volume` numeric,
  PRIMARY KEY (`st_id`)
);
The file stock-data.csv in spring-data-jpa-files folder contains the stock data of Facebook, Google and Netflix from 18 Oct 2018 to 17 Oct 2019. This is public data downloaded from finance.yahoo.com.
Open stock-data.csv file in Excel
Include the following formula in F2 cell, this will display the insert script in F2 cell.
=CONCATENATE("insert into stock (st_code, st_date, st_open, st_close, st_volume) values (""", B2, """, """, YEAR(A2), "-", MONTH(A2), "-", DAY(A2), """, ", C2, ", ", D2, ", ", E2, ");")
Drag the formula for all the rows of data in F column.
Copy all data in F column and paste it in Notepad or Notepad++ and save it as a file named stock-data.sql. Execute this script in mysql command line client which populates data into ormlearn.stock table. Following command assumes that the git project is available in D:.
mysql> source D:\spring-data-jpa-files\stock-data.sql
Create new class Stock in orm-learn project and define the required mapping annotations.
Create StockRepository class to write the Query Methods
Create methods in OrmLearnApplication to test by autowiring StockRepository directly.
Query Methods required for the following scenarios
Get all stock details of Facebook in the month of September 2019. Expected data result of Query Method below.
+---------+------------+---------+----------+-----------+
| st_code | st_date    | st_open | st_close | st_volume |
+---------+------------+---------+----------+-----------+
| FB      | 2019-09-03 |  184.00 |   182.39 |   9779400 |
| FB      | 2019-09-04 |  184.65 |   187.14 |  11308000 |
| FB      | 2019-09-05 |  188.53 |   190.90 |  13876700 |
| FB      | 2019-09-06 |  190.21 |   187.49 |  15226800 |
| FB      | 2019-09-09 |  187.73 |   188.76 |  14722400 |
| FB      | 2019-09-10 |  187.44 |   186.17 |  15455900 |
| FB      | 2019-09-11 |  186.46 |   188.49 |  11761700 |
| FB      | 2019-09-12 |  189.86 |   187.47 |  11419800 |
| FB      | 2019-09-13 |  187.33 |   187.19 |  11441100 |
| FB      | 2019-09-16 |  186.93 |   186.22 |   8444800 |
| FB      | 2019-09-17 |  186.66 |   188.08 |   9671100 |
| FB      | 2019-09-18 |  188.09 |   188.14 |   9681900 |
| FB      | 2019-09-19 |  188.66 |   190.14 |  10392700 |
| FB      | 2019-09-20 |  190.66 |   189.93 |  19934200 |
| FB      | 2019-09-23 |  189.34 |   186.82 |  13327600 |
| FB      | 2019-09-24 |  187.98 |   181.28 |  18546600 |
| FB      | 2019-09-25 |  181.45 |   182.80 |  18068300 |
| FB      | 2019-09-26 |  181.33 |   180.11 |  16083300 |
| FB      | 2019-09-27 |  180.49 |   177.10 |  14656200 |
+---------+------------+---------+----------+-----------+
Get all google stock details where the stock price was greater than 1250
+---------+------------+---------+----------+-----------+
| st_code | st_date    | st_open | st_close | st_volume |
+---------+------------+---------+----------+-----------+
| GOOGL   | 2019-04-22 | 1236.67 |  1253.76 |    954200 |
| GOOGL   | 2019-04-23 | 1256.64 |  1270.59 |   1593400 |
| GOOGL   | 2019-04-24 | 1270.59 |  1260.05 |   1169800 |
| GOOGL   | 2019-04-25 | 1270.30 |  1267.34 |   1567200 |
| GOOGL   | 2019-04-26 | 1273.38 |  1277.42 |   1361400 |
| GOOGL   | 2019-04-29 | 1280.51 |  1296.20 |   3618400 |
| GOOGL   | 2019-10-17 | 1251.40 |  1252.80 |   1047900 |
+---------+------------+---------+----------+-----------+
Find the top 3 dates which had highest volume of transactions
+---------+------------+---------+----------+-----------+
| st_code | st_date    | st_open | st_close | st_volume |
+---------+------------+---------+----------+-----------+
| FB      | 2019-01-31 |  165.60 |   166.69 |  77233600 |
| FB      | 2018-10-31 |  155.00 |   151.79 |  60101300 |
| FB      | 2018-12-19 |  141.21 |   133.24 |  57404900 |
+---------+------------+---------+----------+-----------+
Identify three dates when Netflix stocks were the lowest 
+---------+------------+---------+----------+-----------+
| st_code | st_date    | st_open | st_close | st_volume |
+---------+------------+---------+----------+-----------+
| NFLX    | 2018-12-24 |  242.00 |   233.88 |   9547600 |
| NFLX    | 2018-12-21 |  263.83 |   246.39 |  21397600 |
| NFLX    | 2018-12-26 |  233.92 |   253.67 |  14402700 |
+---------+------------+---------+----------+-----------+
