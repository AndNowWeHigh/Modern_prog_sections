import unittest
from lab2_2 import SingletonExample

class TestSingleton(unittest.TestCase):

    def test_instance(self):
        # Перевірка, що створюється лише один екземпляр класу SingletonExample
        s1 = SingletonExample()
        s2 = SingletonExample()
        self.assertIs(s1, s2)

    def test_property(self):
        # Перевірка збереження стану екземпляра
        s1 = SingletonExample()
        s2 = SingletonExample()
        s1.value = 42
        self.assertEqual(s2.value, 42)

    def test_reset(self):
        # Перевірка, що екземпляр зберігає стан після кількох викликів
        s1 = SingletonExample()
        s1.value = 'hello'
        s2 = SingletonExample()
        self.assertEqual(s2.value, 'hello')
        s2.value = 'world'
        self.assertEqual(s1.value, 'world')


if __name__ == '__main__':
    unittest.main()
