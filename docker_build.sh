#!/bin/zsh

# Build the docker image
docker build --build-arg RECEIPT_USER=$RECEIPT_USER --build-arg RECEIPT_PASSWORD=$RECEIPT_PASSWORD -t ghcr.io/preinpost/recepit .

# Log in to the GitHub Container Registry
echo $CR_PAT | docker login ghcr.io -u preinpost --password-stdin
#
## Push the docker image to the GitHub Container Registry
docker push ghcr.io/preinpost/recepit