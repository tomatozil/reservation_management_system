import time
from locust import HttpUser, task, between


class MyUser(HttpUser):

    @task
    def post_booking(self):
        self.client.post('/booking/1')

    def on_start(self):
        print("Start locust test")
