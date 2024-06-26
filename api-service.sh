#!/bin/bash

# Verificar se Docker está instalado
if ! which docker &> /dev/null
then
    echo "-----------------------------------------------------"
    echo "|               Instalando Docker...                |"
    echo "-----------------------------------------------------"

    # Atualizar pacotes existentes
    sudo apt-get update
    
    clear
    echo "-----------------------------------------------------"
    echo "|               Instalando Docker...                |"
    echo "-----------------------------------------------------"
    # Remover versões antigas do Docker
    sudo apt-get remove -y docker docker-engine docker.io containerd runc

    clear
    echo "-----------------------------------------------------"
    echo "|               Instalando Docker...                |"
    echo "-----------------------------------------------------"
    # Instalar pacotes necessários para usar o repositório APT sobre HTTPS
    sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

    # Adicionar a chave GPG oficial do Docker
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

    # Adicionar o repositório do Docker às fontes do APT
    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

    clear
    echo "-----------------------------------------------------"
    echo "|               Instalando Docker...                |"
    echo "-----------------------------------------------------"
    # Atualizar pacotes do APT novamente para incluir pacotes do Docker
    sudo apt-get update

    clear
    echo "-----------------------------------------------------"
    echo "|               Instalando Docker...                |"
    echo "-----------------------------------------------------"
    # Instalar Docker CE
    sudo apt-get install -y docker-ce

    # Adicionar o usuário atual ao grupo Docker
    sudo usermod -aG docker $USER
    clear
    echo "Docker foi instalado com sucesso."
else
    echo "Docker já está instalado."
fi

# Verificar se Docker Compose está instalado
if ! which docker-compose &> /dev/null
then
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

# Função para verificar se a entrada está correta
check_input() {
    local input=$1
    while [[ -z "$input" || ! "$input" =~ ^(dev|test)$ ]]; do
        echo "Entrada inválida. Por favor, digite 'dev' ou 'test'."
        read -p "Digite o ambiente que deseja entrar (ex: dev, test): " input
    done
    echo $input
}

# Perguntar ao usuário qual ambiente deseja entrar
read -p "Digite o ambiente que deseja entrar (ex: dev, test): " ambiente
ambiente=$(check_input "$ambiente")

# Subir o container MySQL
if [[ "$ambiente" == "dev" ]]; then
    sudo docker run -d --name mysql-service --restart always --network host -p 3306:3306 -e MYSQL_ROOT_PASSWORD=urubu100 -e MYSQL_USER=aluno1 -e MYSQL_PASSWORD=123 jonathancarvalho039/mysql-servico:5.7
    echo "Esperando o MySQL iniciar..."
    sleep 5
fi

# Subir o container Java com o ambiente especificado e expor a porta 8080
sudo docker run -d --name api-server --restart always -p 8080:8080 -e SPRING_PROFILES_ACTIVE=$ambiente jonathancarvalho039/api-server:17 java -jar /api-server.jar

echo "Ambiente de $ambiente selecionado."
echo "Esperando Aplicação iniciar..."
sleep 10

echo "Aplicação iniciada com sucesso! host de acesso:"
echo ""
echo ""
echo "Host principal :  $(curl -s ifconfig.me):8080/api/v1/"
echo ""
echo "Login :  $(curl -s ifconfig.me):8080/api/v1/login"
echo ""
echo "Doc Swagger:  $(curl -s ifconfig.me):8080/swagger-ui/index.html"



