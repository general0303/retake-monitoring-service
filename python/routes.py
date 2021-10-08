from init import app, db
from models import Retakes, Students
from flask import request


def create_link():
    student = Students.query.get(request.form['number'])
    retake = Retakes.query.get(request.form['retake_id'])
    return student, retake


@app.route('/give_access', methods=['POST'])
def give_access():
    student, retake = create_link()
    student.retakes.append(retake)
    db.session.commit()
    return 'ok'


@app.route('/sign_up_for_retake', methods=['POST'])
def sign_up_for_retake():
    student, retake = create_link()
    student.recorded_retakes.append(retake)
    db.session.commit()
    return 'ok'


@app.route('/<number>/all_retakes')
def all_records(number):
    student = Students.query.get(number)
    print(student.retakes)
    print(student.recorded_retakes)
    return 'ok'
