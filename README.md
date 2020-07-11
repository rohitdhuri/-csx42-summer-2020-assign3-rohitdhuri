# CSX42: Assignment 3
## Name: Rohit Mahendra Dhuri

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on this project.


Note: build.xml is present in [studentskills/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dout1="output1.txt" -Dout2="output2.txt" -Dout3="output3.txt" -Derror="error.txt" -Ddebug="debugLevel"
```
Note: Arguments accept the absolute path of the files.

## Description:
1. Assumption
Absolute path of the input file is provided.
All the operations that need to be performed are according to the given input format.
There are no spaces before or after the comma character.
Gpa is in double format and bNumber is in integer format.

1. Data Structures
String have been used as output buffers.
Sets have been used for storing multiple strings.
Arraylists have been used for storing lis of observers.
Arrays have been used while splitting Input strings for parsing.

3. Code Working
Program accpets 7 arguments.
Input file is read and processed one line at a time. If a record with the bNumber alrady exits then its values are updated if not then a record is made and added to the tree and its observers are updated.
The modify file is processed one line at a time and the value of specified field is modified. After this its observers are notified and updated.

1. Time Complexity
For Entire tree traversal (printNodes) - O(n)
For Searching a node in tree (findNode) - O(n)


## References

1. https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [07/10/2020]


