import math  # Used to get the modulus of a number


# x = float(input())
# eps = float(input())


def solution(x, eps):
    # i is needed for efficient factorial calculation
    i = 1

    term = x
    result = 1
    while True:
        # Add the term in any case.
        # If this is not done, the sum of the next several terms may be greater than the accuracy.
        result += term
        # If the modulus of the term is less than the accuracy, then we stop
        if math.fabs(term) < eps:
            return result
        else:
            i += 1
            # Each next term is equal to the previous one, multiplied by x and divided by i
            term *= x / i

# print(solution(x, eps))
