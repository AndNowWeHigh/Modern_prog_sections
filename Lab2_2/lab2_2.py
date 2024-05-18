"""
- обрати будь-який патерн програмування з сайту https://refactoring.guru/uk
- реалізувати його (мова програмування на ваш смак)
- обов'язкове покриття програми юніт-тестами
- додати код у ваш GitHub репозиторій
"""

class SingletonMeta(type):
    # зберігаємо унікальні об'єкти для кожного з наступних класів, які повинні реалізувати цей патерн
    instances = {}


    def __call__(cls, *args, **kwargs):
        # перевіряємо чи є клас з яким ми працюємо в dict метакласу SingletonMeta
        # якщо ні - створюємо новий об'єкт та зберігаємо по ключу
        if cls not in cls.instances:
            cls.instances[cls] = super(SingletonMeta, cls).__call__(*args, **kwargs)
        return cls.instances[cls]


class SingletonExample(metaclass=SingletonMeta):
    def __init__(self):
        self.value = None



if __name__ == "__main__":
    s1 = SingletonExample()
    s2 = SingletonExample()

    s1.value = 12
    print(s2 is s1 and s2.value is s1.value)
    print(s2.value)
