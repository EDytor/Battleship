# Battleship
A digital version of the classic ship game.
Originally played on sheets of paper between two players. 
The application has been extended with the ability to play against the computer.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Game rules](#game-rules)

## General info
Writing this application allowed me to understand the principles of object-oriented Java and I learned how to write unit tests.

## Technologies
Project is created with:
* Java
* JUnit

## Game rules
The game of ships consists in guessing in which fields the enemy has hidden his warships before he destroys our ships. 
At the beginning of the game, each player has several ships of different lengths and places them on the board. 
Each player places ships on his board and targets the spaces on his opponent's board.

#### The goal of the game
The aim of the ship game is to sink the enemy ships as quickly as possible, but also to protect your own ships.

#### Board
The ship board consists of 10 by 10 tiles marked with the following coordinates:
Columns are marked with letters from A to J,
Rows are marked with numbers from 1 to 10.

#### The course of the game
Hitting an enemy ship is based on a shot that guesses the location of a ship. 
Shots are fired alternately, by specifying the coordinates of the field (e.g. A5). 
The sinking of the ship occurs when the player guesses the location of the entire ship. 
The player informs the opponent of a miss with the word "miss", of a hit "hit" or "(hit) sunk".

The first player to sink all the enemy ships wins.
