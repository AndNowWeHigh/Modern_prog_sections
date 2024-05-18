import sys

def count_occurrences(text, word):
    if not text:
        print("Нема тексту", file=sys.stderr)
        sys.exit(1)

    if not word:
        print("Нема слова, яке потрібно найти", file=sys.stderr)
        sys.exit(1)
    words = text.split()
    count = words.count(word)
    return f"Кількість:{count}"

if __name__ == "__main__":
    text = sys.stdin.readline().strip()
    search_word = sys.stdin.readline().strip()

    print(count_occurrences(text, search_word), file=sys.stdout)
