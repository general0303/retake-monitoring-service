from init import app, db
from models import Retakes, Students
from flask import request, render_template, redirect, url_for


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
    retake.max_count -= 1
    db.session.commit()
    return redirect(url_for('all_records', number=student.number))


@app.route('/<number>/all_retakes')
def all_records(number):
    return render_template("retakes.html", student=Students.query.get(number))
