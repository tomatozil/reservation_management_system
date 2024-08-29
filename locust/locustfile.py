from locust import HttpUser, task, between
import random
import uuid


class MyUser(HttpUser):
    wait_time = between(1, 3)

    @task
    def post_booking(self):
        self.client.post('/booking/1')
        self.stop()

    def on_start(self):
        print("Start locust test")

        # 고유한 이메일 생성
        unique_id = uuid.uuid4().hex[:6] # 6자리의 고유한 ID 생성
        name = f"u_{unique_id}"
        email = f"u_{unique_id}@example.com"
        password = f"{unique_id}ab1!"

        self.client.post('/user/signup', json={"name": name,
                                               "email": email,
                                               "password": password})
        response = self.client.get('/user/signin', json={"email": email,
                                                         "password": password})

        cookie_value = response.headers.get("Set-Cookie")
        headers = {"Cookie": cookie_value}
        self.client.headers.update(headers)