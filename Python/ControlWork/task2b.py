# text = input()


def solution(text):
    a = list(text)  # Retrieving a list from a string
    start = 0  # The beginning of a new word
    length = len(a)  # Count the number of words once
    for i in range(length):
        if a[i] == '\n':
            a[i] = ''
        if a[i] == ' ':  # If a whitespace character is found
            # Do not change everything that was before the word and after
            # But change the word
            for j in range((i - start) // 2):
                a[start + j], a[i - j - 1] = a[i - j - 1], a[start + j]
            # The beginning of the next word in the next position
            start = i + 1

    # Need to turn the last word
    for j in range((length - start) // 2):
        a[start + j], a[length - j - 1] = a[length - j - 1], a[start + j]

    # Return the string constructed according to our list
    return ''.join(a)

# print(solution(text))
