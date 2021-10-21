from init import app, db
from models import Retakes, Students
from flask import request, render_template, redirect, url_for
from flask_login import current_user, login_user, logout_user


def create_link():
    student = Students.query.filter_by(number=str(request.form['number'])).first()
    retake = Retakes.query.get(request.form['retake_id'])
    return student, retake


@app.route('/login', methods=['POST'])
def login():
    number = str(request.form['number'])
    password = str(request.form['password'])
    print(number)
    if current_user.is_authenticated:
        return redirect(url_for('all_records', number=current_user.number))
    student = Students.query.filter_by(number=number).first()
    if student is None or student.password != password:
        return redirect('http://localhost/get_student_number')
    login_user(student)
    return redirect(url_for('all_records', number=number))


@app.route('/logout')
def logout():
    logout_user()
    return redirect('http://localhost')


@app.route('/change_password')
def change_password():
    number = str(request.args.get('number'))
    return render_template('change_password.html', number=number)


@app.route('/set_password', methods=['POST'])
def set_password():
    password = str(request.form['password'])
    number = str(request.form['number'])
    student = Students.query.filter_by(number=number).first()
    student.set_password(password)
    db.session.commit()
    return redirect(url_for('all_records', number=student.number))


@app.route('/give_access', methods=['POST'])
def give_access():
    student, retake = create_link()
    student.retakes.append(retake)
    db.session.commit()
    return redirect("http://localhost:8081")


@app.route('/sign_up_for_retake', methods=['POST'])
def sign_up_for_retake():
    student, retake = create_link()
    student.recorded_retakes.append(retake)
    retake.max_count -= 1
    db.session.commit()
    return redirect(url_for('all_records', number=student.number))


@app.route('/all_retakes', methods=['GET'])
def all_records():
    number = str(request.args.get('number'))
    return render_template("retakes.html", student=Students.query.filter_by(number=number).first())
