FROM python:3.12

WORKDIR . /app

ADD app/. .
ADD test/. .

CMD ["sh", "-c", "python app/lab_1.py && python app/lab_1_2.py && python test/test_lab_1.py && python test/test_lab_1_2.py"]

