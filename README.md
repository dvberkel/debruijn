Java with Ant De Bruijn Sequences
===================================

[![Build Status](https://secure.travis-ci.org/dvberkel/debruijn.png?branch=java-ant-finished)](http://travis-ci.org/dvberkel/debruijn)

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

1. WordGeneratorTest. This test verifies the contract of the WordGenerator class.
2. CycleTest. Tests the functionality of the cycle class.
3. DeBruijnTest. The centerpiece test for the application. It
   culminates all the parts of the application.  

There are more tests to examine, but currently the above tests are failing.

Environment
-----------

[Ant](http://ant.apache.org/ "Homepage of Ant") is used to
automate our build.

>Apache Ant is a Java library and command-line tool whose mission is
> to drive processes described in build files as targets and extension
> points dependent upon each other. The main known usage of Ant is the
> build of Java applications. Ant supplies a number of built-in tasks
> allowing to compile, assemble, test and run Java applications.

The [documentation](http://ant.apache.org/manual/index.html "Information how to setup Ant")
page lists information how to setup Ant.

### Running Test

    > ant test

The command above runs all the tests.
