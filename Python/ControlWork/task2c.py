import time
import task2a
import task2b

with open('Text.txt', encoding="utf-8") as file:
    text = file.read()  # Reading a large document
    start_my_algorithm = time.time()  # Getting the start time of my algorithm
    task2a.solution(text)  # The performance of my algorithm
    end_my_algorithm = time.time()  # Getting the end time of my algorithm

    print("Execution time of my algorithm:")
    print(end_my_algorithm - start_my_algorithm)

    start_other_algorithm = time.time()  # Getting the start time of other algorithm
    task2b.solution(text)  # The performance of other algorithm
    end_other_algorithm = time.time()  # Getting the end time of other algorithm

    print("Execution time of other algorithm:")
    print(end_other_algorithm - start_other_algorithm)
