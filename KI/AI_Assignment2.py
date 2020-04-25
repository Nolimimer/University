#!/usr/bin/env python
# coding: utf-8

# # Programming Exercise 2: Constraint Satisfaction Problem

# <div class="alert alert-info">
#     <h3>Please read the following important information before starting with the programming exercise: </h3>
#     <p>In order to avoid problems with the relative file path we recommend to place the provided notebook and csp_programming_exercise.py file in the rootfolder of your <b>aima repository</b>.</p> 
#     <p>Do not use/install any additional packages, which are not provided in the requirements.txt of the  <b>aima repository</b>. </p>
#     <p>For modelling the constraint satisfaction problem you will have to define some variables. Do not change the names of variables that we provided you! Since we use these variables for an automatic evaluation, changing  variable names will result in failing the programming exercise. </p>
#     <p>Do not modify the example with the TWO + TWO = FOUR problem!</p>
#     <p>Do not modify the csp_programming_exercise.py!</p>
#     <p>After completing the exercise, download this jupyter notebook as *.py file (File &rarr; Download as 	&rarr; Python (.py)) </p>
#     <p>Before uploading this file together with your jupyter notebook to moodle, check if you can run <i>'python AI_Assignment2.py'</i> inside your anaconda environment in the root folder of your <b>aima repository</b>. If we are not able to run your submitted files in an environment with the packages provided by the requirements.txt of the <b>aima repository</b>, you will fail the programming exercise.</p>
#     
# </div>

# ## Initialization

# In[1]:


# Do not change this part.
from csp_programming_exercise import *
import sys, os
sys.path.append(os.path.realpath("aima"))


# ## Example for Solving a Constraint Satisfaction Problem

# In this exercise we are going to construct the Science Fair problem as a constraint satisfaction problem in Python using the csp library. The "TWO + TWO = FOUR" problem from the exercise (see Problem 3.4) will help us to understand how to model a constraint satisfaction problem with this library.
# 

# ### Constructing the Domains: TWO + TWO = FOUR

# We start with constructing the domains for our problem. As an example the domains for the TWO + TWO = FOUR- problem from the csp library are given. 

# In[2]:


# Do not change this part
# Here we form the domains for the variables: T, F, W, O, U, R, C1, C2 and C3
# Domains are formed using key-value pairs,
# where the key stands for the variable and the value is for the possible values
# set(range(1, 4)) is a short way of creating an array with numbers from 1 to 4
# set (range(1, 4)) == [1, 2, 3]
# Tip: Remember that you can construct arrays with any variable types

domains_TF = {'T': set(range(1, 10)),
           'F': set(range(1, 10)),
           'W': set(range(0, 10)),
           'O': set(range(0, 10)),
           'U': set(range(0, 10)),
           'R': set(range(0, 10)),
           'C1': set(range(0, 2)), 
           'C2': set(range(0, 2)), 
           'C3': set(range(0, 2))
}


# ### Constructing the Constraints: TWO + TWO = FOUR

# We continue with defining the constraints for our problem, the most important part of any constraint satisfaction problem. Let's take a look at the constraints for our "TWO + TWO = FOUR" problem to give you some insight about how to construct constraints with the csp library.

# In[3]:


# Do not change this part
# Here we define our constraints
# The constraint constructor of csp takes two arguments:
# 1. The variables that take part in the constraint
# 2. The constraint itself which is a function that takes the variables as arguments and returns true or false
# all_diff and eq are functions defined in csp 
# Like their name suggest all_diff returns true if every value is different
# and eq returns true if the two values are equal
# Tip: Take a look at the lambda operator in python https://www.w3schools.com/python/python_lambda.asp


constraint1_TF = Constraint(('T', 'F', 'W', 'O', 'U', 'R'), all_diff)
constraint2_TF = Constraint(('O', 'R', 'C1'), lambda o, r, c1: o + o == r + 10 * c1)
constraint3_TF = Constraint(('W', 'U', 'C1', 'C2'), lambda w, u, c1, c2: c1 + w + w == u + 10 * c2)
constraint4_TF = Constraint(('T', 'O', 'C2', 'C3'), lambda t, o, c2, c3: c2 + t + t == o + 10 * c3)
constraint5_TF = Constraint(('F', 'C3'), eq)


# ### Combine the constraints and set up the TWO + TWO = FOUR Problem

# In[4]:


# Do not change this part
# TWO + TWO = FOUR Problem
two_four_constraints = [constraint1_TF, constraint2_TF, constraint3_TF, constraint4_TF, constraint5_TF]
two_four = NaryCSP(domains_TF, two_four_constraints)


# ### Solve the TWO + TWO = FOUR Problem

# In[5]:


# Do not change this part
ac_search_solver(two_four)


# ## Programming Exercise Science Fair 

# ### Constructing the Domains: Science Fair

# In[17]:


# Define your domains here

# Excersice 2.1
# 0: Solar Power Car
# 1: Potato Battery
# 2: Baking Powder Volcano
# 3: Our Solar System
domains_Ex21 = {'Bella': [1], # Constraint 11 - Bill and Bella present Potato Battery
           'Bethany': set(range(0, 4)),
           'Brian': set(range(0, 4)),
           'Brianna': set(range(0, 4)),
           'Ben': set(range(0, 4)),
           'Bill': [1],
           'Bart': set(range(0,4))
} 

# Excersice 2.2
# 0: Solar Power Car
# 1: Potato Battery
# 2: Baking Powder Volcano
# 3: Our Solar System
domains_Ex22 = {'Bella': set(range(0, 4)),
           'Bethany': set(range(0, 4)),
           'Brian': set(range(0, 4)),
           'Brianna': set(range(0, 4)),
           'Ben': set(range(0, 4)),
           'Bill': set(range(0, 4)),
           'Bart': set(range(0,4))
}

# Excersice 2.3
# 0: Solar Power Car
# 1: Potato Battery
# 2: Baking Powder Volcano
# 3: Our Solar System -  not included in this exercise
domains_Ex23 = {'Bella': [1],
           'Bethany': set(range(0, 4)),
           'Brian': set(range(0, 4)),
           'Brianna': set(range(0, 4)),
           'Ben': set(range(0, 4)),
           'Bill': [1],
           'Bart': set(range(0, 4))     
}

# Excersice 2.4
# 0: Solar Power Car
# 1: Potato Battery
# 2: Baking Powder Volcano
# 3: Our Solar System
domains_Ex24 = {'Bella': [1], # Constraint 11 - Bill and Bella present Potato Battery
           'Bethany': set(range(0, 3)),
           'Brian': set(range(0, 3)),
           'Brianna': set(range(0, 3)),
           'Ben': set(range(0, 3)),
           'Bill': [1],
           'Bart': set(range(0,3))
} 
                
# Excersice 2.5
# 0: Solar Power Car
# 1: Potato Battery
# 2: Baking Powder Volcano
# 3: Our Solar System -  not included in this exercise
domains_Ex25 = {'Bella': [1],
           'Bethany': set(range(0, 3)),
           'Brian': set(range(0, 3)),
           'Brianna': set(range(0, 3)),
           'Ben': set(range(0, 3)),
           'Bill': [1],
           'Bart': set(range(0, 3))     
}

# Excersice 2.6
# 0: Solar Power Car
# 1: Potato Battery
# 2: Baking Powder Volcano
# 3: Our Solar System -  not included in this exercise
domains_Ex26 = {'Bella': [1],
           'Bethany': set(range(0, 3)),
           'Brian': set(range(0, 3)),
           'Brianna': set(range(0, 3)),
           'Ben': set(range(0, 3)),
           'Bill': [1],
           'Bart': set(range(0, 3))     
}


# ### Constructing the Constraints: Science Fair

# In[26]:


def numberOfTrueValues(projectID, bella, bethany, brianna, ben, bill, bart, brian):
    trueValues = 0
    if bella == projectID:
        trueValues += 1
    if bethany == projectID:
        trueValues += 1 
    if brian == projectID:
        trueValues += 1
    if brianna == projectID:
        trueValues += 1
    if ben == projectID:
        trueValues += 1
    if bill == projectID:
        trueValues += 1
    if bart == projectID:
        trueValues += 1
    return trueValues

# Constraints Exercise 2.1
# Constraint: #2 - No one presents alone
constraint1_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint2_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint3_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint4_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) != 1)
# Constraints: #4-7
constraint5_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) <= 4)
constraint6_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) <= 3)
constraint7_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              (numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) >= 3 
                              and numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) <= 5)
                              or numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) == 0)
constraint8_Ex21 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 2
                              or numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 0)
# Constraint: #8
constraint9_Ex21 = Constraint(('Bart', 'Bethany'), lambda bart, bethany: bart == bethany)
constraint10_Ex21 = Constraint(('Brian', 'Ben'), lambda brian, ben: brian != ben)
constraint11_Ex21 = Constraint(('Ben', 'Bill'), lambda ben, bill: ben != bill)
constraint12_Ex21 = Constraint(('Brian', 'Bill'), lambda brian, bill: brian != bill)
# Constraint: #9
constraint13_Ex21 = Constraint(('Bart', 'Bethany'), lambda bart, bethany: bart == bethany)
# Constraint: #10
constraint14_Ex21 = Constraint(('Brianna', 'Bill'), lambda brianna, bill: brianna != 2 and bill != 2)
# Constraint: #12
constraint15_Ex21 = Constraint(('Brianna', 'Bethany', 'Bella'), lambda brianna, bethany, bella: bella == bethany or brianna == bella)


# Constraints Exercise 2.2
# Constraint 1 in Domain
# Constraint: #8
constraint0_Ex22 = Constraint(('Bart', 'Bethany'), lambda bart, bethany: bart == bethany)
constraint1_Ex22 = Constraint(('Brian', 'Ben'), lambda brian, ben: brian != ben)
constraint2_Ex22 = Constraint(('Ben', 'Bill'), lambda ben, bill: ben != bill)
constraint3_Ex22 = Constraint(('Brian', 'Bill'), lambda brian, bill: brian != bill)
# Constraint: #12
constraint4_Ex22 = Constraint(('Brianna', 'Bethany', 'Bella'), lambda brianna, bethany, bella: bella == bethany or brianna == bella)
# Constraint: #10
constraint5_Ex22 = Constraint(('Brianna', 'Bill'), lambda brianna, bill: brianna != 2 and bill != 2)
constraint6_Ex22 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'),
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) >= 1 
                              and numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) <= 4)
constraint7_Ex22 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) >= 1 
                              and numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) <= 3)
constraint8_Ex22 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'),
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) >= 3 
                              and numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) <= 5)
constraint9_Ex22 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 2)

# Constraints Exercise 2.3
constraint1_Ex23 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'),
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) >= 1 
                              and numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) <= 4)
constraint2_Ex23 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) >= 1 
                              and numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) <= 3)
constraint3_Ex23 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'),
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) >= 3 
                              and numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) <= 5)
constraint4_Ex23 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 2)
# Constraint: #10
constraint5_Ex23 = Constraint(('Brianna', 'Bill'), lambda brianna, bill: brianna != 2 and bill != 2)
# Constraint: #11
# constraint6_Ex24 = Constraint(('Bella', 'Bill'), lambda bella, bill: bella == 1 and bill == 1) Already in Domain
# Constraint: #12
constraint6_Ex23 = Constraint(('Brianna', 'Bethany', 'Bella'), lambda brianna, bethany, bella: bella == bethany or brianna == bella)


# Constraints Exercise 2.4
# Constraint 1 and 11 in Domain
# Constraint: #2 - No one presents alone
constraint01_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint02_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint03_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint04_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) != 1)
# Constraints: #4-7
constraint1_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) <= 4)
constraint2_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) <= 3)
constraint3_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              (numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) >= 3 
                              and numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) <= 5)
                              or numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) == 0)
constraint4_Ex24 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 2
                              or numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 0)
# Constraint: #10
constraint5_Ex24 = Constraint(('Brianna', 'Bill'), lambda brianna, bill: brianna != 2 and bill != 2)
# Constraint: #11
# constraint6_Ex24 = Constraint(('Bella', 'Bill'), lambda bella, bill: bella == 1 and bill == 1) Already in Domain
# Constraint: #12
constraint7_Ex24 = Constraint(('Brianna', 'Bethany', 'Bella'), lambda brianna, bethany, bella: bella == bethany or brianna == bella)

# Constraints Exercise 2.5
# Constraint 1, 11 and 13 in Domain
# Constraint: #2 - No one presents alone
constraint1_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint2_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint3_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint4_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) != 1)
# Constraints: #4-7
constraint5_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) <= 4)
constraint6_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) <= 3)
constraint7_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              (numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) >= 3 
                              and numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) <= 5)
                              or numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) == 0)
constraint8_Ex25 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 2
                              or numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 0)
# Constraint: #8
constraint9_Ex25 = Constraint(('Bart', 'Bethany'), lambda bart, bethany: bart == bethany)
constraint10_Ex25 = Constraint(('Brian', 'Ben'), lambda brian, ben: brian != ben)
constraint11_Ex25 = Constraint(('Ben', 'Bill'), lambda ben, bill: ben != bill)
constraint12_Ex25 = Constraint(('Brian', 'Bill'), lambda brian, bill: brian != bill)
# Constraint: #9
constraint13_Ex25 = Constraint(('Bart', 'Bethany'), lambda bart, bethany: bart == bethany)
# Constraint: #10
constraint14_Ex25 = Constraint(('Brianna', 'Bill'), lambda brianna, bill: brianna != 2 and bill != 2)

# Constraints Exercise 2.6
# Constraint: #2 - No one presents alone
constraint1_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint2_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint3_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) != 1)
constraint4_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                               lambda bella, bethany, brianna, ben, bill, bart, brian: 
                               numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) != 1)
# Constraints: #4-7
constraint5_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(0, bella, bethany, brianna, ben, bill, bart, brian) <= 4)
constraint6_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(1, bella, bethany, brianna, ben, bill, bart, brian) <= 3)
constraint7_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              (numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) >= 3 
                              and numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) <= 5)
                              or numberOfTrueValues(2, bella, bethany, brianna, ben, bill, bart, brian) == 0)
constraint8_Ex26 = Constraint(('Bella', 'Bethany', 'Brianna', 'Ben', 'Bill', 'Bart', 'Brian'), 
                              lambda bella, bethany, brianna, ben, bill, bart, brian: 
                              numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 2
                              or numberOfTrueValues(3, bella, bethany, brianna, ben, bill, bart, brian) == 0)
# Constraint: #10
constraint9_Ex26 = Constraint(('Brianna', 'Bill'), lambda brianna, bill: brianna != 2 and bill != 2)
# Constraint: #12
constraint10_Ex26 = Constraint(('Brianna', 'Bethany', 'Bella'), lambda brianna, bethany, bella: bella == bethany or brianna == bella)
# Constraint: #14
constraint11_Ex26 = Constraint(('Brian', 'Brianna'), lambda brian, brianna: brian == brianna)



# ### Combine the constraints and set up the CSPs for the different problems

# <div class="alert alert-info">
#     <p>The variables csp_21, csp_22, .. are defined for setting up the CSPs for the corresponding problems. You have to use these variable names otherwise this will result in failing the programming exercise. For setting up the CSPs, you have to use the NaryCSP class (without any modifications) from the module csp_programming_exercise which was imported before. </p> 
# </div>

# In[27]:


# Construct the Science Fair Problems

# Combine Constraints and set up the csp for Problem 2.1
# TODO: csp_21 = 
constraints_Ex21 = [constraint1_Ex21, 
                    constraint2_Ex21, constraint3_Ex21, 
                    constraint4_Ex21, constraint5_Ex21, 
                    constraint6_Ex21, constraint7_Ex21, 
                    constraint8_Ex21, constraint9_Ex21,
                    constraint10_Ex21, constraint11_Ex21, 
                    constraint12_Ex21, constraint13_Ex21, 
                    constraint14_Ex21, constraint15_Ex21] 
csp_21 = NaryCSP(domains_Ex21, constraints_Ex21)

# Combine Constraints and set up the csp for Problem 2.2 
# TODO: csp_22 =
constraints_Ex22 = [constraint0_Ex22, constraint1_Ex22, 
                    constraint2_Ex22, constraint3_Ex22, 
                    constraint4_Ex22, constraint5_Ex22, 
                    constraint6_Ex22, constraint7_Ex22, 
                    constraint8_Ex22, constraint9_Ex22] 
csp_22 = NaryCSP(domains_Ex22, constraints_Ex22)


# Combine Constraints and set up the csp for Problem 2.3
# TODO: csp_23 = 
constraints_Ex23 = [constraint1_Ex23, 
                    constraint2_Ex23, constraint3_Ex23, 
                    constraint4_Ex23, constraint5_Ex23, 
                    constraint6_Ex23] 
csp_23 = NaryCSP(domains_Ex23, constraints_Ex23)

# Combine Constraints and set up the csp for Problem 2.4
# TODO: csp_24 =
constraints_Ex24 = [constraint01_Ex24, constraint02_Ex24, 
                    constraint03_Ex24, constraint04_Ex24, 
                    constraint1_Ex24, constraint2_Ex24,   
                    constraint3_Ex24, constraint4_Ex24,
                    constraint5_Ex24, constraint7_Ex24] 
csp_24 = NaryCSP(domains_Ex24, constraints_Ex24)

# Combine Constraints and set up the csp for Problem 2.5
# TODO: csp_25 =
constraints_Ex25 = [constraint1_Ex25, 
                    constraint2_Ex25, constraint3_Ex25, 
                    constraint4_Ex25, constraint5_Ex25, 
                    constraint6_Ex25, constraint7_Ex25, 
                    constraint8_Ex25, constraint9_Ex25,
                    constraint10_Ex25, constraint11_Ex25, 
                    constraint12_Ex25, constraint13_Ex25, 
                    constraint14_Ex25] 
csp_25 = NaryCSP(domains_Ex25, constraints_Ex25)

# Combine Constraints and set up the csp for Problem 2.6
# TODO: csp_26 =
constraints_Ex26 = [constraint1_Ex26, 
                    constraint2_Ex26, constraint3_Ex26, 
                    constraint4_Ex26, constraint5_Ex26, 
                    constraint6_Ex26, constraint7_Ex26, 
                    constraint8_Ex26, constraint9_Ex26,
                    constraint10_Ex26, constraint11_Ex26] 
csp_26 = NaryCSP(domains_Ex26, constraints_Ex26)


# ### Solving the CSP

# <div class="alert alert-info">
#     <p>Do not change the following cell. If you can't execute the following cell, you may have renamed the variables defined by us.</p> 
# </div>

# In[28]:


print(ac_search_solver(csp_21))
print(ac_search_solver(csp_22))
print(ac_search_solver(csp_23))
print(ac_search_solver(csp_24))
print(ac_search_solver(csp_25))
print(ac_search_solver(csp_26))


# In[ ]:





# In[ ]:




