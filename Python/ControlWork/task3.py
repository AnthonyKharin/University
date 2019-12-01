import numpy as np  # Numpy for easy work with matrices


def solution(file_name):
    # Getting a matrix from a file
    matrix = np.loadtxt(file_name + '.txt', usecols=range(9))

    # A perfect set to compare rows/columns/fragments with
    ideal_set = {1, 2, 3, 4, 5, 6, 7, 8, 9}

    # Declaring sets that will change each iteration
    numbers_in_row = set()
    numbers_in_columns = set()
    numbers_in_fragment = set()

    for i in range(9):

        # Clearing sets that will change each iteration
        numbers_in_row.clear()
        numbers_in_columns.clear()
        numbers_in_fragment.clear()

        # The indices of the beginning of the fragment
        start_fragment_x = (i // 3) * 3
        start_fragment_y = (i % 3) * 3

        # Separating a fragment into a separate matrix
        fragment = matrix[start_fragment_x:start_fragment_x + 3, start_fragment_y:start_fragment_y + 3]

        for j in range(9):
            # The index within the fragment
            x_in_fragment = j // 3
            y_in_fragment = j % 3
            # The filling sets
            numbers_in_fragment.add(fragment[x_in_fragment][y_in_fragment])
            numbers_in_row.add(matrix[i][j])
            numbers_in_columns.add(matrix[j][i])
        # If at least one of the sets is different from the ideal, then the Sudoku is filled in incorrectly
        if numbers_in_row != ideal_set or numbers_in_columns != ideal_set or numbers_in_fragment != ideal_set:
            return "NO"
    # If we checked all the Sudoku and did not find the error, then the Sudoku is filled correctly
    return "YES"
