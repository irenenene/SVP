# Project 1
Irene Liu  
irliu@chapman.edu  
2313260  
CPSC 380  


*To run: run from SVP.java with a text file given as an argument*

## Design:
1. main thread
2. row check thread 
3. column check thread 
4. subgrid check thread 

4 total threads were used to facilitate the validation process. This is so each check thread can search concurrently, as their results do not depend on each other or require serial computation. Each thread will iterate over rows/columns/subgrids of the Sudoku board. As a duplicate value is found, the coordinates of the tile and the stored value will be saved in a thread-safe list to be used in the main thread after a join.

Once all threads have finished processing for duplicates, they are joined onto the main thread where work is done to calculate a correction. This is done by determining which of the "duped" tiles is can be replaced by a new number that satisfies both row and column uniqueness.
