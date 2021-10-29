FROM python:3.8-alpine

RUN apk update && apk add bash

COPY requirements.txt requirements.txt
RUN python -m venv venv
RUN /bin/bash -c "source venv/bin/activate venv"
RUN pip install -r requirements.txt

COPY app.py ./
COPY config.py ./
COPY init.py ./
COPY models.py ./
COPY routes.py ./
COPY templates ./templates
COPY static ./static

ENV FLASK_APP app.py

EXPOSE 5000
ENTRYPOINT ["python", "app.py"]