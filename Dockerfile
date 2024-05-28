FROM python:3.12

WORKDIR . /app

ADD app/. .
ADD test/. .

CMD ["sh", "-c", "python ./lab_1.py && python ./lab_1_2.py && python ./test_lab_1.py && python ./test_lab_1_2.py"]

