# text = input()  # Getting text from console


def solution(text):
    words = text.split()  # Division of text into words

    result = ' '.join(i[::-1] for i in words)  # Get a string of upside-down words

    # Similar code, but slower
    # for i in words:
    #    result += i[::-1] + " "

    return result

# print(solution(text))
