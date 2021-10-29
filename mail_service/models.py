from init import db
from flask_login import UserMixin
from init import login


class Letter(db.Model):
    id = db.Column(db.INTEGER, primary_key=True)
    text = db.Column(db.String)
    author_id = db.Column(db.Integer, db.ForeignKey('scribe.id'))
    recipient_id = db.Column(db.Integer, db.ForeignKey('scribe.id'))


class Scribe(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    number = db.Column(db.String, unique=True)
    password = db.Column(db.String)
    sent_letters = db.relationship('Letter', backref='author', lazy='dynamic', foreign_keys=[Letter.author_id])
    received_letters = db.relationship('Letter', backref='recipient', lazy='dynamic', foreign_keys=[Letter.recipient_id])

    def set_password(self, password):
        self.password = password


@login.user_loader
def load_user(number):
    return Scribe.query.filter_by(number=number).first()
