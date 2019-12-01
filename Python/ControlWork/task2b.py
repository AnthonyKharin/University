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
            a = a[:start] + a[start:i][::-1] + a[i:]
            # The beginning of the next word in the next position
            start = i + 1

    # Need to turn the last word
    a = a[:start] + a[start:len(a) + 1][::-1] + a[len(a) + 1:]

    # Return the string constructed according to our list
    return ''.join(a)

# print(solution(text))
