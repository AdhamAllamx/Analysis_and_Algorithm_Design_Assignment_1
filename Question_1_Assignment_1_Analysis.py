# -*- coding: utf-8 -*-
"""
Created on Fri Nov  3 02:21:36 2023

@author: ALLAM
"""
import time
import matplotlib.pyplot as plt
  

""" Iterative Approach method to get the Powering of N"""

def Iterative_PowerOfNumber(a, n):
    time.sleep(0.4)
    result = 1
    for i in range(n):
        result *= a
    return result

""" D&C Approach method to get the Powering of N"""

def divide_and_conquer_PowerOfNumber(a,n) :
    time.sleep(0.4)
    if n==0 :
        return 1 
    elif n == 1 :
        return a 
    elif n%2 !=0:
        result = divide_and_conquer_PowerOfNumber(a, n//2)
        return result * result * a
    else:
        result = divide_and_conquer_PowerOfNumber(a, n//2)
        return result*result 
if __name__ == "__main__":
    a = 5
    
    """ resever array for the iterative method """
    timings_iterative = []
    """ resever array for the D&C method """
    timings_divide_conquer = []
    """test printings"""
    print(divide_and_conquer_PowerOfNumber(2,8))
    print(Iterative_PowerOfNumber(3, 7))
    
    """ X_VALUES  array is for the x axis values of n """
    x_values = [10**i for i in range(7)]
    """ N_VALUES  array same as x_values but used inside for loop"""
    n_values = [10**i for i in range(7)]
    
    for n in n_values:
        """ setting the time metric difference for the iterative method """
    
        starting_time = time.time()
        iterative_Result = Iterative_PowerOfNumber(a,n)
        ending_time = time.time()
        timings_iterative.append((ending_time - starting_time))
        """ setting the time metric difference for the D&C method """
        starting_time_DC = time.time()
        divideAndConquer_Result = divide_and_conquer_PowerOfNumber(a , n)
        ending_time_DC = time.time()
        timings_divide_conquer.append((ending_time_DC - starting_time_DC))
    
        
        
    """
    plotting the graphs of both Iterative Powering and Divide and conquer but because of the time.sleep() it shift the graph downwards alittle bit 
    so in the long term iterative method will tends to go with time complexity of THETA(n) but the Divide and conquer approach will 
    increase with time complexity theta(log n )
    @author: ALLAM
    """
    plt.plot(x_values, timings_iterative, label="Iterative Power")
    plt.plot(x_values, timings_divide_conquer, label="Divide and Conquer Power")
    plt.xlabel('n')
    plt.ylabel('Time in (SEC)')
    plt.title('Iterative vs Divide and Conquer Power Calculation Time')
    plt.legend()
    plt.grid(True)
    plt.show()