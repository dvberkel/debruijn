Java with Maven De Bruijn Sequences
===================================

[![Build Status](https://secure.travis-ci.org/dvberkel/debruijn.png?branch=java-maven-finished)](http://travis-ci.org/dvberkel/debruijn)

This branch is a compendium to the De Bruijn project. It provides
support code which a participate can use to start-off implementing De
Bruijn sequences.

See the [main branch](https://github.com/dvberkel/debruijn "Main Branch") 
for an exposition about the entire project.

Goal
----

The project has a number of tests. All of which are failing at the
moment.

Your mission, should you chose to accept it, is to make all the test pass.

Environment
-----------

[Maven](http://maven.apache.org/ "Homepage of Maven") is used to
automate our build.

> Apache Maven is a software project management and comprehension
> tool. Based on the concept of a project object model (POM), Maven
> can manage a project's build, reporting and documentation from a
> central piece of information.

The [documentation](http://maven.apache.org/users/index.html "Information how to setup Maven")
page lists information how to setup Maven.

### Eclipse

The following command generate an eclipse project definition.

    > mvn eclipse:eclipse

This allows a participant to import the project into eclipse.

### Running Test

    > mvn test

The command above runs all the tests.
