FROM python:3.12

WORKDIR . /app

ADD app/lab_1.py .
ADD app/lab_1_2.py .

CMD ["sh", "-c", "python ./lab_1.py && python ./lab_1_2.py"]

