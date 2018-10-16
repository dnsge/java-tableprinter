# java-tableprinter

*Generate formatted tables with easy-to-read formatting*

[![GitHub](https://img.shields.io/github/license/mashape/apistatus.svg?style=flat-square)](https://opensource.org/licenses/mit-license.php)

A library that allows you to generate text based tables from different rows, columns, or custom objects.

A typical table would look like this:
```text
#  | Name           | Party       | Birthplace
------------------------------------------------------
43 | George W. Bush | Republican  | New Haven, CT
44 | Barack Obama   | Democrat    | Honolulu, HI
45 | Donald Trump   | Republican  | New York City, NY
47 |                | Democrat    |
48 | Taylor Swift   | Independent | Reading, PA
```

### Features
 - Simple
 - Custom headers
 - Supports empty cells
 - Make tables from rows or columns
 - Print out tables based off of custom objects
 
### Examples

 - [Columns to Table](examples/ColumnsToTable.java)
   * [Output](examples/ColumnsToTable.output.txt)
 - [Row Factory Example](examples/RowFactoryExample.java)
   * [Output](examples/RowFactoryExample.output.txt)
 - [RowMakeable Object to Table](examples/ObjectsToTable.java)
   * [Output](examples/ObjectsToTable.output.txt)
   
##### Documentation available at [dnsge.github.io/java-tableprinter](https://dnsge.github.io/java-tableprinter/)

###### Made by Daniel Sage (2018)
