curl -v -s -H "$ct" --data @msg.json "http://localhost:8080/pf_gradle/rest/put_msg/"
curl -v "http://localhost:8080/pf_gradle/rest/get_msg/1/?sleep=1"

