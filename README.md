Ruby De Bruijn Sequences
========================

[![Build Status](https://secure.travis-ci.org/dvberkel/debruijn.png?branch=ruby-finished)](http://travis-ci.org/dvberkel/debruijn)

This branch is a compendium to the De Bruijn project. It provides
support code which a participate can use to start-off implementing De
Bruijn sequences. 

See the 
[main branch](https://github.com/dvberkel/debruijn "Main branch of debruijn project")
for an exposition about the entire project.

Goals
-----

The project has a number of tests. All of which are failing at the moment.

Your mission, should you chose to accept it, is to make all the test pass.

Environment
-----------

### RVM

We are using 
[ruby version manager](https://rvm.beginrescueend.com/ "Homepage for rvm") (rvm)
to control the ruby version and the gems installed.

We issued the following commands when started on this project.

    > rvm use 1.9.2
    > rvm gemset create debruijn

Everytime we work on the project we use rvm we are using 1.9.2 with
the debruijn gemset.

    > rvm use 1.9.2@debruijn

### Bundler

We use
[bundler](http://gembundler.com/ "Homepage of bundler")
to manage our dependencies.

Bundler can be installed with the following command.

    > gem install bundler

To install all the dependencies issue the following command

    > bundle install

### Running Tests

    > bundle exec rake

Will run all the tests in the project.
