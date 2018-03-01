from django.db import models

# Create your models here.
class User(models.Model):
    username = models.CharField(max_length=128, unique=True)
    password = models.CharField(max_length=128)
    role = models.CharField(max_length=128)

class Device(models.Model):
    device_id = models.CharField(max_length=128, primary_key=True)
    device_version = models.CharField(max_length=128)
    software_version = models.CharField(max_length=128)
    device_state = models.CharField(max_length=32)
    add_time = models.DateTimeField(auto_now_add=True)