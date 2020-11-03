VERSION?=1.0
TAG?=auth-service:$(VERSION)

run-dev:
	./gradlew bootRun

build-artifact:
	./gradlew clean build

build-image: build-artifact
	docker build -t $(TAG) .

run: build-image
	docker run -d --name auth-service -p 8080:8080 --rm $(TAG)

stop:
	docker stop auth-service
