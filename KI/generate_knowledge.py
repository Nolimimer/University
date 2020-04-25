import numpy as np
from field_var import field_var

def isInRow(value, row, sudoku):
    
    for x in range(0,4):
        if value == sudoku[x][row]:
            return True
        
    return False

def isInColumn(value, column, sudoku):
    
    for y in range(0,4):
        if value == sudoku[column][y]:
            return True
    
    return False
    
def isIn2x2Square(value, column, row, sudoku):
    
    # Lower right 
    if column > 1 and row > 1:
        for y in range (2,4):
            for x in range (2,4):
                if value == sudoku[x][y]:
                    return True
      
    # Upper right
    if column > 1 and row < 2:
        for y in range (0,2):
            for x in range (2,4):
                if value == sudoku[x][y]:
                    return True
     
    # Lower left
    if column < 2 and row > 1:
        for y in range (2,4):
            for x in range (0,2):
                if value == sudoku[x][y]:
                    return True
       
    # Upper left
    if column < 2 and row < 2:
         for y in range (0,2):
            for x in range (0,2):
                if value == sudoku[x][y]:
                    return True
    
    return False
                   

def checkConditions(column, row, sudoku):
    string = ""
    for value in range(1,5):
        if not isInRow(value, row, sudoku) and not isInColumn(value, column, sudoku) and not isIn2x2Square(value, column, row, sudoku): 
            if string != "":
                string += " | " + field_var(value, column, row)
            elif string == "":
                string += field_var(value, column, row)
                
    return string

def generate_knowledge(sudoku):
   
    kb = [] 
    
    # Initial numbers
    for y in range(0,4):
        for x in range(0,4):
            if sudoku[x][y] != 0:
                new_proposition = field_var(int(sudoku[x][y]),int(x),int(y)) 
                kb.append(new_proposition)  
                    
    for column in range(0,4):
        for row in range(0,4):
            if checkConditions(column, row, sudoku) and sudoku[column][row] == 0:
                new_proposition = checkConditions(column, row, sudoku)
                kb.append(new_proposition) 
                
                
    # Upper left
    for fv in range(1,5):
        for sv in range(1,5):
            for tv in range(1,5):
                for fov in range(1,5):
                    if fv != sv and fv != tv and fv != fov and sv != tv and sv != fov and tv != fov:
                        new_proposition = field_var(fv, 0, 0) + " & " + field_var(sv, 0, 1) + " & " + field_var(tv, 1, 0) + " ==> " + field_var(fov, 1, 1) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 1, 1) + " & " + field_var(sv, 0, 1) + " & " + field_var(tv, 1, 0) + " ==> " + field_var(fov, 0, 0) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 0, 0) + " & " + field_var(sv, 1, 1) + " & " + field_var(tv, 1, 0) + " ==> " + field_var(fov, 0, 1) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 0, 0) + " & " + field_var(sv, 0, 1) + " & " + field_var(tv, 1, 1) + " ==> " + field_var(fov, 1, 0) 
                        kb.append(new_proposition)
                        
   
                
    # Upper right
    for fv in range(1,5):
        for sv in range(1,5):
            for tv in range(1,5):
                for fov in range(1,5):
                    if fv != sv and fv != tv and fv != fov and sv != tv and sv != fov and tv != fov:
                        new_proposition = field_var(fv, 2, 0) + " & " + field_var(sv, 2, 1) + " & " + field_var(tv, 3, 0) + " ==> " + field_var(fov, 3, 1) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 3, 1) + " & " + field_var(sv, 2, 1) + " & " + field_var(tv, 3, 0) + " ==> " + field_var(fov, 2, 0) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 2, 0) + " & " + field_var(sv, 3, 1) + " & " + field_var(tv, 3, 0) + " ==> " + field_var(fov, 2, 1) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 2, 0) + " & " + field_var(sv, 2, 1) + " & " + field_var(tv, 3, 1) + " ==> " + field_var(fov, 3, 0) 
                        kb.append(new_proposition)
                        
                        
    # Lower left
    for fv in range(1,5):
        for sv in range(1,5):
            for tv in range(1,5):
                for fov in range(1,5):
                    if fv != sv and fv != tv and fv != fov and sv != tv and sv != fov and tv != fov:
                        new_proposition = field_var(fv, 0, 2) + " & " + field_var(sv, 0, 3) + " & " + field_var(tv, 1, 2) + " ==> " + field_var(fov, 1, 3) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 1, 3) + " & " + field_var(sv, 0, 3) + " & " + field_var(tv, 1, 2) + " ==> " + field_var(fov, 0, 2) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 0, 2) + " & " + field_var(sv, 1, 3) + " & " + field_var(tv, 1, 2) + " ==> " + field_var(fov, 0, 3) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 0, 2) + " & " + field_var(sv, 0, 3) + " & " + field_var(tv, 1, 3) + " ==> " + field_var(fov, 1, 2) 
                        kb.append(new_proposition) 
                        
                        
    # Lower right
    for fv in range(1,5):
        for sv in range(1,5):
            for tv in range(1,5):
                for fov in range(1,5):
                    if fv != sv and fv != tv and fv != fov and sv != tv and sv != fov and tv != fov:
                        new_proposition = field_var(fv, 2, 2) + " & " + field_var(sv, 2, 3) + " & " + field_var(tv, 2, 2) + " ==> " + field_var(fov, 2, 3) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 3, 3) + " & " + field_var(sv, 2, 3) + " & " + field_var(tv, 3, 2) + " ==> " + field_var(fov, 2, 2) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 2, 2) + " & " + field_var(sv, 3, 3) + " & " + field_var(tv, 3, 2) + " ==> " + field_var(fov, 2, 3) 
                        kb.append(new_proposition) 
                        new_proposition = field_var(fv, 2, 2) + " & " + field_var(sv, 2, 3) + " & " + field_var(tv, 3, 3) + " ==> " + field_var(fov, 3, 2) 
                        kb.append(new_proposition) 
                        
                        
     # Vertical row
    for row in range(0,4): 
        for fv in range(1,5):
            for sv in range(1,5):
                for tv in range(1,5):
                    for fov in range(1,5):
                        if fv != sv and fv != tv and fv != fov and sv != tv and sv != fov and tv != fov:
                            new_proposition = field_var(fv, row, 1) + " & " + field_var(sv, row, 2) + " & " + field_var(tv, row, 3) + " ==> " + field_var(fov, row, 0) 
                            kb.append(new_proposition) 
                            new_proposition = field_var(fv, row, 1) + " & " + field_var(sv, row, 2) + " & " + field_var(tv, row, 0) + " ==> " + field_var(fov, row, 3) 
                            kb.append(new_proposition) 
                            new_proposition = field_var(fv, row, 1) + " & " + field_var(sv, row, 0) + " & " + field_var(tv, row, 3) + " ==> " + field_var(fov, row, 2) 
                            kb.append(new_proposition) 
                            new_proposition = field_var(fv, row, 0) + " & " + field_var(sv, row, 2) + " & " + field_var(tv, row, 3) + " ==> " + field_var(fov, row, 1) 
                            kb.append(new_proposition) 
                            
     # Horizontal column                      
    for column in range(0,4): 
        for fv in range(1,5):
            for sv in range(1,5):
                for tv in range(1,5):
                    for fov in range(1,5):
                        if fv != sv and fv != tv and fv != fov and sv != tv and sv != fov and tv != fov:
                            new_proposition = field_var(fv, 1, column) + " & " + field_var(sv, 2, column) + " & " + field_var(tv, 3, column) + " ==> " + field_var(fov, 0, column) 
                            kb.append(new_proposition) 
                            new_proposition = field_var(fv, 1, column) + " & " + field_var(sv, 2, column) + " & " + field_var(tv, 0, column) + " ==> " + field_var(fov, 3, column) 
                            kb.append(new_proposition) 
                            new_proposition = field_var(fv, 1, column) + " & " + field_var(sv, 0, column) + " & " + field_var(tv, 3, column) + " ==> " + field_var(fov, 2, column) 
                            kb.append(new_proposition) 
                            new_proposition = field_var(fv, 0, column) + " & " + field_var(sv, 2, column) + " & " + field_var(tv, 3, column) + " ==> " + field_var(fov, 1, column) 
                            kb.append(new_proposition)
                            
     ## First square
                  
        # Value for position: 0,1
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 3, 0) + " | " + field_var(value, 2, 0) + ") & (" + field_var(value, 1, 2) + " | " + field_var(value, 1, 3) + ") ==> " + field_var(value, 0, 1) 
            kb.append(new_proposition) 
            
        # Value for position: 1,0
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 3, 1) + " | " + field_var(value, 2, 1) + ") & (" + field_var(value, 0, 2) + " | " + field_var(value, 0, 3) + ") ==> " + field_var(value, 1, 0) 
            kb.append(new_proposition)
            
         # Value for position: 0,0
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 1, 2) + " | " + field_var(value, 1, 3) + ") & (" + field_var(value, 2, 1) + " | " + field_var(value, 3, 1) + ") ==> " + field_var(value, 0, 0) 
            kb.append(new_proposition)
            
         # Value for position: 1,1
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 2) + " | " + field_var(value, 0, 3) + ") & (" + field_var(value, 2, 0) + " | " + field_var(value, 3, 0) + ") ==> " + field_var(value, 1, 1) 
            kb.append(new_proposition)
            
         ## Second square
        
          # Value for position: 1,2
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 2, 3) + " | " + field_var(value, 3, 3) + ") & (" + field_var(value, 0, 0) + " | " + field_var(value, 0, 1) + ") ==> " + field_var(value, 1, 2) 
            kb.append(new_proposition)
           
        # Value for position: 0,2
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 1, 0) + " | " + field_var(value, 1, 1) + ") & (" + field_var(value, 2, 3) + " | " + field_var(value, 3, 3) + ") ==> " + field_var(value, 0, 2) 
            kb.append(new_proposition)
         
        # Value for position: 0,3
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 1, 0) + " | " + field_var(value, 1, 1) + ") & (" + field_var(value, 2, 2) + " | " + field_var(value, 3, 2) + ") ==> " + field_var(value, 0, 3) 
            kb.append(new_proposition)
            
        # Value for position: 1,3
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 0) + " | " + field_var(value, 0, 1) + ") & (" + field_var(value, 2, 2) + " | " + field_var(value, 3, 2) + ") ==> " + field_var(value, 1, 3) 
            kb.append(new_proposition)
        
        ## Third square
        # Value for position: 2,0
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 1) + " | " + field_var(value, 1, 1) + ") & (" + field_var(value, 3, 2) + " | " + field_var(value, 3, 3) + ") ==> " + field_var(value, 2, 0) 
            kb.append(new_proposition)
            
        # Value for position: 3,0
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 1) + " | " + field_var(value, 1, 1) + ") & (" + field_var(value, 2, 2) + " | " + field_var(value, 2, 3) + ") ==> " + field_var(value, 3, 0) 
            kb.append(new_proposition)
   
        # Value for position: 3,1
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 0) + " | " + field_var(value, 1, 0) + ") & (" + field_var(value, 2, 2) + " | " + field_var(value, 2, 3) + ") ==> " + field_var(value, 3, 1) 
            kb.append(new_proposition)
            
        # Value for position: 2,1
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 0) + " | " + field_var(value, 1, 0) + ") & (" + field_var(value, 3, 2) + " | " + field_var(value, 3, 3) + ") ==> " + field_var(value, 2, 1) 
            kb.append(new_proposition)
           
        ## Fourth square
        
         # Value for position: 2,2
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 3) + " | " + field_var(value, 1, 3) + ") & (" + field_var(value, 3, 0) + " | " + field_var(value, 3, 1) + ") ==> " + field_var(value, 2, 2) 
            kb.append(new_proposition)
            
        # Value for position: 2,3
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 2) + " | " + field_var(value, 1, 2) + ") & (" + field_var(value, 3, 0) + " | " + field_var(value, 3, 1) + ") ==> " + field_var(value, 2, 3) 
            kb.append(new_proposition)
            
        # Value for position: 3,3
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 2) + " | " + field_var(value, 1, 2) + ") & (" + field_var(value, 2, 0) + " | " + field_var(value, 2, 1) + ") ==> " + field_var(value, 3, 3) 
            kb.append(new_proposition)
            
        # Value for position: 3,3
        for value in range(1,5):
            new_proposition = "(" + field_var(value, 0, 3) + " | " + field_var(value, 1, 3) + ") & (" + field_var(value, 2, 0) + " | " + field_var(value, 2, 1) + ") ==> " + field_var(value, 3, 2) 
            kb.append(new_proposition)
         
        # ***********
        # Second square (vertical)
        
        # Value for position: 1,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 2) + " & (" + field_var(value, 0, 0) + " | " + field_var(value, 0,1) + ")  ==> " + field_var(value, 1, 3) 
                    kb.append(new_proposition)
        
        # Value for position: 1,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 3) + " & (" + field_var(value, 0, 0) + " | " + field_var(value, 0,1) + ")  ==> " + field_var(value, 1, 2) 
                    kb.append(new_proposition)
                                
        # Value for position: 0,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 3) + " & (" + field_var(value, 1, 0) + " | " + field_var(value, 1,1) + ")  ==> " + field_var(value, 0, 2) 
                    kb.append(new_proposition)
                            
        # Value for position: 0,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 2) + " & (" + field_var(value, 1, 0) + " | " + field_var(value, 1,1) + ")  ==> " + field_var(value, 0, 3) 
                    kb.append(new_proposition)
         
        # First square (vertical)
        
        # Value for position: 0,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 1) + " & (" + field_var(value, 1, 2) + " | " + field_var(value, 1,3) + ")  ==> " + field_var(value, 0, 0) 
                    kb.append(new_proposition)
        
        # Value for position: 0,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 0) + " & (" + field_var(value, 1, 2) + " | " + field_var(value, 1,3) + ")  ==> " + field_var(value, 0, 1) 
                    kb.append(new_proposition)
                                
        # Value for position: 1,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 1) + " & (" + field_var(value, 0, 2) + " | " + field_var(value, 0,3) + ")  ==> " + field_var(value, 1, 0) 
                    kb.append(new_proposition)
                            
        # Value for position: 1,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 0) + " & (" + field_var(value, 0, 2) + " | " + field_var(value, 0,3) + ")  ==> " + field_var(value, 1, 1) 
                    kb.append(new_proposition) 
        
        # Third square (vertical)
        
        # Value for position: 2,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 1) + " & (" + field_var(value, 3, 2) + " | " + field_var(value, 3,3) + ")  ==> " + field_var(value, 2, 0) 
                    kb.append(new_proposition)
        
        # Value for position: 2,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 0) + " & (" + field_var(value, 3, 2) + " | " + field_var(value, 3,3) + ")  ==> " + field_var(value, 2, 1) 
                    kb.append(new_proposition)
                                
        # Value for position: 3,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 1) + " & (" + field_var(value, 2, 2) + " | " + field_var(value, 2,3) + ")  ==> " + field_var(value, 3, 0) 
                    kb.append(new_proposition)
                            
        # Value for position: 3,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 0) + " & (" + field_var(value, 2, 2) + " | " + field_var(value, 2,3) + ")  ==> " + field_var(value, 3, 1) 
                    kb.append(new_proposition)  
                              
         # Fourth square (vertical)
        
        # Value for position: 2,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 3) + " & (" + field_var(value, 3, 0) + " | " + field_var(value, 3,1) + ")  ==> " + field_var(value, 2, 2) 
                    kb.append(new_proposition)
        
        # Value for position: 2,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 2) + " & (" + field_var(value, 3, 0) + " | " + field_var(value, 3,1) + ")  ==> " + field_var(value, 2, 3) 
                    kb.append(new_proposition)
                                
        # Value for position: 3,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 3) + " & (" + field_var(value, 2, 0) + " | " + field_var(value, 2,1) + ")  ==> " + field_var(value, 3, 2) 
                    kb.append(new_proposition)
                            
        # Value for position: 3,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 2) + " & (" + field_var(value, 2, 0) + " | " + field_var(value, 2,1) + ")  ==> " + field_var(value, 3, 3) 
                    kb.append(new_proposition) 
                                
        ######## Horizontal
        
         # First square (Horizontal)
        
        # Value for position: 0,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 0) + " & (" + field_var(value, 2, 1) + " | " + field_var(value, 3,1) + ")  ==> " + field_var(value, 0, 0) 
                    kb.append(new_proposition)
        
        # Value for position: 1,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 0) + " & (" + field_var(value, 2, 1) + " | " + field_var(value, 3,1) + ")  ==> " + field_var(value, 1, 0) 
                    kb.append(new_proposition)
                                
        # Value for position: 0,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 1) + " & (" + field_var(value, 2, 0) + " | " + field_var(value, 3,0) + ")  ==> " + field_var(value, 0, 1) 
                    kb.append(new_proposition)
                            
        # Value for position: 1,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 1) + " & (" + field_var(value, 2, 0) + " | " + field_var(value, 3,0) + ")  ==> " + field_var(value, 1, 1) 
                    kb.append(new_proposition) 
        
        # Second square (vertical)
        
        # Value for position: 0,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 2) + " & (" + field_var(value, 2, 3) + " | " + field_var(value, 3,3) + ")  ==> " + field_var(value, 0, 2) 
                    kb.append(new_proposition)
        
        # Value for position: 1,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 2) + " & (" + field_var(value, 2, 3) + " | " + field_var(value, 3,3) + ")  ==> " + field_var(value, 1, 2) 
                    kb.append(new_proposition)
                                
        # Value for position: 0,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 1, 3) + " & (" + field_var(value, 2, 2) + " | " + field_var(value, 3,2) + ")  ==> " + field_var(value, 0, 3) 
                    kb.append(new_proposition)
                            
        # Value for position: 1,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 0, 3) + " & (" + field_var(value, 2, 2) + " | " + field_var(value, 3,2) + ")  ==> " + field_var(value, 1, 3) 
                    kb.append(new_proposition)  
                              
         # Fourth square (vertical)
        
        # Value for position: 2,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 2) + " & (" + field_var(value, 0, 3) + " | " + field_var(value, 1,3) + ")  ==> " + field_var(value, 2, 2) 
                    kb.append(new_proposition)
        
        # Value for position: 2,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 2) + " & (" + field_var(value, 0, 3) + " | " + field_var(value, 1,3) + ")  ==> " + field_var(value, 3, 2) 
                    kb.append(new_proposition)
                                
        # Value for position: 3,2
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 3) + " & (" + field_var(value, 0, 2) + " | " + field_var(value, 1,2) + ")  ==> " + field_var(value, 2, 3) 
                    kb.append(new_proposition)
                            
        # Value for position: 3,3
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 3) + " & (" + field_var(value, 0, 2) + " | " + field_var(value, 1,2) + ")  ==> " + field_var(value, 3, 3) 
                    kb.append(new_proposition)            
                    
        # Third square (vertical)
        
        # Value for position: 3,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 1) + " & (" + field_var(value, 0, 0) + " | " + field_var(value, 1,0) + ")  ==> " + field_var(value, 3, 1) 
                    kb.append(new_proposition)
        
        # Value for position: 2,1
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 1) + " & (" + field_var(value, 0, 0) + " | " + field_var(value, 1,0) + ")  ==> " + field_var(value, 2, 1) 
                    kb.append(new_proposition)
                                
        # Value for position: 2,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 3, 0) + " & (" + field_var(value, 0, 1) + " | " + field_var(value, 1,1) + ")  ==> " + field_var(value, 2, 0) 
                    kb.append(new_proposition)
                            
        # Value for position: 3,0
        for value in range(1,5):
            for othervalue in range(1,5):
                if value != othervalue:
                    new_proposition = field_var(othervalue, 2, 0) + " & (" + field_var(value, 0, 1) + " | " + field_var(value, 1,1) + ")  ==> " + field_var(value, 3, 0) 
                    kb.append(new_proposition)
                  
                            
                    
                     
                        
                    
    return kb