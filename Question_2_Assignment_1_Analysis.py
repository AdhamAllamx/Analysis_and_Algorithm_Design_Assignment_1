# -*- coding: utf-8 -*-
"""
Created on Fri Nov  3 17:43:15 2023

@author: ALLAM
"""
import matplotlib.pyplot as plt
import time
import random

""" Binary_Search method that uses the binary Search Algorthim technique and return true if the element was found in 
the array or not and false otherwise using the divide and conquer algorithm       """"

def binary_Search(arr, target , start , end):
    if start > end :
        return False
    mid  = start + (end-start)//2
    if arr[mid] < target :
        return binary_Search(arr, target, mid+1, end)
    elif arr[mid]> target :
        return binary_Search(arr, target, start, mid-1)
    else:
        return True
   
  """"  Merge_Sort method that uses the Merge sort Algorthim technique and sort the given array """
def merge_Sort(arr):
    if len(arr)>1:
        mid = len(arr)//2
        left_SubArray= arr[:mid]
        right_SubArray = arr[mid:]

        merge_Sort(left_SubArray)
        merge_Sort(right_SubArray)

        j=k=i=0

        while i < len(left_SubArray) and j < len(right_SubArray):
            if left_SubArray[i] > right_SubArray[j]:
                arr[k] = right_SubArray[j]
                j += 1
              
            else:
                arr[k] = left_SubArray[i]
                i += 1
            k += 1

        while i < len(left_SubArray):
            arr[k] = left_SubArray[i]
            k += 1
            i += 1
          

        while j < len(right_SubArray):
            arr[k] = right_SubArray[j]
            j += 1
            k += 1
            
""""Sum_Pairs_Search methd the actually uses both merge_sort and binary Search methods by using principle of the divide and 
conquer technique to give all of the pairs that thier sum is equal to the given input target"""

def sum_Pairs_Search(arr , target ):
    result_Pairs = []
    """"sending the input array to be sorted to the merge sort method and the merge sort will divide it into two subproblems"""
    merge_Sort(arr)
    
    for i in range(len(arr)):
        target_complement = target - arr[i]
        """"using the binary search mehtod to search and check whehter the complement of target is present inside the given array or not """
        if binary_Search(arr, target_complement, i, len(arr)-1):
            pair_To_Add = [arr[i], target_complement]
            result_Pairs.append(pair_To_Add)
            
    return result_Pairs

if __name__ == "__main__":
    arr  = [5,2,7,9,1,4,1,10,0,11,-1,12,-2]
    target = 10
    timings_sumofPairs = []
    """ N_VALUES  array same as x_values but used inside for loop"""
    n_values = [10**i for i in range(7)]
    """ X_VALUES  array is for the x axis values of n """
    x_values = [10**i for i in range(7)]

    for n in n_values:
        """"Generating random arrays with random size every iteration and send it to the sumPairSearch method withn same target to find and 
        analyis it in different time frames in order to scale the results and plot them"""
        random_arr = [random.randint(-n, n) for _ in range(n)]  
        starting_time = time.time()
        result_pairs = sum_Pairs_Search(random_arr, target)
        ending_time = time.time()
        timings_sumofPairs.append((ending_time - starting_time))
    """printing the results of calling sum_pairs_search"""    
    result = sum_Pairs_Search(arr, target)
    print(result)
    """"plotting the results"""
    plt.plot(x_values, timings_sumofPairs, label="Sum Of Pairs")
    plt.xlabel('n')
    plt.ylabel('Time in (SEC)')
    plt.title('Sum Of Pairs Calculation Time')
    plt.legend()
    plt.grid(True)
    plt.show()

    

