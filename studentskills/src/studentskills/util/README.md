# CSX42: Assignment 1
## Name: Rohit Mahendra Dhuri

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on this project.
#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile wordPlay/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:

1. Assumption
All sentences end with a period.
Absolute path of the input file is provided.
Sentences are well formatted and special characters are not used.

2. Data structures
Strings have been used as the output buffer.

3. Code working
One word is polled at a time, processed and stored onto the output string.
Some variables are maintained during this processing which are then used to calculate the metrics.
Both the output string and metrics are then printed on the console and file.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [06/10/2020]


