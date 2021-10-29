from init import app, db
from models import Letter, Scribe
from flask import request, render_template, redirect, url_for
from flask_login import current_user, login_user, logout_user, login_required


@app.route('/login', methods=['POST'])
def login():
    number = str(request.form['number'])
    password = str(request.form['password'])
    if current_user.is_authenticated:
        return redirect(url_for('all_records', number=current_user.number))
    scribe = Scribe.query.filter_by(number=number).first()
    if scribe is None or scribe.password != password:
        return redirect('http://localhost')
    login_user(scribe)
    return redirect(url_for('write_letter', number=number))


@app.route('/logout')
def logout():
    logout_user()
    return redirect('http://localhost')


@app.route('/login_form')
def login_form():
    return render_template('login_form.html')


@login_required
@app.route('/write_letter')
def write_letter():
    number = str(request.args.get('number'))
    return render_template('letter_form.html', number=number)


@login_required
@app.route('/sent_letter', methods=['POST'])
def sent_letter():
    author_number = str(request.form['author'])
    number = str(request.form['number'])
    text = str(request.form['text'])
    letter = Letter()
    letter.text = text
    author = Scribe.query.filter_by(number=author_number).first()
    recipient = Scribe.query.filter_by(number=number).first()
    letter.author = author
    letter.recipient = recipient
    db.session.commit()
    return redirect(url_for('write_letter'))

