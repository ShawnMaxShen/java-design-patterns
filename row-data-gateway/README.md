---
layout: pattern
title: Row Data Gateway
folder: row-data-gateway
permalink: /patterns/row-data-gateway/
categories: Structural
language: en
tags:
 - Data access
---

## Intent
An object that acts as a Gateway to a single record in a data source. There is one instance per row. Here Gateway means
an object that encapsulates access to an external system or resource. This object does not contain domain logic methods. 
If you introduce other methods (in particular domain logic) the object becomes an Active Record Pattern.

## Explanation
- In plain words
Embedding database access code in in-memory objects can leave you with a few disadvantages. For a start, if your
in-memory objects have business logic of their own, adding the database manipulation code increases complexity. Testing
is awkward too since if your in-memory objects are tied to a database, tests are slower to run because of all the 
database access. You may have to access multiple databases with all those annoying little variations on their SQL.
A Row Data Gateway gives you objects that look exactly like the record in your record structure but can be accessed with
the regular mechanisms of your programming language. All details of data source access are hidden behind this interface.
The Row Data Gateway widely used with Transaction Script pattern. In this case, it nicely factors out the database access
code and allows it to be reused easily by different Transaction Scripts.

- Wikipedia says
Row Data Gateway is a design pattern in which an object acts as a gateway to a single database row.

-Programmatic example

## Credits

- [Row Data Gateway Pattern](https://www.sourcecodeexamples.net/2018/04/row-data-gateway.html)
