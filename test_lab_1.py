import unittest
from lab_1 import task

class TestTask3(unittest.TestCase):

    def test_task_(self):
        self.assertEqual(task(['tar', 'rat', 'arrr', 'rrar', 'tarr']), [['tar', 'rat'], ['arrr', 'rrar'], ['tarr']])

    def test_signs(self):
        with self.assertRaises(TypeError) as ex:
            task([1, 'tar', 'rat'])
        self.assertEqual('Усі елементи мають бути рядками (типу str)', ex.exception.args[0])

if __name__ == '__main__':
    unittest.main()