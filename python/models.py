from init import db
from flask_login import UserMixin
from init import login

enrolled_students = db.Table('enrolled_students',
                             db.Column('retake_id', db.INTEGER, db.ForeignKey('retakes.id')),
                             db.Column('student_number', db.INTEGER, db.ForeignKey('students.id'))
                             )

student_retakes = db.Table('student_retakes',
                           db.Column('retake_id', db.INTEGER, db.ForeignKey('retakes.id')),
                           db.Column('student_number', db.INTEGER, db.ForeignKey('students.id'))
                           )


class Students(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    number = db.Column(db.String, unique=True)
    first_name = db.Column(db.String)
    second_name = db.Column(db.String)
    password = db.Column(db.String)
    retakes = db.relationship('Retakes', secondary=student_retakes, backref='students')
    recorded_retakes = db.relationship('Retakes', secondary=enrolled_students, backref='enrolled_students')

    def set_password(self, password):
        self.password = password


@login.user_loader
def load_user(student_id):
    return Students.query.get(student_id)


class Scribe(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    number = db.Column(db.String, unique=True)
    password = db.Column(db.String)

    def set_password(self, password):
        self.password = password


class Retakes(db.Model):
    id = db.Column(db.INTEGER, primary_key=True)
    max_count = db.Column(db.INTEGER)
    subject = db.Column(db.String)
    date = db.Column(db.DATETIME)
