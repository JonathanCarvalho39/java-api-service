#!/bin/bash

# Verificar se Docker está instalado
if ! which docker &> /dev/null
then
    echo "-----------------------------------------------------"
    echo "|               Instalando Docker...                |"
    echo "-----------------------------------------------------"

    # Atualizar pacotes existentes
    sudo apt-get update

    # Remover versões antigas do Docker
    sudo apt-get remove -y docker docker-engine docker.io containerd runc

    # Instalar pacotes necessários para usar o repositório APT sobre HTTPS
    sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

    # Adicionar a chave GPG oficial do Docker
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

    # Adicionar o repositório do Docker às fontes do APT
    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

    # Atualizar pacotes do APT novamente para incluir pacotes do Docker
    sudo apt-get update

    # Instalar Docker CE
    sudo apt-get install -y docker-ce

    # Adicionar o usuário atual ao grupo Docker
    sudo usermod -aG docker $USER

    echo "Docker foi instalado com sucesso."

    newgrp docker

    sudo systemctl restart docker

else
    echo "Docker já está instalado."
fi

# Verificar se Docker Compose está instalado
if ! which docker-compose &> /dev/null
then
    echo "-----------------------------------------------------"
    echo "|           Instalando Docker Compose...            |"
    echo "-----------------------------------------------------"

    # Instalar Docker Compose
    sudo curl -L "https://github.com/docker/compose/releases/download/$(curl -s https://api.github.com/repos/docker/compose/releases/latest | grep -Po '"tag_name": "\K.*?(?=")')/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

    # Aplicar permissões corretas ao binário do Docker Compose
    sudo chmod +x /usr/local/bin/docker-compose

    # Criar um link simbólico para Docker Compose em /usr/bin se não existir
    if [ ! -f /usr/bin/docker-compose ]; then
        sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
    fi
    
    echo "Docker Compose foi instalado com sucesso."
else
    echo "Docker Compose já está instalado."
fi

# Verificar instalação
echo "Docker version:"
docker --version

echo "Docker Compose version:"
docker-compose --version

# Criar o arquivo docker-compose.yml
cat <<EOF > docker-compose.yml
version: '3.8'

services:
  mysql-service:
    container_name: mysql-service
    image: jonathancarvalho039/mysql-servico:5.7
    restart: always
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: urubu100
      MYSQL_USER: aluno1
      MYSQL_PASSWORD: 123
    network_mode: host

  api-server:
    container_name: api-server
    image: jonathancarvalho039/api-server:17
    restart: always
    depends_on:
      - mysql-service
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: dev
    command: java -jar /api-server.jar
EOF

docker-compose up
echo "Aplicação de Pé"

# Esperar alguns segundos para garantir que os serviços estejam totalmente iniciados
sleep 10

# Remover o arquivo docker-compose.yml
rm docker-compose.yml
