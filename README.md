De Bruijn Sequences
===================

This project will be proposed as an entry for the 2012 Software
Craftsmanship Conference held at Bletchley Park on June 14th 2012

Goals
-----

This project will serve the following goals.

* Gathering place for information concerning the SC2012 entry.
* Host accompanying presentations.
* Provide support code in various languages.

Software Craftsmanship Conference
---------------------------------

[Software Craftsmanship 2012](http://www.codemanship.co.uk/softwarecraftsmanship/ "Homepage for SC2012")
(SC2012) is the fourth international conference for software
craftsmen. This years theme is *Computer Science for Software
Craftsman*, in celebration of the work of computer pioneer Alan
Turing.

### Alan Turing

[Alan Turing](http://en.wikipedia.org/wiki/Alan_Turing "Wikipedia on Alan Turing")
was a English mathematician. His seminal paper "On Computable Numbers,
with an Application to the Entscheidungsproblem" introduced
[Turing machines](http://en.wikipedia.org/wiki/Turing_machine "Wikipedia on Turing Machines")
, an abstract machine capable of performing computations.

During the Second World War he was at Bletchley Park and played a
pivotal role in breaking the
[Enigma](http://en.wikipedia.org/wiki/Enigma_machine "Wikipedia on the Enigma").

### Bletchley Park

[Bletchley Park](http://en.wikipedia.org/wiki/Bletchley_Park "Wikipedia on Bletchley Park")
is an estate in Bletchley England which house the National Codes
Centre and the National Museum for Computing.

During the Second World war it was the center of the United Kingdom's
decryption establishment.

De Bruijn Sequences
-------------------

From Wikipedia on [De Bruijn Sequence](http://en.wikipedia.org/wiki/De_Bruijn_sequence "Wikipedia on De Bruijn Sequence")

> a *k-ary* **De Bruijn sequence** *B(k, n)* of order *n* is a cyclic
> sequence of a given alphabet *A* with size *k* for which every possible
> subsequence of length *n* in *A* appears as a sequence of consecutive
> characters exactly once.

An example of a De Bruijn sequence *B(3, 2)* for the alphabet
`{0, 1, 2}` is given below.

    0 0 1 1 0 2 1 2 2

Notice this sequence has the stated property as can seen by taking
consequtive consecutive characters.

    0 0 1 1 0 2 1 2 2
    0 0
      0 1
        1 1
          1 0
            0 2
              2 1
                1 2
                  2 2
    0               2

### De Bruijn

De Bruijn sequences are named after
[Nicolaas Govert de Bruijn](http://en.wikipedia.org/wiki/Nicolaas_Govert_de_Bruijn "Wikipedia on Nicolaas de Bruijn")
, a Dutch mathematician who was, until recently, affiliated as
professor emeritus at the Eindhoven University of Technology. He
passed away on 17 February 2012.
