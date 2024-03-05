import unittest
from lab_1_2 import Is_Prime

class MyTestCase(unittest.TestCase):

    def test_task_(self):
        self.assertEqual(Is_Prime(171).is_prime(), False)

    def test_signs(self):
        with self.assertRaises(TypeError) as ex:
            Is_Prime("171")
        self.assertEqual('Клас приймає лише змінні типу (int)', ex.exception.args[0])


if __name__ == '__main__':
    unittest.main()
