Java with Ant De Bruijn Sequences
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
