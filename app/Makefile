build-dist:
	./gradlew clean
	./gradlew installDist
lint:
	./gradlew checkstyleMain
test:
	./gradlew test
build: build-dist lint test

run:
	./build/install/app/bin/app

.PHONY: build