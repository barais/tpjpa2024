Report TP1 - TAA:

## Presentation:

This repository is the code of the first lab of the class TAA. It was written by Ammar Kazem and Heming Wang during our second year of master's degree in software engineering at the University of Rennes 1.

The goal of this lab was to create a small back-end application with "Models" and the "DAO" that goes with it. We created a very basic representation the famous website "Doctolib".

You can see a representation of our models and the links between them in the image (1).

![2022-09-25-09-03-36-image](https://user-images.githubusercontent.com/50889372/192132459-506adbd7-9ce6-4dbd-9bbd-9fb547950f59.png)

*Image (1) : Class diagram*

For the DAOs we created a generic DAO with the basic methods from which the other DAOs inherited. Requests were written in JAP.

## How to run

To run the project you first need to start the hsql server like this:

Windows:

`.\run-hsqldb-server.bat`

Linux / Macos:

`./run-hsqldb-server.sh`

After starting the database server you can launch the main class `JPATest.java` with your ide or from a terminal. Methods have to be called manually in the main class. A small scenario was written but feel free to add calls to it.
