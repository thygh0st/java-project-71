build-dist:
	./gradlew clean
	./gradlew installDist
lint:
	./gradlew checkstyleMain
build: build-dist lint
test:
	./gradlew test

run:
	./build/install/app/bin/app

.PHONY: build