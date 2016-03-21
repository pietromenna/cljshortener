# cljshortener

The URL Shortener project used at my DKOM Demo at SAP Labs Latin America 2016.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running a local version

To start a web server for the application, run:

    lein ring server

## How to deploy to SAP HANA Cloud Platform

To deploy to HCP, it is necesary to create a war file. To do so, run:

    lein ring uberwar

## License

Copyright © 2016 Pietro Francesco Menna Ruiz Diaz. This project is licensed under MIT License.
