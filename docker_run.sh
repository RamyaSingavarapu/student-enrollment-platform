docker run --add-host=host.docker.internal:host-gateway \
  -p 9080:8080 \
  -e SPRING_DATA_MONGODB_URI="mongodb://host.docker.internal:27017/yourdb" \
  ramya-student-enrollment-platform