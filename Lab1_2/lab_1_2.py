import sys

def count_occurrences(text, word):
    words = text.split()
    count = words.count(word)

    return count

if __name__ == "__main__":
    # input
    text = sys.stdin.readline().strip()
    # search
    search_word = sys.stdin.readline().strip()
    # count
    occurrences = count_occurrences(text, search_word)
    # output
    print(occurrences, file=sys.stdout)
