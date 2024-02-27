#!/bin/bash
#
# Run SonarQube analysis in a local Docker container before pushing code

# Запуск контейнера SonarQube
#docker run -d --name sonarqube-1 -p 9000:9000 -p 9092:9092 sonarqube
docker-compose up -d

# Ожидание запуска SonarQube (может потребоваться некоторое время)
sleep 30

# Запуск анализа кода в контейнере SonarQube
mvn clean verify -DskipTests sonar:sonar -Dsonar.token=squ_83c4ebb9ca96f59b844acf9a5271a41224f61dc0

# Проверка статуса анализа
if [ $? -ne 0 ]; then
    echo "Error: SonarQube analysis failed, push aborted"
    # Остановка и удаление контейнера SonarQube
#    docker stop sonarqube
#    docker rm sonarqube
    exit 1
fi

# Остановка и удаление контейнера SonarQube
docker stop sonarqube
docker rm sonarqube

# Если анализ завершился успешно, разрешить push
exit 0
