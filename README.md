Java with Maven De Bruijn Sequences
===================================

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

### Test Order

The order in which to examine the tests is outlined below.

1. `WordGeneratorTest`. This test verifies the contract of the
   `WordGenerator` class. 
2. `CycleTest`. Tests the functionality of the cycle class.
3. `DeBruijnTest`. The centerpiece test for the application. It
   culminates all the parts of the application.

There are more tests to examine, but currently the above tests are
failing.

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
