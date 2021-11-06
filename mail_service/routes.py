from init import app, db
from models import Letter, Scribe
from flask import request, render_template, redirect, url_for, jsonify
from flask_login import current_user, login_user, logout_user, login_required


@app.route('/login', methods=['POST'])
def login():
    number = str(request.form['login'])
    password = str(request.form['password'])
    scribe = Scribe.query.filter_by(number=number).first()
    login_user(scribe)
    return redirect(url_for('index'))


@app.route('/logout')
def logout():
    logout_user()
    return redirect('http://localhost')


@app.route('/login_form')
def login_form():
    return render_template('login_form.html')


@login_required
@app.route('/')
def index():
    return render_template('index.html', scribe=Scribe.query.filter_by(number=current_user.number).first())


@login_required
@app.route('/sent_emails')
def sent_letters():
    return render_template('sent_letters.html', scribe=Scribe.query.filter_by(number=current_user.number).first())


@login_required
@app.route('/write_letter')
def write_letter():
    return render_template('letter_form.html')


@login_required
@app.route('/sent_letter', methods=['POST'])
def sent_letter():
    number = str(request.form['number'])
    text = str(request.form['text'])
    letter = Letter()
    letter.text = text
    recipient = Scribe.query.filter_by(number=number).first()
    letter.author = current_user
    letter.recipient = recipient
    db.session.commit()
    return redirect(url_for('sent_letters'))


@app.route('/letter/<letter_id>')
def letter(letter_id):
    return render_template('letter.html', letter=Letter.query.get(letter_id))

