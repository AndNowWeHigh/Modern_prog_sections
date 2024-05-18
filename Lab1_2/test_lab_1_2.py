import unittest
from io import StringIO
from unittest.mock import patch
import sys
from lab_1_2 import count_occurrences

class MyTestCase(unittest.TestCase):

    @patch('sys.stderr', new_callable=StringIO)
    def test_empty_text(self, mock_stderr):
        with patch('sys.stdin', side_effect=["\n", "hello\n"]):
            with self.assertRaises(SystemExit) as cm:
                count_occurrences("", "hello")
        self.assertEqual(cm.exception.code, 1)
        self.assertEqual(mock_stderr.getvalue().strip(), "Нема тексту")

    @patch('sys.stderr', new_callable=StringIO)
    def test_empty_search_word(self, mock_stderr):
        with patch('sys.stdin', side_effect=["hello\n", "\n"]):
            with self.assertRaises(SystemExit) as cm:
                count_occurrences("hello world", "")
        self.assertEqual(cm.exception.code, 1)
        self.assertEqual(mock_stderr.getvalue().strip(), "Нема слова, яке потрібно найти")

    @patch('sys.stderr', new_callable=StringIO)
    def test_error_stream(self, mock_stderr):
        with patch('sys.stdin', side_effect=["\n", "hello\n"]):
            with self.assertRaises(SystemExit):
                count_occurrences("", "hello")
        self.assertNotEqual(mock_stderr.getvalue().strip(), "")
        self.assertEqual(mock_stderr.getvalue().strip(), "Нема тексту")

    @patch('sys.stdout', new_callable=StringIO)
    def test_correct_output(self, mock_stdout):
        result = count_occurrences("hello world hello", "hello")
        print(result, file=sys.stdout)
        self.assertEqual(mock_stdout.getvalue().strip(), "Кількість:2")


if __name__ == '__main__':
    unittest.main()
