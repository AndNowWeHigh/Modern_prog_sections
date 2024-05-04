import unittest
from lab_1_2 import count_occurrences

class MyTestCase(unittest.TestCase):
    def test_task(self):
        self.assertEqual(count_occurrences("world hello world", "world"), 2)

    def test_empty_word(self):
        self.assertEqual(count_occurrences("", "hello"), 0)

    def test_word_empty(self):
        self.assertEqual(count_occurrences("hello world hello", ""), 0)

    def test_invalid_word(self):
        self.assertEqual(count_occurrences('171 1 71 17 1', '1'), 2)

if __name__ == '__main__':
    unittest.main()
