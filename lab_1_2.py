""""""


class Is_Prime:
    def __init__(self, num):
        if not isinstance(num, int):
            raise TypeError('Клас приймає лише змінні типу (int)')

        self.num = num
        self.a = int(self.num ** (1 / 2))

    def task_1_2(self, num, a_1):
        if a_1 == 1 or num == 2:
            return True
        elif num % a_1 == 0 or num == 1:
            return False
        else:
            return self.task_1_2(num, a_1 - 1)

    def is_prime(self):
        return self.task_1_2(self.num, self.a)

