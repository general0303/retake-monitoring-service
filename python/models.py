from init import db

enrolled_students = db.Table('enrolled_students',
                             db.Column('retake_id', db.INTEGER, db.ForeignKey('retakes.id')),
                             db.Column('student_number', db.String, db.ForeignKey('students.number'))
                             )

student_retakes = db.Table('student_retakes',
                           db.Column('retake_id', db.INTEGER, db.ForeignKey('retakes.id')),
                           db.Column('student_number', db.String, db.ForeignKey('students.number'))
                           )


class Students(db.Model):
    number = db.Column(db.String, primary_key=True)
    first_name = db.Column(db.String)
    second_name = db.Column(db.String)
    retakes = db.relationship('Retakes', secondary=student_retakes, backref='students')
    recorded_retakes = db.relationship('Retakes', secondary=enrolled_students, backref='enrolled_students')


class Retakes(db.Model):
    id = db.Column(db.INTEGER, primary_key=True)
    max_count = db.Column(db.INTEGER)
    subject = db.Column(db.String)
    date = db.Column(db.DATETIME)
