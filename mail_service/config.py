import os


class Config:
    SECRET_KEY = os.environ.get('SECRET_KEY') or 'retake-monitoring-service'
    SQLALCHEMY_DATABASE_URI = 'mysql+pymysql://root:pass@db:3306/petr'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
