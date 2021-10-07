from init import app, db
from models import Retakes, Students


@app.route('/give_access/<number>/<id>')
def give_access(number, id):
    student = Students.query.get(number)
    retake = Retakes.query.get(id)
    student.retakes.append(retake)
    db.session.commit()
    return 'ok'


@app.route('/sign_up_for_retake/<number>/<id>')
def sign_up_for_retake(number, id):
    student = Students.query.get(number)
    retake = Retakes.query.get(id)
    student.recorded_retakes.append(retake)
    db.session.commit()
    return 'ok'


@app.route('/<number>/all_retakes')
def all_records(number):
    student = Students.query.get(number)
    print(student.retakes)
    print(student.recorded_retakes)
    return 'ok'

