clean:
	mvn -f app/pom.xml clean

build: clean
	mvn -f app/pom.xml package

run: build
	java -jar app/target/*.jar
	
deploy-infra:
	terraform -chdir=./infra init
	terraform -chdir=./infra apply -target=module.arquitecture -auto-approve

deploy-local: build
	terraform -chdir=./infra init
	terraform -chdir=./infra apply -auto-approve

.DEFAULT_GOAL := deploy-local
