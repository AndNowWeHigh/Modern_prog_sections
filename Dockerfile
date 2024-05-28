FROM python:3.12

WORKDIR . 

ADD test/. .

CMD ["sh", "-c", "python test_lab_1.py && python test_lab_1_2.py"]

