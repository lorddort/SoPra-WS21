# User Stories

## General

### Load CryptoCoin courses of interest
- As **website user** I want to **select crypto currencies that I am interested in** to **make the software load all necessary data from external websites beforehand**.

### Fetch data from course website
- As a **dev** I want to **fetch specific crypto currency courses from an external website and store it** to **allow following work on it**.

---
## Graphical Representation

### Visualize courses of selected crypto currencies
- As **website user** I want to **get a line plot of different courses over the last 24 hours**.

### Select currency
- As **website user** I want to **choose a currency from either EUR or USD**.

### Select time span
- As **website user** I want to **select a time span from last 24h, today, this week, this month or this year for representation**.

---
## Maths

### Get correlation coefficient between two courses
- As **website user** I want to **get the correlation coefficient between to selectable courses computed**.

### Fit correlation coefficient to time span
- As **website user** I want **the correlation coefficient being computed in the given time span even if I change it mid term**.

### Textual interpretation to correlation coefficient
- As **project owner** I want to **harass users by giving a textual interpration to the correlation coefficient**. Something like: 
> BTC korreliert hoch positiv mit ETH während der letzten 24 Stunden, dies zeigt an, dass Preisänderungen bei BTC in ähnlichem Maße bei ETH auftreten.

---
## Overview

### Overview over all computed correlation coefficients
- As a **website user** I want to have a view where I can **see all computed coefficients in a table**.

## Select time span for which to compute overview
- As a **website user** I want to be able to **set a time span for which to generate the overview**.

## Filter table for specific crypto currencies
 - As a **website user** I want to **filter the table for specific currencies and only get them shown**.