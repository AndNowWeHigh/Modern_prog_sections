# Вказуємо базовий образ з Python 3.11.5
FROM python:3.11.5-slim


# Встановлюємо робочу директорію всередині контейнера
WORKDIR .

# Копіюємо файл з вимогами (requirements.txt) у контейнер
COPY requirements.txt .

# Встановлюємо залежності
RUN pip install --no-cache-dir -r requirements.txt

# Копіюємо весь вміст поточної директорії у контейнер
COPY . .

# Вказуємо команду для запуску додатку
CMD ["sh", "-c", "python lab_1.py && python test_lab_1_2.py"]

