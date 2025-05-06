build-dist:
	./gradlew clean
	./gradlew installDist
lint:
	./gradlew check
build: build-dist lint

run:
	./build/install/app/bin/app

.PHONY: build