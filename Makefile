include .env

DATABASE_NAME ?= mysql-db
MYSQL_DATABASE ?= restaurant-db
MYSQL_ROOT_PASSWORD ?= secretPwd123!
MYSQL_USER ?= restaurantUser
MYSQL_PASSWORD ?= restaurantPwd
IMAGE_TAG = 8.0

run-mysql:
	docker run --rm --name ${DATABASE_NAME} -p 3306:3306 \
		-e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} \
		-e MYSQL_DATABASE=${MYSQL_DATABASE} \
		-e MYSQL_USER=${MYSQL_USER} \
		-e MYSQL_PASSWORD=${MYSQL_PASSWORD} \
		-d mysql:${IMAGE_TAG}
